package com.morfando.android.morfando.Class;

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
    public int averageCalification;
    public List<CalificationBranch> calificationBranch;
    public RangePrice range;
    public List<Menu> menu;
    public List<PhotoBranch> photo;
    public List<Service> service;
    public List<Timetable> timetable;
    public List<Filter> filter;
    public List<Cuisine> cuisine;
    public List<Promotion> promotion;



}
