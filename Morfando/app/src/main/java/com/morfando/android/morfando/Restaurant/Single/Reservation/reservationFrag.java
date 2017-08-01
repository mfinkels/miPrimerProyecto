package com.morfando.android.morfando.Restaurant.Single.Reservation;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.morfando.android.morfando.Class.Cuisine;
import com.morfando.android.morfando.Class.ParseQuery;
import com.morfando.android.morfando.Class.Reservation;
import com.morfando.android.morfando.Main2Activity;
import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;
import com.shawnlin.numberpicker.NumberPicker;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.Calendar;

/**
 * Created by Matias on 25/7/17.
 */

public class reservationFrag extends Fragment implements View.OnClickListener{

    Main2Activity main2;

    Button reserve;
    NumberPicker guestPicker;

    String[] guest = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_reservation, group, false);

        main2 = (Main2Activity) getActivity();

        guestPicker = (NumberPicker) toReturn.findViewById(R.id.number_picker);
        reserve = (Button)toReturn.findViewById(R.id.makeReservation);
        reserve.setOnClickListener(this);

        return toReturn;
    }

    public void onClick(View v){
        //main2.createReservation(guestPicker.getValue());
    }
}
