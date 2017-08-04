package com.morfando.android.morfando.Restaurant.Single;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;

/**
 * Created by Matias on 6/30/2017.
 */

public class menuRestaurantFrag extends Fragment{

    MainActivity main;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_menu_restaurant, group, false);
        main = (MainActivity)getActivity();

        return toReturn;
    }

}
