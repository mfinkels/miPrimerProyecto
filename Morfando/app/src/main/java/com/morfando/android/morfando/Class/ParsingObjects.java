package com.morfando.android.morfando.Class;

import android.provider.ContactsContract;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Matias on 8/7/2017.
 */

public class ParsingObjects {

    //Consultas para parsear JSON

    public User user(JSONObject json){
        try{
            User myUser = new User();
            myUser.idUser = json.getInt("idUser");
            myUser.name = json.getString("name");
            myUser.lastName = json.getString("lastName");
            myUser.password = json.getString("password");
            myUser.latitude = json.getString("latitude");
            myUser.longitude = json.getString("longitude");
            myUser.photo = json.getString("photo");
            myUser.phone = json.getString("phone");
            myUser.email = json.getString("email");

            return myUser;

        }catch (JSONException e){
            Log.d("Error JSON",e.getMessage());
            return null;
        }
    }

    public Branch branch(JSONObject json){
        try {
            Branch b = new Branch();
            b.idBranch = json.getInt("idBranchRestaurant");
            b.name = json.getString("name");
            b.latitude = json.getString("latitude");
            b.longitude = json.getString("longitude");
            b.averageCalification = json.getDouble("averageCalification");
            b.averageFood = json.getDouble("averageFood");
            b.averageService = json.getDouble("averageService");
            b.averageAmbience = json.getDouble("averageAmbience");
            return b;

        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public Restaurant restaurant(JSONObject json){
        try{
            Restaurant r = new Restaurant();
            r.idRestaurant = json.getInt("idRestaurant");
            r.name = json.getString("name");
            r.description = json.getString("description");
            return r;
        }
        catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }

    }

    public SocialNetwork socialNetwork(JSONObject json){
        try{
            SocialNetwork sn = new SocialNetwork();
            sn.idSocialNetwork = json.getInt("idSocialNetworkRestaurant");
            JSONObject typeSocial = json.getJSONObject("type");
            sn.name = typeSocial.getString("name");
            sn.value = json.getString("value");
            return sn;
        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public RangePrice rangePrice(JSONObject json){
        try{
            RangePrice r = new RangePrice();
            r.idRangePrice = json.getInt("idRangePriceBranch");
            r.maximum = json.getInt("maximum");
            r.minimum = json.getInt("minimum");
            return r;
        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public PhotoBranch photoBranch(JSONObject json){
        try {
            PhotoBranch p = new PhotoBranch();
            p.idPhoto = json.getInt("idBranchPhoto");
            p.user = user(json);
            p.photo = json.getString("photo");
            return p;

        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public Cuisine cuisine(JSONObject json){
        try{
            Cuisine cn = new Cuisine();
            cn.idCuisine = json.getInt("idCousine");
            cn.name = json.getString("name");
            return cn;
        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public Menu menu(JSONObject json){
        try {
            Menu mn = new Menu();
            mn.idMenu = json.getInt("idTypeMenu");
            mn.type = json.getString("name");
            JSONArray array = json.getJSONArray("plates");
            mn.plates = plate(array);
            Log.d("Menu", mn.toString());
            return mn;

        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public Plate plate(JSONObject json){
        try {
            Plate p = new Plate();
            p.idPlate = json.getInt("idPlateMenu");
            JSONObject category = json.getJSONObject("category");
            p.CategoryPlate = category.getString("name");
            p.averageCalification = json.getInt("averageCalification");
            JSONArray arrayCali = json.getJSONArray("calification");
            p.calification = calificationPlate(arrayCali);
            p.name = json.getString("name");
            p.price = json.getDouble("price");
            p.description = json.getString("description");
            JSONArray arrayPhotos = json.getJSONArray("photo");
            p.photos = photoPlate(arrayPhotos);
            return p;

        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public CalificationPlate calificationPlate(JSONObject json){
        try {
            CalificationPlate cali = new CalificationPlate();
            cali.idCalificationPlate = json.getInt("idCalificationPlate");
            cali.idBranch = json.getInt("idBranchRestaurant");
            cali.idUser = json.getInt("idUser");
            cali.value = json.getInt("value");
            cali.message = json.getString("message");
            return cali;
        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public PhotoPlate photoPlate(JSONObject json){
        try {
            PhotoPlate photoPlate = new PhotoPlate();
            photoPlate.idPhotoPlate = json.getInt("idPhotoPlate");
            photoPlate.idPlate = json.getInt("idPhotoPlate");
            photoPlate.idUser = json.getInt("idUser");
            photoPlate.photo = json.getString("photo");
            return photoPlate;
        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public Filter filter(JSONObject json){
        try {
            Filter ft = new Filter();
            ft.idFilter = json.getInt("idTypeFilter");
            ft.name = json.getString("name");
            ft.icon = json.getString("icon");
            return ft;
        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public Service service(JSONObject json) {
        try {
            Service sv = new Service();
            sv.idService = json.getInt("idService");
            sv.name = json.getString("name");
            return sv;
        } catch (JSONException e) {
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public CalificationBranch calificationBranch(JSONObject json){
        try {
            CalificationBranch cali = new CalificationBranch();
            cali.idCalification = json.getInt("idCalificationBranch");
            cali.ambience = json.getInt("ambience");
            cali.food  = json.getInt("food");
            cali.service   = json.getInt("service");
            cali.message = json.getString("message");
            cali.date = Utility.convertStringToCalendar(json.getString("date"));
            JSONObject user = json.getJSONObject("user");
            cali.user = user(user);
            if(!json.isNull("typeDining")){
                JSONObject typeDining = json.getJSONObject("typeDining");
                cali.typeDining = typeDining.getString("name");
            }

            JSONArray photosJSON = json.getJSONArray("photo");
            ArrayList<PhotoBranch> photos = new ArrayList<PhotoBranch>();
            for (int p = 0; p < photosJSON.length(); p++){
                JSONObject obj = photosJSON.getJSONObject(p);
                photos.add(photoBranch(obj));
            }
            cali.photo = photos;

            return cali;

        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public Timetable timetable(JSONObject json) {
        try {
            Timetable tt = new Timetable();
            tt.idTimetable = json.getInt("idTimetableBranch");
            tt.openingHours = json.getString("openingHour");
            tt.closingHours = json.getString("closingHour");
            JSONObject dayObj = json.getJSONObject("day");
            tt.Day = dayObj.getString("name");
            return tt;
        } catch (JSONException e) {
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public Promotion promotion(JSONObject json){
        try {
            Promotion prom = new Promotion();
            prom.idPromotion = json.getInt("idPromotion");
            prom.code = json.getString("code");
            prom.name = json.getString("name");
            prom.startDate = Utility.convertStringToCalendar(json.getString("startDate"));
            prom.expireDate = Utility.convertStringToCalendar(json.getString("expireDate"));
            prom.description = json.getString("description");
            prom.value = json.getInt("value");
            JSONObject type = json.getJSONObject("type");
            prom.TypePromotion = type.getString("name");
            return prom;

        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public Reservation reservation(JSONObject json){
        try {
            Reservation res = new Reservation();
            res.idReservation = json.getInt("idReservation");
            res.idUser = json.getInt("idUser");
            JSONObject branch = json.getJSONObject("branch");
            res.branch = branch(branch);
            JSONObject resto = branch.getJSONObject("restaurant");
            res.branch.restaurant = restaurant(resto);
            res.date = Utility.convertStringToCalendar(json.getString("date"));
            res.guest = json.getInt("guest");
            res.orders = null;
            return res;

        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }



    //Return Lists

    public ArrayList<SocialNetwork> socialNetwork(JSONArray jsonArray){
        try {
            ArrayList<SocialNetwork> arrayList = new ArrayList<SocialNetwork>();
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                SocialNetwork sn = socialNetwork(obj);
                arrayList.add(sn);
            }
            return  arrayList;

        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public ArrayList<PhotoBranch> photoBranch(JSONArray jsonArray){
        try {
            ArrayList<PhotoBranch> arrayList = new ArrayList<PhotoBranch>();
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                PhotoBranch p = photoBranch(obj);
                arrayList.add(p);
            }
            return  arrayList;

        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public ArrayList<Cuisine> cuisine(JSONArray jsonArray){
        try {
            ArrayList<Cuisine> arrayList = new ArrayList<Cuisine>();
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                Cuisine c = cuisine(obj);
                arrayList.add(c);
            }
            return  arrayList;

        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public ArrayList<Menu> menu(JSONArray jsonArray){
        try {
            ArrayList<Menu> arrayList = new ArrayList<Menu>();
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                Menu m = menu(obj);
                arrayList.add(m);
            }
            return arrayList;

        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public ArrayList<Filter> filter(JSONArray jsonArray){
        try {
            ArrayList<Filter> arrayList = new ArrayList<Filter>();
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                Filter f = filter(obj);
                arrayList.add(f);
            }
            return  arrayList;

        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public ArrayList<Service> service(JSONArray jsonArray){
        try {
            ArrayList<Service> arrayList = new ArrayList<Service>();
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                Service s = service(obj);
                arrayList.add(s);
            }
            return  arrayList;

        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public ArrayList<CalificationBranch> calificationBranch(JSONArray jsonArray){
        try {
            ArrayList<CalificationBranch> arrayList = new ArrayList<CalificationBranch>();
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                CalificationBranch cali = calificationBranch(obj);
                arrayList.add(cali);
            }
            return  arrayList;

        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public ArrayList<Timetable> timetable(JSONArray jsonArray){
        try {
            ArrayList<Timetable> arrayList = new ArrayList<Timetable>();
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                Timetable tt = timetable(obj);
                arrayList.add(tt);
            }
            return  arrayList;

        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public ArrayList<Promotion> promotion(JSONArray jsonArray){
        try {
            ArrayList<Promotion> arrayList = new ArrayList<Promotion>();
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                Promotion p = promotion(obj);
                arrayList.add(p);
            }
            return  arrayList;

        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public ArrayList<Reservation> reservation(JSONArray jsonArray){
        try {
            ArrayList<Reservation> arrayList = new ArrayList<Reservation>();
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                Reservation res = reservation(obj);
                arrayList.add(res);
            }
            return  arrayList;

        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public ArrayList<Plate> plate(JSONArray jsonArray){
        try {
            ArrayList<Plate> arrayList = new ArrayList<Plate>();
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                Plate p = plate(obj);
                arrayList.add(p);
            }
            return  arrayList;

        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public ArrayList<PhotoPlate> photoPlate(JSONArray jsonArray){
        try {
            ArrayList<PhotoPlate> arrayList = new ArrayList<PhotoPlate>();
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                PhotoPlate pp = photoPlate(obj);
                arrayList.add(pp);
            }
            return  arrayList;

        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    public ArrayList<CalificationPlate> calificationPlate(JSONArray jsonArray){
        try {
            ArrayList<CalificationPlate> arrayList = new ArrayList<CalificationPlate>();
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                CalificationPlate cali = calificationPlate(obj);
                arrayList.add(cali);
            }
            return  arrayList;

        }catch (JSONException e){
            Log.d("Error", e.getMessage());
            return null;
        }
    }

}
