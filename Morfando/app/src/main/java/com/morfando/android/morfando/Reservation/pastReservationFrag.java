package com.morfando.android.morfando.Reservation;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.morfando.android.morfando.Class.ParseQuery;
import com.morfando.android.morfando.Class.Reservation;
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

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_past_reservation, group, false);
        main = (MainActivity)getActivity();
        pq = new ParseQuery(main);

        reservation = (ListView)toReturn.findViewById(R.id.listPastReservation);

        int idUser = main.getIdUser();

        ArrayList<Reservation> list = new ArrayList<Reservation>();
        reservationAdapter adapter = new reservationAdapter(list, main);
        pq.setResAdapter(adapter);
        pq.setReservations(list);
        pq.getListReservation(idUser,"past");
        reservation.setAdapter(adapter);

        return toReturn;
    }
}
