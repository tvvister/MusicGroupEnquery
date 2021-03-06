package com.maintwister.musicgroupfile.model;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.maintwister.musicgroupfile.provider.ISingerInfoProvider;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Andrey on 04.04.2016.
 */
public class ApplicationViewModel {
    private final ISingerInfoProvider singerInfoProvider;

    public ApplicationViewModel(@NonNull ISingerInfoProvider singerInfoProvider){
        this.singerInfoProvider = singerInfoProvider;
    }

    public void getSingerInfoViewModels(final ICallback<SingerInfoViewModel[]> callback) {
        isLoading.set(true);
        singerInfoProvider.getAllSingers(new Callback<SingerInfo[]>() {
            @Override
            public void success(SingerInfo[] singerInfos, Response response) {
                SingerInfoViewModel[] singerInfoViewModels = new SingerInfoViewModel[singerInfos.length];
                for (int index = 0; index < singerInfos.length; ++index) {
                    singerInfoViewModels[index] = new SingerInfoViewModel(singerInfos[index]);
                }
                isLoading.set(false);
                hasListLoadingError.set(false);
                callback.handle(singerInfoViewModels);
            }
            @Override
            public void failure(RetrofitError error) {
                isLoading.set(false);
                hasListLoadingError.set(true);
                callback.handle(new SingerInfoViewModel[0]);
            }
        });
    }

    final public ObservableField<SingerInfoViewModel> selectedSingerInfoViewModel = new ObservableField<>(null);
    final public ObservableField<Boolean> hasListLoadingError = new ObservableField<>(false);
    final public ObservableField<Boolean> isLoading = new ObservableField<>(false);
}
