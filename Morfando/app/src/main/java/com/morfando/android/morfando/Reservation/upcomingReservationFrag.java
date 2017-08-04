package com.morfando.android.morfando.Reservation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.morfando.android.morfando.Class.Reservation;
import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;
import com.morfando.android.morfando.Reservation.Adapter.reservationAdapter;

import java.util.ArrayList;

/**
 * Created by Matias on 2/8/17.
 */

public class upcomingReservationFrag extends Fragment{

    MainActivity main;

    ListView reservation;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_upcoming_reservation, group, false);
        main = (MainActivity)getActivity();
        ArrayList<Reservation> list = main.getReservation("upcoming");

        if(list.size() > 0){
            reservation = (ListView)toReturn.findViewById(R.id.listUpcomingReservation);
            reservationAdapter adapter = new reservationAdapter(list, main);
            reservation.setAdapter(adapter);
        }
        return toReturn;
    }
}
