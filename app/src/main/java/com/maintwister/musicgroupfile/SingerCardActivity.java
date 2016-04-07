package com.maintwister.musicgroupfile;

import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.maintwister.musicgroupfile.app.ViewModelActivity;
import com.maintwister.musicgroupfile.databinding.ActivitySingerCardBinding;
import com.maintwister.musicgroupfile.model.ApplicationViewModel;
import com.maintwister.musicgroupfile.model.SingerInfoViewModel;

public class SingerCardActivity extends ViewModelActivity<ApplicationViewModel> {

    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ApplicationViewModel viewModel = getViewModel();
        SingerInfoViewModel singerInfo = viewModel.selectedSingerInfoViewModel.get();


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(singerInfo.getName());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);

        ActivitySingerCardBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_singer_card);
        binding.setSingerInfoViewModel(singerInfo);

        relativeLayout = (RelativeLayout)findViewById(R.id.relativeContainer);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            int height = getResources().getDisplayMetrics().heightPixels;
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) relativeLayout.getLayoutParams();
            params.height = height / 2;
            relativeLayout.setLayoutParams(params);
        }
    }
}
