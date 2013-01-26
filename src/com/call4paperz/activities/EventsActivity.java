package com.call4paperz.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.call4paperz.R;
import com.call4paperz.adapters.EventsAdapter;
import com.call4paperz.exception.NotConnectionException;
import com.call4paperz.exception.RetrieveException;
import com.call4paperz.model.Event;
import com.call4paperz.util.Retrieve;

import java.util.ArrayList;
import java.util.List;

public class EventsActivity extends SherlockListActivity {

    private Retrieve retrieve;
    private ListView eventsListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);

        retrieve = new Retrieve(this);

        eventsListView = (ListView) findViewById(android.R.id.list);
        eventsListView.setClickable(true);
        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Event event = (Event) eventsListView.getAdapter().getItem(position);
                Intent eventIntent = new Intent(EventsActivity.this, EventActivity.class);
                // TODO Move to static variable
                eventIntent.putExtra("event", event);
                startActivity(eventIntent);
            }
        });


        new LoadEventsTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.menu_events, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        new LoadEventsTask().execute();

        return super.onOptionsItemSelected(item);
    }


    private class LoadEventsTask extends AsyncTask<Object, Object, List<Event>> {

        private ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(EventsActivity.this, getString(R.string.loading), getString(R.string.load_events), true, true);
        }

        @Override
        protected List<Event> doInBackground(Object... params) {
            try {
                return retrieve.events();
            } catch (NotConnectionException e) {
                Toast.makeText(EventsActivity.this, getString(R.string.not_connection), Toast.LENGTH_LONG).show();
            } catch (RetrieveException e) {
                Toast.makeText(EventsActivity.this, getString(R.string.parse_error), Toast.LENGTH_LONG).show();
            }
            return new ArrayList<Event>();
        }

        @Override
        protected void onPostExecute(final List<Event> events) {
            ArrayAdapter<Event> adapter = new EventsAdapter(EventsActivity.this, events);
            setListAdapter(adapter);

            progress.dismiss();
        }

    }

}