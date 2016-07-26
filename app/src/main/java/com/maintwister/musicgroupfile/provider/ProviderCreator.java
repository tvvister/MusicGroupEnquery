package com.maintwister.musicgroupfile.provider;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Andrey on 27.03.2016.
 */
public class ProviderCreator {
    private static final String uri = "http://download.cdn.yandex.net/mobilization-2016";

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder()
                    .header("Cache-Control", String.format("max-age=%d, only-if-cached, max-stale=%d", 120, 0))
                    .build();
        }
    };

    public static ISingerInfoProvider createSingerInfoProvider(@NotNull final Application context)
    {
        File cacheDirectory = new File(context.getCacheDir(), "responses");
        Cache cache = new Cache(cacheDirectory, 10 * 1024 * 1024);

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setCache(cache);
        okHttpClient.networkInterceptors().add(REWRITE_CACHE_CONTROL_INTERCEPTOR);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(uri)
                .setClient(new OkClient(okHttpClient))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();


        ISingerInfoProvider provider = restAdapter.create(ISingerInfoProvider.class);
        return provider;
    }

    public static boolean isOnline(@NotNull final Application context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static ISingerInfoProvider createTestSingerInfoProvider()
    {
        return new TestSingerInfoProvider();
    }

}
