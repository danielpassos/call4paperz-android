package com.call4paperz.android.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.ListView;

import com.call4paperz.android.Constants;
import com.call4paperz.android.R;
import com.call4paperz.android.activities.EventActivity_;
import com.call4paperz.android.activities.EventsActivity;
import com.call4paperz.android.adapters.EventsAdapter;
import com.call4paperz.android.model.Event;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.events)
public class EventsFragments extends Fragment {

    private List<Event> events;

    private Context context;

    @ViewById(android.R.id.list)
    ListView eventsListView;

    @AfterViews
    void displayEvents() {
        events = (List<Event>) getArguments().getSerializable(Constants.EVENTS);
        EventsActivity activity = (EventsActivity) getActivity();
        context = activity.getApplicationContext();
        eventsListView.setAdapter(new EventsAdapter(context, events));
        activity.getAttacher().addRefreshableView(eventsListView, activity);
    }

    @ItemClick(android.R.id.list)
    public void di(int position) {
        Event event = (Event) events.get(position);
        Intent eventIntent = new Intent(context, EventActivity_.class);
        eventIntent.putExtra(Constants.EVENT, event);
        startActivity(eventIntent);
    }
}
