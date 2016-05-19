package com.twitter.ruben.twitterclient;

/**
 * Created by Ruben on 5/11/2016.
 */
public class Tweet {

    private final String user;
    private final String tweet;

    public Tweet(String user, String tweet) {
        this.tweet = tweet;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public String getTweet() {
        return tweet;
    }
}
