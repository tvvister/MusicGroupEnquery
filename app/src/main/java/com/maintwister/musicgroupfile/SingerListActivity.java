package com.maintwister.musicgroupfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.maintwister.musicgroupfile.app.ViewModelActivity;
import com.maintwister.musicgroupfile.model.ApplicationViewModel;

public class SingerListActivity extends ViewModelActivity<ApplicationViewModel> {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        setContentView(R.layout.activity_layout);
        android.app.FragmentManager fm = getFragmentManager();
        SingerListFragment listFragment = (SingerListFragment) fm.findFragmentByTag("SingerListFragment");

        if (listFragment == null) {
            listFragment = new SingerListFragment();
            listFragment.setRetainInstance(true);
        }
        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, listFragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                AboutDialog aboutDialogCanBeNull = (AboutDialog)getFragmentManager().findFragmentByTag("AboutDialog");
                AboutDialog aboutDialog = aboutDialogCanBeNull == null ? new AboutDialog() : aboutDialogCanBeNull;
                if (aboutDialog.isAdded()) aboutDialog.dismiss();
                aboutDialog.show(getFragmentManager(), "AboutDialog");
                break;
            case R.id.action_feedback:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { getString(R.string.owner_email)});

                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.feedback_message));

                startActivityForResult(Intent.createChooser(intent, "Send Email"), 200);
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
