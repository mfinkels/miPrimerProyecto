package com.morfando.android.morfando.Reservation;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;
import com.morfando.android.morfando.Restaurant.Single.informationRestaurantFrag;
import com.morfando.android.morfando.Restaurant.Single.menuRestaurantFrag;
import com.morfando.android.morfando.Restaurant.Single.restaurantSingleFrag;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Matias on 1/8/17.
 */

public class reservationMainFrag extends Fragment {

    MainActivity main;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_reservation_main, group, false);
        main = (MainActivity)getActivity();

        viewPager = (ViewPager) toReturn.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) toReturn.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        return toReturn;
    }

    private void setupViewPager(ViewPager viewPager) {
        reservationMainFrag.ViewPagerAdapter adapter = new reservationMainFrag.ViewPagerAdapter(main.getSupportFragmentManager());
        adapter.addFragment(new upcomingReservationFrag(), "Upcoming");
        adapter.addFragment(new pastReservationFrag(), "Past");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<android.support.v4.app.Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(android.support.v4.app.Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
