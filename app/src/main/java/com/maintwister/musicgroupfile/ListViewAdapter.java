package com.maintwister.musicgroupfile;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.maintwister.musicgroupfile.databinding.DataBindingItemBinding;

import java.util.ArrayList;

/**
 * Created by Andrey on 27.03.2016.
 */

public class ListViewAdapter extends BaseAdapter {

    LayoutInflater lInflater;
    ArrayList<User> users;

    ListViewAdapter(Context context, ArrayList<User> users) {
        this.users = users;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    // кол-во элементов
    @Override
    public int getCount() {
        return users.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            User user = users.get(position);
            DataBindingItemBinding binding = DataBindingUtil.inflate(lInflater, R.layout.data_binding_item, parent, false);
            binding.setUser(user);
            convertView = binding.getRoot();
        }
        return convertView ;
    }
}
