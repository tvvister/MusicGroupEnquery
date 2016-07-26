package com.maintwister.musicgroupfile.app;

import com.maintwister.musicgroupfile.model.ApplicationViewModel;
import com.maintwister.musicgroupfile.provider.ProviderCreator;

/**
 * Created by Andrey on 05.04.2016.
 */
public class TopSingerWikiApplication extends ViewModelApplication<ApplicationViewModel> {

    private ApplicationViewModel applicationViewModel;

    @Override
    public ApplicationViewModel getViewModel(){
        if (applicationViewModel == null)
        {
            applicationViewModel = new ApplicationViewModel(ProviderCreator.createSingerInfoProvider(this));
        }
        return applicationViewModel;
    }

}
