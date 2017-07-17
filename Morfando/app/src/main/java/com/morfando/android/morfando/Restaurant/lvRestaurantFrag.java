package com.morfando.android.morfando.Restaurant;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.morfando.android.morfando.Class.Branch;
import com.morfando.android.morfando.Class.Cuisine;
import com.morfando.android.morfando.Class.Filter;
import com.morfando.android.morfando.Class.Menu;
import com.morfando.android.morfando.Class.ParseQuery;
import com.morfando.android.morfando.Class.PhotoBranch;
import com.morfando.android.morfando.Class.Promotion;
import com.morfando.android.morfando.Class.Service;
import com.morfando.android.morfando.Class.SocialNetwork;
import com.morfando.android.morfando.Class.Timetable;
import com.morfando.android.morfando.Class.Utility;
import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;
import com.morfando.android.morfando.Restaurant.Adapter.lvRestaurantAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Matias on 6/29/2017.
 */

public class lvRestaurantFrag extends Fragment  implements AdapterView.OnItemClickListener{

    MainActivity main;
    ListView restaurantItems;
    lvRestaurantAdapter adapter;

    ArrayList<Branch> listBranches;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_restaurant_lv, group, false);
        main = (MainActivity)getActivity();

        restaurantItems = (ListView) toReturn.findViewById(R.id.lvRestaurantItems);
        ParseQuery pq = new ParseQuery();
        listBranches = pq.getAllBranch(10, 0);
        adapter = new lvRestaurantAdapter(listBranches, main);
        restaurantItems.setAdapter(adapter);
        restaurantItems.setOnItemClickListener(this);
        return toReturn;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int idBranch = adapter.getId(position);
        main.BranchSelected(idBranch);
    }
}
