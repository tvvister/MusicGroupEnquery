package com.maintwister.musicgroupfile.model;

import android.databinding.ObservableField;
import android.view.View;
import android.webkit.URLUtil;

import java.util.List;

/**
 * Created by Andrey on 01.04.2016.
 */
public class SingerInfoViewModel {
    private final SingerInfo singerInfo;



    public SingerInfoViewModel(SingerInfo singerInfo){
        this.singerInfo = singerInfo;
    }

    public String getName() {
        return singerInfo.getName();
    }

    public String getDescription() {
        return singerInfo.getDescription();
    }

    public String getSmall() {
        return singerInfo.getSmall();
    }

    public String getBig() {
        return singerInfo.getBig();
    }

    public String getGenresString()
    {
        List<String> genres = singerInfo.getGenres();
        if (genres == null || genres.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (String genresUnit : genres)
        {
            sb.append(genresUnit);
            sb.append(", ");
        }
        return sb.substring(0, Math.max(sb.length() - 2, 0));
    }

    public String getMusicCount()
    {
        String info = null;
        Integer albums = singerInfo.getAlbums();
        Integer tracks = singerInfo.getTracks();
        if (albums > 0)
        {
            info = albums + " альбомов";
        }
        else
        {
            return tracks > 0 ? tracks + " трэков" : "";
        }
        if (tracks > 0)
        {
            info += ", " + tracks + " трэков";
            return info;
        }

        return info;
    }

    public String getMusicCount2()
    {
        return getMusicCount2(getMusicCount());
    }


    public String getMusicCount2(String info)
    {
        String musicCount = getMusicCount();
        return  musicCount != null ? musicCount.replace(", ", " · ") : "";
    }

    public String getLink() {
        return singerInfo.getLink();
    }

    public int getLinkVisibility() {
        String link = getLink();
        if (link == null) return View.GONE;
        return URLUtil.isHttpUrl(link) || URLUtil.isHttpsUrl(link) ? View.VISIBLE : View.GONE;
    }

    final public ObservableField<Integer> progressBarVisibility = new ObservableField<>(View.INVISIBLE);
    final public ObservableField<Integer> imageVisibility = new ObservableField<>(View.VISIBLE);


}
