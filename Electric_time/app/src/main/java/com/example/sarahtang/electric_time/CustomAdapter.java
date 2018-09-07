package com.example.sarahtang.electric_time;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sarahtang on 9/3/18.
 */

public class CustomAdapter extends BaseAdapter {
    private static ArrayList<TravelResults> travelArrayList;
    private LayoutInflater mInflater;

    @Override
    public int getCount() {
        return travelArrayList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.custom_row_view, null);
            holder = new ViewHolder();
            holder.travelType = (TextView) convertView.findViewById(R.id.travelType);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.travelType.setText(travelArrayList.get(position).getType());
        holder.time.setText(travelArrayList.get(position).getTime()); //#hacked
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return travelArrayList.get(position);
    }


    public CustomAdapter(Context context, ArrayList<TravelResults> results) {
        travelArrayList = results;
        mInflater = LayoutInflater.from(context);
    }

    static class ViewHolder {
        TextView travelType;
        TextView time;
    }
}
