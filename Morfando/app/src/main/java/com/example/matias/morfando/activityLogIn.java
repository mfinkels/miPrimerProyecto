package com.example.matias.morfando;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class activityLogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

    public void signUp(View viewRecibida){

        Intent actividadDestino;
        actividadDestino=new Intent(activityLogIn.this, activitySignUp.class);
        startActivity(actividadDestino);
    }

    public void resetPassword(View viewRecibida){

        Intent actividadDestino;
        actividadDestino=new Intent(activityLogIn.this, ActivityResetPassword.class);
        startActivity(actividadDestino);
    }


}
