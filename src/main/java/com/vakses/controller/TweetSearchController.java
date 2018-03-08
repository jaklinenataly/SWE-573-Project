package com.vakses.controller;

import com.vakses.service.TweetSearchService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by veraxmedax on 26/02/2018.
 */
@RestController
public class TweetSearchController {
    private TweetSearchService tweetSearchService;

    @Inject
    public TweetSearchController(TweetSearchService tweetSearchService) {
        this.tweetSearchService = tweetSearchService;
    }

    @RequestMapping(value = "/tweets", method = RequestMethod.GET)
    public void searchTweets() {
        tweetSearchService.findTweets();

    }
}
