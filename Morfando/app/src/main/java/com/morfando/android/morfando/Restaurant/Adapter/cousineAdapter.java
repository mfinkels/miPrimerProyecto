package com.morfando.android.morfando.Restaurant.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.morfando.android.morfando.Class.Cuisine;
import com.morfando.android.morfando.Class.Menu;

import java.util.ArrayList;

/**
 * Created by Matias on 11/24/2017.
 */

public class cousineAdapter extends BaseAdapter {
    private ArrayList<Cuisine> cuisines;
    private Context myContext;
    private boolean reservation;

    public cousineAdapter (ArrayList<Cuisine> list, Context usedContext) {
        this.cuisines = list;
        this.myContext = usedContext;
    }

    @Override
    public int getCount() {
        return cuisines.size();
    }

    @Override
    public Cuisine getItem(int position) {
        return cuisines.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cuisines.get(position).idCuisine;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Cuisine c = getItem(position);
        TextView type = new TextView(myContext);

        type.setText(c.name);
        type.setTextSize(24f);
        return type;
    }

}
