package com.morfando.android.morfando.Class;

import java.util.List;

/**
 * Created by Matias on 6/12/2017.
 */

public class User {

    public int idUser;
    public String name;
    public String lastName;
    public String password;
    public String photo;
    public String phone;
    public String email;
    public String latitude;
    public String longitude;
    public Promotion promotion;
    public List<Promotion> userPromotions;
    public List<Reservation> reservations;
    public List<OrderReservation> orders;

    /*
    public void User(int id, String name, String lastName, String password, String photo, String phone, String email, String latitude, String longitude, Promotion promotion){

        this.idUser = id;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.photo = photo;
        this.phone = phone;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.promotion = promotion;
    }*/



}
