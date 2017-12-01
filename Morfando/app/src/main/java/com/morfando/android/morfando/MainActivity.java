package com.morfando.android.morfando;

import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.morfando.android.morfando.Class.Branch;
import com.morfando.android.morfando.Class.CalificationPlate;
import com.morfando.android.morfando.Class.OrderReservation;
import com.morfando.android.morfando.Class.ParseQuery;
import com.morfando.android.morfando.Class.Plate;
import com.morfando.android.morfando.Class.Reservation;
import com.morfando.android.morfando.Class.User;
import com.morfando.android.morfando.Class.Utility;
import com.morfando.android.morfando.Interface.asyncTaskCompleted;
import com.morfando.android.morfando.Profile.profileFrag;
import com.morfando.android.morfando.Registration.logInFrag;
import com.morfando.android.morfando.Registration.resetPasswordFrag;
import com.morfando.android.morfando.Registration.signUpFrag;
import com.morfando.android.morfando.Reservation.Single.calificationPlateFrag;
import com.morfando.android.morfando.Reservation.Single.orderReservationFrag;
import com.morfando.android.morfando.Reservation.Single.plateWithCartFrag;
import com.morfando.android.morfando.Reservation.Single.singleReservationFrag;
import com.morfando.android.morfando.Reservation.reservationMainFrag;
import com.morfando.android.morfando.Reservation.upcomingReservationFrag;
import com.morfando.android.morfando.Restaurant.Single.Reservation.reservationFrag;
import com.morfando.android.morfando.Restaurant.Single.calificationRestaurantFrag;
import com.morfando.android.morfando.Restaurant.lvRestaurantFrag;
import com.morfando.android.morfando.Restaurant.Single.restaurantSingleFrag;
import com.morfando.android.morfando.Restaurant.setFilterFrag;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    FragmentManager adminFragment;
    FragmentTransaction trans;

    ParseQuery pq;

    private Branch branch;
    private Reservation reservation;
    private ArrayList<Plate> plates;
    private Plate myPlate;

    private boolean userLoggedIn = false;

    public User getMyUser() {
        return myUser;
    }

    private User myUser;
    BottomBar bottomBar;

    private int idCuisine, idService, rating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        adminFragment = getSupportFragmentManager();

        Fragment lvRestaurantFrag;
        lvRestaurantFrag = new lvRestaurantFrag();

        idCuisine = 0;
        idService = 0;
        rating = 0;

        trans=adminFragment.beginTransaction();
        trans.replace(R.id.fragmentConteiner, lvRestaurantFrag);
        trans.commit();

        pq = new ParseQuery(getApplicationContext());
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Fragment fm;
                switch (tabId){
                    case R.id.tab_restaurant:
                        fm = new lvRestaurantFrag();
                        updateFragment(fm);
                        break;
                    /*case R.id.tab_favorites:

                        break;*/
                    case R.id.tab_resevation:
                        if(userLoggedIn){
                            updateFragment(new reservationMainFrag());
                        } else{
                            showDialogFragment(new logInFrag());
                        }
                        break;
                    case R.id.tab_profile:
                        if(userLoggedIn){
                            fm = new profileFrag();
                            updateFragment(fm);
                        } else{
                            logInFrag register = new logInFrag();
                            showDialogFragment(register);
                        }
                        break;
                    default:
                        fm = new lvRestaurantFrag();
                        updateFragment(fm);
                        break;
                }

            }
        });
    }

    public void viewPressed(View v) {
        switch (v.getId()){
            case R.id.resetPassword:
                resetPasswordFrag reset = new resetPasswordFrag();
                showDialogFragment(reset);
                break;
            case R.id.register:
                signUpFrag signUp = new signUpFrag();
                showDialogFragment(signUp);
                break;
            case R.id.facebook:

                break;

            //Reset Password
            case R.id.sendReset:

                break;

            //Profile
            case R.id.preferences:

                break;
            case R.id.promotions:

                break;
            case R.id.payments:

                break;
            case R.id.califications:

                break;
            case R.id.logOut:

                break;


        }
    }

    private void updateFragment(Fragment newFragment) {
        trans=adminFragment.beginTransaction();
        trans.replace(R.id.fragmentConteiner, newFragment);
        trans.commit();
    }

    public Plate getMyPlate() {
        return myPlate;
    }

    public void setMyPlate(Plate myPlate) {
        this.myPlate = myPlate;
    }

    private void errorAlert(String title, String message) {
        AlertDialog.Builder errorDisplay = new AlertDialog.Builder(this);
        errorDisplay.setTitle(title);
        errorDisplay.setMessage(message);
        errorDisplay.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface errorDisplay, int id) {
                errorDisplay.dismiss();
            }
        });
        errorDisplay.show();
    }

    public void signUp(String name, String lastName, String email, String password, String confirmPassword, String phone) {
        String result = "";
        User user = new User();
        user.name = name;
        user.lastName = lastName;

        if(Utility.isValidEmail(email)) {
            user.email = email;
        } else {
            result = "Correo Electronico invalido";

        }

        if (password.compareTo(confirmPassword) == 0) {
            user.password = password;
        }else {
            result += "\t" + "Las contraseñas no coinciden";
        }

        if (Utility.isValidPhone(phone))
        {
            user.phone = phone;
        }else {
            result += "\t" + "El Telefono Movil es incorrecto";
        }
        if (result.length() == 0) {
            // crear usuario.
            /*
            Fragment profile;
            profile = new profileFrag();
            trans=adminFragment.beginTransaction();
            trans.replace(R.id.fragmentConteiner, profile);
            trans.commit();
            */
        } else {
            errorAlert("Error", result);
        }
    }

    public void updateDialogFragment(DialogFragment dialog) {
        showDialogFragment(dialog);
    }

    private void showDialogFragment(DialogFragment newFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, newFragment)
                .addToBackStack(null).commit();
    }

    public void makeReservation(){
        if(userLoggedIn){
            showDialogFragment(new reservationFrag());
        }else {
            // si no esta logeado
            showDialogFragment(new logInFrag());
        }

    }

    public void BranchSelected(Branch b){
        branch = b;
        updateDialogFragment(new restaurantSingleFrag());
    }

    public void showAllCalification(){
        calificationRestaurantFrag cali = new calificationRestaurantFrag();
        showDialogFragment(cali);
    }

    public void createReservation(int guest, Calendar date, asyncTaskCompleted listener) {
        Reservation res = new Reservation();
        res.idUser = myUser.idUser;
        res.branch = branch;
        res.date = date;
        res.guest = guest;
        pq.createReservation(res, listener);
    }

    public Branch getBranch(){
        return branch;
    }


    public void logInPressed(String email, String password,asyncTaskCompleted listener) {
        pq.setUser(myUser);
        pq.setUserIsLogged(userLoggedIn);
        pq.logUser(email, password, listener);
    }

    public int getIdUser(){
        return myUser.idUser;
    }

    public void userLogged(User u) {
        myUser = u;
        userLoggedIn = true;
        updateFragment(new profileFrag());
    }

    public void reservationCreated() {
        updateFragment(new reservationMainFrag());
    }

    public void ReservationSelected(Reservation res) {
        setReservation(res);
        updateDialogFragment(new singleReservationFrag());
        //Muestro reserva
    }

    public void setPlates(ArrayList<Plate> plates) {
        this.plates = plates;
    }

    public ArrayList<Plate> getPlates(){
        return plates;
    }

    public void setReservation(Reservation reservation){
        this.reservation = reservation;
    }

    public Reservation getReservation(){
        return reservation;
    }

    public void viewOrder() {
        updateDialogFragment(new orderReservationFrag());
    }

    public void addNewOrder() {
        updateDialogFragment(new plateWithCartFrag());
    }

    public void addOrderToReservation(ArrayList<OrderReservation> order, asyncTaskCompleted listener) {
        pq.createOrder(order, listener);
    }

    public void showPlateCalification() {
        updateDialogFragment(new calificationPlateFrag());
    }

    public void setImportantInformationCalificactionPlate(CalificationPlate calification) {
        calification.idUser = myUser.idUser;
        calification.idBranch = reservation.branch.idBranch;
    }

    public void FilterBrachesShow() {
        updateDialogFragment(new setFilterFrag());
    }


    public int getIdCuisine() {
        return idCuisine;
    }

    public int getIdService() {
        return idService;
    }

    public int getRating() {
        return rating;
    }

    public void setIdCuisine(int idCuisine) {
        this.idCuisine = idCuisine;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    public void filterBranch(int idCuisine, int idService, int rating) {
        this.idCuisine = idCuisine;
        this.idService = idService;
        this.rating = rating;
    }


}
