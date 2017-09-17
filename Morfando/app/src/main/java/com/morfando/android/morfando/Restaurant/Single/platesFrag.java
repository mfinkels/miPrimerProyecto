package com.morfando.android.morfando.Restaurant.Single;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.morfando.android.morfando.Class.ParseQuery;
import com.morfando.android.morfando.Class.Plate;
import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;
import com.morfando.android.morfando.Restaurant.Adapter.plateAdapter;

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

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.restaurant_single_frag, group, false);
        main = (MainActivity)getActivity();
        pq = new ParseQuery(main);

        myPlates = main.getPlates();
        plates = (ListView)toReturn.findViewById(R.id.listPlates);
        adapter = new plateAdapter(myPlates,main,false);
        plates.setAdapter(adapter);


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
