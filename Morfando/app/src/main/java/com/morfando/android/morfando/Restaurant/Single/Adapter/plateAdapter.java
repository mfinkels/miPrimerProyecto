package com.morfando.android.morfando.Restaurant.Single.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.morfando.android.morfando.Class.Branch;
import com.morfando.android.morfando.Class.Cuisine;
import com.morfando.android.morfando.Class.Menu;
import com.morfando.android.morfando.Class.Plate;
import com.morfando.android.morfando.Interface.getPlateToOrder;
import com.morfando.android.morfando.R;

import java.util.ArrayList;

/**
 * Created by Matias on 8/11/2017.
 */

public class plateAdapter  extends BaseAdapter{
    private ArrayList<Plate> plates;
    private Context myContext;
    private boolean reservation;
    private getPlateToOrder listener;

    public plateAdapter (ArrayList<Plate> listPlate, Context usedContext, boolean reservation, getPlateToOrder listener) {
        this.plates = listPlate;
        this.myContext = usedContext;
        this.reservation = reservation;
        this.listener = listener;
    }

    public void setData(ArrayList<Plate> plates) {
        this.plates=plates;
    }

    public int getCount() {
        return plates.size();
    }

    public Plate getItem(int position) {
        Plate p = plates.get(position);
        return p;
    }

    public long getItemId(int position) {
        Plate p = plates.get(position);
        return p.idPlate;
    }

    public int getId(int position) {
        Plate p = plates.get(position);
        return p.idPlate;
    }

    public View getView(final int positionActual, View viewActual, ViewGroup groupActual) {

        View returnView;


        LayoutInflater inflater;
        inflater=(LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        returnView = inflater.inflate(R.layout.plate_menu_item,groupActual,false);

        final Plate plate  = getItem(positionActual);

        TextView name, price, calification;

        name = (TextView)returnView.findViewById(R.id.plateName);
        price = (TextView)returnView.findViewById(R.id.platePrice);
        calification = (TextView)returnView.findViewById(R.id.plateAverageCalification);


        name.setText(plate.name);
        price.setText("$" + plate.price);
        calification.setText(plate.averageCalification + "");

        Button addCart= (Button)returnView.findViewById(R.id.btnCart);

        if (reservation){
            addCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        listener.onPostPlate(plate);
                    }

                }
            });

        } else {
            addCart.setVisibility(View.GONE);
        }

        return returnView;
    }

    private void addToCart(Plate plate) {

    }
}
