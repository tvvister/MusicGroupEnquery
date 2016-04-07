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
import com.squareup.picasso.Picasso;

/**
 * Created by Andrey on 29.03.2016.
 */
public class SingersRecyclerViewAdapter extends RecyclerView.Adapter<SingerInfoViewHolder> {
    SingerInfoViewModel[] singerInfoViewModels;
    private final View.OnClickListener onClickListener;
    private final ObservableField<SingerInfoViewModel> selectedSingerInfoViewModel;

    public SingersRecyclerViewAdapter(SingerInfoViewModel[] singerInfoViewModels,
                                      final View.OnClickListener onClickListener,
                                      ObservableField<SingerInfoViewModel> selectedSingerInfoViewModel) {
        this.singerInfoViewModels = singerInfoViewModels;
        this.onClickListener = onClickListener;
        this.selectedSingerInfoViewModel = selectedSingerInfoViewModel;
    }

    @Override
    public SingerInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SingerItemBinding binding = SingerItemBinding.inflate(inflater, parent, false);
        return new SingerInfoViewHolder(binding.getRoot(), onClickListener, selectedSingerInfoViewModel);
    }

    @Override
    public void onBindViewHolder(SingerInfoViewHolder holder, int position) {
        SingerInfoViewModel singerInfo = singerInfoViewModels[position];
        holder.setSingerInfo(singerInfo);
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
