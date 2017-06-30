package com.morfando.android.morfando.Restaurant;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.morfando.android.morfando.Class.Branch;
import com.morfando.android.morfando.Class.Cuisine;
import com.morfando.android.morfando.Class.Filter;
import com.morfando.android.morfando.Class.Menu;
import com.morfando.android.morfando.Class.PhotoBranch;
import com.morfando.android.morfando.Class.Promotion;
import com.morfando.android.morfando.Class.Service;
import com.morfando.android.morfando.Class.SocialNetwork;
import com.morfando.android.morfando.Class.Timetable;
import com.morfando.android.morfando.Class.Utility;
import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;
import com.morfando.android.morfando.Restaurant.Adapter.lvRestaurantAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Matias on 6/30/2017.
 */

public class restaurantSingleFrag extends Fragment {

    MainActivity main;
    TextView name, range, description, cousine,service;
    RatingBar calification;


    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.restaurant_single_frag, group, false);
        main = (MainActivity)getActivity();
        name = (TextView)toReturn.findViewById(R.id.nameSingle);
        range = (TextView)toReturn.findViewById(R.id.priceRangeSingle);
        description = (TextView)toReturn.findViewById(R.id.descriptionRestaurant);
        // socialNetworks
        cousine = (TextView)toReturn.findViewById(R.id.cousineRestaurant);
        service = (TextView)toReturn.findViewById(R.id.ServicesRestaurant);
        calification = (RatingBar)toReturn.findViewById(R.id.calificationSingle);
        int id = getArguments().getInt("id");
        String url = "http://apimorfandoort.azurewebsites.net/api/branch/" + id;
        new GetBranchById().execute(url);



        return toReturn;
    }


    private class GetBranchById extends AsyncTask<String, Void, Branch> {

        protected void onPostExecute(Branch datos) {
            super.onPostExecute(datos);
            if (datos != null) {
                name.setText(datos.name);
                range.setText("$" + datos.range.minimum + " - $" + datos.range.maximum);
                description.setText(datos.restaurant.description);
                String cousines = "";
                for (Cuisine c : datos.cuisine) {
                    if (cousines.length() != 0){
                        cousines += "|" + c.name;
                    }else {
                        cousines = c.name;
                    }
                }
                cousine.setText(cousines);
                String services = "";
                for (Service s : datos.service) {
                    if (services.length() != 0){
                        services += "|" + s.name;
                    }else {
                        services = s.name;
                    }
                }
                service.setText(services);
                calification.setRating(datos.averageCalification);

            } else {
                Toast.makeText(main, "Error connection database", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected Branch doInBackground(String... parametros) {
            String url = parametros[0];

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            String resultado;
            try {
                Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                resultado = response.body().string();
            } catch (IOException e) {
                Log.d("Error", e.getMessage());             // Error de Network
                return null;
            }

            try {
                JSONArray jsonBranch = new JSONArray(resultado);

                Branch b = new Branch();

                JSONObject obj = jsonBranch.getJSONObject(0);
                b.idBranch = obj.getInt("idBranchRestaurant");
                JSONObject resto = obj.getJSONObject("restaurant");
                b.restaurant.idRestaurant = resto.getInt("idRestaurant");
                b.restaurant.name = resto.getString("name");
                b.restaurant.description = resto.getString("description");
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
                JSONObject rangeObj = obj.getJSONObject("RangePrice");
                b.range.idRangePrice = rangeObj.getInt("idRangePriceBranch");
                b.range.maximum = rangeObj.getInt("maximum");
                b.range.minimum = rangeObj.getInt("minimum");
                b.name = obj.getString("name");
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
                JSONArray menu = obj.getJSONArray("menu");
                ArrayList<Menu> listMenu = new ArrayList<Menu>();
                for (int m = 0; m < menu.length(); m++) {
                    JSONObject menuObj = menu.getJSONObject(m);
                    Menu mn = new Menu();
                    mn.idMenu = menuObj.getInt("idTypeMenu");
                    mn.type = menuObj.getString("name");
                }
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
                b.latitude = obj.getString("latitude");
                b.longitude = obj.getString("longitude");
                b.averageCalification = obj.getInt("averageCalification");
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
}
