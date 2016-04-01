package com.maintwister.musicgroupfile.provider;

import com.maintwister.musicgroupfile.model.SingerInfoViewModel;

/**
 * Created by Andrey on 29.03.2016.
 */
public interface ISendMessage {
    public void sendMessage(SingerInfoViewModel singerInfoViewModel, int x, int y);
}
