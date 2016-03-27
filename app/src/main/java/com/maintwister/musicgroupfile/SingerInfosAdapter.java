package com.maintwister.musicgroupfile;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.maintwister.musicgroupfile.databinding.SingerItemBinding;
import com.maintwister.musicgroupfile.model.SingerInfo;

/**
 * Created by Andrey on 27.03.2016.
 */

public class SingerInfosAdapter extends BaseAdapter {

    LayoutInflater lInflater;
    SingerInfo[] singerInfos;

    SingerInfosAdapter(Context context, SingerInfo[] singerInfos) {
        this.singerInfos = singerInfos;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    // кол-во элементов
    @Override
    public int getCount() {
        return singerInfos.length;
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return singerInfos[position];
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
            SingerInfo singerInfo = (SingerInfo)getItem(position);
            SingerItemBinding binding = DataBindingUtil.inflate(lInflater, R.layout.singer_item, parent, false);
            binding.setInfo(singerInfo);
            convertView = binding.getRoot();
        }
        return convertView ;
    }
}
