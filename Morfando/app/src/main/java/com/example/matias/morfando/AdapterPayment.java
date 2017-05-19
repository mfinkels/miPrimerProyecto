package com.example.matias.morfando;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Matias on 5/12/2017.
 */

public class AdapterPayment extends BaseAdapter {

    private ArrayList<Payment> payments;
    private Context myContext;

    public AdapterPayment (ArrayList<Payment> listPromotions, Context usedContext) {
        payments = listPromotions;
        myContext = usedContext;
    }

    public int getCount() {
        return payments.size();
    }

    public Payment getItem(int position) {
        Payment myPayment = new Payment();
        myPayment = payments.get(position);
        return myPayment;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int positionActual, View viewActual, ViewGroup groupActual) {

        View returnView;
        returnView = null;


        LayoutInflater inflater;
        inflater=(LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        returnView = inflater.inflate(R.layout.listview_payment_item, groupActual, false);


        // no encuentra la imagen ni imageView
        ImageView creditCardImage;
        creditCardImage = (ImageView) returnView.findViewById(R.id.creditCardImage);

        Drawable creditCardToActualPosition;
        creditCardToActualPosition = getItem(positionActual).iconCreditCard;

        creditCardImage.setImageDrawable(creditCardToActualPosition);


        TextView creditCardName;
        creditCardName = (TextView)returnView.findViewById(R.id.creditCardName);

        String creditCardNameToActualPosition;
        creditCardNameToActualPosition = getItem(positionActual).opereted;
        int numberCreditCardLastDigits = getItem(positionActual).numberCreditCard;


        creditCardName.setText(creditCardNameToActualPosition + " termina en " + numberCreditCardLastDigits);


        TextView textHolder;
        textHolder = (TextView)returnView.findViewById(R.id.textHolder);

        String textHolderToActualPosition;
        textHolderToActualPosition = getItem(positionActual).holderCreditCard;
        textHolder.setText(textHolderToActualPosition);

        TextView validThru;
        validThru = (TextView)returnView.findViewById(R.id.validThru);

        Date validThruToActualPosition;
        validThruToActualPosition = getItem(positionActual).validThru;
        textHolder.setText(validThruToActualPosition.toString());


        return returnView;
    }

    private int lastDigit(Long number) {
        int lastDigits = (int) (long) number % 10;
        return lastDigits;
    }

}
