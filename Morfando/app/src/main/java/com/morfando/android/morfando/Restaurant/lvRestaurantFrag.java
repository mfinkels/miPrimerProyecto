package com.morfando.android.morfando.Restaurant;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
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
 * Created by Matias on 6/29/2017.
 */

public class lvRestaurantFrag extends Fragment  implements AdapterView.OnItemClickListener{

    MainActivity main;
    ListView restaurantItems;
    lvRestaurantAdapter adapter;

    ArrayList<Branch> listBranches;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_restaurant_lv, group, false);
        main = (MainActivity)getActivity();

        restaurantItems = (ListView) toReturn.findViewById(R.id.lvRestaurantItems);
        new BranchGetAll().execute("http://apimorfandoort.azurewebsites.net/api/branch/", "10", "0");
        restaurantItems.setOnItemClickListener(this);
        return toReturn;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int idBranch = adapter.getId(position);
        main.BranchSelected(idBranch);
    }

    private class BranchGetAll extends AsyncTask<String, Void, ArrayList<Branch>> {

        protected void onPostExecute(ArrayList<Branch> datos) {
            super.onPostExecute(datos);
            if (datos != null){
                adapter = new lvRestaurantAdapter(datos, main);
                restaurantItems.setAdapter(adapter);

            } else {
                Toast.makeText(main,"Error connection database", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected ArrayList<Branch> doInBackground(String... parametros) {
            String url = parametros[0] + parametros[1] + "/" + parametros[2];
            int limit = Integer.parseInt(parametros[1]);

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

                Branch b = new Branch();

                for (int i = 0; i < jsonBranch.length(); i++){
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
}
