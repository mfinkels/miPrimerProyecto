package com.morfando.android.morfando.Reservation.Single;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;

import com.morfando.android.morfando.Class.ParseQuery;
import com.morfando.android.morfando.Class.Plate;
import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;
import com.morfando.android.morfando.Reservation.Adapter.plateAdapter;

import java.util.ArrayList;

/**
 * Created by Matias on 16/9/17.
 */

public class platesFrag extends DialogFragment {
    MainActivity main;
    ParseQuery pq;
    ArrayList<Plate> myPlates;
    ListView plates;
    plateAdapter adapter;
    FloatingActionButton cart;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_plates, group, false);
        main = (MainActivity)getActivity();
        pq = new ParseQuery(main);

        myPlates = main.getPlates();
        plates = (ListView)toReturn.findViewById(R.id.listPlates);
        cart = (FloatingActionButton)toReturn.findViewById(R.id.btnCart);
        adapter = new plateAdapter(myPlates,main,true);
        plates.setAdapter(adapter);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOutCart();
            }
        });

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

    private void checkOutCart() {

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
