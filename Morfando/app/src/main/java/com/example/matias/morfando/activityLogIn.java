package com.example.matias.morfando;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activityLogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

    public void logIn(View viewRecibida){
        EditText email, password;
        email=(EditText)findViewById(R.id.emailUser);
        password=(EditText)findViewById(R.id.passwordUser);


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
        startActivity(actividadDestino);
    }


}
