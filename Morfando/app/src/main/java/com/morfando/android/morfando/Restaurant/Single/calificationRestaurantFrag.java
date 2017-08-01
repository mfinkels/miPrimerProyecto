package com.morfando.android.morfando.Restaurant.Single;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.morfando.android.morfando.Main2Activity;
import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;
import com.morfando.android.morfando.Restaurant.Single.Adapter.calificationAdapter;

/**
 * Created by Matias on 6/30/2017.
 */

public class calificationRestaurantFrag extends Fragment {

    Main2Activity main2;

    ListView calificationList;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_calification_restaurant, group, false);
        main2 = (Main2Activity)getActivity();

        calificationList = (ListView)toReturn.findViewById(R.id.calificationSingleLV);
        calificationAdapter adapterCali = new calificationAdapter(main2.getCalification(), main2);
        calificationList.setAdapter(adapterCali);

        return toReturn;
    }

}
