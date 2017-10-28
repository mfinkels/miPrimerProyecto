package com.morfando.android.morfando.Restaurant;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonIOException;
import com.morfando.android.morfando.Class.Branch;
import com.morfando.android.morfando.Class.Cuisine;
import com.morfando.android.morfando.Class.Filter;
import com.morfando.android.morfando.Class.Menu;
import com.morfando.android.morfando.Class.ParseQuery;
import com.morfando.android.morfando.Class.Promotion;
import com.morfando.android.morfando.Class.Restaurant;
import com.morfando.android.morfando.Class.Service;
import com.morfando.android.morfando.Class.User;
import com.morfando.android.morfando.Class.PhotoBranch;
import com.morfando.android.morfando.Class.SocialNetwork;
import com.morfando.android.morfando.Class.Timetable;
import com.morfando.android.morfando.Class.Utility;
import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;
import com.morfando.android.morfando.Restaurant.Adapter.RestaurantAdapter;
import com.morfando.android.morfando.Restaurant.Adapter.lvRestaurantAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Matias on 6/18/2017.
 */

public class restaurantFrag extends Fragment implements RestaurantAdapter.ListItemClickListener {
    MainActivity main;
    private RecyclerView listRestaurant;
    private RestaurantAdapter resAdapter;

    private ParseQuery querys = new ParseQuery(main);
    private ArrayList<Branch> myBranches;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_restaurant, group, false);
        main = (MainActivity)getActivity();
        listRestaurant = (RecyclerView) toReturn.findViewById(R.id.rv_restaurant);
        loadRestaurantData();

        return toReturn;
    }

    private void loadRestaurantData() {
        //myBranches = querys.getAllBranch(10, 0);
        LinearLayoutManager layoutManager = new LinearLayoutManager(main);
        listRestaurant.setLayoutManager(layoutManager);
        listRestaurant.setHasFixedSize(true);



        resAdapter = new RestaurantAdapter(30, this);
        listRestaurant.setAdapter(resAdapter);
        listRestaurant.setVisibility(View.VISIBLE);
        resAdapter.setmRestaurantData(myBranches);

    }

    @Override
    public void onListItemClick(int clickedItemIndex){
        // entrar al single view de un restaurant

    }
}
