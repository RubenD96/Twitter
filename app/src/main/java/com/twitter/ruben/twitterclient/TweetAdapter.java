package com.twitter.ruben.twitterclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Ruben on 5/11/2016.
 */
public class TweetAdapter extends ArrayAdapter<Tweet> {

    public TweetAdapter(Context context) {
        super(context, 0, TweetCollection.getInstance().getTweets());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_main, parent, false);
        }

        TextView tv1 = (TextView) convertView.findViewById(R.id.textView);
        TextView tv2 = (TextView) convertView.findViewById(R.id.textView2);

        Tweet text = getItem(position);
        tv1.setText(text.getUser());
        tv2.setText(text.getTweet());

        return convertView;
    }
}
