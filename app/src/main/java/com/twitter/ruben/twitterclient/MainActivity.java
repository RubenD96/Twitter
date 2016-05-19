package com.twitter.ruben.twitterclient;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private TweetAdapter adapter;
    private ArrayList<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listView);

        try {
            JSONObject jObj = new JSONObject(readAssetIntoString("tweets.json"));
            JSONArray statuses = jObj.getJSONArray("statuses");
            if (TweetCollection.getInstance().getTweets().isEmpty()) {
                for (int i = 0; i < statuses.length(); i++) {

                    JSONObject status = statuses.getJSONObject(i);
                    JSONObject userObj = status.getJSONObject("user");
                    String userStr = userObj.getString("name");
                    String tweet = status.getString("text");

                    JSONObject entities = status.getJSONObject("entities");
                    JSONArray hashtags = entities.getJSONArray("hashtags");
                    JSONArray symbols = entities.getJSONArray("symbols");
                    JSONArray user_mentions = entities.getJSONArray("user_mentions");
                    JSONArray urls = entities.getJSONArray("urls");
                    JSONArray media = entities.getJSONArray("media");

                    String text_final = "";

                    TweetCollection.getInstance().addTweet(userStr, tweet);
                }
            }
        } catch (JSONException jsone) {
            jsone.printStackTrace(System.err);
        } catch (IOException ioe) {
            ioe.printStackTrace(System.err);
        }

        adapter = new TweetAdapter(this);
        lv.setAdapter(adapter);
    }

    /**
     * Reads an asset file and returns a string with the full contents.
     *
     * @param filename  The filename of the file to read.
     * @return          The contents of the file.
     * @throws IOException  If file could not be found or not read.
     */
    private String readAssetIntoString(String filename) throws IOException {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            InputStream is = getAssets().open(filename, AssetManager.ACCESS_BUFFER);
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
