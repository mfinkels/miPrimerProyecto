package com.morfando.android.morfando.Restaurant.Single;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.morfando.android.morfando.Class.Branch;
import com.morfando.android.morfando.Class.Cuisine;
import com.morfando.android.morfando.Class.Filter;
import com.morfando.android.morfando.Class.Menu;
import com.morfando.android.morfando.Class.ParseQuery;
import com.morfando.android.morfando.Class.ParsingObjects;
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
import com.morfando.android.morfando.Restaurant.Single.Adapter.calificationAdapter;
import com.morfando.android.morfando.Restaurant.Single.Adapter.serviceAdapter;
import com.morfando.android.morfando.Restaurant.Single.Adapter.socialNetworkAdapter;
import com.morfando.android.morfando.Restaurant.Single.Adapter.timetableAdapter;
import com.morfando.android.morfando.Restaurant.Single.calificationRestaurantFrag;
import com.morfando.android.morfando.Restaurant.Single.informationRestaurantFrag;
import com.morfando.android.morfando.Restaurant.Single.menuRestaurantFrag;
import com.morfando.android.morfando.Restaurant.lvRestaurantFrag;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Matias on 6/30/2017.
 */

public class restaurantSingleFrag extends DialogFragment {

    MainActivity main;
    TextView name, range, cousine;
    RatingBar calification;
    ImageView imgResto;

    ParseQuery pq;

    Button reserve, info, menu;

    FragmentManager adminFragment;
    FragmentTransaction trans;


    private Branch myBranch;


    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.restaurant_single_frag, group, false);
        main = (MainActivity)getActivity();
        pq = new ParseQuery(main);

        adminFragment = main.getSupportFragmentManager();

        myBranch = main.getBranch();

        name = (TextView)toReturn.findViewById(R.id.nameSingle);
        cousine = (TextView) toReturn.findViewById(R.id.cousineSingle);
        calification = (RatingBar)toReturn.findViewById(R.id.calificationSingle);
        range = (TextView)toReturn.findViewById(R.id.priceRangeSingle);
        imgResto =(ImageView)toReturn.findViewById(R.id.imgRestaurant);
        reserve = (Button) toReturn.findViewById(R.id.btnReservation);
        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.makeReservation();
            }
        });

        info = (Button)toReturn.findViewById(R.id.btnInfo);
        menu =(Button)toReturn.findViewById(R.id.btnMenu);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoPressed();
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuPressed();
            }
        });

        if (myBranch.photo.size() >= 0){
            new DownloadImageTask(imgResto).execute(myBranch.photo.get(0).photo);
        }

        infoPressed();
        setInformation(myBranch);


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

    private void infoPressed(){
        info.setEnabled(false);
        menu.setEnabled(true);

        trans=adminFragment.beginTransaction();
        trans.replace(R.id.fragmentSingleRestaurant, new informationRestaurantFrag());
        trans.commit();
    }

    private void menuPressed(){
        menu.setEnabled(false);
        info.setEnabled(true);

        trans=adminFragment.beginTransaction();
        trans.replace(R.id.fragmentSingleRestaurant, new menuRestaurantFrag());
        trans.commit();
    }

    private void setInformation(Branch myBranch) {

        name.setText(myBranch.restaurant.name + " " + myBranch.name);
        Cuisine c = new Cuisine();
        cousine.setText(c.cousineList(myBranch.cuisine));

        calification.setRating(Float.parseFloat(myBranch.averageCalification + ""));

        range.setText("$" + String.valueOf(myBranch.range.minimum) + " - $" + String.valueOf(myBranch.range.maximum));
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
                return mIcon11;
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                return null;
            }

        }

        protected void onPostExecute(Bitmap result) {
            if (result != null){
                bmImage.setImageBitmap(result);
            }
        }
    }



}
