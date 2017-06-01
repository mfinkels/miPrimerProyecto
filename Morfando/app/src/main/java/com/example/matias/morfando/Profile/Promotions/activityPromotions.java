package com.example.matias.morfando.Profile.Promotions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.example.matias.morfando.Class.*;
import com.example.matias.morfando.Class.Promotion;
import com.example.matias.morfando.R;

import java.util.ArrayList;

public class activityPromotions extends AppCompatActivity {

    ArrayList<Promotion> userPromotions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotions);

        Promotion myPromotion;
        myPromotion = new Promotion();
        userPromotions = myPromotion.getAll();

        ListView listViewPromotions;
        listViewPromotions = (ListView) findViewById(R.id.listPromotion);

        AdapterPromotions myAdapterPromotions;
        myAdapterPromotions = new AdapterPromotions(userPromotions, this);

        listViewPromotions.setAdapter(myAdapterPromotions);

    }
}
