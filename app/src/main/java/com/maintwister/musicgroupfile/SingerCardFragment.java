package com.maintwister.musicgroupfile;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.maintwister.musicgroupfile.app.ViewModelFragment;
import com.maintwister.musicgroupfile.databinding.FragmentSingerCardBinding;
import com.maintwister.musicgroupfile.model.ApplicationViewModel;
import com.maintwister.musicgroupfile.model.SingerInfoViewModel;

public class SingerCardFragment extends ViewModelFragment<ApplicationViewModel> {

    private RelativeLayout relativeLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        final ApplicationViewModel viewModel = getViewModel();
        SingerInfoViewModel singerInfo = viewModel.selectedSingerInfoViewModel.get();

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle(singerInfo.getName());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);

        FragmentSingerCardBinding binding = FragmentSingerCardBinding.inflate(inflater);
        binding.setSingerInfoViewModel(singerInfo);
        View root = binding.getRoot();

        relativeLayout = (RelativeLayout)root.findViewById(R.id.relativeContainer);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            int height = getResources().getDisplayMetrics().heightPixels;
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) relativeLayout.getLayoutParams();
            params.height = height / 2;
            relativeLayout.setLayoutParams(params);
        }
        return root;
    }


}
