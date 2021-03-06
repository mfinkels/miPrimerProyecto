package com.morfando.android.morfando.Class;

import android.app.ProgressDialog;
import android.content.Context;
import android.icu.text.LocaleDisplayNames;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Range;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.morfando.android.morfando.Interface.asyncTaskCompleted;
import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;
import com.morfando.android.morfando.Reservation.Adapter.reservationAdapter;
import com.morfando.android.morfando.Restaurant.Adapter.lvRestaurantAdapter;
import com.morfando.android.morfando.Restaurant.Single.Adapter.calificationAdapter;
import com.morfando.android.morfando.Restaurant.Single.Adapter.serviceAdapter;
import com.morfando.android.morfando.Restaurant.Single.Adapter.socialNetworkAdapter;
import com.morfando.android.morfando.Restaurant.Single.Adapter.timetableAdapter;
import com.morfando.android.morfando.Restaurant.Single.restaurantSingleFrag;
import com.morfando.android.morfando.Restaurant.lvRestaurantFrag;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Matias on 7/14/2017.
 */

public class ParseQuery {

    public ParseQuery(Context usedContext) {
        this.context = usedContext;
    }

    private Context context;

    private static final String server = "http://appmorfando.azurewebsites.net/api/";

    private static final MediaType JSON = MediaType.parse("application/json");


    ParsingObjects parse = new ParsingObjects();
    CreateObjects create = new CreateObjects();



    // Query for log User


    public void setUser(User user) {
        this.user = user;
    }

    private User user = new User();

    public void setUserIsLogged(Boolean userIsLogged) {
        this.userIsLogged = userIsLogged;
    }

    private Boolean userIsLogged;

    public void logUser(String email, String password, asyncTaskCompleted listener) {
        new LogInUser(listener).execute(email, password);
    }

    private class LogInUser extends AsyncTask<String, Void, User> {

        private asyncTaskCompleted<Object> listener;

        public LogInUser(asyncTaskCompleted listener){
            this.listener = listener;
        }

        protected void onPostExecute(User datos) {
            super.onPostExecute(datos);
            if (datos != null){
                userIsLogged = true;
            } else {
                userIsLogged = false;
            }
            Log.d("userIsLogged estado", userIsLogged + "");
            user = datos;
            Log.d("user", user + "");
            listener.onPostAsyncTask(datos);
        }

