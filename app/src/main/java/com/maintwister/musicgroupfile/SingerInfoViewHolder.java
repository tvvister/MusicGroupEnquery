package com.maintwister.musicgroupfile;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.maintwister.musicgroupfile.databinding.SingerItemBinding;
import com.maintwister.musicgroupfile.model.SingerInfoViewModel;
import com.maintwister.musicgroupfile.provider.ISendMessage;

public class SingerInfoViewHolder extends RecyclerView.ViewHolder {
    private final View v;
    private final ISendMessage sendMessageAction;
    private final ObservableField<SingerInfoViewModel> selectedSingerInfoViewModel;
    SingerItemBinding binding;
    public SingerInfoViewHolder(View v, final ISendMessage sendMessageAction, ObservableField<SingerInfoViewModel> selectedSingerInfoViewModel) {
        super(v);
        this.v = v;
        this.sendMessageAction = sendMessageAction;
        this.selectedSingerInfoViewModel = selectedSingerInfoViewModel;
        binding = DataBindingUtil.bind(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingerInfoViewHolder.this.selectedSingerInfoViewModel.set(binding.getSingerInfoViewModel());
                sendMessageAction.sendMessage(binding.getSingerInfoViewModel(), v.getWidth() / 2, (int)((v.getY() + v.getHeight()) / 2));
            }
        });
    }
}
