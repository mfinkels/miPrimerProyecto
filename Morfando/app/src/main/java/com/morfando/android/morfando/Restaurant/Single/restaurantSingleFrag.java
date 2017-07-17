package com.morfando.android.morfando.Restaurant.Single;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.morfando.android.morfando.Class.PhotoBranch;
import com.morfando.android.morfando.Class.Promotion;
import com.morfando.android.morfando.Class.Service;
import com.morfando.android.morfando.Class.SocialNetwork;
import com.morfando.android.morfando.Class.Timetable;
import com.morfando.android.morfando.Class.Utility;
import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;
import com.morfando.android.morfando.Restaurant.Adapter.lvRestaurantAdapter;
import com.morfando.android.morfando.Restaurant.Single.calificationRestaurantFrag;
import com.morfando.android.morfando.Restaurant.Single.informationRestaurantFrag;
import com.morfando.android.morfando.Restaurant.Single.menuRestaurantFrag;

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
 * Created by Matias on 6/30/2017.
 */

public class restaurantSingleFrag extends Fragment {

    MainActivity main;
    TextView name, range, cousine, description,service;
    RatingBar calification;

    ParseQuery pq;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private Branch myBranch;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.restaurant_single_frag, group, false);
        main = (MainActivity)getActivity();
        pq = new ParseQuery();

        myBranch = main.getBranch();

        name = (TextView)toReturn.findViewById(R.id.nameSingle);
        name.setText(myBranch.restaurant.name + " " + myBranch.name);

        cousine = (TextView) toReturn.findViewById(R.id.cousineSingle);
        Cuisine c = new Cuisine();
        cousine.setText(c.cousineList(myBranch.cuisine));

        calification = (RatingBar)toReturn.findViewById(R.id.calificationSingle);
        calification.setRating(Float.parseFloat(myBranch.averageCalification + ""));

        range = (TextView)toReturn.findViewById(R.id.priceRangeSingle);
        range.setText("$" + String.valueOf(myBranch.range.minimum) + " - $" + String.valueOf(myBranch.range.maximum));

        viewPager = (ViewPager) toReturn.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) toReturn.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        return toReturn;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(main.getSupportFragmentManager());
        adapter.addFragment(new informationRestaurantFrag(), "Information");
        adapter.addFragment(new menuRestaurantFrag(), "Menu");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
