package com.morfando.android.morfando.Registration;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.morfando.android.morfando.Class.User;
import com.morfando.android.morfando.Class.Utility;
import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Matias on 6/10/2017.
 */

public class signUpFrag extends Fragment implements View.OnClickListener {

    MainActivity main;
    private Button btnSignUp;
    private EditText name, lastname, email, password, confirm_password, phone;
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_sign_up, group, false);
        main = (MainActivity)getActivity();

        btnSignUp = (Button) toReturn.findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);

        name=(EditText)toReturn.findViewById(R.id.nameUser);
        lastname=(EditText)toReturn.findViewById(R.id.lastnameUser);
        email=(EditText)toReturn.findViewById(R.id.emailUser);
        password=(EditText)toReturn.findViewById(R.id.passwordUser);
        confirm_password=(EditText)toReturn.findViewById(R.id.confirm_passwordUser);
        phone=(EditText)toReturn.findViewById(R.id.photneUser);


        return toReturn;
    }

    public void onClick(View v){
        main.signUp(name.getText().toString(), lastname.getText().toString(), email.getText().toString(), password.getText().toString(), confirm_password.getText().toString(), phone.getText().toString());
    }
}
