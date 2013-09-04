package com.call4paperz.android.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.call4paperz.android.R;
import com.call4paperz.android.model.Event;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EventsAdapter extends ArrayAdapter<Event> {

    public EventsAdapter(Context context, List<Event> events) {
        super(context, R.layout.events_item, R.event.name, events);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Event event = getItem(position);

        View view = super.getView(position, convertView, parent);

        final ImageView logo = (ImageView) view.findViewById(R.event.logo);

        String imageURL = event.getPicture().getCropped().getUrl();
        Picasso.with(getContext()).load(imageURL).placeholder(R.drawable.no_image).into(logo);

        TextView proposals = (TextView) view.findViewById(R.event.proposals);
        proposals.setText(String.valueOf(event.getProposals().size()));

        TextView votes = (TextView) view.findViewById(R.event.votes);
        votes.setText(String.valueOf(event.getVotesCount()));

        return view;

    }

}