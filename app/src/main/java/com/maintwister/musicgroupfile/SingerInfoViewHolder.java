package com.maintwister.musicgroupfile;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.maintwister.musicgroupfile.databinding.SingerItemBinding;
import com.maintwister.musicgroupfile.model.SingerInfoViewModel;

public class SingerInfoViewHolder extends RecyclerView.ViewHolder {
    private final View view;
    private final View.OnClickListener onClickListener;
    private final ObservableField<SingerInfoViewModel> selectedSingerInfoViewModel;
    private SingerItemBinding binding;

    public void setSingerInfo(SingerInfoViewModel singerInfoViewModel) {
        binding.setSingerInfoViewModel(singerInfoViewModel);
    }

    public SingerInfoViewHolder(final View view,
                                final View.OnClickListener onClickListener,
                                final ObservableField<SingerInfoViewModel> selectedSingerInfoViewModel) {
        super(view);
        this.view = view;
        this.onClickListener = onClickListener;
        this.selectedSingerInfoViewModel = selectedSingerInfoViewModel;
        binding = DataBindingUtil.bind(view);
        view.post(new Runnable() {
            @Override
            public void run() {
                View imageView = view.findViewById(R.id.imageView);

                ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                layoutParams.height = imageView.getWidth();

                imageView.setLayoutParams(layoutParams);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedSingerInfoViewModel.set(binding.getSingerInfoViewModel());
                onClickListener.onClick(view);
            }
        });
    }
}
