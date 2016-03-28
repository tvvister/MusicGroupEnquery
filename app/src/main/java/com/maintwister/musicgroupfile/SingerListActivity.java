package com.maintwister.musicgroupfile;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.maintwister.musicgroupfile.model.SingerInfo;
import com.maintwister.musicgroupfile.provider.ISingerInfoProvider;
import com.maintwister.musicgroupfile.provider.ProviderCreator;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class SingerListActivity extends ListActivity {
    ProgressBar pbar;
    private SingerInfosAdapter adapter;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_singer_list);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        pbar = (ProgressBar) findViewById(R.id.progressBar);
        pbar.setVisibility(View.VISIBLE);
        ISingerInfoProvider infoProvider = ProviderCreator.createSingerInfoProvider();
        infoProvider.getAllSingers(new Callback<SingerInfo[]>() {
            @Override
            public void success(SingerInfo[] singerInfos, Response response) {
                adapter = new SingerInfosAdapter(SingerListActivity.this, singerInfos);
                SingerListActivity.this.setListAdapter(adapter);
                pbar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void failure(RetrofitError error) {
                pbar.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        SingerInfo selectedSingerInfo = adapter.getSingerInfo(position);
        Intent intent = new Intent(this, SingerCardActivity.class);
        Gson gson = new Gson();
        intent.putExtra("singerInfo", gson.toJson(selectedSingerInfo));
        startActivity(intent);
    }
}
