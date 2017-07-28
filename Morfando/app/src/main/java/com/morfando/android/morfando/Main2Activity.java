package com.morfando.android.morfando;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.morfando.android.morfando.Restaurant.Single.restaurantSingleFrag;
import com.morfando.android.morfando.Restaurant.lvRestaurantFrag;
import com.morfando.android.morfando.Restaurant.restaurantFrag;

public class Main2Activity extends AppCompatActivity {

    FragmentManager adminFragment;
    FragmentTransaction trans;

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
}
