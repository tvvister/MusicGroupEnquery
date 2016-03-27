package com.maintwister.musicgroupfile;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.maintwister.musicgroupfile.model.SingerInfo;
import com.maintwister.musicgroupfile.provider.ISingerInfoProvider;
import com.maintwister.musicgroupfile.provider.ProviderCreator;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ListActivity {
    ProgressBar pbar;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        pbar = (ProgressBar) findViewById(R.id.progressBar);
        pbar.setVisibility(View.VISIBLE);
        ISingerInfoProvider infoProvider = ProviderCreator.createSingerInfoProvider();
        infoProvider.getAllSingers(new Callback<SingerInfo[]>() {
            @Override
            public void success(SingerInfo[] singerInfos, Response response) {
                SingerInfosAdapter adapter = new SingerInfosAdapter(MainActivity.this, singerInfos);
                MainActivity.this.setListAdapter(adapter);
                pbar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void failure(RetrofitError error) {
                pbar.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " выбран", Toast.LENGTH_LONG).show();
    }
}
