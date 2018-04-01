package com.vakses.service;

import com.vakses.model.entity.DonationEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by veraxmedax on 08/03/2018.
 */
@Slf4j
@Service
public class TweetSearchService {
    private static final long TEN_MINUTES_IN_MS = 10 * 60 * 1000;

    private Twitter twitter;
    private DonationStoreService tweetParserService;
    private DonationRetweetService donationRetweetService;
    private NotificationService notificationService;

    @Autowired
    public TweetSearchService(Twitter twitter, DonationStoreService tweetParserService, DonationRetweetService donationRetweetService, NotificationService notificationService) {
        this.twitter = twitter;
        this.tweetParserService = tweetParserService;
        this.donationRetweetService = donationRetweetService;
        this.notificationService = notificationService;
    }

    @Scheduled(fixedRate = TEN_MINUTES_IN_MS)
    public void findTweets() {
        log.info("Tweet search job started..");
        final Set<Tweet> tweetSet = new HashSet<>();
        final Set<Tweet> storedSet = new HashSet<>();
        final Set<DonationEntity> donationSet = new HashSet<>();
        final Set<String> tweetTextSet = new HashSet<>();

        twitter.searchOperations().search(generateSearchParameters()).getTweets()
                .stream()
                .forEach(tweet -> {
                    if (!tweetTextSet.contains(tweet.getText())) {
                        if (tweet.getRetweetedStatus() == null) {
                            tweetSet.add(tweet);
                            tweetTextSet.add(tweet.getText());
                        } else {
                            tweetSet.add(tweet.getRetweetedStatus());
                            tweetTextSet.add(tweet.getText());
                        }
                    }
                });

        for (Tweet tweet : tweetSet) {
            DonationEntity storedEntity = tweetParserService.parseAndStore(tweet);
            if (!Objects.isNull(storedEntity)) {
                storedSet.add(tweet);
                donationSet.add(storedEntity);
            }
        }

        shareDonationRequest(storedSet, donationSet);
    }

    private void shareDonationRequest(Set<Tweet> storedSet, Set<DonationEntity> donationSet) {
        if (storedSet.size() > 0) {
            log.info("Total of " + storedSet.size() + " new record found, retweeting..");
            donationRetweetService.retweet(storedSet);
            donationSet.forEach(donationEntity -> notificationService.notifySubscribers(donationEntity));
            return;
        }
        log.info("There is no new donation request!");
    }

    private SearchParameters generateSearchParameters() {
        Set<String> bloodGroups = new HashSet<>();
        bloodGroups.add("A Rh +");
        bloodGroups.add("A Rh -");
        bloodGroups.add("B Rh +");
        bloodGroups.add("B Rh -");
        bloodGroups.add("AB Rh +");
        bloodGroups.add("AB Rh -");
        bloodGroups.add("0 Rh +");
        bloodGroups.add("0 Rh -");

        StringBuilder query = new StringBuilder();
        for (String group : bloodGroups) {
            query.append(group);
            if (bloodGroups.iterator().hasNext()) {
                query.append(" OR ");
            }
        }
        query.append(" exclude:replies AND exclude:retweets ");

        return new SearchParameters(query.toString()).
                locale("tr").
                lang("tr");
    }
}
