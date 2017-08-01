package com.morfando.android.morfando;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.IdRes;
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
import com.morfando.android.morfando.Class.ParseQuery;
import com.morfando.android.morfando.Class.User;
import com.morfando.android.morfando.Class.Utility;
import com.morfando.android.morfando.Profile.profileFrag;
import com.morfando.android.morfando.Registration.resetPasswordFrag;
import com.morfando.android.morfando.Registration.signUpFrag;
import com.morfando.android.morfando.Restaurant.lvRestaurantFrag;
import com.morfando.android.morfando.Restaurant.Single.restaurantSingleFrag;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    FragmentManager adminFragment;
    FragmentTransaction trans;

    ParseQuery pq;

    private Branch branch;

    private boolean userLoggedIn;
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

        pq = new ParseQuery();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Fragment fm;
                switch (tabId){
                    case R.id.tab_restaurant:
                        fm = new lvRestaurantFrag();
                        break;
                    /*case R.id.tab_favorites:

                        break;*/
                    /*case R.id.tab_resevation:

                        break;*/
                    case R.id.tab_profile:
                        fm = new profileFrag();
                        break;
                    default:
                        fm = new lvRestaurantFrag();
                        break;
                }
                updateFragment(fm);
            }
        });
    }

    public void viewPressed(View v) {
        switch (v.getId()){
            case R.id.resetPassword:
                Fragment reset;
                reset = new resetPasswordFrag();
                updateFragment(reset);
                break;
            case R.id.register:
                Fragment signUp;
                signUp = new signUpFrag();
                updateFragment(signUp);
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

    public void BranchSelected(int id){
        branch = pq.getBranch(id);
        updateToMain2();
    }

    private void updateToMain2(){
        Intent i = new Intent(this, Main2Activity.class);
        i.putExtra("user", (Serializable) myUser);
        i.putExtra("userLoggedIn", userLoggedIn);
        i.putExtra("branch", (Serializable) branch);
        startActivity(i);
    }

    public Branch getBranch(){
        return branch;
    }


    public void logInPressed(String email, String password) {
        myUser = pq.logUser(email, password);

        if (myUser != null){
            userLoggedIn = true;
            updateFragment(new lvRestaurantFrag());
        } else {
            Toast.makeText(this, "Error Log In",Toast.LENGTH_SHORT);
        }
    }
}
