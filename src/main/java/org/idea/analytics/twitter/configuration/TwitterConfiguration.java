package org.idea.analytics.twitter.configuration;

import org.idea.analytics.configuration.ApplicationConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@Configuration
@Import(ApplicationConfiguration.class)
public class TwitterConfiguration {
    @Value("${spring.social.twitter.appId}")
    private String consumerKey;

    @Value("${spring.social.twitter.appSecret}")
    private String consumerSecret;

    @Value("${twitter.accessToken}")
    private String accessToken;

    @Value("${twitter.accessTokenSecret}")
    private String accessTokenSecret;

    @Bean
    public Twitter twitter() {
        return new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
    }
}
