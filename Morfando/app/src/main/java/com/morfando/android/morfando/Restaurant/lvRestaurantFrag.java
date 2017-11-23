<<<<<<< HEAD
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
import com.morfando.android.morfando.Class.test;
import com.morfando.android.morfando.Interface.asyncTaskCompleted;
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
    ParseQuery pq;

    ArrayList<Branch> listBranches = new ArrayList<Branch>();

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_restaurant_lv, group, false);
        main = (MainActivity)getActivity();
        pq = new ParseQuery(main);

        restaurantItems = (ListView) toReturn.findViewById(R.id.lvRestaurantItems);
        restaurantItems.setOnItemClickListener(this);


        asyncTaskCompleted listener = new asyncTaskCompleted() {
            @Override
            public void onPostAsyncTask(Object result) {
                ArrayList<Branch> branches = (ArrayList<Branch>) result;
                if (branches != null){
                    adapter = new lvRestaurantAdapter(branches, main);
                    restaurantItems.setAdapter(adapter);
                }else {
                    Toast.makeText(main,"Error loading Branches",Toast.LENGTH_SHORT);
                }
            }
        };


        pq.getAllBranch(10, 0, listener);
        return toReturn;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Branch branch = adapter.getItem(position);
        main.BranchSelected(branch);
    }
}
=======
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
import com.morfando.android.morfando.Interface.asyncTaskCompleted;
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
    ParseQuery pq;

    ArrayList<Branch> listBranches = new ArrayList<Branch>();

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_restaurant_lv, group, false);
        main = (MainActivity)getActivity();
        pq = new ParseQuery(main);

        restaurantItems = (ListView) toReturn.findViewById(R.id.lvRestaurantItems);
        restaurantItems.setOnItemClickListener(this);


        asyncTaskCompleted listener = new asyncTaskCompleted() {
            @Override
            public void onPostAsyncTask(Object result) {
                ArrayList<Branch> branches = (ArrayList<Branch>) result;
                if (branches != null){
                    adapter = new lvRestaurantAdapter(branches, main);
                    restaurantItems.setAdapter(adapter);
                }else {
                    Toast.makeText(main,"Error loading Branches",Toast.LENGTH_SHORT);
                }
            }
        };


        pq.getAllBranch(10, 0, listener);
        return toReturn;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Branch branch = adapter.getItem(position);
        main.BranchSelected(branch);
    }
}
>>>>>>> origin/master
