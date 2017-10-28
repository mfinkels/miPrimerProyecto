package com.morfando.android.morfando.Class;

import java.util.ArrayList;

/**
 * Created by Matias on 6/26/2017.
 */

 public class Cuisine {
    public int idCuisine;
    public String name;

    public String cousineList(ArrayList<Cuisine> cuisines){
        String result = "";
        for (Cuisine c : cuisines) {
            if (result.length() != 0){
                result += "|" + c.name;
            }else {
                result = c.name;
            }
        }
        return result;
    }

}
