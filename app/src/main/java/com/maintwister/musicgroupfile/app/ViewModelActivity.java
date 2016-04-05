package com.maintwister.musicgroupfile.app;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Andrey on 05.04.2016.
 */
public abstract class ViewModelActivity<VM> extends AppCompatActivity implements IViewModelHolder<VM> {
    @Override
    public VM getViewModel() {
        return ((IViewModelHolder<VM>) this.getApplication()).getViewModel();
    }
}
