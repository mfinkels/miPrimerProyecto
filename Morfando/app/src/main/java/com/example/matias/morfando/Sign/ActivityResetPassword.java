package com.example.matias.morfando.Sign;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.widget.Button;
import android.widget.EditText;

import com.example.matias.morfando.R;

public class ActivityResetPassword extends AppCompatActivity {

    EditText email;
    Button resetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        email = (EditText) findViewById(R.id.emailToReset);
    }
}
