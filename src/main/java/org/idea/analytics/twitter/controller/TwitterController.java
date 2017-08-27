package org.idea.analytics.twitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping(TwitterController.TWITTER)
public class TwitterController {
    public static final String TWEETS = "/tweets";
    public static final String TWITTER = "/twitter";
    public static final String PROFILES = "/profiles";
    public static final String HANDLE = "/{twitterHandle}";
    public static final String SEARCH = "/search";

    @Autowired
    private Twitter twitter;

    public Twitter getTwitter() {
        return twitter;
    }

    @RequestMapping(value = TWEETS+HANDLE, method = RequestMethod.GET)
    public List<Tweet> tweets(@PathVariable String twitterHandle) {
        List<Tweet> tweets = getTwitter().timelineOperations().getUserTimeline(twitterHandle);
        return tweets;
    }

    @RequestMapping(value = PROFILES + HANDLE, method = RequestMethod.GET)
    public TwitterProfile profile(@PathVariable String twitterHandle) {
        TwitterProfile profile = getTwitter().userOperations().getUserProfile(twitterHandle);
        return profile;
    }

    @RequestMapping(value = SEARCH, method = RequestMethod.GET)
    public SearchResults search(WebRequest request) {
        String query = request.getParameter("q");
        if ((query == null) || (query.isEmpty())) return null;
        SearchResults results = getTwitter().searchOperations().search(query);
        return results;
    }
}
