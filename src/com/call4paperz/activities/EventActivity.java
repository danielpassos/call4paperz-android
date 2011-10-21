package com.call4paperz.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.call4paperz.R;
import com.call4paperz.model.Event;
import com.call4paperz.util.BitmapGenerator;

public class EventActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event);

        Event event = (Event) getIntent().getExtras().getSerializable("event");

        ImageView logo = (ImageView) findViewById(R.event.logo);
        AsyncTask<String, ProgressDialog, Bitmap> imageLoad = new BitmapGenerator(logo);
        imageLoad.execute(event.getImageUrl());

        TextView name = (TextView) findViewById(R.event.name);
        name.setText(event.getName());

        TextView description = (TextView) findViewById(R.event.description);
        description.setText(event.getDescription());

        TextView date = (TextView) findViewById(R.event.date);
        date.setText(event.getStringDate());

        TextView url = (TextView) findViewById(R.event.url);
        url.setText(event.getWebsite());

        TextView twitter = (TextView) findViewById(R.event.twitter);
        twitter.setText(event.getTwitter());
    }
}
