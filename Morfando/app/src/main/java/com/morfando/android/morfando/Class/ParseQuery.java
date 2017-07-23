package com.morfando.android.morfando.Class;

import android.icu.text.LocaleDisplayNames;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Range;
import android.widget.Toast;

import com.morfando.android.morfando.Restaurant.Adapter.lvRestaurantAdapter;
import com.morfando.android.morfando.Restaurant.lvRestaurantFrag;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Matias on 7/14/2017.
 */

public class ParseQuery {

    private class ParsingObjects{

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
                b.averageCalification = json.getInt("averageCalification");
                b.averageFood = json.getInt("averageFood");
                b.averageService = json.getInt("averageService");
                b.averageAmbience = json.getInt("averageAmbience");
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
                return mn;

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

        public Service service(JSONObject json){
            try {
                Service sv = new Service();
                sv.idService = json.getInt("idService");
                sv.name = json.getString("name");
                return sv;
            }catch (JSONException e){
                Log.d("Error", e.getMessage());
                return null;
            }
        }

        public CalificationBranch calificationBranch(JSONObject json){
            try {
                CalificationBranch cali = new CalificationBranch();
                cali.idCalification = json.getInt("idCalification");
                cali.ambience = json.getInt("ambience");
                cali.food  = json.getInt("food");
                cali.service   = json.getInt("service");
                cali.averageCalification = json.getDouble("averageCalification");
                cali.message = json.getString("message");
                cali.date = Utility.convertStringToCalendar(json.getString("date"));;
                cali.user = user(json);
                JSONObject typeDining = json.getJSONObject("typeDining");
                cali.typeDining = typeDining.getString("name");

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

        public Timetable timetable(JSONObject json){
            try {
                Timetable tt = new Timetable();
                tt.idTimetable = json.getInt("idTimetableBranch");
                tt.openingHours = json.getString("openingHour");
                tt.closingHours = json.getString("closingHour");
                JSONObject dayObj = json.getJSONObject("day");
                tt.Day = dayObj.getString("name");
                return tt;
            }catch (JSONException e){
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



    }

    ParsingObjects parse = new ParsingObjects();

    // Query for List Branches

    private ArrayList<Branch> branches = new ArrayList<Branch>();

    public ArrayList<Branch> getAllBranch(int limit, int offset){
        new BranchGetAll().execute("http://apimorfandoort.azurewebsites.net/api/branch/", limit + "", offset + "");
        return branches;
    }

    private class BranchGetAll extends AsyncTask<String, Void, ArrayList<Branch>> {

        protected void onPostExecute(ArrayList<Branch> datos) {
            super.onPostExecute(datos);
            branches = datos;
        }

        @Override
        protected ArrayList<Branch> doInBackground(String... parametros) {
            String url = parametros[0] + parametros[1] + "/" + parametros[2];

            ArrayList<Branch> branchList = new ArrayList<Branch>();

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            String resultado;
            try {
                Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                resultado = response.body().string();
            } catch (IOException e) {
                Log.d("Error",e.getMessage());             // Error de Network
                return null;
            }

            try{
                JSONArray jsonBranch = new JSONArray(resultado);



                for (int i = 0; i < jsonBranch.length(); i++){
                    Branch b = new Branch();
                    JSONObject obj = jsonBranch.getJSONObject(i);
                    b.idBranch = obj.getInt("idBranchRestaurant");
                    JSONObject resto = obj.getJSONObject("restaurant");
                    b.restaurant.idRestaurant = resto.getInt("idRestaurant");
                    b.restaurant.name = resto.getString("name");
                    b.restaurant.description = resto.getString("description");
                    JSONArray socialNetwork = resto.getJSONArray("socialNetwork");
                    ArrayList<SocialNetwork> list = new ArrayList<SocialNetwork>();
                    for (int j = 0; j < socialNetwork.length(); j++){
                        JSONObject social = socialNetwork.getJSONObject(j);
                        SocialNetwork sn = new SocialNetwork();
                        sn.idSocialNetwork = social.getInt("idSocialNetworkRestaurant");
                        JSONObject typeSocial = social.getJSONObject("type");
                        sn.name = typeSocial.getString("name");
                        sn.value = social.getString("value");
                        list.add(sn);
                    }
                    b.restaurant.social = list;
                    JSONObject rangeObj = obj.getJSONObject("RangePrice");
                    b.range.idRangePrice = rangeObj.getInt("idRangePriceBranch");
                    b.range.maximum = rangeObj.getInt("maximum");
                    b.range.minimum = rangeObj.getInt("minimum");
                    b.name = obj.getString("name");
                    JSONArray photo = obj.getJSONArray("photo");
                    ArrayList<PhotoBranch> listPhoto = new ArrayList<PhotoBranch>();
                    for (int k = 0; k < photo.length(); k++){
                        JSONObject photoObj = photo.getJSONObject(k);
                        PhotoBranch p = new PhotoBranch();
                        p.idPhoto = photoObj.getInt("idBranchPhoto");
                        p.user = parse.user(photoObj);
                        p.photo = photoObj.getString("photo");
                        listPhoto.add(p);
                    }
                    b.photo = listPhoto;
                    JSONArray cuisine = obj.getJSONArray("cuisine");
                    ArrayList<Cuisine> listCuisine = new ArrayList<Cuisine>();
                    for (int c = 0; c < cuisine.length(); c++){
                        JSONObject cuisineObj = cuisine.getJSONObject(c);
                        Cuisine cn = new Cuisine();
                        cn.idCuisine = cuisineObj.getInt("idCousine");
                        cn.name = cuisineObj.getString("name");
                        listCuisine.add(cn);
                    }
                    b.cuisine = listCuisine;
                    JSONArray menu = obj.getJSONArray("menu");
                    ArrayList<Menu> listMenu = new ArrayList<Menu>();
                    for (int m = 0; m < menu.length(); m++){
                        JSONObject menuObj = menu.getJSONObject(m);
                        Menu mn = new Menu();
                        mn.idMenu = menuObj.getInt("idTypeMenu");
                        mn.type = menuObj.getString("name");
                        listMenu.add(mn);
                    }
                    b.menu = listMenu;
                    JSONArray filter = obj.getJSONArray("filter");
                    ArrayList<Filter> listFilter = new ArrayList<Filter>();
                    for (int f = 0; f < filter.length(); f++){
                        JSONObject filterObj = filter.getJSONObject(f);
                        Filter ft = new Filter();
                        ft.idFilter = filterObj.getInt("idTypeFilter");
                        ft.name = filterObj.getString("name");
                        ft.icon = filterObj.getString("icon");
                        listFilter.add(ft);
                    }
                    b.filter = listFilter;
                    JSONArray service = obj.getJSONArray("service");
                    ArrayList<Service> listService = new ArrayList<Service>();
                    for (int s = 0; s < service.length(); s++) {
                        JSONObject svObj = service.getJSONObject(s);
                        Service sv = new Service();
                        sv.idService = svObj.getInt("idService");
                        sv.name = svObj.getString("name");
                        listService.add(sv);
                    }
                    b.service = listService;
                    JSONArray timetable = obj.getJSONArray("timetable");
                    ArrayList<Timetable> listTimetable = new ArrayList<Timetable>();
                    for(int t = 0; t < timetable.length(); t++) {
                        JSONObject ttObj = timetable.getJSONObject(t);
                        Timetable tt = new Timetable();
                        tt.idTimetable = ttObj.getInt("idTimetableBranch");
                        tt.openingHours = ttObj.getString("openingHour");
                        tt.closingHours = ttObj.getString("closingHour");
                        JSONObject dayObj = ttObj.getJSONObject("day");
                        tt.Day = dayObj.getString("name");
                        listTimetable.add(tt);
                    }
                    b.timetable = listTimetable;
                    b.latitude = obj.getString("latitude");
                    b.longitude = obj.getString("longitude");
                    b.averageCalification = obj.getInt("averageCalification");
                    JSONArray promotion = obj.getJSONArray("promotion");
                    ArrayList<Promotion> listPromotion = new ArrayList<Promotion>();
                    for (int p = 0; p < promotion.length(); p++){
                        JSONObject pObj = promotion.getJSONObject(p);
                        Promotion prom = new Promotion();
                        prom.idPromotion = pObj.getInt("idPromotion");
                        prom.code = pObj.getString("code");
                        prom.name = pObj.getString("name");
                        prom.startDate = Utility.convertStringToCalendar(pObj.getString("startDate"));
                        prom.expireDate = Utility.convertStringToCalendar(pObj.getString("expireDate"));
                        prom.description = pObj.getString("description");
                        prom.value = pObj.getInt("value");
                        JSONObject type = pObj.getJSONObject("type");
                        prom.TypePromotion = type.getString("name");
                        listPromotion.add(prom);
                    }
                    b.promotion = listPromotion;
                    branchList.add(b);
                }
                return branchList;
            }
            catch (JSONException e) {
                Log.d("error", e.getMessage());
                return null;
            }
        }

    }


    // Query for log User

    private User userLogged = new User();

    public User logUser(String email, String password){
        new LogInUser().execute(email, password);
        return userLogged;
    }

    private class LogInUser extends AsyncTask<String, Void, User> {

        protected void onPostExecute(User datos) {
            super.onPostExecute(datos);
            userLogged = datos;
        }

        @Override
        protected User doInBackground(String... parametros) {
            String email = parametros[0];
            String password = parametros[1];


            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://apimorfandoort.azurewebsites.net/api/user/" + email + "/" + password)
                    .build();
            try {
                Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                String resultado = response.body().string();
                try {

                    JSONObject json = new JSONObject(resultado);
                    User myUser = parse.user(json);

                    return myUser;
                }
                catch (JSONException e){
                    Log.d("Error JSON",e.getMessage());
                    return null;
                }
            } catch (IOException e) {
                Log.d("Error",e.getMessage());             // Error de Network
                return null;
            }
        }

    }

    // Query for get Detail Branch

    private Branch b = new Branch();

    public Branch getBranch(int id){
        new GetBranchById().execute(id);
        return b;
    }

    private class GetBranchById extends AsyncTask<Integer, Void, Branch> {

        protected void onPostExecute(Branch datos) {
            super.onPostExecute(datos);
            b = datos;
        }

        @Override
        protected Branch doInBackground(Integer... parametros) {
            int id = parametros[0];

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://apimorfandoort.azurewebsites.net/api/branch/" + id)
                    .build();
            String resultado;
            try {
                Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                resultado = response.body().string();
            } catch (IOException e) {
                Log.d("error", e.getMessage());             // Error de Network
                return null;
            }

            try {
                Branch b = new Branch();

                JSONObject obj = new JSONObject(resultado);
                b.idBranch = obj.getInt("idBranchRestaurant");
                //Restaurant
                JSONObject resto = obj.getJSONObject("restaurant");
                b.restaurant.idRestaurant = resto.getInt("idRestaurant");
                b.restaurant.name = resto.getString("name");
                b.restaurant.description = resto.getString("description");

                //Social Network
                JSONArray socialNetwork = resto.getJSONArray("socialNetwork");
                ArrayList<SocialNetwork> list = new ArrayList<SocialNetwork>();
                for (int j = 0; j < socialNetwork.length(); j++) {
                    JSONObject social = socialNetwork.getJSONObject(j);
                    SocialNetwork sn = new SocialNetwork();
                    sn.idSocialNetwork = social.getInt("idSocialNetworkRestaurant");
                    JSONObject typeSocial = social.getJSONObject("type");
                    sn.name = typeSocial.getString("name");
                    sn.value = social.getString("value");
                    list.add(sn);
                }
                b.restaurant.social = list;

                // Range Price
                JSONObject rangeObj = obj.getJSONObject("RangePrice");
                b.range.idRangePrice = rangeObj.getInt("idRangePriceBranch");
                b.range.maximum = rangeObj.getInt("maximum");
                b.range.minimum = rangeObj.getInt("minimum");

                //Name
                b.name = obj.getString("name");

                //Photos
                JSONArray photo = obj.getJSONArray("photo");
                ArrayList<PhotoBranch> listPhoto = new ArrayList<PhotoBranch>();
                for (int k = 0; k < photo.length(); k++) {
                    JSONObject photoObj = photo.getJSONObject(k);
                    PhotoBranch p = new PhotoBranch();
                    p.idPhoto = photoObj.getInt("idBranchPhoto");
                    p.idUser = photoObj.getInt("idUser");
                    p.photo = photoObj.getString("photo");
                    listPhoto.add(p);
                }
                b.photo = listPhoto;

                // Cuisine
                JSONArray cuisine = obj.getJSONArray("cuisine");
                ArrayList<Cuisine> listCuisine = new ArrayList<Cuisine>();
                for (int c = 0; c < cuisine.length(); c++) {
                    JSONObject cuisineObj = cuisine.getJSONObject(c);
                    Cuisine cn = new Cuisine();
                    cn.idCuisine = cuisineObj.getInt("idCousine");
                    cn.name = cuisineObj.getString("name");
                    listCuisine.add(cn);
                }
                b.cuisine = listCuisine;

                //Menu
                JSONArray menu = obj.getJSONArray("menu");
                ArrayList<Menu> listMenu = new ArrayList<Menu>();
                for (int m = 0; m < menu.length(); m++) {
                    JSONObject menuObj = menu.getJSONObject(m);
                    Menu mn = new Menu();
                    mn.idMenu = menuObj.getInt("idTypeMenu");
                    mn.type = menuObj.getString("name");
                    listMenu.add(mn);
                }
                b.menu = listMenu;

                //Filter
                JSONArray filter = obj.getJSONArray("filter");
                ArrayList<Filter> listFilter = new ArrayList<Filter>();
                for (int f = 0; f < filter.length(); f++) {
                    JSONObject filterObj = filter.getJSONObject(f);
                    Filter ft = new Filter();
                    ft.idFilter = filterObj.getInt("idTypeFilter");
                    ft.name = filterObj.getString("name");
                    ft.icon = filterObj.getString("icon");
                    listFilter.add(ft);
                }
                b.filter = listFilter;

                //Service
                JSONArray service = obj.getJSONArray("service");
                ArrayList<Service> listService = new ArrayList<Service>();
                for (int s = 0; s < service.length(); s++) {
                    JSONObject svObj = service.getJSONObject(s);
                    Service sv = new Service();
                    sv.idService = svObj.getInt("idService");
                    sv.name = svObj.getString("name");
                    listService.add(sv);
                }
                b.service = listService;

                //Calification
                JSONArray calification = obj.getJSONArray("calification");
                ArrayList<CalificationBranch> listcalification = new ArrayList<CalificationBranch>();
                for (int c = 0; c < calification.length(); c++) {
                    JSONObject caliObj = calification.getJSONObject(c);
                    CalificationBranch cali = new CalificationBranch();
                    cali.idCalification = caliObj.getInt("idCalification");
                    cali.ambience = caliObj.getInt("ambience");
                    cali.food  = caliObj.getInt("food");
                    cali.service   = caliObj.getInt("service");
                    cali.averageCalification = caliObj.getDouble("averageCalification");
                    cali.message = caliObj.getString("message");
                }
                b.calification = listcalification;

                //Timetable
                JSONArray timetable = obj.getJSONArray("timetable");
                ArrayList<Timetable> listTimetable = new ArrayList<Timetable>();
                for (int t = 0; t < timetable.length(); t++) {
                    JSONObject ttObj = timetable.getJSONObject(t);
                    Timetable tt = new Timetable();
                    tt.idTimetable = ttObj.getInt("idTimetableBranch");
                    tt.openingHours = ttObj.getString("openingHour");
                    tt.closingHours = ttObj.getString("closingHour");
                    JSONObject dayObj = ttObj.getJSONObject("day");
                    tt.Day = dayObj.getString("name");
                    listTimetable.add(tt);
                }
                b.timetable = listTimetable;

                // Latitude and Longitude
                b.latitude = obj.getString("latitude");
                b.longitude = obj.getString("longitude");

                //Average Calification
                b.averageCalification = obj.getInt("averageCalification");

                //Promotion
                JSONArray promotion = obj.getJSONArray("promotion");
                ArrayList<Promotion> listPromotion = new ArrayList<Promotion>();
                for (int p = 0; p < promotion.length(); p++) {
                    JSONObject pObj = promotion.getJSONObject(p);
                    Promotion prom = new Promotion();
                    prom.idPromotion = pObj.getInt("idPromotion");
                    prom.code = pObj.getString("code");
                    prom.name = pObj.getString("name");
                    prom.startDate = Utility.convertStringToCalendar(pObj.getString("startDate"));
                    prom.expireDate = Utility.convertStringToCalendar(pObj.getString("expireDate"));
                    prom.description = pObj.getString("description");
                    prom.value = pObj.getInt("value");
                    JSONObject type = pObj.getJSONObject("type");
                    prom.TypePromotion = type.getString("name");
                    listPromotion.add(prom);
                }
                b.promotion = listPromotion;
                return b;
            }
            catch(JSONException e){
                Log.d("error", e.getMessage());
                return null;
            }
        }
    }

    // Get calification from specific Branch

    private ArrayList<CalificationBranch> calificationBranch = new ArrayList<CalificationBranch>();

    public ArrayList<CalificationBranch> getBranchCalification(int id, int limit, int offset){
        new GetCalificationBranch().execute(id,limit,offset);
        return  calificationBranch;
    }

    private class GetCalificationBranch extends AsyncTask<Integer, Void, ArrayList<CalificationBranch>> {

        protected void onPostExecute(ArrayList<CalificationBranch> datos) {
            super.onPostExecute(datos);
            calificationBranch = datos;
        }

        @Override
        protected ArrayList<CalificationBranch> doInBackground(Integer... parametros) {
            int id = parametros[0];
            int limit = parametros[1];
            int offset = parametros[3];


            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://apimorfandoort.azurewebsites.net/api/"+ id +"/"+ limit +"/"+ offset)
                    .build();
            String resultado;
            try {
                Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                resultado = response.body().string();
            } catch (IOException e) {
                Log.d("error", e.getMessage());             // Error de Network
                return null;
            }

            try {

                //Calification
                JSONObject obj = new JSONObject(resultado);
                JSONArray calification = obj.getJSONArray("calification");
                ArrayList<CalificationBranch> listcalification = new ArrayList<CalificationBranch>();
                for (int c = 0; c < calification.length(); c++) {
                    JSONObject caliObj = calification.getJSONObject(c);
                    CalificationBranch cali = new CalificationBranch();
                    cali.idCalification = caliObj.getInt("idCalification");
                    cali.ambience = caliObj.getInt("ambience");
                    cali.food  = caliObj.getInt("food");
                    cali.service   = caliObj.getInt("service");
                    cali.averageCalification = caliObj.getDouble("averageCalification");
                    cali.message = caliObj.getString("message");
                    cali.date;
                    cali.user = parse.user(caliObj);
                    JSONObject typeDining = caliObj.getJSONObject("typeDining");
                    cali.typeDining = typeDining.getString("name");
                    JSONArray photos = caliObj.getJSONArray("photo");
                    for (int p = 0; p < photos.length(); p++){
                        JSONObject photoObj = photos.getJSONObject(p);
                        PhotoBranch photo = new PhotoBranch();
                        photo.idPhoto = photoObj.getInt("idPhoto");
                        photo.photo = photoObj.getString("photo");
                        photo.user = cali.user;
                    }

                }
                return listcalification;


            }
            catch(JSONException e){
                Log.d("error", e.getMessage());
                return null;
            }
        }
    }



}
