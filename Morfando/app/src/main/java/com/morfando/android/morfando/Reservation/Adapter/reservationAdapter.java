package com.morfando.android.morfando.Reservation.Adapter;

import android.content.Context;
import android.service.restrictions.RestrictionsReceiver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.morfando.android.morfando.Class.Branch;
import com.morfando.android.morfando.Class.Cuisine;
import com.morfando.android.morfando.Class.Reservation;
import com.morfando.android.morfando.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Matias on 8/3/2017.
 */

public class reservationAdapter extends BaseAdapter {

    private ArrayList<Reservation> reservations;
    private Context myContext;

    public reservationAdapter (ArrayList<Reservation> list, Context usedContext) {
        reservations = list;
        myContext = usedContext;
    }

    public void setData(ArrayList<Reservation> reservations){
        this.reservations = reservations;
    }

    public int getCount() {
        return reservations.size();
    }

    public Reservation getItem(int position) {
        Reservation res = reservations.get(position);
        return res;
    }

    public long getItemId(int position) {
        Reservation res = reservations.get(position);
        return res.idReservation;
    }

    public int getId(int position) {
        Reservation res = reservations.get(position);
        return res.idReservation;
    }

    public View getView(int positionActual, View viewActual, ViewGroup groupActual) {

        View returnView;


        LayoutInflater inflater;
        inflater=(LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        returnView = inflater.inflate(R.layout.reservation_item,groupActual,false);

        Reservation res = getItem(positionActual);

        TextView restaurantName, guest, time, day, month;
        restaurantName = (TextView)returnView.findViewById(R.id.restaurantName);
        guest = (TextView)returnView.findViewById(R.id.guest);
        time = (TextView)returnView.findViewById(R.id.time);
        day = (TextView)returnView.findViewById(R.id.day);
        month = (TextView)returnView.findViewById(R.id.month);

        restaurantName.setText(res.branch.restaurant.name + " " +res.branch.name);
        guest.setText(res.guest + "");
        time.setText(res.date.get(Calendar.HOUR_OF_DAY) + ":" + res.date.get(Calendar.MINUTE));
        day.setText(res.date.get(Calendar.DAY_OF_MONTH));
        month.setText(res.date.get(Calendar.MONTH));


        return returnView;
    }

}
