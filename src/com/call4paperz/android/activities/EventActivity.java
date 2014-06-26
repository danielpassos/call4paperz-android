package com.call4paperz.android.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.call4paperz.android.Constants;
import com.call4paperz.android.R;
import com.call4paperz.android.model.Event;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.event_detail)
@OptionsMenu(R.menu.menu_event)
public class EventActivity extends ActionBarActivity {

    private Event event;

    @ViewById
    TextView name, description, date, url, twitter, organizer;

    @ViewById
    ImageView logo;

    @AfterViews
    void displayEventDetail() {
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        this.event = (Event) getIntent().getExtras().getSerializable(Constants.EVENT);

        String imageURL = event.getPicture().getCropped().getUrl();
        Picasso.with(this).load(imageURL).placeholder(R.drawable.no_image).into(logo);

        name.setText(event.getName());
        description.setText(event.getDescription());
        date.setText(event.getStringDate());
        url.setText(event.getUrl());
        twitter.setText(event.getTwitter());
        organizer.setText(event.getOrganizer().getName());
    }

    @OptionsItem(android.R.id.home)
    void back() {
        finish();
    }

    @OptionsItem(R.id.proposal)
    void proposalSelected() {
        Intent intent = new Intent(getApplicationContext(), ProposalsActivity_.class);
        intent.putExtra(Constants.EVENT, event);
        startActivity(intent);
    }

}
