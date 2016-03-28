package com.maintwister.musicgroupfile;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.google.gson.Gson;
import com.maintwister.musicgroupfile.databinding.ActivitySingerCardBinding;
import com.maintwister.musicgroupfile.model.SingerInfo;

public class SingerCardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singer_card);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        Intent intent = getIntent();
        if (intent != null) {
            Gson gson = new Gson();
            SingerInfo singerInfo = gson.fromJson(intent.getStringExtra("singerInfo"), SingerInfo.class);
            actionBar.setTitle(singerInfo.getName());
            ActivitySingerCardBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_singer_card);
            binding.setInfo(singerInfo);
        }
    }
}
