package com.morfando.android.morfando.Restaurant.Single.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.morfando.android.morfando.Class.CalificationBranch;
import com.morfando.android.morfando.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Matias on 8/4/2017.
 */

public class promotionAdapter extends BaseAdapter {

    private ArrayList<CalificationBranch> califications;
    private Context myContext;

    public promotionAdapter (ArrayList<CalificationBranch> califications, Context usedContext) {
        this.califications = califications;
        this.myContext = usedContext;
    }

    public int getCount() {
        return califications.size();
    }

    public CalificationBranch getItem(int position) {
        CalificationBranch cb = califications.get(position);
        return cb;
    }

    public long getItemId(int position) {
        CalificationBranch cb = califications.get(position);
        return cb.idCalification;
    }

    public int getId(int position) {
        CalificationBranch cb = califications.get(position);
        return cb.idCalification;
    }

    public View getView(int positionActual, View viewActual, ViewGroup groupActual) {

        View returnView;


        LayoutInflater inflater;
        inflater=(LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        returnView = inflater.inflate(R.layout.calification_item,groupActual,false);

        CalificationBranch cb = getItem(positionActual);

        CircleImageView imgProfile = (CircleImageView) returnView.findViewById(R.id.imageUser);

        TextView username;
        username = (TextView)returnView.findViewById(R.id.userNameCalificatation);
        username.setText(cb.user.name + " " + cb.user.lastName);

        TextView date;
        date = (TextView)returnView.findViewById(R.id.datetimeCalification);
        date.setText(cb.date.toString());

        RatingBar calification;
        calification = (RatingBar)returnView.findViewById(R.id.calificationUser);
        Float rating = (float) (cb.food + cb.ambience + cb.service) /3;
        calification.setRating(rating);

        TextView message;
        message = (TextView)returnView.findViewById(R.id.messageCalificationUser);
        message.setText(cb.message);

        return returnView;
    }

}
