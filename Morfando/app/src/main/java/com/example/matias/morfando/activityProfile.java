package com.example.matias.morfando;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class activityProfile extends AppCompatActivity {

    private TextView userProfileName;
    private CircleImageView profileImage;
    private Bundle datosRecibidos;
    private String nombre;
    private String lastName;
    private String mail;
    private String password;
    private String phone;
    private Bitmap profilePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Perfil");
        setContentView(R.layout.activity_profile);
        userProfileName=(TextView)findViewById(R.id.user_profile_name);
        profileImage=(CircleImageView) findViewById(R.id.profile_image);
        datosRecibidos=this.getIntent().getExtras();

        nombre = datosRecibidos.getString("name");
        lastName=datosRecibidos.getString("lastname");
        mail =datosRecibidos.getString("email");
        password =datosRecibidos.getString("password");
        phone =datosRecibidos.getString("phone");
        profilePhoto= Utility.convertBytesToImage(datosRecibidos.getByteArray("profilePhoto"));

        userProfileName.setText(nombre + " " + lastName);
        profileImage.setImageBitmap(profilePhoto);
    }
}