        @Override
        protected User doInBackground(String... parametros) {
            String email = parametros[0];
            String password = parametros[1];


            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(server + "user/LogInUser/" + email + "/" + password)
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


    // Query for List Branches


    public void getAllBranch(int limit, int offset, asyncTaskCompleted listener){
        new BranchGetAll(listener).execute("branch/", limit + "", offset + "");
    }

    private class BranchGetAll extends AsyncTask<String, Void, ArrayList<Branch>> {

        private asyncTaskCompleted listener;

        public BranchGetAll(asyncTaskCompleted listener) {
            this.listener = listener;
        }

        @Override
        protected void onPostExecute(ArrayList<Branch> datos) {
            super.onPostExecute(datos);
            listener.onPostAsyncTask(datos);
        }

        @Override
        protected ArrayList<Branch> doInBackground(String... parametros) {
            String url = parametros[0] + parametros[1] + "/" + parametros[2];

            ArrayList<Branch> branchList = new ArrayList<Branch>();

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(server + url)
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

                    b = parse.branch(obj);
                    //Restaurant
                    JSONObject restoObj = obj.getJSONObject("restaurant");
                    b.restaurant = parse.restaurant(restoObj);

                    // Range Price
                    JSONObject rangeObj = obj.getJSONObject("RangePrice");
                    b.range = parse.rangePrice(rangeObj);

                    //Photos
                    JSONArray photo = obj.getJSONArray("photo");
                    b.photo = parse.photoBranch(photo);

                    // Cuisine
                    JSONArray cuisine = obj.getJSONArray("cuisine");
                    b.cuisine = parse.cuisine(cuisine);

                    //Filter
                    JSONArray filter = obj.getJSONArray("filter");
                    b.filter = parse.filter(filter);

                    //Promotion
                    JSONArray promotion = obj.getJSONArray("promotion");
                    b.promotion = parse.promotion(promotion);

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


    // Query for get Detail Branch


    public void getBranch(int id, asyncTaskCompleted listener){
        new GetBranchById(listener).execute(id);
    }

    private class GetBranchById extends AsyncTask<Integer, Void, Branch> {

        private asyncTaskCompleted listener;
        public GetBranchById(asyncTaskCompleted listener) {
            this.listener = listener;
        }

        protected void onPostExecute(Branch datos) {
            super.onPostExecute(datos);
            listener.onPostAsyncTask(datos);
        }

        @Override
        protected Branch doInBackground(Integer... parametros) {
            int id = parametros[0];

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(server + "branch/" + id)
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

                b = parse.branch(obj);

                //Restaurant
                JSONObject resto = obj.getJSONObject("restaurant");
                b.restaurant = parse.restaurant(resto);

                //Social Network
                JSONArray socialNetwork = resto.getJSONArray("socialNetwork");
                b.restaurant.social = parse.socialNetwork(socialNetwork);

                //Photos
                JSONArray photo = obj.getJSONArray("photo");
                b.photo = parse.photoBranch(photo);

                //Service
                JSONArray service = obj.getJSONArray("service");
                b.service = parse.service(service);

                //Calification
                JSONArray calification = obj.getJSONArray("calification");
                b.calification = parse.calificationBranch(calification);

                //Timetable
                JSONArray timetable = obj.getJSONArray("timetable");
                b.timetable = parse.timetable(timetable);

                //Promotion
                JSONArray promotion = obj.getJSONArray("promotion");
                b.promotion = parse.promotion(promotion);
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

    public void setAdapter(calificationAdapter adapter) {
        this.adapterCali = adapter;
    }

    private calificationAdapter adapterCali;

    public void getBranchCalification(int id, int limit, int offset){
        new GetCalificationBranch().execute(id,limit,offset);
    }

    private class GetCalificationBranch extends AsyncTask<Integer, Void, ArrayList<CalificationBranch>> {

        protected void onPostExecute(ArrayList<CalificationBranch> datos) {
            super.onPostExecute(datos);
            adapterCali.setData(datos);
            adapterCali.notifyDataSetChanged();
        }

        @Override
        protected ArrayList<CalificationBranch> doInBackground(Integer... parametros) {
            int id = parametros[0];
            int limit = parametros[1];
            int offset = parametros[2];


            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(server + id +"/"+ limit +"/"+ offset)
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
                JSONArray calification = new JSONArray(resultado);
                return parse.calificationBranch(calification);
            }
            catch(JSONException e){
                Log.d("error", e.getMessage());
                return null;
            }
        }
    }

    // Create Reservation in Restaurants

    public void createReservation(Reservation res, asyncTaskCompleted listener){
        new insertReservation(listener).execute(res);
    }

    private class insertReservation extends AsyncTask<Reservation, Void, Boolean>{

        private asyncTaskCompleted listener;

        public insertReservation(asyncTaskCompleted listener){
            this.listener = listener;
        }

        protected void onPostExecute(Boolean datos) {
            super.onPostExecute(datos);
            listener.onPostAsyncTask(datos);
        }

        @Override
        protected Boolean doInBackground(Reservation... params) {
            Reservation res = params[0];

            OkHttpClient client = new OkHttpClient();

            String insert = create.reservation(res);
            Log.d("reservation", insert);

            RequestBody body = RequestBody.create(JSON, insert);
            Request request = new Request.Builder()
                    .url(server + "reservation")
                    .post(body)
                    .build();
            try {
                Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                String respuesta = response.body().string();
                Log.d("reservation", respuesta);
                if (respuesta != "Datos incorrectos."){
                    return true;
                }
                return false;
            } catch (IOException e) {
                Log.d("error", e.getMessage());             // Error de Network
                return false;
            }
        }
    }

    // Query Get Reservation list

    public void getListReservation(int idUser, String type, asyncTaskCompleted listener){
        new getReservations(listener).execute(String.valueOf(idUser), type);
    }

    private class getReservations extends AsyncTask<String, Void, ArrayList<Reservation>> {

        private asyncTaskCompleted listener;

        public getReservations(asyncTaskCompleted listener) {
            this.listener = listener;
        }

        protected void onPostExecute(ArrayList<Reservation> datos) {
            super.onPostExecute(datos);
            listener.onPostAsyncTask(datos);
        }

        @Override
        protected ArrayList<Reservation> doInBackground(String... parametros) {
            String id = parametros[0];
            String type = parametros[1];
            String actionResult;

            if (type.compareTo("past") == 0){
                actionResult = "GetReservationPastByUser";

            }else {
                actionResult = "GetReservationUpcomingByUser";

            }

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(server + "reservation/" + actionResult + "/" + type + "/" + id)
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
                //Reservation
                JSONArray arr = new JSONArray(resultado);
                return parse.reservation(arr);
            }
            catch(JSONException e){
                Log.d("error", e.getMessage());
                return null;
            }
        }
    }

    // query get Menu


    public void getMenuInfo(int idBranch, asyncTaskCompleted listener){
        new GetMenu(listener).execute(idBranch);
    }

    private class GetMenu extends AsyncTask<Integer, Void, ArrayList<Menu>> {

        private asyncTaskCompleted listener;

        public GetMenu(asyncTaskCompleted listener) {
            this.listener = listener;
        }

        @Override
        protected void onPostExecute(ArrayList<Menu> datos) {
            super.onPostExecute(datos);
            listener.onPostAsyncTask(datos);
        }

        @Override
        protected ArrayList<Menu> doInBackground(Integer... params) {
            int id = params[0];

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(server + "branch/menu/" + id)
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
                //Reservation
                JSONArray arr = new JSONArray(resultado);
                return parse.menu(arr);
            }
            catch(JSONException e){
                Log.d("error", e.getMessage());
                return null;
            }
        }
    }



    // query get Plates Order

    public void getOrder(int idReservation, asyncTaskCompleted listener){
        new getOrderPlates(listener).execute(idReservation);
    }

    private class getOrderPlates extends AsyncTask<Integer, Void, ArrayList<Plate>> {

        private asyncTaskCompleted listener;

        public getOrderPlates(asyncTaskCompleted listener) {
            this.listener = listener;
        }

        @Override
        protected void onPostExecute(ArrayList<Plate> datos) {
            super.onPostExecute(datos);
            Log.d("order", datos.toString());
            listener.onPostAsyncTask(datos);
        }

        @Override
        protected ArrayList<Plate> doInBackground(Integer... params) {
            int id = params[0];

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(server + id + "/orders")
                    .build();

            String resultado;
            try {
                Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                resultado = response.body().string();
                Log.d("order", resultado);
            } catch (IOException e) {
                Log.d("error", e.getMessage());             // Error de Network
                return null;
            }

            try {
                //Reservation
                JSONArray arr = new JSONArray(resultado);
                return parse.plate(arr);
            }
            catch(JSONException e){
                Log.d("error", e.getMessage());
                return null;
            }
        }
    }


    // query insert Order

    public void createOrder(ArrayList<OrderReservation> listOrders, asyncTaskCompleted listener) {
        new insertOrder(listener).execute(listOrders);
    }

    private class insertOrder extends AsyncTask<ArrayList<OrderReservation>, Void, Boolean>{

        private asyncTaskCompleted listener;

        public insertOrder(asyncTaskCompleted listener){
            this.listener = listener;
        }

        protected void onPostExecute(Boolean datos) {
            super.onPostExecute(datos);
            listener.onPostAsyncTask(datos);
        }

        @Override
        protected Boolean doInBackground(ArrayList<OrderReservation>... params) {
            ArrayList<OrderReservation> list = params[0];

            OkHttpClient client = new OkHttpClient();

            String insert = create.order(list);
            Log.d("order", insert);


            RequestBody body = RequestBody.create(JSON, insert);
            Request request = new Request.Builder()
                    .url(server + "reservation/order")
                    .post(body)
                    .build();
            try {
                Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                String respuesta = response.body().string();
                Log.d("order", respuesta);
                if (respuesta != "Datos incorrectos."){
                    return true;
                }
                return false;
            } catch (IOException e) {
                Log.d("error", e.getMessage());             // Error de Network
                return false;
            }
        }
    }


    // query create Calification Plate

    public void createCalificationPlate(CalificationPlate cali, asyncTaskCompleted listener){
        new insertCalificationPlate(listener).execute(cali);
    }

    private class insertCalificationPlate extends AsyncTask<CalificationPlate, Void, Boolean>{

        private asyncTaskCompleted listener;

        public insertCalificationPlate(asyncTaskCompleted listener){
            this.listener = listener;
        }

        protected void onPostExecute(Boolean datos) {
            super.onPostExecute(datos);
            listener.onPostAsyncTask(datos);
        }

        @Override
        protected Boolean doInBackground(CalificationPlate... params) {
            CalificationPlate cali = params[0];

            OkHttpClient client = new OkHttpClient();

            String insert = create.calificationPlate(cali);
            Log.d("calificationPlate", insert);

            RequestBody body = RequestBody.create(JSON, insert);
            Request request = new Request.Builder()
                    .url(server + "reservation/PostPlateCalification")
                    .post(body)
                    .build();
            try {
                Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                String respuesta = response.body().string();
                if (respuesta.compareTo("Error Cali") != 0){
                    return true;
                }
                return false;
            } catch (IOException e) {
                Log.d("error", e.getMessage());             // Error de Network
                return false;
            }
        }
    }

    //Query Get Branch with Filter

    public void getFilterBranch(int cuisine, int service, int rating, asyncTaskCompleted listener){
        new BranchFilter(listener).execute(cuisine + "", service + "",rating + "");
    }

    private class BranchFilter extends AsyncTask<String, Void, ArrayList<Branch>> {

        private asyncTaskCompleted listener;

        public BranchFilter(asyncTaskCompleted listener) {
            this.listener = listener;
        }

        @Override
        protected void onPostExecute(ArrayList<Branch> datos) {
            super.onPostExecute(datos);
            listener.onPostAsyncTask(datos);
        }

        @Override
        protected ArrayList<Branch> doInBackground(String... parametros) {
            String url = parametros[0]+ "/" + parametros[1] + "/" + parametros[2];

            ArrayList<Branch> branchList = new ArrayList<Branch>();

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(server + "branch/" + url)
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

                    b = parse.branch(obj);
                    //Restaurant
                    JSONObject restoObj = obj.getJSONObject("restaurant");
                    b.restaurant = parse.restaurant(restoObj);

                    // Range Price
                    JSONObject rangeObj = obj.getJSONObject("RangePrice");
                    b.range = parse.rangePrice(rangeObj);

                    //Photos
                    JSONArray photo = obj.getJSONArray("photo");
                    b.photo = parse.photoBranch(photo);

                    // Cuisine
                    JSONArray cuisine = obj.getJSONArray("cuisine");
                    b.cuisine = parse.cuisine(cuisine);

                    //Filter
                    JSONArray filter = obj.getJSONArray("filter");
                    b.filter = parse.filter(filter);

                    //Promotion
                    JSONArray promotion = obj.getJSONArray("promotion");
                    b.promotion = parse.promotion(promotion);

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


    //Query Get Services

    public void getServices(asyncTaskCompleted listener){
        new GetServices(listener).execute();
    }

    private class GetServices extends AsyncTask<String, Void, ArrayList<Service>> {

        private asyncTaskCompleted listener;

        public GetServices(asyncTaskCompleted listener) {
            this.listener = listener;
        }

        @Override
        protected void onPostExecute(ArrayList<Service> datos) {
            super.onPostExecute(datos);
            listener.onPostAsyncTask(datos);
        }

        @Override
        protected ArrayList<Service> doInBackground(String... parametros) {


            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(server + "branch/service")
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
                JSONArray jsonArr = new JSONArray(resultado);

                return parse.service(jsonArr);
            }
            catch (JSONException e) {
                Log.d("error", e.getMessage());
                return null;
            }
        }

    }


    //Query Get Cuisine

    public void getCuisine(asyncTaskCompleted listener){
        new GetCuisine(listener).execute();
    }

    private class GetCuisine extends AsyncTask<String, Void, ArrayList<Cuisine>> {

        private asyncTaskCompleted listener;

        public GetCuisine(asyncTaskCompleted listener) {
            this.listener = listener;
        }

        @Override
        protected void onPostExecute(ArrayList<Cuisine> datos) {
            super.onPostExecute(datos);
            listener.onPostAsyncTask(datos);
        }

        @Override
        protected ArrayList<Cuisine> doInBackground(String... parametros) {


            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(server + "branch/cousine")
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
                JSONArray jsonArr = new JSONArray(resultado);

                return parse.cuisine(jsonArr);
            }
            catch (JSONException e) {
                Log.d("error", e.getMessage());
                return null;
            }
        }

    }



}