package com.morfando.android.morfando;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.ParcelUuid;
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
import com.morfando.android.morfando.Class.CalificationBranch;
import com.morfando.android.morfando.Class.ParseQuery;
import com.morfando.android.morfando.Class.Reservation;
import com.morfando.android.morfando.Class.User;
import com.morfando.android.morfando.Class.Utility;
import com.morfando.android.morfando.Profile.profileFrag;
import com.morfando.android.morfando.Registration.logInFrag;
import com.morfando.android.morfando.Registration.resetPasswordFrag;
import com.morfando.android.morfando.Registration.signUpFrag;
import com.morfando.android.morfando.Reservation.reservationMainFrag;
import com.morfando.android.morfando.Restaurant.Single.Reservation.reservationFrag;
import com.morfando.android.morfando.Restaurant.Single.calificationRestaurantFrag;
import com.morfando.android.morfando.Restaurant.lvRestaurantFrag;
import com.morfando.android.morfando.Restaurant.Single.restaurantSingleFrag;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    FragmentManager adminFragment;
    FragmentTransaction trans;

    ParseQuery pq;

    private Branch branch;

    private boolean userLoggedIn = false;
    private User myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        adminFragment = getSupportFragmentManager();

        Fragment lvRestaurantFrag;
        lvRestaurantFrag = new lvRestaurantFrag();

        trans=adminFragment.beginTransaction();
        trans.replace(R.id.fragmentConteiner, lvRestaurantFrag);
        trans.commit();

        pq = new ParseQuery(getApplicationContext());

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
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
                    /*case R.id.tab_resevation:

                        break;*/
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
            Fragment profile;
            profile = new profileFrag();
            trans=adminFragment.beginTransaction();
            trans.replace(R.id.fragmentConteiner, profile);
            trans.commit();
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

    public void createReservation(int guest, Calendar date) {
        Reservation res = new Reservation();
        res.idUser = myUser.idUser;
        res.branch = branch;
        res.date = date;
        res.guest = guest;
        Boolean ok = pq.createReservation(res);
        String message = "";
        if(ok){
            updateFragment(new reservationMainFrag());
        } else {
            Toast.makeText(this,"error", Toast.LENGTH_SHORT)
                    .show();
        }

    }

    public Branch getBranch(){
        return branch;
    }


    public void logInPressed(String email, String password) {
        pq.setUserLogged(myUser);
        pq.logUser(email, password);
    }

    public ArrayList<Reservation> getReservation(String type){
        return pq.getListReservation(myUser.idUser, type);
    }
}
