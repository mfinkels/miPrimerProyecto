package com.morfando.android.morfando.Restaurant.Single.Reservation;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.morfando.android.morfando.Class.Cuisine;
import com.morfando.android.morfando.Class.ParseQuery;
import com.morfando.android.morfando.Class.Reservation;
import com.morfando.android.morfando.Interface.asyncTaskCompleted;
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
    TextView datePicker, timePicker;

    String[] guest = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    Calendar datetime;


    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_reservation, group, false);

        main = (MainActivity)getActivity();

        datePicker = (TextView)toReturn.findViewById(R.id.datePicker);
        timePicker = (TextView)toReturn.findViewById(R.id.timePicker);

        datetime = Calendar.getInstance();
        datetime.add(Calendar.DATE,1);

        datePicker.setText(datetime.get(Calendar.DAY_OF_MONTH) + "/" + (datetime.get(Calendar.MONTH)+1) + "/" + datetime.get(Calendar.YEAR));
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePicker(v);
            }
        });

        timePicker.setText("12:00");
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePicker(v);
            }
        });



        guestPicker = (NumberPicker) toReturn.findViewById(R.id.number_picker);
        reserve = (Button)toReturn.findViewById(R.id.makeReservation);
        reserve.setOnClickListener(this);

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

    private void DateTimePicker(View v) {

        switch (v.getId()){
            case R.id.datePicker:
                DatePickerDialog dp = new DatePickerDialog(main, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        datetime.set(year, month,dayOfMonth);
                        datePicker.setText(datetime.get(Calendar.DAY_OF_MONTH) + "/" + (datetime.get(Calendar.MONTH)+1) + "/" + datetime.get(Calendar.YEAR));
                    }
                }, datetime.get(Calendar.DAY_OF_MONTH), datetime.get(Calendar.MONTH), datetime.get(Calendar.YEAR));
                dp.getDatePicker().setMinDate(datetime.getTimeInMillis());
                dp.show();
                break;
            case R.id.timePicker:
                TimePickerDialog tp = new TimePickerDialog(main, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        datetime.set(Calendar.MINUTE, minute);
                        timePicker.setText(hourOfDay + ":" + minute);
                    }
                }, datetime.get(Calendar.HOUR_OF_DAY), datetime.get(Calendar.MINUTE), false);
                tp.show();
                break;
        }
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
        asyncTaskCompleted listener = new asyncTaskCompleted() {
            @Override
            public void onPostAsyncTask(Object result) {
                boolean created = (boolean)result;
                if (created){
                    dismiss();
                    Toast.makeText(main,"se creo la reserva",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(main,"Ups! Hubo un error",Toast.LENGTH_SHORT).show();
                }
            }
        };
        main.createReservation(guestPicker.getValue(), datetime, listener);
    }
}
