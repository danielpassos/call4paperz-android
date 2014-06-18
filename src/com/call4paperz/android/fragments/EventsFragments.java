package com.call4paperz.android.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.call4paperz.android.Constants;
import com.call4paperz.android.R;
import com.call4paperz.android.activities.EventActivity_;
import com.call4paperz.android.activities.EventsActivity;
import com.call4paperz.android.adapters.EventsAdapter;
import com.call4paperz.android.model.Event;

import java.util.List;

public class EventsFragments extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final EventsActivity activity = (EventsActivity) getActivity();
        final List<Event> events = (List<Event>) getArguments().getSerializable(Constants.EVENTS);

        View view = LayoutInflater.from(activity).inflate(R.layout.events, null);
        ListView eventsListView = (ListView) view.findViewById(android.R.id.list);
        eventsListView.setAdapter(new EventsAdapter(activity, events));
        eventsListView.setClickable(true);
        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Event event = (Event) adapter.getItemAtPosition(position);
                Intent eventIntent = new Intent(activity, EventActivity_.class);
                eventIntent.putExtra(Constants.EVENT, event);
                startActivity(eventIntent);
            }
        });

        activity.getAttacher().addRefreshableView(eventsListView, activity);

        return view;
    }
}
