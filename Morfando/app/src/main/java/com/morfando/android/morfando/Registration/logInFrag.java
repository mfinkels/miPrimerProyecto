package com.morfando.android.morfando.Registration;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.morfando.android.morfando.Class.User;
import com.morfando.android.morfando.Interface.asyncTaskCompleted;
import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;

/**
 * Created by Matias on 6/9/2017.
 */

public class logInFrag extends DialogFragment implements View.OnClickListener {

    EditText email, password;
    TextView resetPassword;
    Button btnLogIn, register, facebook, google;
    MainActivity main;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_log_in, group, false);

        Toolbar toolbar = (Toolbar) toReturn.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
        }
        setHasOptionsMenu(true);

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

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // handle close button click here
            dismiss();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logIn() {
        asyncTaskCompleted<Object> postListener = new asyncTaskCompleted<Object>() {
            @Override
            public void onPostAsyncTask(Object result) {
                User u = (User) result;
                if (u != null) {
                    dismiss();
                    main.userLogged(u);
                } else {
                    Toast.makeText(main, "Error Log In", Toast.LENGTH_SHORT).show();
                }
            }
        };

        main.logInPressed(email.getText().toString(), password.getText().toString(), postListener);
    }

    public void onClick(View v) {
        main.viewPressed(v);
    }
}
