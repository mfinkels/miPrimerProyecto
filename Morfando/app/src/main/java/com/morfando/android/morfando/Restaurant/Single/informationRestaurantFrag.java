package com.morfando.android.morfando.Restaurant.Single;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.morfando.android.morfando.Class.Branch;
import com.morfando.android.morfando.Class.SocialNetwork;
import com.morfando.android.morfando.Class.Timetable;
import com.morfando.android.morfando.Main2Activity;
import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;
import com.morfando.android.morfando.Restaurant.Adapter.lvRestaurantAdapter;
import com.morfando.android.morfando.Restaurant.Single.Adapter.calificationAdapter;
import com.morfando.android.morfando.Restaurant.Single.Adapter.promotionAdapter;
import com.morfando.android.morfando.Restaurant.Single.Adapter.serviceAdapter;
import com.morfando.android.morfando.Restaurant.Single.Adapter.socialNetworkAdapter;
import com.morfando.android.morfando.Restaurant.Single.Adapter.timetableAdapter;
import com.morfando.android.morfando.Restaurant.lvRestaurantFrag;

import java.util.ArrayList;

/**
 * Created by Matias on 6/30/2017.
 */

public class informationRestaurantFrag extends Fragment implements View.OnClickListener {

    MainActivity main;
    Main2Activity main2;

    TextView description, food, service, ambience, typeFood, typeAmbience, typeService;
    GridView serviceGV;
    LinearLayout socialNetwork, promotion;

    ListView timetable, calification;

    Button address, moreCalification;

    Branch myBranch;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_information_restaurant, group, false);
        main = (MainActivity)getActivity();
        main2 = (Main2Activity)getActivity();
        myBranch = main.getBranch();

        description = (TextView)toReturn.findViewById(R.id.descriptionSingle);
        description.setText(myBranch.restaurant.description);

        //si tiene socialNetwork
        if (myBranch.restaurant.social.size() > 0){
            socialNetwork = (LinearLayout)toReturn.findViewById(R.id.socialNetworkConteiner);

            TextView titleSocial = new TextView(main);
            titleSocial.setText("Social NetWork");

            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            titleSocial.setGravity(Gravity.CENTER);
            titleSocial.setTextSize(24);
            titleSocial.setLayoutParams(parms);
            socialNetwork.addView(titleSocial);

            GridView gridView= new GridView(main);

            gridView.setLayoutParams(new GridView.LayoutParams(GridLayout.LayoutParams.FILL_PARENT, GridLayout.LayoutParams.FILL_PARENT));
            gridView.setNumColumns(myBranch.restaurant.social.size());
            gridView.setColumnWidth(GridView.AUTO_FIT);
            final socialNetworkAdapter adapterSocial = new socialNetworkAdapter(main, myBranch.restaurant.social);
            gridView.setAdapter(adapterSocial);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    socialNetworkPressed(adapterSocial.getItem(position).value);
                }
            });


            socialNetwork.addView(gridView);

        }

        address = (Button)toReturn.findViewById(R.id.btnGetDirection);
        address.setOnClickListener(this);


        serviceGV = (GridView) toReturn.findViewById(R.id.gridviewService);
        serviceAdapter adapterService = new serviceAdapter(main, myBranch.service);
        serviceGV.setAdapter(adapterService);

        timetable = (ListView) toReturn.findViewById(R.id.timetableLV);
        timetableAdapter adapterTimetable = new timetableAdapter(myBranch.timetable, main);
        timetable.setAdapter(adapterTimetable);

        // si tiene promotion
        if(myBranch.promotion.size() > 0){
            promotion = (LinearLayout)toReturn.findViewById(R.id.conteinerPromotion);

            TextView titlePromotion = new TextView(main);
            titlePromotion.setText("Promotion");

            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            titlePromotion.setGravity(Gravity.CENTER);
            titlePromotion.setTextSize(24);
            titlePromotion.setLayoutParams(parms);
            promotion.addView(titlePromotion);

            ListView promotionList = new ListView(main);
            promotionList.setLayoutParams(parms);

            //promotionAdapter adapterPromotion = new promotionAdapter();
            //promotionList.setAdapter(adapterPromotion);

        }

        // se puede enlazar los types
        food = (TextView)toReturn.findViewById(R.id.foodCalification);
        service = (TextView)toReturn.findViewById(R.id.serviceCalification);
        ambience = (TextView)toReturn.findViewById(R.id.ambienceCalification);

        food.setText(myBranch.averageFood + "");
        service.setText(myBranch.averageService + "");
        ambience.setText(myBranch.averageAmbience + "");

        calification = (ListView)toReturn.findViewById(R.id.listCalification);
        calificationAdapter adapterCalification = new calificationAdapter(myBranch.calification, main);
        calification.setAdapter(adapterCalification);

        moreCalification = (Button)toReturn.findViewById(R.id.btnMoreCalification);
        moreCalification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.showAllCalification();
            }
        });


        return toReturn;
    }

    private void socialNetworkPressed(String value) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(value));
        startActivity(browserIntent);
    }

    public void onClick(View v){
        //Como llegar
    }
}
