package com.morfando.android.morfando.Reservation;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;
import com.morfando.android.morfando.Restaurant.Single.informationRestaurantFrag;
import com.morfando.android.morfando.Restaurant.Single.menuRestaurantFrag;
import com.morfando.android.morfando.Restaurant.Single.restaurantSingleFrag;
import com.morfando.android.morfando.Restaurant.lvRestaurantFrag;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Matias on 1/8/17.
 */

public class reservationMainFrag extends Fragment {

    MainActivity main;

    Button upcoming, past;

    FragmentManager adminFragment;
    FragmentTransaction trans;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_reservation_main, group, false);
        main = (MainActivity)getActivity();

        upcoming = (Button)toReturn.findViewById(R.id.btnUpcoming);
        past = (Button)toReturn.findViewById(R.id.btnPast);
        upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upComingPressed();
            }
        });
        past.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pastPressed();
            }
        });

        adminFragment = main.getSupportFragmentManager();

        upComingPressed();

        return toReturn;
    }

    private void pastPressed() {
        upcoming.setEnabled(true);
        past.setEnabled(false);

        trans=adminFragment.beginTransaction();
        trans.replace(R.id.fragReservation, new pastReservationFrag());
        trans.commit();
    }

    private void upComingPressed() {
        upcoming.setEnabled(false);
        past.setEnabled(true);

        trans=adminFragment.beginTransaction();
        trans.replace(R.id.fragReservation, new upcomingReservationFrag());
        trans.commit();
    }
}
