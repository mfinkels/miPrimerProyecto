package com.morfando.android.morfando.Profile;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.morfando.android.morfando.Class.User;
import com.morfando.android.morfando.MainActivity;
import com.morfando.android.morfando.R;
import com.morfando.android.morfando.Registration.resetPasswordFrag;
import com.morfando.android.morfando.Registration.signUpFrag;

import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Matias on 6/12/2017.
 */

public class profileFrag extends Fragment implements View.OnClickListener {


    MainActivity main;

    private TextView userProfileName, preferences, promotions, payments, califications, logOut;
    private CircleImageView profileImage;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle data) {


        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_profile, group, false);

        main = (MainActivity)getActivity();
        userProfileName=(TextView)toReturn.findViewById(R.id.user_profile_name);
        profileImage=(CircleImageView) toReturn.findViewById(R.id.profile_image);

        preferences = (TextView)toReturn.findViewById(R.id.preferences);
        promotions = (TextView)toReturn.findViewById(R.id.promotions);
        payments = (TextView)toReturn.findViewById(R.id.payments);
        califications = (TextView)toReturn.findViewById(R.id.califications);
        logOut = (TextView)toReturn.findViewById(R.id.logOut);
        User myUser = main.getMyUser();

        userProfileName.setText(myUser.name + " " + myUser.lastName);

        /*
        preferences.setOnClickListener(this);
        promotions.setOnClickListener(this);
        payments.setOnClickListener(this);
        califications.setOnClickListener(this);
        logOut.setOnClickListener(this);
        */

        return toReturn;
    }

    public void onClick (View v) {
        main.viewPressed(v);
    }
}
