package com.call4paperz.activities;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.call4paperz.R;
import com.call4paperz.adapters.EventsAdapater;
import com.call4paperz.exception.NotConnectionException;
import com.call4paperz.exception.RetrieveException;
import com.call4paperz.model.Event;
import com.call4paperz.util.Retrieve;

import java.util.ArrayList;
import java.util.List;

public class EventsActivity extends ListActivity {

    private Retrieve retriver;
    private ListView eventsListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);

        retriver = new Retrieve(this);

        new LoadEventsTask().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new LoadEventsTask().execute();
    }

    private class LoadEventsTask extends AsyncTask<Object, Object, List<Event>> {
		private ProgressDialog progress;
		@Override
		protected void onPreExecute() {
            progress = ProgressDialog.show(	EventsActivity.this, "Loading...", "Loading Events", true , true);
		}

		@Override
		protected List<Event> doInBackground(Object... params) {
			try {
				return retriver.events();
            } catch (NotConnectionException e) {
                Toast.makeText(EventsActivity.this, getString(R.string.not_connection), Toast.LENGTH_LONG).show();
            } catch (RetrieveException e) {
                Toast.makeText(EventsActivity.this, getString(R.string.parse_error), Toast.LENGTH_LONG).show();
            }
            return new ArrayList<Event>();
		}

		@Override
		protected void onPostExecute(final List<Event> events) {
			ArrayAdapter<Event> adapter = new EventsAdapater(EventsActivity.this, events);
            setListAdapter(adapter);

			progress.dismiss();
		}
	}

}