package com.morfando.android.morfando.Class;

import android.app.ProgressDialog;
import android.content.Context;
import android.icu.text.LocaleDisplayNames;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
            listener.onPostAsyncTask(userIsLogged);
        }

        @Override
        protected User doInBackground(String... parametros) {
            String email = parametros[0];
            String password = parametros[1];


            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(server + "user/LogInUser" + email + "/" + password)
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
    private lvRestaurantAdapter adapter;

    public void setAdapter(lvRestaurantAdapter adapter) {
        this.adapter = adapter;
    }


    public void getAllBranch(int limit, int offset){
        new BranchGetAll().execute("branch/", limit + "", offset + "");
    }

    private class BranchGetAll extends AsyncTask<String, Void, ArrayList<Branch>> {
        @Override
        protected void onPostExecute(ArrayList<Branch> datos) {
            super.onPostExecute(datos);
            adapter.setData(datos);
            adapter.notifyDataSetChanged();
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

    public void setBranch(Branch b) {
        this.b = b;
    }

    private Branch b = new Branch();

    public void setInformationRestaurantFrag(View informationRestaurantFrag) {
        this.informationRestaurantFrag = informationRestaurantFrag;
    }

    TextView description, food, service, ambience, typeFood, typeAmbience, typeService;
    GridView serviceGV;
    LinearLayout socialNetwork, promotion;

    ListView timetable, calification;

    Button address, moreCalification;

    private View informationRestaurantFrag;


    public void getBranch(int id){
        new GetBranchById().execute(id);
    }

    private void showInformation(Branch myBranch){
        description.setText(myBranch.restaurant.description);

        //si tiene socialNetwork
        if (myBranch.restaurant.social.size() > 0){

            TextView titleSocial = new TextView(context);
            titleSocial.setText("Social NetWork");

            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            titleSocial.setGravity(Gravity.CENTER);
            titleSocial.setTextSize(24);
            titleSocial.setLayoutParams(parms);
            socialNetwork.addView(titleSocial);

            GridView gridView= new GridView(context);

            gridView.setLayoutParams(new GridView.LayoutParams(GridLayout.LayoutParams.FILL_PARENT, GridLayout.LayoutParams.FILL_PARENT));
            gridView.setNumColumns(myBranch.restaurant.social.size());
            gridView.setColumnWidth(GridView.AUTO_FIT);
            final socialNetworkAdapter adapterSocial = new socialNetworkAdapter(context, myBranch.restaurant.social);
            gridView.setAdapter(adapterSocial);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                }
            });


            socialNetwork.addView(gridView);

        }



        serviceAdapter adapterService = new serviceAdapter(context, myBranch.service);
        serviceGV.setAdapter(adapterService);

        timetableAdapter adapterTimetable = new timetableAdapter(myBranch.timetable, context);
        timetable.setAdapter(adapterTimetable);

        // si tiene promotion
        if(myBranch.promotion.size() > 0){

            TextView titlePromotion = new TextView(context);
            titlePromotion.setText("Promotion");

            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            titlePromotion.setGravity(Gravity.CENTER);
            titlePromotion.setTextSize(24);
            titlePromotion.setLayoutParams(parms);
            promotion.addView(titlePromotion);

            ListView promotionList = new ListView(context);
            promotionList.setLayoutParams(parms);

            //promotionAdapter adapterPromotion = new promotionAdapter();
            //promotionList.setAdapter(adapterPromotion);

        }


        food.setText(myBranch.averageFood + "");
        service.setText(myBranch.averageService + "");
        ambience.setText(myBranch.averageAmbience + "");

        calificationAdapter adapterCalification = new calificationAdapter(myBranch.calification, context);
        calification.setAdapter(adapterCalification);
    }

    private class GetBranchById extends AsyncTask<Integer, Void, Branch> {

        protected void onPostExecute(Branch datos) {
            super.onPostExecute(datos);

            description = (TextView)informationRestaurantFrag.findViewById(R.id.descriptionSingle);
            serviceGV = (GridView) informationRestaurantFrag.findViewById(R.id.gridviewService);
            timetable = (ListView) informationRestaurantFrag.findViewById(R.id.timetableLV);
            food = (TextView)informationRestaurantFrag.findViewById(R.id.foodCalification);
            service = (TextView)informationRestaurantFrag.findViewById(R.id.serviceCalification);
            ambience = (TextView)informationRestaurantFrag.findViewById(R.id.ambienceCalification);
            calification = (ListView)informationRestaurantFrag.findViewById(R.id.listCalification);
            promotion = (LinearLayout)informationRestaurantFrag.findViewById(R.id.conteinerPromotion);
            socialNetwork = (LinearLayout)informationRestaurantFrag.findViewById(R.id.socialNetworkConteiner);

            showInformation(datos);
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

                //Social Network
                JSONObject resto = obj.getJSONObject("restaurant");
                JSONArray socialNetwork = resto.getJSONArray("socialNetwork");
                b.restaurant.social = parse.socialNetwork(socialNetwork);

                //Photos
                JSONArray photo = obj.getJSONArray("photo");
                b.photo = parse.photoBranch(photo);

                //Menu
                JSONArray menu = obj.getJSONArray("menu");
                b.menu = parse.menu(menu);

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
    private Boolean reservtionCreated;

    public boolean createReservation(Reservation res){
        new insertReservation().execute(res);
        return reservtionCreated;
    }

    private class insertReservation extends AsyncTask<Reservation, Void, Boolean>{



        protected void onPostExecute(Boolean datos) {
            super.onPostExecute(datos);
            reservtionCreated = datos;
        }

        @Override
        protected Boolean doInBackground(Reservation... params) {
            Reservation res = params[0];

            OkHttpClient client = new OkHttpClient();

            RequestBody body = RequestBody.create(JSON, create.reservation(res));
            Request request = new Request.Builder()
                    .url(server)
                    .post(body)
                    .build();
            try {
                Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                if (response.body().string() == ""){
                    return true;
                }
                return false;
            } catch (IOException e) {
                Log.d("error", e.getMessage());             // Error de Network
                return false;
            }
        }
    }


    private ArrayList<Reservation> listReservation = new ArrayList<Reservation>();

    public ArrayList<Reservation> getListReservation(int idUser, String type){
        new getReservations().execute(String.valueOf(idUser), type);
        return listReservation;
    }

    private class getReservations extends AsyncTask<String, Void, ArrayList<Reservation>> {

        protected void onPostExecute(ArrayList<Reservation> datos) {
            super.onPostExecute(datos);
            listReservation = datos;
        }

        @Override
        protected ArrayList<Reservation> doInBackground(String... parametros) {
            String id = parametros[0];
            String type = parametros[1];


            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(server + "reservation/" + type + "/" + id)
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
                JSONObject obj = new JSONObject(resultado);
                JSONArray reservation = obj.getJSONArray("reservation");
                return parse.reservation(reservation);
            }
            catch(JSONException e){
                Log.d("error", e.getMessage());
                return null;
            }
        }
    }



}