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
 * Created by Matias on 6/10/2017.
 */

public class resetPasswordFrag extends Fragment implements View.OnClickListener {

    EditText email;
    Button sendReset;
    MainActivity main;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_reset_password, group, false);
        main = (MainActivity) getActivity();

        email = (EditText) toReturn.findViewById(R.id.emailToReset);
        sendReset = (Button)toReturn.findViewById(R.id.sendReset);
        sendReset.setOnClickListener(this);
        return toReturn;
    }

    public void onClick(View v){
        main.viewPressed(v);
    }
}
