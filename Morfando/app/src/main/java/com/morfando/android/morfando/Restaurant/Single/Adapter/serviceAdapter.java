package com.morfando.android.morfando.Restaurant.Single.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.morfando.android.morfando.Class.Service;
import com.morfando.android.morfando.R;

import java.util.ArrayList;

/**
 * Created by Matias on 7/14/2017.
 */

public class serviceAdapter extends BaseAdapter{

    private final Context mContext;
    private final ArrayList<Service> services;

    // 1
    public serviceAdapter(Context context, ArrayList<Service> services) {
        this.mContext = context;
        this.services =services;
    }

    @Override
    public int getCount() {
        return services.size();
    }

    @Override
    public Service getItem(int position) {
        Service s = services.get(position);
        return s;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater;
        inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.service_item,parent,false);

        TextView service = (TextView)convertView.findViewById(R.id.serviceItem);
        service.setText(getItem(position).name);
        return convertView;
    }
}
