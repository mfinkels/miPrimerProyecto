package com.morfando.android.morfando.Restaurant.Single.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.morfando.android.morfando.Class.Branch;
import com.morfando.android.morfando.Class.Cuisine;
import com.morfando.android.morfando.Class.Timetable;
import com.morfando.android.morfando.R;

import java.util.ArrayList;

/**
 * Created by Matias on 7/14/2017.
 */

public class timetableAdapter extends BaseAdapter {

    private ArrayList<Timetable> timetables;
    private Context myContext;

    public timetableAdapter (ArrayList<Timetable> listTimetable, Context usedContext) {
        timetables = listTimetable;
        myContext = usedContext;
    }

    public int getCount() {
        return timetables.size();
    }

    public Timetable getItem(int position) {
        Timetable tt = timetables.get(position);
        return tt;
    }

    public long getItemId(int position) {
        Timetable tt = timetables.get(position);
        return tt.idTimetable;
    }

    public int getId(int position) {
        Timetable tt = timetables.get(position);
        return tt.idTimetable;
    }

    public View getView(int positionActual, View viewActual, ViewGroup groupActual) {

        View returnView;


        LayoutInflater inflater;
        inflater=(LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        returnView = inflater.inflate(R.layout.timetable_item,groupActual,false);

        Timetable tt = getItem(positionActual);

        TextView day;
        day = (TextView)returnView.findViewById(R.id.day);
        day.setText(tt.Day);

        TextView hour;
        hour = (TextView)returnView.findViewById(R.id.hours);
        hour.setText(tt.openingHours + " - " + tt.closingHours + " hs.");




        return returnView;
    }
}
