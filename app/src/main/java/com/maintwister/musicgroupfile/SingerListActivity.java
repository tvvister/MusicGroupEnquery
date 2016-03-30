package com.maintwister.musicgroupfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.maintwister.musicgroupfile.model.SingerInfo;
import com.maintwister.musicgroupfile.provider.ISendMessage;
import com.maintwister.musicgroupfile.provider.ISingerInfoProvider;
import com.maintwister.musicgroupfile.provider.ProviderCreator;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SingerListActivity extends AppCompatActivity {
    ProgressBar pbar;
    RecyclerView recView;
    private SingersRecyclerViewAdapter adapter;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_singer_list);
        // ActionBar actionBar = getActionBar();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        Log.i("new activity", this.hashCode() + "");
        pbar = (ProgressBar) findViewById(R.id.progressBar);
        recView = (RecyclerView) findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(this));
        pbar.setVisibility(View.VISIBLE);
        ISingerInfoProvider infoProvider = ProviderCreator.createSingerInfoProvider();
        infoProvider.getAllSingers(new Callback<SingerInfo[]>() {
            @Override
            public void success(SingerInfo[] singerInfos, Response response) {
                adapter = new SingersRecyclerViewAdapter(singerInfos, new ISendMessage() {
                    @Override
                    public void sendMessage(SingerInfo singerInfo) {

                        Intent intent = new Intent(SingerListActivity.this, SingerCardActivity.class);
                        Gson gson = new Gson();
                        intent.putExtra("singerInfo", gson.toJson(singerInfo));
                        startActivity(intent);
                    }
                });
                SingerListActivity.this.recView.setAdapter(adapter);
                pbar.setVisibility(View.INVISIBLE);
            }
            @Override
            public void failure(RetrofitError error) {
                pbar.setVisibility(View.INVISIBLE);
            }
        });
    }


}
