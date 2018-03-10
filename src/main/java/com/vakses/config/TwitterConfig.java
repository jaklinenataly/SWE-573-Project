package com.vakses.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by veraxmedax on 10/03/2018.
 */
@Component
public class TwitterConfig {
    @Value("${spring.social.twitter.app-id}")
    private String appId;
    @Value("${spring.social.twitter.app-secret}")
    private String appSecret;
    @Value("${twitter.oauth.accessToken}")
    private String accessToken;
    @Value("${twitter.oauth.accessToken.secret}")
    private String accessTokenSecret;

    @Bean
    public TwitterTemplate twitterTemplate() {
        return new TwitterTemplate(appId, appSecret, accessToken, accessTokenSecret);
    }
}
