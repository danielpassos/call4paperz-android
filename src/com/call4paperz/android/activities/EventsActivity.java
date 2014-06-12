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
import com.call4paperz.android.R;
import com.call4paperz.android.fragments.EventsFragments;
import com.call4paperz.android.fragments.LoadFragment;
import com.call4paperz.android.model.Event;
import com.crashlytics.android.Crashlytics;
import com.google.gson.GsonBuilder;

import org.jboss.aerogear.android.Pipeline;
import org.jboss.aerogear.android.impl.pipeline.GsonResponseParser;
import org.jboss.aerogear.android.impl.pipeline.PipeConfig;
import org.jboss.aerogear.android.pipeline.LoaderPipe;
import org.jboss.aerogear.android.pipeline.support.AbstractFragmentActivityCallback;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;

public class EventsActivity extends ActionBarActivity {

    private LoaderPipe<Event> eventPipe;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Crashlytics.start(this);

        setContentView(R.layout.main);

        createPipe();

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

    public void createPipe() {
        try {

            URL baseURL = new URL("http://call4paperz.com");

            GsonBuilder gsonBuilder = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES);

            Pipeline pipeline = new Pipeline(baseURL);

            PipeConfig pipeConfigEvent = new PipeConfig(baseURL, Event.class);
            pipeConfigEvent.setName("events");
            pipeConfigEvent.setEndpoint("events.json");
            pipeConfigEvent.setDataRoot("events");
            pipeConfigEvent.setResponseParser(new GsonResponseParser(gsonBuilder.create()));
            pipeline.pipe(Event.class, pipeConfigEvent);

            eventPipe = pipeline.get("events", this);

        } catch (MalformedURLException e) {
            Toast.makeText(getApplicationContext(), getString(R.string.an_error_occurred),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void loadEvents() {
        displayLoad();
        eventPipe.read(new ReadCallback());
    }

    private void displayFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main, fragment);
        fragmentTransaction.commit();
    }

    private void displayLoad() {
        displayFragment(new LoadFragment());
    }

    private void displayEvents(List<Event> events) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("Events", (Serializable) events);

        EventsFragments eventsFragments = new EventsFragments();
        eventsFragments.setArguments(bundle);

        displayFragment(eventsFragments);
    }

    private static class ReadCallback extends AbstractFragmentActivityCallback<List<Event>> {

        @Override
        public void onSuccess(List<Event> events) {
            EventsActivity activity = (EventsActivity) getFragmentActivity();
            activity.displayEvents(events);
        }

        @Override
        public void onFailure(Exception e) {
            Log.e("Call4Paperz", e.getMessage(), e);
            Toast.makeText(getFragmentActivity().getApplicationContext(),
                    getFragmentActivity().getString(R.string.an_error_occurred), Toast.LENGTH_LONG).show();
        }

    }

}