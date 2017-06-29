package com.morfando.android.morfando.Restaurant;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonIOException;
import com.morfando.android.morfando.Class.Branch;
import com.morfando.android.morfando.Class.Cuisine;
import com.morfando.android.morfando.Class.Filter;
import com.morfando.android.morfando.Class.Menu;
import com.morfando.android.morfando.Class.Promotion;
import com.morfando.android.morfando.Class.Restaurant;
import com.morfando.android.morfando.Class.Service;
import com.morfando.android.morfando.Class.User;
import com.morfando.android.morfando.Class.PhotoBranch;
import com.morfando.android.morfando.Class.SocialNetwork;
import com.morfando.android.morfando.Class.Timetable;
import com.morfando.android.morfando.Class.Utility;
import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;
import com.morfando.android.morfando.Restaurant.Adapter.RestaurantAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Matias on 6/18/2017.
 */

public class restaurantFrag extends Fragment implements RestaurantAdapter.ListItemClickListener {
    MainActivity main;
    private RecyclerView listRestaurant;
    private RestaurantAdapter resAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {;
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_restaurant, group, false);
        main = (MainActivity)getActivity();
        listRestaurant = (RecyclerView) toReturn.findViewById(R.id.rv_restaurant);
        loadRestaurantData();

        return toReturn;
    }

    private void loadRestaurantData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(main);
        listRestaurant.setLayoutManager(layoutManager);
        listRestaurant.setHasFixedSize(true);

        resAdapter = new RestaurantAdapter(30, this);
        listRestaurant.setAdapter(resAdapter);

        new BranchGetAll().execute("http://apimorfandoort.azurewebsites.net/api/branch/1/0");
    }

    @Override
    public void onListItemClick(int clickedItemIndex){
        // entrar al single view de un restaurant
    }


    private class BranchGetAll extends AsyncTask<String, Void, ArrayList<Branch>> {

        protected void onPostExecute(ArrayList<Branch> datos) {
            super.onPostExecute(datos);
            if (datos != null){
                listRestaurant.setVisibility(View.VISIBLE);
                resAdapter.setmRestaurantData(datos);

            } else {
                Toast.makeText(main,"Error connection database", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected ArrayList<Branch> doInBackground(String... parametros) {
            String url = parametros[0];

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try {
                Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                String resultado = response.body().string();

                try{
                    ArrayList<Branch> branchList = new ArrayList<>();
                    JSONArray jsonBranch = new JSONArray(resultado);

                    Branch b = new Branch();

                    for (int i = 0; i < jsonBranch.length(); i++){
                        JSONObject obj = jsonBranch.getJSONObject(i);
                        b.idBranch = obj.getInt("idBranchRestaurant");
                        JSONObject resto = obj.getJSONObject("restaurant");
                        //aca ha un error
                        b.restaurant.idRestaurant = resto.getInt("idRestaurant");
                        b.restaurant.name = resto.getString("name");
                        b.restaurant.description = resto.getString("description");
                        JSONArray socialNetwork = resto.getJSONArray("socialNetwork");
                        ArrayList<SocialNetwork> list = new ArrayList<SocialNetwork>();
                        for (int j = 0; j < socialNetwork.length(); j++){
                            JSONObject social = socialNetwork.getJSONObject(j);
                            SocialNetwork sn = new SocialNetwork();
                            sn.idSocialNetwork = social.getInt("idSocialNetwork");
                            sn.name = social.getString("name");
                            sn.value = social.getString("value");
                            list.add(sn);
                        }
                        b.restaurant.social = list;
                        JSONObject rangeObj = obj.getJSONObject("RangePrice");
                        b.range.idRangePrice = rangeObj.getInt("idRangePrice");
                        b.range.maximum = rangeObj.getInt("maximum");
                        b.range.minimum = rangeObj.getInt("minimum");
                        b.name = obj.getString("name");
                        JSONArray photo = obj.getJSONArray("photo");
                        ArrayList<PhotoBranch> listPhoto = new ArrayList<PhotoBranch>();
                        for (int k = 0; k < photo.length(); k++){
                            JSONObject photoObj = photo.getJSONObject(k);
                            PhotoBranch p = new PhotoBranch();
                            p.idPhoto = photoObj.getInt("idBranchPhoto");
                            p.idUser = photoObj.getInt("idUser");
                            p.photo = photoObj.getString("photo");
                            listPhoto.add(p);
                        }
                        b.photo = listPhoto;
                        JSONArray cuisine = obj.getJSONArray("cuisine");
                        ArrayList<Cuisine> listCuisine = new ArrayList<Cuisine>();
                        for (int c = 0; c < cuisine.length(); c++){
                            JSONObject cuisineObj = cuisine.getJSONObject(c);
                            Cuisine cn = new Cuisine();
                            cn.idCuisine = cuisineObj.getInt("idCuisine");
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
                        }
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
                            tt.idTimetable = ttObj.getInt("");
                            tt.openingHours = ttObj.getString("");
                            tt.closingHours = ttObj.getString("");
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
                }catch (JSONException e) {
                    Log.d("error", e.getMessage());
                    String a = "puta";
                    return null;
                }



            } catch (IOException e) {
                Log.d("Error",e.getMessage());             // Error de Network
                return null;
            }
        }

    }


}
