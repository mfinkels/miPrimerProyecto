package com.morfando.android.morfando.Reservation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;

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

        reservation = (ListView)toReturn.findViewById(R.id.listUpcomingReservation);

        return toReturn;
    }
}
