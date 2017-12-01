package com.morfando.android.morfando.Restaurant.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.morfando.android.morfando.Class.Cuisine;
import com.morfando.android.morfando.Class.Service;

import java.util.ArrayList;

/**
 * Created by Matias on 11/24/2017.
 */

public class serviceAdapter extends BaseAdapter {
    private ArrayList<Service> services;
    private Context myContext;

    public serviceAdapter (ArrayList<Service> list, Context usedContext) {
        this.services = list;
        this.myContext = usedContext;
    }

    @Override
    public int getCount() {
        return services.size();
    }

    @Override
    public Service getItem(int position) {
        return services.get(position);
    }

    @Override
    public long getItemId(int position) {
        return services.get(position).idService;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Service s = getItem(position);
        TextView type = new TextView(myContext);

        type.setText(s.name);
        type.setTextSize(24f);
        return type;
    }

}
