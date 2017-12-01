package com.morfando.android.morfando.Reservation.Single;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.morfando.android.morfando.Class.CalificationPlate;
import com.morfando.android.morfando.Class.ParseQuery;
import com.morfando.android.morfando.Class.Plate;
import com.morfando.android.morfando.Class.Reservation;
import com.morfando.android.morfando.Interface.asyncTaskCompleted;
import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;
import com.morfando.android.morfando.Restaurant.Single.Adapter.plateAdapter;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Matias on 11/7/2017.
 */

public class calificationPlateFrag extends android.support.v4.app.DialogFragment {
    MainActivity main;
    ParseQuery pq;

    Plate myPlate;

    TextView name, description;

    RatingBar value;
    EditText message;


    Button send;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_calification_plate, group, false);
        main = (MainActivity)getActivity();
        pq = new ParseQuery(main);

        myPlate = main.getMyPlate();

        name = (TextView)toReturn.findViewById(R.id.plateName);
        description = (TextView)toReturn.findViewById(R.id.plateDescription);

        name.setText(myPlate.name);
        description.setText(myPlate.description);

        value = (RatingBar)toReturn.findViewById(R.id.calificationPlate);
        message = (EditText)toReturn.findViewById(R.id.messageCalificationPlate);

        send = (Button)toReturn.findViewById(R.id.btnSend);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPressed();
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

    private void sendPressed(){
        // genero Calification
        CalificationPlate calification = new CalificationPlate();
        main.setImportantInformationCalificactionPlate(calification);
        calification.idPlate = myPlate.idPlate;
        calification.message = message.getText().toString();
        calification.value = (int)value.getRating();

        //listener y mando
        asyncTaskCompleted listener = new asyncTaskCompleted() {
            @Override
            public void onPostAsyncTask(Object result) {
                Boolean resultado = (Boolean)result;
                if (resultado){
                    dismiss();
                } else {
                    Toast.makeText(main, "Error", Toast.LENGTH_LONG).show();
                }
            }
        };
        pq.createCalificationPlate(calification, listener);
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
