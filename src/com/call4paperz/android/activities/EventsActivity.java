package com.call4paperz.android.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.call4paperz.android.Call4PaperzApplication;
import com.call4paperz.android.R;
import com.call4paperz.android.fragments.EventsFragments;
import com.call4paperz.android.fragments.LoadFragment;
import com.call4paperz.android.model.Event;
import org.jboss.aerogear.android.Callback;
import org.jboss.aerogear.android.pipeline.LoaderPipe;

import java.io.Serializable;
import java.util.List;

public class EventsActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        loadEvents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_events, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        loadEvents();
        return super.onOptionsItemSelected(item);
    }

    private void loadEvents() {

        displayFragment(new LoadFragment());

        Call4PaperzApplication application = (Call4PaperzApplication) getApplication();
        LoaderPipe<Event> eventPipe = application.getEventPipe(this);

        eventPipe.read(new Callback<List<Event>>() {
            @Override
            public void onSuccess(List<Event> events) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("Events", (Serializable) events);

                EventsFragments eventsFragments = new EventsFragments();
                eventsFragments.setArguments(bundle);

                displayFragment(eventsFragments);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("Call4Paperz", e.getMessage(), e);
                Toast.makeText(EventsActivity.this, getString(R.string.not_connection), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void displayFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main, fragment);
        fragmentTransaction.commit();
    }

}