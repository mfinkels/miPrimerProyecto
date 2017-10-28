package com.morfando.android.morfando.Restaurant.Single.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.morfando.android.morfando.Class.CalificationBranch;
import com.morfando.android.morfando.Class.Promotion;
import com.morfando.android.morfando.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Matias on 8/4/2017.
 */

public class promotionAdapter extends BaseAdapter {

    private ArrayList<Promotion> promotions;
    private Context myContext;

    public promotionAdapter (ArrayList<Promotion> promotions, Context usedContext) {
        this.promotions = promotions;
        this.myContext = usedContext;
    }

    public int getCount() {
        return promotions.size();
    }

    public Promotion getItem(int position) {
        Promotion obj = promotions.get(position);
        return obj;
    }

    public long getItemId(int position) {
        Promotion p = promotions.get(position);
        return p.idPromotion;
    }

    public int getId(int position) {
        Promotion p = promotions.get(position);
        return p.idPromotion;
    }

    public View getView(int positionActual, View viewActual, ViewGroup groupActual) {

        View returnView;


        LayoutInflater inflater;
        inflater=(LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        return null;
    }

}
