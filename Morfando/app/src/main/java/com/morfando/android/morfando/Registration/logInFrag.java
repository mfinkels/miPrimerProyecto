package com.morfando.android.morfando.Registration;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;

/**
 * Created by Matias on 6/9/2017.
 */

public class logInFrag extends Fragment implements View.OnClickListener {

    EditText email, password;
    TextView resetPassword;
    Button btnLogIn, register, facebook, google;
    MainActivity main;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_log_in, group, false);

        email=(EditText)toReturn.findViewById(R.id.emailUser);
        password=(EditText)toReturn.findViewById(R.id.passwordUser);
        resetPassword = (TextView)toReturn.findViewById(R.id.resetPassword);
        btnLogIn =(Button)toReturn.findViewById(R.id.btnLogIn);
        register = (Button)toReturn.findViewById(R.id.register);
        facebook = (Button)toReturn.findViewById(R.id.facebook);
        google = (Button)toReturn.findViewById(R.id.google);
        main = (MainActivity)getActivity();

        resetPassword.setOnClickListener(this);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });
        register.setOnClickListener(this);
        facebook.setOnClickListener(this);
        google.setOnClickListener(this);



        return toReturn;
    }

    private void logIn() {
        main.logInPressed(email.getText().toString(), password.getText().toString());
    }

    public void onClick(View v) {
        main.viewPressed(v);
    }
}
