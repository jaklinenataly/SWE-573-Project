package com.vakses.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.OperationNotPermittedException;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by veraxmedax on 10/03/2018.
 */
@Slf4j
@Service
public class DonationRetweetService {

    private Twitter twitter;

    @Autowired
    public DonationRetweetService(Twitter twitter) {
        this.twitter = twitter;
    }

    public void retweet(Set<Tweet> tweetSet) {
        tweetSet.forEach(tweet -> {
            try {
                twitter.timelineOperations().retweet(tweet.getId());
                log.info("Tweet with id: {} retweeted successfully.", tweet.getId());
            } catch (OperationNotPermittedException ex) {
                log.info("Tweet with id: {} already retweeted", tweet.getId());
            }
        });
    }
}
