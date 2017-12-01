package com.morfando.android.morfando.Restaurant;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.morfando.android.morfando.Class.Branch;
import com.morfando.android.morfando.Class.Cuisine;
import com.morfando.android.morfando.Class.Menu;
import com.morfando.android.morfando.Class.ParseQuery;
import com.morfando.android.morfando.Class.Reservation;
import com.morfando.android.morfando.Class.Service;
import com.morfando.android.morfando.Interface.asyncTaskCompleted;
import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;
import com.morfando.android.morfando.Restaurant.Adapter.lvRestaurantAdapter;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Matias on 11/24/2017.
 */

public class setFilterFrag extends DialogFragment {
    MainActivity main;
    ParseQuery pq;

    Spinner cousine, service;
    NumberPicker npRating;
    Button makeFilter;

    ArrayList<Service> listService;
    ArrayList<Cuisine> listCuisine;

    int idService, idCuisine, rating;


    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_set_filter, group, false);
        main = (MainActivity)getActivity();
        pq = new ParseQuery(main);

        idService = 0;
        idCuisine = 0;
        rating = 0;

        cousine = (Spinner)toReturn.findViewById(R.id.spCousine);
        service = (Spinner)toReturn.findViewById(R.id.spService);
        npRating = (NumberPicker)toReturn.findViewById(R.id.npRatingFilter);
        makeFilter = (Button)toReturn.findViewById(R.id.btnMakeFilter);
        makeFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeFilterPressed();
            }
        });

        asyncTaskCompleted listenerS = new asyncTaskCompleted() {
            @Override
            public void onPostAsyncTask(Object result) {
                listService = (ArrayList<Service>) result;
                if (listService != null){
                    listServices();
                }else {
                    Toast.makeText(main, "Error Menu", Toast.LENGTH_SHORT).show();
                }
            }
        };
        pq.getServices(listenerS);
        asyncTaskCompleted listenerC = new asyncTaskCompleted() {
            @Override
            public void onPostAsyncTask(Object result) {
                listCuisine = (ArrayList<Cuisine>) result;
                if (listCuisine != null){
                    listCuisines();
                }else {
                    Toast.makeText(main, "Error Menu", Toast.LENGTH_SHORT).show();
                }
            }
        };
        pq.getCuisine(listenerC);



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

    private void listCuisines() {
        ArrayList<String> listItems = new ArrayList<String>();
        for (Cuisine c : listCuisine) {
            listItems.add(c.name);
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(main, android.R.layout.simple_spinner_dropdown_item, listItems);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        service.setAdapter(adapter);
        service.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CuisineSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void CuisineSelected(int position) {
        idCuisine = listCuisine.get(position).idCuisine;
    }

    private void listServices() {
        ArrayList<String> listItems = new ArrayList<String>();
        for (Service s : listService) {
            listItems.add(s.name);
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(main, android.R.layout.simple_spinner_dropdown_item, listItems);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        service.setAdapter(adapter);
        service.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ServiceSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void ServiceSelected(int position) {
        idService = listService.get(position).idService;
    }

    private void makeFilterPressed() {
        dismiss();
        main.filterBranch(idCuisine, idService, rating);
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
