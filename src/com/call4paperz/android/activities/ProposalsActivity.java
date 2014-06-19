package com.call4paperz.android.activities;

import android.app.AlertDialog;
import android.app.ListActivity;

import com.call4paperz.android.Constants;
import com.call4paperz.android.R;
import com.call4paperz.android.adapters.ProposalsAdapter;
import com.call4paperz.android.model.Event;
import com.call4paperz.android.model.Proposal;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;

import java.util.List;

@EActivity(R.layout.events)
public class ProposalsActivity extends ListActivity {

    private List<Proposal> proposals;

    @AfterViews
    void displayProposals() {
        Event event = (Event) getIntent().getExtras().getSerializable(Constants.EVENT);
        this.proposals = event.getProposals();
        setListAdapter(new ProposalsAdapter(getApplicationContext(), this.proposals));
    }

    @ItemClick(android.R.id.list)
    void showProposal(int position) {
        Proposal proposal = this.proposals.get(position);
        new AlertDialog.Builder(ProposalsActivity.this)
                .setTitle(proposal.getName())
                .setMessage(proposal.getDescription())
                .setPositiveButton(getString(R.string.modal_close), null)
                .show();
    }

}