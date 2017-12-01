package com.morfando.android.morfando.Restaurant.Single;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;

import com.morfando.android.morfando.Class.Branch;
import com.morfando.android.morfando.Class.CalificationBranch;
import com.morfando.android.morfando.Class.ParseQuery;
import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;
import com.morfando.android.morfando.Restaurant.Single.Adapter.calificationAdapter;

import java.util.ArrayList;

/**
 * Created by Matias on 6/30/2017.
 */

public class calificationRestaurantFrag extends DialogFragment {

    MainActivity main;

    ParseQuery pq;

    ListView calificationList;

    Branch myBranch;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_calification_restaurant, group, false);

        main = (MainActivity)getActivity();
        pq = new ParseQuery(main);
        ArrayList<CalificationBranch> listCalifcations = new ArrayList<CalificationBranch>();
        calificationAdapter adapterCali = new calificationAdapter(listCalifcations, main);
        pq.setAdapter(adapterCali);
        pq.getBranchCalification(main.getBranch().idBranch,10,0);


        calificationList = (ListView)toReturn.findViewById(R.id.calificationSingleLV);
        calificationList.setAdapter(adapterCali);

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
