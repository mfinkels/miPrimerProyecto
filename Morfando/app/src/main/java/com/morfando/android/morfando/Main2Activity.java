package com.morfando.android.morfando;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.morfando.android.morfando.Class.Branch;
import com.morfando.android.morfando.Class.CalificationBranch;
import com.morfando.android.morfando.Class.ParseQuery;
import com.morfando.android.morfando.Class.Reservation;
import com.morfando.android.morfando.Class.User;
import com.morfando.android.morfando.Registration.resetPasswordFrag;
import com.morfando.android.morfando.Registration.signUpFrag;
import com.morfando.android.morfando.Restaurant.Single.calificationRestaurantFrag;
import com.morfando.android.morfando.Restaurant.Single.restaurantSingleFrag;
import com.morfando.android.morfando.Restaurant.lvRestaurantFrag;
import com.morfando.android.morfando.Restaurant.restaurantFrag;

import java.util.ArrayList;
import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {

    FragmentManager adminFragment;
    FragmentTransaction trans;

    ParseQuery pq;

    private Branch branch;

    private boolean userLoggedIn;
    private User myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Fragment singleRestaurant;
        singleRestaurant = new restaurantSingleFrag();

        trans=adminFragment.beginTransaction();
        trans.replace(R.id.fragmentConteinerFullPage, singleRestaurant);
        trans.commit();


    }

    public void viewPressed(View v) {
        switch (v.getId()){
            case R.id.btnMoreCalification:
                Fragment calification;
                calification = new calificationRestaurantFrag();
                updateFragment(calification);
                break;
        }
    }

    private void updateFragment(Fragment newFragment) {
        trans=adminFragment.beginTransaction();
        trans.replace(R.id.fragmentConteinerFullPage, newFragment);
        trans.commit();
    }




}
