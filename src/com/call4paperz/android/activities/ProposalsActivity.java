package com.call4paperz.android.activities;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.call4paperz.android.R;
import com.call4paperz.android.adapters.ProposalsAdapter;
import com.call4paperz.android.model.Event;
import com.call4paperz.android.model.Proposal;

public class ProposalsActivity extends ListActivity {

    private ListView proposalsListView;
    private Event event;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);

        event = (Event) getIntent().getExtras().getSerializable("event");

        proposalsListView = (ListView) findViewById(android.R.id.list);
        proposalsListView.setClickable(true);
        proposalsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Proposal proposal = (Proposal) proposalsListView.getAdapter().getItem(position);
                new AlertDialog.Builder(ProposalsActivity.this)
                        .setTitle(proposal.getName())
                        .setMessage(proposal.getDescription())
                        .setPositiveButton(getString(R.string.modal_close), null)
                        .show();
            }
        });

        setListAdapter(new ProposalsAdapter(ProposalsActivity.this, event.getProposals()));
    }

}