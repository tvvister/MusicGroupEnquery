package com.maintwister.musicgroupfile;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.maintwister.musicgroupfile.databinding.SingerItemBinding;
import com.maintwister.musicgroupfile.provider.ISendMessage;

public class SingerInfoViewHolder extends RecyclerView.ViewHolder {

    SingerItemBinding binding;
    public SingerInfoViewHolder(View v, final ISendMessage sendMessageAction) {
        super(v);
        binding = DataBindingUtil.bind(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageAction.sendMessage(binding.getInfo());
            }
        });
    }
}
