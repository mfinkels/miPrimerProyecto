package com.example.matias.morfando;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Matias on 5/12/2017.
 */

public class Payment extends Activity {

    public Drawable iconCreditCard;
    public int numberCreditCard;
    public String holderCreditCard;
    public int validThru;
    public String opereted;

    public ArrayList<Payment> getAll() {

        ArrayList<Payment> list;
        list = new ArrayList<>();
        Payment myPayment;
        myPayment = new Payment();

        myPayment.holderCreditCard = "Matias Finkelstein";


        myPayment.iconCreditCard = getResources().getDrawable(R.drawable.mastercard);
        myPayment.numberCreditCard = 2345;
        myPayment.opereted = "MasterCard";
        myPayment.validThru = 1019;

        list.add(myPayment);
        return list;

    }
}
