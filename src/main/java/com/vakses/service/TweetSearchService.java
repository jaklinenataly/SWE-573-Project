package com.vakses.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.annotation.Secured;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by veraxmedax on 08/03/2018.
 */
@Slf4j
@Service
public class TweetSearchService {
    private Twitter twitter;
    private DonationStoreService tweetParserService;
    private DonationRetweetService donationRetweetService;
    private static final long TEN_MINUTES_IN_MS = 10 * 60 * 1000;

    @Autowired
    public TweetSearchService(Twitter twitter, DonationStoreService tweetParserService, DonationRetweetService donationRetweetService) {
        this.twitter = twitter;
        this.tweetParserService = tweetParserService;
        this.donationRetweetService = donationRetweetService;
    }

    @Scheduled(fixedRate = TEN_MINUTES_IN_MS)
    @Secured({"ROLE_ADMIN"})
    public void findTweets() {
        log.info("Tweet search job started..");
        final Set<Tweet> tweetSet = new HashSet<>();
        final Set<Tweet> storedSet = new HashSet<>();

        twitter.searchOperations().search(generateSearchParameters()).getTweets()
                .stream()
                .forEach(tweet -> {
                    if (tweet.getRetweetedStatus() == null) {
                        tweetSet.add(tweet);
                    } else {
                        tweetSet.add(tweet.getRetweetedStatus());
                    }
                });

        for (Tweet tweet : tweetSet) {
            boolean isStored = tweetParserService.parseAndStore(tweet);
            if (isStored) {
                storedSet.add(tweet);
            }
        }

        if (storedSet.size() > 0) {
            log.info("Total of " + storedSet.size() + " new record found, retweeting..");
            donationRetweetService.retweet(storedSet);
        }
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
