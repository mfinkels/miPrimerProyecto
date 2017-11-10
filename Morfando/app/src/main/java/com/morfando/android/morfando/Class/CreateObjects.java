<<<<<<< HEAD
package com.morfando.android.morfando.Class;

import android.icu.text.SimpleDateFormat;
import android.util.Log;

import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Matias on 8/7/2017.
 */

public class CreateObjects {

    public String reservation(Reservation obj){
        String json = "";
        try {
            JSONObject ObjJson = new JSONObject();
            ObjJson.accumulate("idUser", obj.idUser);
            ObjJson.accumulate("idBranchRestaurant", obj.branch.idBranch);
            String datetime = obj.date.get(Calendar.YEAR) + "-" + (obj.date.get(Calendar.MONTH)+1) + "-" + obj.date.get(Calendar.DAY_OF_MONTH) + " " + obj.date.get(Calendar.HOUR_OF_DAY) + ":" + obj.date.get(Calendar.MINUTE) + ":00";
            ObjJson.accumulate("date", datetime);
            ObjJson.accumulate("guest", obj.guest);
            json = ObjJson.toString();
            Log.d("reservation obj", json);
            return json;

        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public String order(OrderReservation obj){
        String json = "";
        try {
            JSONObject ObjJson = new JSONObject();
            ObjJson.accumulate("idReservation", obj.idReservation);
            ObjJson.accumulate("idPlateMenu", obj.idPlate);
            ObjJson.accumulate("idUser", obj.idUser);
            json = ObjJson.toString();
            Log.d("order obj", json);
            return json;

        }catch (JSONException e){
            Log.d("Error order", e.getMessage());
            return null;
        }
    }

    public String order(ArrayList<OrderReservation> arr){
        try {
            String JSONArray = "[";
            //JSONArray array = new JSONArray();
            for (int i = 0; i < arr.size(); i++){
                OrderReservation order = arr.get(i);
                String orderJson = order(order);
                if (i != arr.size()-1){
                    JSONArray += orderJson + ",";
                } else {
                    JSONArray += orderJson + "]";
                }
            }

            /*for (OrderReservation order : arr) {
                String orderJson = order(order);
                array.put(orderJson);
            }*/

            return JSONArray;

        }catch (Exception e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }


}
=======
package com.morfando.android.morfando.Class;

import android.icu.text.SimpleDateFormat;
import android.util.Log;

import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Matias on 8/7/2017.
 */

public class CreateObjects {

    public String reservation(Reservation obj){
        String json = "";
        try {
            JSONObject ObjJson = new JSONObject();
            ObjJson.accumulate("idUser", obj.idUser);
            ObjJson.accumulate("idBranchRestaurant", obj.branch.idBranch);
            String datetime = obj.date.get(Calendar.YEAR) + "-" + (obj.date.get(Calendar.MONTH)+1) + "-" + obj.date.get(Calendar.DAY_OF_MONTH) + " " + obj.date.get(Calendar.HOUR_OF_DAY) + ":" + obj.date.get(Calendar.MINUTE) + ":00";
            ObjJson.accumulate("date", datetime);
            ObjJson.accumulate("guest", obj.guest);
            json = ObjJson.toString();
            Log.d("reservation obj", json);
            return json;

        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public String calificationPlate(CalificationPlate obj){
        String json = "";
        try {
            JSONObject ObjJson = new JSONObject();
            ObjJson.accumulate("idCalificationPlate", obj.idCalificationPlate);
            ObjJson.accumulate("idBranchRestaurant", obj.idBranch);
            ObjJson.accumulate("idUser", obj.idUser);
            ObjJson.accumulate("idPlateMenu", obj.idPlate);
            ObjJson.accumulate("value", obj.value);
            ObjJson.accumulate("message", obj.message);
            json = ObjJson.toString();
            Log.d("calification obj", json);
            return json;

        }catch (JSONException e){
            Log.d("Error calification", e.getMessage());
            return null;
        }
    }

    public String order(OrderReservation obj){
        String json = "";
        try {
            JSONObject ObjJson = new JSONObject();
            ObjJson.accumulate("idReservation", obj.idReservation);
            ObjJson.accumulate("idPlateMenu", obj.idPlate);
            ObjJson.accumulate("idUser", obj.idUser);
            json = ObjJson.toString();
            Log.d("order obj", json);
            return json;

        }catch (JSONException e){
            Log.d("Error order", e.getMessage());
            return null;
        }
    }

    public String order(ArrayList<OrderReservation> arr){
        try {
            String JSONArray = "[";
            //JSONArray array = new JSONArray();
            for (int i = 0; i < arr.size(); i++){
                OrderReservation order = arr.get(i);
                String orderJson = order(order);
                if (i != arr.size()-1){
                    JSONArray += orderJson + ",";
                } else {
                    JSONArray += orderJson + "]";
                }
            }

            /*for (OrderReservation order : arr) {
                String orderJson = order(order);
                array.put(orderJson);
            }*/

            return JSONArray;

        }catch (Exception e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }


}
>>>>>>> origin/master
