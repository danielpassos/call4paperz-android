package com.call4paperz.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.call4paperz.R;
import com.call4paperz.model.Proposal;

import java.util.List;

public class ProposalsAdapter extends ArrayAdapter<Proposal> {

    public ProposalsAdapter(Context context, List<Proposal> proposals) {
        super(context, R.layout.proposals_line, R.proposal.name, proposals);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Proposal proposal = getItem(position);

        View view = super.getView(position, convertView, parent);

        TextView points = (TextView) view.findViewById(R.proposal.points);
        points.setText(String.valueOf(proposal.getPoints()));

        int color;
        if( proposal.getPoints() > 0 ) {
            color = R.color.green;
        } else if( proposal.getPoints() < 0 ) {
            color = R.color.red;
        } else {
            color = R.color.gray;
        }

        points.setBackgroundResource(color);

        TextView speaker = (TextView) view.findViewById(R.proposal.speaker);
        speaker.setText(proposal.getSpeaker());

        return view;

    }

}