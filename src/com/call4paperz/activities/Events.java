package com.call4paperz.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.call4paperz.R;
import com.call4paperz.exception.NotConnectionException;
import com.call4paperz.exception.RetrieveException;
import com.call4paperz.model.Event;
import com.call4paperz.util.Retrieve;

public class Events extends Activity {

    private Retrieve retriver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);

        retriver = new Retrieve(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        try {
            ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(this, android.R.layout.simple_list_item_1, retriver.events());
            ListView eventsListView = (ListView) findViewById(R.events.listview);
            eventsListView.setAdapter(adapter);
        } catch (NotConnectionException e) {
            Toast.makeText(this, getString(R.string.not_connection), Toast.LENGTH_LONG).show();
        } catch (RetrieveException e) {
            Toast.makeText(this, getString(R.string.parse_error), Toast.LENGTH_LONG).show();
        }

    }

    private void startLoad() {

    }

    private void finishLoad() {

    }

}
