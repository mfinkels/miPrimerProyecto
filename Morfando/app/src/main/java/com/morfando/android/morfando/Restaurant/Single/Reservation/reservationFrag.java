package com.morfando.android.morfando.Restaurant.Single.Reservation;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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

public class reservationFrag extends DialogFragment implements View.OnClickListener{

    MainActivity main;

    Button reserve;
    NumberPicker guestPicker;

    String[] guest = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_reservation, group, false);

        main = (MainActivity)getActivity();

        guestPicker = (NumberPicker) toReturn.findViewById(R.id.number_picker);
        reserve = (Button)toReturn.findViewById(R.id.makeReservation);
        reserve.setOnClickListener(this);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
        }

        return toReturn;
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

    public void onClick(View v){
        main.createReservation(guestPicker.getValue());
    }
}
