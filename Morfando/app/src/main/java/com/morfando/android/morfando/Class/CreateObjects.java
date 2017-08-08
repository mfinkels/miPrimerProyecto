package com.morfando.android.morfando.Class;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

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
            ObjJson.accumulate("date", obj.date);
            ObjJson.accumulate("guest", obj.guest);
            json = ObjJson.toString();
            return json;

        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

}
