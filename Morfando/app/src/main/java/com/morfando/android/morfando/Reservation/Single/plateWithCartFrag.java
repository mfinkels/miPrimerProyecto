package com.morfando.android.morfando.Reservation.Single;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.morfando.android.morfando.Class.Menu;
import com.morfando.android.morfando.Class.OrderReservation;
import com.morfando.android.morfando.Class.ParseQuery;
import com.morfando.android.morfando.Class.Plate;
import com.morfando.android.morfando.Class.Reservation;
import com.morfando.android.morfando.Interface.asyncTaskCompleted;
import com.morfando.android.morfando.Interface.getPlateToOrder;
import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;
import com.morfando.android.morfando.Restaurant.Single.Adapter.plateAdapter;

import java.util.ArrayList;

/**
 * Created by Matias on 9/22/2017.
 */

public class plateWithCartFrag extends DialogFragment {
    MainActivity main;
    ParseQuery pq;

    Reservation myReservation;

    ListView plates;
    Spinner menus;
    TextView items;
    FloatingActionButton cart;

    ArrayList<OrderReservation> order;

    int countItems;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_plates, group, false);
        main = (MainActivity)getActivity();
        pq = new ParseQuery(main);

        myReservation = main.getReservation();
        countItems = 0;
        order = new ArrayList<OrderReservation>();

        menus = (Spinner)toReturn.findViewById(R.id.spMenus);
        plates = (ListView)toReturn.findViewById(R.id.listPlates);
        items = (TextView)toReturn.findViewById(R.id.displayItemsInCart);

        items.setText(countItems + "");

        cart = (FloatingActionButton)toReturn.findViewById(R.id.btnCart);

        asyncTaskCompleted listener = new asyncTaskCompleted() {
            @Override
            public void onPostAsyncTask(Object result) {
                ArrayList<Menu> listMenu = (ArrayList<Menu>) result;
                if (listMenu != null){
                    myReservation.branch.menu = listMenu;
                    listTypesMenu();
                }else {
                    Toast.makeText(main, "Error Menu", Toast.LENGTH_SHORT).show();
                }
            }
        };
        pq.getMenuInfo(myReservation.branch.idBranch, listener);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOutCart();
            }
        });

        Toolbar toolbar = (Toolbar) toReturn.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
        }
        setHasOptionsMenu(true);

        return toReturn;
    }

    private void listTypesMenu() {
        ArrayList<String> types = new ArrayList<String>();
        for (Menu m : myReservation.branch.menu) {
            types.add(m.type);
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(main, android.R.layout.simple_spinner_dropdown_item, types);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        menus.setAdapter(adapter);
        menus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                menuSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void menuSelected(int position) {
        Menu m = myReservation.branch.menu.get(position);
        listPlates(m);
    }

    private void listPlates(Menu m) {
        getPlateToOrder listener = new getPlateToOrder() {
            @Override
            public void onPostPlate(Plate plate) {
                Log.d("addToCart", plate.idPlate + "");
                order.add(createOrder(plate));
                countItems++;
                items.setText(countItems + "");
            }
        };
        plateAdapter adapterP = new plateAdapter(m.plates,main,true,listener);
        plates.setAdapter(adapterP);
        plates.deferNotifyDataSetChanged();
    }

    private OrderReservation createOrder(Plate plate) {
        OrderReservation order = new OrderReservation();
        order.idReservation = myReservation.idReservation;
        order.idPlate = plate.idPlate;
        order.plate = plate;
        order.idUser = main.getIdUser();
        return order;
    }

    private void checkOutCart() {
        AlertDialog.Builder confirmmationOrder = new AlertDialog.Builder(main);
        confirmmationOrder.setTitle("Order Confirmation");
        String message = "";
        for (OrderReservation o : order){
            message += o.plate.name + System.getProperty("line.separator");
        }
        confirmmationOrder.setMessage(message);
        confirmmationOrder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface confirmmationOrder, int id) {
                orderCofirmed();
                confirmmationOrder.dismiss();
            }
        });
        confirmmationOrder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface errorDisplay, int id) {
                order.clear();
                countItems = 0;
                items.setText(countItems +"");
                errorDisplay.dismiss();
            }
        });
        confirmmationOrder.show();
    }

    private void orderCofirmed() {
        asyncTaskCompleted listener = new asyncTaskCompleted() {
            @Override
            public void onPostAsyncTask(Object result) {
                Boolean completed = (Boolean) result;
                if (completed){
                    Toast.makeText(main,"Order Submit",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(main,"Error Submitting Order",Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        };
        main.addOrderToReservation(order, listener);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // handle close button click here
            dismiss();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}