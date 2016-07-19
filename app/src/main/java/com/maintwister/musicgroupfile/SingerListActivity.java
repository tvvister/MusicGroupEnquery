package com.maintwister.musicgroupfile;

import android.os.Bundle;

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
            fm.beginTransaction().add(listFragment, "SingerListFragment").commit();

        }
        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, listFragment)
                    .commit();
        }

    }




}
