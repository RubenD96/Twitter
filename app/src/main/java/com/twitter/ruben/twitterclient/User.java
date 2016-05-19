package com.twitter.ruben.twitterclient;

import org.json.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ruben on 5/9/2016.
 */
public class User {

    private final String name;
    private final int friends;
    private final String description;
    private final int followers_count;
    private List<String> locations = new ArrayList<>();

    public User(JSONObject jsonObject) throws JSONException {
        name = jsonObject.getString("name");
        friends = jsonObject.getInt("friends_count");
        description = jsonObject.getString("description");
        followers_count = jsonObject.getInt("followers");

        String locString = jsonObject.getString("locations");
        if (locString.contains(",")) {
            locations = Arrays.asList(locString.split(", "));
        } else {
            locations.add(locString);
        }
    }

    public String getName() {
        return name;
    }

    public int getFriends() {
        return friends;
    }

    public String getDescription() {
        return description;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public List<String> getLocations() {
        return locations;
    }

}
