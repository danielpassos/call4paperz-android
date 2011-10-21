package com.call4paperz.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.call4paperz.R;
import com.call4paperz.model.Event;
import com.call4paperz.util.BitmapGenerator;

public class EventActivity extends Activity {

    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event);

        event = (Event) getIntent().getExtras().getSerializable("event");

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

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(EventActivity.this, ProposalsActivity.class);
        intent.putExtra("event", event);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }

}
