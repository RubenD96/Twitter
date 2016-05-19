package com.twitter.ruben.twitterclient;

import java.util.ArrayList;

/**
 * Created by Ruben on 5/11/2016.
 */
public class TweetCollection {

    ArrayList<Tweet> tweets = new ArrayList<>();
    private static TweetCollection instance;

    private TweetCollection() {

    }

    public static TweetCollection getInstance() {
        if (instance == null) {
            instance = new TweetCollection();
        }
        return instance;
    }

    public ArrayList<Tweet> getTweets() {
        return tweets;
    }

    public void addTweet(String user, String tweet) {
        tweets.add(new Tweet(user, tweet));
    }
}
