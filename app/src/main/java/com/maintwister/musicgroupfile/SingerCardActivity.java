package com.maintwister.musicgroupfile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.maintwister.musicgroupfile.app.ViewModelActivity;
import com.maintwister.musicgroupfile.databinding.ActivitySingerCardBinding;
import com.maintwister.musicgroupfile.model.ApplicationViewModel;
import com.maintwister.musicgroupfile.model.SingerInfoViewModel;

public class SingerCardActivity extends ViewModelActivity<ApplicationViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singer_card);

        final ApplicationViewModel viewModel = getViewModel();
        SingerInfoViewModel singerInfo = viewModel.selectedSingerInfoViewModel.get();


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(singerInfo.getName());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);

        ActivitySingerCardBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_singer_card);
        binding.setSingerInfoViewModel(singerInfo);
    }
}
