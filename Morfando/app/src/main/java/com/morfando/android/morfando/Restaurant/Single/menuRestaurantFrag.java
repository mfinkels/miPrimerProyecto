package com.morfando.android.morfando.Restaurant.Single;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;

import com.morfando.android.morfando.Class.Branch;
import com.morfando.android.morfando.Class.Menu;
import com.morfando.android.morfando.Class.ParseQuery;
import com.morfando.android.morfando.Interface.asyncTaskCompleted;
import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;

import java.util.ArrayList;

/**
 * Created by Matias on 6/30/2017.
 */

public class menuRestaurantFrag extends DialogFragment{

    MainActivity main;

    ParseQuery pq;
    ListView menu;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_menu_restaurant, group, false);
        main = (MainActivity)getActivity();
        pq = new ParseQuery(main);

        Toolbar toolbar = (Toolbar) toReturn.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
        }
        setHasOptionsMenu(true);

        menu = (ListView)toReturn.findViewById(R.id.menuList);
        asyncTaskCompleted listener = new asyncTaskCompleted() {
            @Override
            public void onPostAsyncTask(Object result) {
                ArrayList<Menu> list = (ArrayList<Menu>) result;
                if (list != null){

                }
            }
        };

        return toReturn;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onCreateOptionsMenu(android.view.Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.cart_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cartItem){
            // hacer algo si toca el carrito
        } else if (id == android.R.id.home) {
            // handle close button click here
            dismiss();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
