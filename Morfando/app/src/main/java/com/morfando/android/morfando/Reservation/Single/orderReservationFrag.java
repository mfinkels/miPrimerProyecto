package com.morfando.android.morfando.Reservation.Single;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.morfando.android.morfando.Class.ParseQuery;
import com.morfando.android.morfando.Class.Plate;
import com.morfando.android.morfando.Class.Reservation;
import com.morfando.android.morfando.Interface.asyncTaskCompleted;
import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;
import com.morfando.android.morfando.Restaurant.Single.Adapter.plateAdapter;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Matias on 9/21/2017.
 */

public class orderReservationFrag extends DialogFragment {
    MainActivity main;
    ParseQuery pq;

    ListView listOrder;
    plateAdapter adapter;

    Button newOrder;

    Reservation myReservation;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_order_reservation, group, false);
        main = (MainActivity)getActivity();
        pq = new ParseQuery(main);
        listOrder =(ListView)toReturn.findViewById(R.id.listOrder);
        newOrder = (Button)toReturn.findViewById(R.id.btnNewOrder);

        myReservation = main.getReservation();

        if (myReservation.date.getTimeInMillis() < Calendar.getInstance().getTimeInMillis()){
            newOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    makeNewOrder();
                }
            });
        } else {
            newOrder.setVisibility(View.INVISIBLE);
            listOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Plate plate = adapter.getItem(position);
                    main.setMyPlate(plate);
                    //main.showPlateCalification();
                }
            });
        }

        final asyncTaskCompleted listener = new asyncTaskCompleted() {
            @Override
            public void onPostAsyncTask(Object result) {
                ArrayList<Plate> plates = (ArrayList<Plate>) result;
                if (plates != null && plates.size() >= 0) {
                    myReservation.orders = plates;
                    main.setReservation(myReservation);
                    plateAdapter adapter = new plateAdapter(myReservation.orders,main,false, null);
                    listOrder.setAdapter(adapter);
                    listOrder.deferNotifyDataSetChanged();
                } else {
                    Toast.makeText(main,"Order is empty",Toast.LENGTH_SHORT).show();
                }
            }
        };
        pq.getOrder(myReservation.idReservation, listener);

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

    private void makeNewOrder() {
        main.addNewOrder();
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
