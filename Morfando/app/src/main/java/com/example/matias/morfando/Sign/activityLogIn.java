package com.example.matias.morfando;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class activityLogIn extends AppCompatActivity {

    public boolean isFirstStart;
    private  FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(activityLogIn.this, activityRestaurantMain.class));
            finish();
        }

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Intro App Initialize SharedPreferences
                SharedPreferences getSharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());

                //  Create a new boolean and preference and set it to true
                isFirstStart = getSharedPreferences.getBoolean("firstStart", true);

                //  Check either activity or app is open very first time or not and do action
                if (isFirstStart) {

                    //  Launch application introduction screen
                    Intent i = new Intent(activityLogIn.this, myIntro.class);
                    startActivity(i);
                    SharedPreferences.Editor e = getSharedPreferences.edit();
                    e.putBoolean("firstStart", false);
                    e.apply();
                }
            }
        });
        t.start();

    }

    public void logIn(View viewRecibida){
        EditText email, password;
        email=(EditText)findViewById(R.id.emailUser);
        password=(EditText)findViewById(R.id.passwordUser);

        loginUser(email.getText().toString(), password.getText().toString());
    }

    public void signUp(View viewRecibida){

        Button botonPresionado;
        botonPresionado=(Button)viewRecibida;

        Intent actividadDestino;

        if(botonPresionado.getId() == R.id.facebook){
            actividadDestino = new Intent(activityLogIn.this, activitySignUp.class);
        }else{
            actividadDestino = new Intent(activityLogIn.this, activitySignUp.class);
        }

        startActivity(actividadDestino);
    }

    public void resetPassword(View viewRecibida){

        Intent actividadDestino;
        actividadDestino=new Intent(activityLogIn.this, ActivityResetPassword.class);
    }

    private void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            // error al logearse
                        } else {
                            startActivity(new Intent(activityLogIn.this, activityRestaurantMain.class));
                        }
                    }
                });
    }
}
