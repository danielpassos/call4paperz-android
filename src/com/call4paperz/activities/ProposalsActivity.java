package com.call4paperz.activities;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.call4paperz.R;
import com.call4paperz.adapters.ProposalsAdapter;
import com.call4paperz.exception.NotConnectionException;
import com.call4paperz.exception.RetrieveException;
import com.call4paperz.model.Event;
import com.call4paperz.model.Proposal;
import com.call4paperz.util.Retrieve;

import java.util.ArrayList;
import java.util.List;

public class ProposalsActivity extends ListActivity {

    private Retrieve retrieve;
    private ListView proposalsListView;
    private Event event;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);

        event = (Event) getIntent().getExtras().getSerializable("event");

        retrieve = new Retrieve(this);

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

        new LoadEventsTask().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();

        new LoadEventsTask().execute();
    }

    private class LoadEventsTask extends AsyncTask<Object, Object, List<Proposal>> {

        private ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(ProposalsActivity.this, getString(R.string.loading), getString(R.string.load_proposals), true, true);
        }

        @Override
        protected List<Proposal> doInBackground(Object... params) {
            try {
                return retrieve.proposals(event);
            } catch (NotConnectionException e) {
                Toast.makeText(ProposalsActivity.this, getString(R.string.not_connection), Toast.LENGTH_LONG).show();
            } catch (RetrieveException e) {
                Toast.makeText(ProposalsActivity.this, getString(R.string.parse_error), Toast.LENGTH_LONG).show();
            }
            return new ArrayList<Proposal>();
        }

        @Override
        protected void onPostExecute(final List<Proposal> proposals) {
            ArrayAdapter<Proposal> adapter = new ProposalsAdapter(ProposalsActivity.this, proposals);
            setListAdapter(adapter);

            progress.dismiss();
        }

    }

}