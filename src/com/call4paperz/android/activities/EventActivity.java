package com.call4paperz.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.call4paperz.android.R;
import com.call4paperz.android.model.Event;
import com.squareup.picasso.Picasso;

public class EventActivity extends SherlockActivity {

    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_detail);

        event = (Event) getIntent().getExtras().getSerializable("event");

        ImageView logo = (ImageView) findViewById(R.event.logo);
        String imageURL = event.getPicture().getCropped().getUrl();
        Picasso.with(this).load(imageURL).placeholder(R.drawable.no_image).into(logo);

        TextView name = (TextView) findViewById(R.event.name);
        name.setText(event.getName());

        TextView description = (TextView) findViewById(R.event.description);
        description.setText(event.getDescription());

        TextView date = (TextView) findViewById(R.event.date);
        date.setText(event.getStringDate());

        TextView url = (TextView) findViewById(R.event.url);
        url.setText(event.getUrl());

        TextView twitter = (TextView) findViewById(R.event.twitter);
        twitter.setText(event.getTwitter());

        TextView organizer = (TextView) findViewById(R.event.organizer);
        organizer.setText(event.getOrganizer().getName());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
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
