package com.call4paperz.android.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.call4paperz.android.R;
import com.call4paperz.android.activities.EventActivity;
import com.call4paperz.android.adapters.EventsAdapter;
import com.call4paperz.android.model.Event;

import java.util.List;

public class EventsFragments extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.events, null);

        final List<Event> events = (List<Event>) getArguments().getSerializable("Events");

        final ListView eventsListView = (ListView) view.findViewById(android.R.id.list);
        eventsListView.setAdapter(new EventsAdapter(getActivity(), events));

        eventsListView.setClickable(true);
        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Event event = (Event) eventsListView.getAdapter().getItem(position);
                Intent eventIntent = new Intent(getActivity(), EventActivity.class);
                // TODO Move to static variable
                eventIntent.putExtra("event", event);
                startActivity(eventIntent);
            }
        });

        return view;
    }
}
