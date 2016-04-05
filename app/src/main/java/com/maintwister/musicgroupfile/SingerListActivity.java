package com.maintwister.musicgroupfile;

import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.maintwister.musicgroupfile.app.ViewModelActivity;
import com.maintwister.musicgroupfile.databinding.ActivitySingerListBinding;
import com.maintwister.musicgroupfile.model.ApplicationViewModel;
import com.maintwister.musicgroupfile.model.ICallback;
import com.maintwister.musicgroupfile.model.SingerInfoViewModel;
import com.maintwister.musicgroupfile.provider.ISendMessage;

public class SingerListActivity extends ViewModelActivity<ApplicationViewModel> {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        final ApplicationViewModel viewModel = getViewModel();
        ActivitySingerListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_singer_list);
        binding.setApplicationViewModel(viewModel);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        final RecyclerView recView = (RecyclerView) findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.getSingerInfoViewModels(new ICallback<SingerInfoViewModel[]>() {
            @Override
            public void handle(SingerInfoViewModel[] singerInfoViewModels) {
                SingersRecyclerViewAdapter adapter = new SingersRecyclerViewAdapter(singerInfoViewModels, new ISendMessage() {
                    @Override
                    public void sendMessage(SingerInfoViewModel singerInfoViewModel, int x, int y) {
                        Intent intent = new Intent(SingerListActivity.this, SingerCardActivity.class);
                        Bundle bundle = null;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            ActivityOptions activityOptions = ActivityOptions.makeScaleUpAnimation(recView, x, y, 100, 100);
                            bundle = activityOptions.toBundle();
                            SingerListActivity.this.startActivity(intent, bundle);
                        } else SingerListActivity.this.startActivity(intent);

                    }
                }, viewModel.selectedSingerInfoViewModel);
                recView.setAdapter(adapter);
            }
        });
    }
}
