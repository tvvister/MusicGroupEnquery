package com.maintwister.musicgroupfile;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class DataBindingTestActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_binding_test);
        // ActivityDataBindingTestBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_test);

        ArrayList<User> users = new ArrayList<>();
        users.add(0, new User("fsfsfg", "fsdfgs"));
        users.add(1, new User("ssdfgdfgsdfg", "sfgsdfgsdfgs"));

        ListViewAdapter adapter = new ListViewAdapter(this,users);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}
