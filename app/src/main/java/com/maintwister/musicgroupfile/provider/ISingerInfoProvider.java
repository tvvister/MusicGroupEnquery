package com.maintwister.musicgroupfile.provider;

import com.maintwister.musicgroupfile.model.SingerInfo;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;

///http://download.cdn.yandex.net/mobilization-2016/artists.json
public interface ISingerInfoProvider {
    @Headers("Cache-Control: public, max-age=640000, s-maxage=640000 , max-stale=2419200")
    @GET("/artists.json")      //here is the other url part.best way is to start using /
    public void getAllSingers(Callback<SingerInfo[]> response);     //string user is for passing values from edittext for eg: user=basil2style,google
    //response is the response from the server which is now in the POJO
}
