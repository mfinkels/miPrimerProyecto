package com.morfando.android.morfando.Reservation;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.morfando.android.morfando.Class.ParseQuery;
import com.morfando.android.morfando.Class.Reservation;
import com.morfando.android.morfando.Interface.asyncTaskCompleted;
import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;
import com.morfando.android.morfando.Reservation.Adapter.reservationAdapter;

import java.util.ArrayList;

/**
 * Created by Matias on 2/8/17.
 */

public class pastReservationFrag extends Fragment {

    MainActivity main;

    ParseQuery pq;

    ListView reservation;
    reservationAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_past_reservation, group, false);
        main = (MainActivity)getActivity();
        pq = new ParseQuery(main);

        reservation = (ListView)toReturn.findViewById(R.id.listPastReservation);

        int idUser = main.getIdUser();


        asyncTaskCompleted listener = new asyncTaskCompleted() {
            @Override
            public void onPostAsyncTask(Object result) {
                ArrayList<Reservation> list = (ArrayList<Reservation>) result;
                if (list != null){
                    adapter = new reservationAdapter(list, main);
                    reservation.setAdapter(adapter);
                    reservation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            reservationPressed(position);
                        }
                    });
                }
            }
        };

        pq.getListReservation(idUser,"past", listener);

        return toReturn;
    }

    private void reservationPressed(int position) {
        Reservation res = adapter.getItem(position);
        main.ReservationSelected(res);
    }
}
