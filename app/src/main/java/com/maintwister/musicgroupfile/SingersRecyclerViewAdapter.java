package com.maintwister.musicgroupfile;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.maintwister.musicgroupfile.databinding.SingerItemBinding;
import com.maintwister.musicgroupfile.model.SingerInfoViewModel;
import com.maintwister.musicgroupfile.provider.ISendMessage;
import com.squareup.picasso.Picasso;

/**
 * Created by Andrey on 29.03.2016.
 */
public class SingersRecyclerViewAdapter extends RecyclerView.Adapter<SingerInfoViewHolder> {
    SingerInfoViewModel[] singerInfoViewModels;
    private final ISendMessage sendMessageAction;
    private final ObservableField<SingerInfoViewModel> selectedSingerInfoViewModel;

    public SingersRecyclerViewAdapter(SingerInfoViewModel[] singerInfoViewModels, final ISendMessage sendMessageAction, ObservableField<SingerInfoViewModel> selectedSingerInfoViewModel) {
        this.singerInfoViewModels = singerInfoViewModels;
        this.sendMessageAction = sendMessageAction;
        this.selectedSingerInfoViewModel = selectedSingerInfoViewModel;
    }

    @Override
    public SingerInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SingerItemBinding binding = SingerItemBinding.inflate(inflater, parent, false);
        return new SingerInfoViewHolder(binding.getRoot(), sendMessageAction, selectedSingerInfoViewModel);
    }

    @Override
    public void onBindViewHolder(SingerInfoViewHolder holder, int position) {
        SingerInfoViewModel singerInfo = singerInfoViewModels[position];
        holder.binding.setSingerInfoViewModel(singerInfo);
    }
    public SingerInfoViewModel getSingerInfo(int position) {
        return singerInfoViewModels[position];
    }
    @Override
    public int getItemCount() {
        return singerInfoViewModels.length;
    }

    @BindingAdapter({"bind:imageUrl", "bind:singerInfoViewModel"})
    public static void loadImage(ImageView view, String imageUrl, final SingerInfoViewModel singerInfoViewModel) {
        singerInfoViewModel.progressBarVisibility.set(View.VISIBLE);
        singerInfoViewModel.imageVisibility.set(View.INVISIBLE);
        Picasso.with(view.getContext())
                .load(imageUrl)
                .noPlaceholder()//(R.mipmap.ic_launcher)
                .error(R.color.colorPrimaryDark)
                .into(view, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        singerInfoViewModel.progressBarVisibility.set(View.INVISIBLE);
                        singerInfoViewModel.imageVisibility.set(View.VISIBLE);
                    }

                    @Override
                    public void onError() {
                        singerInfoViewModel.progressBarVisibility.set(View.INVISIBLE);
                        singerInfoViewModel.imageVisibility.set(View.VISIBLE);
                        singerInfoViewModel.errorViewVisibility.set(View.VISIBLE);
                    }}
                );
    }

}
