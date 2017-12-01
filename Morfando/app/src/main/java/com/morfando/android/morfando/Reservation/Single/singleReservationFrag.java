package com.morfando.android.morfando.Reservation.Single;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.morfando.android.morfando.Class.ParseQuery;
import com.morfando.android.morfando.Class.Plate;
import com.morfando.android.morfando.Class.Reservation;
import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;

import java.util.Calendar;

/**
 * Created by Matias on 9/18/2017.
 */

public class singleReservationFrag extends DialogFragment {
    MainActivity main;
    ParseQuery pq;

    TextView resName, calendar, time, guest;
    Button order, cancel;
    
    Reservation myReservation;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_single_reservation, group, false);
        main = (MainActivity)getActivity();
        pq = new ParseQuery(main);

        myReservation = main.getReservation();

        
        resName = (TextView)toReturn.findViewById(R.id.restaurantName);
        calendar = (TextView)toReturn.findViewById(R.id.calendar);
        time = (TextView)toReturn.findViewById(R.id.time);
        guest = (TextView)toReturn.findViewById(R.id.guest);
        order = (Button)toReturn.findViewById(R.id.btnOrder);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeOrder();
            }
        });


        resName.setText(myReservation.branch.restaurant.name + " " + myReservation.branch.name);
        calendar.setText(myReservation.date.get(Calendar.DAY_OF_MONTH) + "/" + myReservation.date.get(Calendar.MONTH));
        time.setText(myReservation.date.get(Calendar.HOUR_OF_DAY) + ":" + myReservation.date.get(Calendar.MINUTE));
        guest.setText(myReservation.guest + "");



        cancel = (Button)toReturn.findViewById(R.id.btnCancelReservation);

        if (myReservation.date.getTimeInMillis() > Calendar.getInstance().getTimeInMillis()){
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cancelReservation();
                }
            });
        } else {
            cancel.setVisibility(View.GONE);
        }





        Toolbar toolbar = (Toolbar) toReturn.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
        }
        setHasOptionsMenu(true);

        return toReturn;
    }

    private void cancelReservation() {
    }

    private void makeOrder() {

        main.viewOrder();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // handle close button click here
            dismiss();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
