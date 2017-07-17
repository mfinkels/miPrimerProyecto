package com.morfando.android.morfando.Class;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matias on 6/25/2017.
 */

public class Branch {

    // list<service>, list<timetable>, list<filter>, list<cuisine>, list<promotion>

    public int idBranch;
    public  Restaurant restaurant;
    public String name;
    public String latitude;
    public String longitude;
    public double averageCalification;
    public double averageFood;
    public double averageService;
    public double averageAmbience;
    public ArrayList<CalificationBranch> calificationBranch;
    public RangePrice range;
    public ArrayList<Menu> menu;
    public ArrayList<PhotoBranch> photo;
    public ArrayList<Service> service;
    public ArrayList<Timetable> timetable;
    public ArrayList<Filter> filter;
    public ArrayList<Cuisine> cuisine;
    public ArrayList<Promotion> promotion;


    public Branch() {
        this.restaurant = new Restaurant();
        this.range = new RangePrice();
    }
}
