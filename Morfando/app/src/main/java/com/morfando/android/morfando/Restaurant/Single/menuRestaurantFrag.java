package com.morfando.android.morfando.Restaurant.Single;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.morfando.android.morfando.Class.Branch;
import com.morfando.android.morfando.Class.Menu;
import com.morfando.android.morfando.Class.ParseQuery;
import com.morfando.android.morfando.Interface.asyncTaskCompleted;
import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;
import com.morfando.android.morfando.Restaurant.Adapter.menuAdapter;
import com.morfando.android.morfando.Restaurant.Adapter.plateAdapter;

import java.util.ArrayList;

/**
 * Created by Matias on 6/30/2017.
 */

public class menuRestaurantFrag extends Fragment{

    MainActivity main;

    ParseQuery pq;
    ListView lv;
    menuAdapter adapter;
    ArrayList<Menu> list;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_menu_restaurant, group, false);
        main = (MainActivity)getActivity();
        pq = new ParseQuery(main);
        Branch myBranch = main.getBranch();


        lv = (ListView)toReturn.findViewById(R.id.menuList);
        asyncTaskCompleted listener = new asyncTaskCompleted() {
            @Override
            public void onPostAsyncTask(Object result) {
                list = (ArrayList<Menu>) result;
                if (list != null){
                    putMenu();
                }else {
                    Toast.makeText(main, "Error Menu", Toast.LENGTH_SHORT).show();
                }
            }
        };
        pq.getMenuInfo(myBranch.idBranch, listener);

        return toReturn;
    }

    private void menuPressed(int position) {
        Menu m = adapter.getItem(position);
        plateAdapter adapterP = new plateAdapter(m.plates,main,false);
        lv.setAdapter(adapterP);
        //Volver al Menu
        lv.deferNotifyDataSetChanged();
    }
    private void putMenu(){
        adapter = new menuAdapter(list, main);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                menuPressed(position);
            }
        });
    }
}
