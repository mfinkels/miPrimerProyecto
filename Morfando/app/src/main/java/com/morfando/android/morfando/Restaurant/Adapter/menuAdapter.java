package com.morfando.android.morfando.Restaurant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.morfando.android.morfando.Class.Menu;
import com.morfando.android.morfando.Class.Plate;
import com.morfando.android.morfando.R;

import java.util.ArrayList;

/**
 * Created by Matias on 8/25/2017.
 */

public class menuAdapter extends BaseAdapter {
    private ArrayList<Menu> menus;
    private Context myContext;
    private boolean reservation;

    public menuAdapter (ArrayList<Menu> list, Context usedContext) {
        this.menus = list;
        this.myContext = usedContext;
    }

    @Override
    public int getCount() {
        return menus.size();
    }

    @Override
    public Menu getItem(int position) {
        return menus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return menus.get(position).idMenu;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Menu m = getItem(position);
        TextView type = new TextView(myContext);

        type.setText(m.type);
        type.setTextSize(24f);
        return type;
    }
}
