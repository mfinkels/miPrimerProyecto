package com.morfando.android.morfando.Reservation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class upcomingReservationFrag extends Fragment{

    MainActivity main;
    ParseQuery pq;

    ListView reservation;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_upcoming_reservation, group, false);
        main = (MainActivity)getActivity();
        pq = new ParseQuery(main);
        int idUser = main.getIdUser();

        reservation = (ListView)toReturn.findViewById(R.id.listUpcomingReservation);


        ArrayList<Reservation> list = new ArrayList<Reservation>();
        reservationAdapter adapter = new reservationAdapter(list, main);
        asyncTaskCompleted listener = new asyncTaskCompleted() {
            @Override
            public void onPostAsyncTask(Object result) {
                ArrayList<Reservation> list = (ArrayList<Reservation>) result;
                if (list != null){
                    reservationAdapter adapter = new reservationAdapter(list, main);
                    reservation.setAdapter(adapter);
                }
            }
        };

        pq.getListReservation(idUser,"upcoming", listener);

        return toReturn;
    }
}
