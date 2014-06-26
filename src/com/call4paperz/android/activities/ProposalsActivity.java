package com.call4paperz.android.activities;

import android.app.AlertDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.call4paperz.android.Constants;
import com.call4paperz.android.R;
import com.call4paperz.android.adapters.ProposalsAdapter;
import com.call4paperz.android.model.Event;
import com.call4paperz.android.model.Proposal;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;

import java.util.List;

@EActivity(R.layout.proposals)
public class ProposalsActivity extends ActionBarActivity {

    private List<Proposal> proposals;

    @AfterViews
    void displayProposals() {
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Event event = (Event) getIntent().getExtras().getSerializable(Constants.EVENT);
        this.proposals = event.getProposals();
        final ListView view = (ListView) findViewById(android.R.id.list);
        view.setAdapter(new ProposalsAdapter(getApplicationContext(), this.proposals));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return false;
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