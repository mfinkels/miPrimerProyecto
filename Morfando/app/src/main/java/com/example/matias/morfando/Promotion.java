package com.example.matias.morfando;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Matias on 5/12/2017.
 */

public class Promotion {

    public String name;
    public Date dateOfEnd;


    public ArrayList<Promotion> getAll() {
        ArrayList<Promotion> list;
        list = new ArrayList<>();
        Promotion myPromotion;
        myPromotion = new Promotion();
        myPromotion.name = "new promotion Example";
        myPromotion.dateOfEnd=new Date(2017,4,26);
        list.add(myPromotion);
        return list;
    }


}
