package com.morfando.android.morfando.Restaurant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.morfando.android.morfando.Class.Branch;
import com.morfando.android.morfando.Class.Cuisine;
import com.morfando.android.morfando.R;

import java.util.ArrayList;

/**
 * Created by Matias on 6/29/2017.
 */

public class lvRestaurantAdapter extends BaseAdapter {

    private ArrayList<Branch> branches;
    private Context myContext;

    public lvRestaurantAdapter (ArrayList<Branch> listBranch, Context usedContext) {
        branches = listBranch;
        myContext = usedContext;
    }

    public int getCount() {
        return branches.size();
    }

    public Branch getItem(int position) {
        Branch b = branches.get(position);
        return b;
    }

    public long getItemId(int position) {
        Branch b = branches.get(position);
        return b.idBranch;
    }

    public int getId(int position) {
        Branch b = branches.get(position);
        return b.idBranch;
    }

    public View getView(int positionActual, View viewActual, ViewGroup groupActual) {

        View returnView;


        LayoutInflater inflater;
        inflater=(LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        returnView = inflater.inflate(R.layout.restaurant_list_item,groupActual,false);

        TextView resName = (TextView) returnView.findViewById(R.id.item_res_name);
        TextView priceRange = (TextView) returnView.findViewById(R.id.item_price_range);
        RatingBar calification = (RatingBar) returnView.findViewById(R.id.item_res_calification);
        TextView cuisine = (TextView)returnView.findViewById(R.id.item_res_cuisine);


        Branch b = getItem(positionActual);

        resName.setText(b.name);
        priceRange.setText("$" + String.valueOf(b.range.minimum) + "- $" + String.valueOf(b.range.maximum));

        calification.setRating(b.averageCalification);
        String cousines = "";
        for (Cuisine c : b.cuisine) {
            if (cousines.length() != 0){
                cousines += "|" + c.name;
            }else {
                cousines = c.name;
            }
        }
        cuisine.setText(cousines);

        return returnView;
    }
}
