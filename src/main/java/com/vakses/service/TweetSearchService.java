package com.vakses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by veraxmedax on 08/03/2018.
 */
@Service
public class TweetSearchService {
    private Twitter twitter;
    private DonationStoreService tweetParserService;
    private DonationRetweetService donationRetweetService;

    @Autowired
    public TweetSearchService(Twitter twitter, DonationStoreService tweetParserService, DonationRetweetService donationRetweetService) {
        this.twitter = twitter;
        this.tweetParserService = tweetParserService;
        this.donationRetweetService = donationRetweetService;
    }

    public void findTweets() {
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

        donationRetweetService.retweet(tweetSet);
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
