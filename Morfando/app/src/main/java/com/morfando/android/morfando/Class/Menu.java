package com.morfando.android.morfando.Class;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matias on 6/25/2017.
 */

public class Menu {
    //IdMenu, menú(nombre), menú(type), list<plate>
    public int idMenu;
    public String type;
    public ArrayList<Plate> plates;

    public Menu(){
        this.plates = new ArrayList<Plate>();
    }
}
