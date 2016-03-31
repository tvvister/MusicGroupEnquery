package com.maintwister.musicgroupfile;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.maintwister.musicgroupfile.databinding.SingerItemBinding;
import com.maintwister.musicgroupfile.model.SingerInfo;
import com.maintwister.musicgroupfile.provider.ISendMessage;
import com.squareup.picasso.Picasso;

/**
 * Created by Andrey on 29.03.2016.
 */
public class SingersRecyclerViewAdapter extends RecyclerView.Adapter<SingerInfoViewHolder> {
    SingerInfo[] singerInfos;
    private final ISendMessage sendMessageAction;

    public SingersRecyclerViewAdapter(SingerInfo[] singerInfos, final ISendMessage sendMessageAction) {
        this.singerInfos = singerInfos;
        this.sendMessageAction = sendMessageAction;
    }

    @Override
    public SingerInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SingerItemBinding binding = SingerItemBinding.inflate(inflater, parent, false);
        return new SingerInfoViewHolder(binding.getRoot(), sendMessageAction);
    }

    @Override
    public void onBindViewHolder(SingerInfoViewHolder holder, int position) {
        SingerInfo singerInfo = singerInfos[position];
        holder.binding.setInfo(singerInfo);
    }
    public SingerInfo getSingerInfo(int position) {
        return (SingerInfo)singerInfos[position];
    }
    @Override
    public int getItemCount() {
        return singerInfos.length;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext())
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.color.colorPrimaryDark)
        .into(view);
    }
}
