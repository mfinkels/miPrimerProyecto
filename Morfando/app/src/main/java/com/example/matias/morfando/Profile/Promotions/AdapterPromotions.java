package com.example.matias.morfando.Profile.Promotions;

import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.matias.morfando.Class.*;
import com.example.matias.morfando.Class.Promotion;
import com.example.matias.morfando.R;

import java.util.ArrayList;

/**
 * Created by Matias on 5/12/2017.
 */

public class AdapterPromotions extends BaseAdapter{

    private ArrayList<Promotion> promotions;
    private Context myContext;

    public AdapterPromotions (ArrayList<Promotion> listPromotions, Context usedContext) {
        promotions = listPromotions;
        myContext = usedContext;
    }

    public int getCount() {
        return promotions.size();
    }

    public Promotion getItem(int position) {
        Promotion myPromotion = new Promotion();
        myPromotion = promotions.get(position);
        return myPromotion;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int positionActual, View viewActual, ViewGroup groupActual) {

        View returnView;
        returnView = null;


        LayoutInflater inflater;
        inflater=(LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        returnView = inflater.inflate(R.layout.listview_promotions_item, groupActual, false);

        TextView codePromotion;
        codePromotion = (TextView)returnView.findViewById(R.id.codePromotion);

        String codeFromActualPosition;
        codeFromActualPosition = getItem(positionActual).name;
        codePromotion.setText(codeFromActualPosition);


        TextView textDuration;
        textDuration = (TextView)returnView.findViewById(R.id.textDuration);

        String textDurationFromActualPosition;
        textDurationFromActualPosition = "Valido hasta" + " " + getItem(positionActual).dateOfEnd.toString();
        textDuration.setText(textDurationFromActualPosition);

        return returnView;
    }

}
