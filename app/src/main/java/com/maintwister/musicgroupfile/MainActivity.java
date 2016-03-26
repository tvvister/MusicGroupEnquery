package com.maintwister.musicgroupfile;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.maintwister.musicgroupfile.api.SingerInfoProviderAPI;
import com.maintwister.musicgroupfile.model.SingerInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;

import retrofit.RestAdapter;
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

        RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(SingerInfoProviderAPI.uri).build();                                        //create an adapter for retrofit with base url

        SingerInfoProviderAPI git = restAdapter.create(SingerInfoProviderAPI.class);                            //creating a service for adapter with our GET class

        //Now ,we need to call for response
        //Retrofit using gson for JSON-POJO conversion

        git.getAllSingers(new Callback<SingerInfo[]>() {
            @Override
            public void success(SingerInfo[] singerInfos, Response response) {
                String[] values = new String[3];
                values[0] = singerInfos[0].getName();
                values[1] = singerInfos[1].getName();
                values[2] = singerInfos[2].getName();

                ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(values.length);

                Map<String, Object> m;
                for (int i = 0; i < values.length; i++) {
                    m = new HashMap<String, Object>();
                    m.put("LargeName", values[i]);
                    m.put("SmallName", values[i]);
                    data.add(m);
                }
                SimpleAdapter adapter = new SimpleAdapter( MainActivity.this, data,
                        R.layout.item_layout, new String[]{"LargeName", "SmallName"}, new int[]{R.id.largeText, R.id.SmallText});
                MainActivity.this.setListAdapter(adapter);
                pbar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void failure(RetrofitError error) {
                pbar.setVisibility(View.INVISIBLE);
            }
        });
/*        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2" };
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(values.length);

        Map<String, Object> m;
        for (int i = 0; i < values.length; i++) {
            m = new HashMap<String, Object>();
            m.put("LargeName", values[i]);
            m.put("SmallName", values[i]);
            data.add(m);
        }*/



    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " выбран", Toast.LENGTH_LONG).show();
    }
}
