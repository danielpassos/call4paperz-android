package com.call4paperz.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

        return view;

    }

}