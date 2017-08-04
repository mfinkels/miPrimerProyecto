package com.morfando.android.morfando.Class;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Matias on 6/26/2017.
 */

public class Reservation {
    // IdReservation, Branch(todos datos de la sucursal), date, hour, guest, list<orderReservation>

    public int idReservation;
    public int idUser;
    public Branch branch;
    public Calendar date;
    public int guest;
    public List<OrderReservation> orders;

}

