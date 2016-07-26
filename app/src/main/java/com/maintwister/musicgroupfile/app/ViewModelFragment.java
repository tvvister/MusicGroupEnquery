package com.maintwister.musicgroupfile.app;


import android.app.Fragment;

/**
 * Created by Andrey on 19.07.2016.
 */
public class ViewModelFragment<VM> extends Fragment implements IViewModelHolder<VM> {
    @Override
    public VM getViewModel() {
        return ((IViewModelHolder<VM>) this.getActivity()).getViewModel();
    }
}
