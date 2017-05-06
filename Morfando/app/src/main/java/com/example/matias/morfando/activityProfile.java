package com.example.matias.morfando;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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


    public void profileButtons(View ButtonsPressed) {

        TextView buttonProfilePressed;
        buttonProfilePressed = (TextView)ButtonsPressed;
        Intent i;
        Bundle packageOfData;
        packageOfData=this.getIntent().getExtras();
        i.putExtras(packageOfData);

        switch (buttonProfilePressed.getId()){
            case R.id.preferences:
                i = new Intent(activityProfile.this, activityPreferences.class);
                break;
            case R.id.promotions:
                i = new Intent(activityProfile.this, activityPromotions.class);
                break;
            case R.id.payments:
                i = new Intent(activityProfile.this, activityPayments.class);
                break;
            case R.id.califications:
                i = new Intent(activityProfile.this, activityCalifications.class);
                break;
            case R.id.logOut:
                AlertDialog.Builder logOutMessage = new AlertDialog.Builder(this);
                logOutMessage.setTitle("Cerrar Sesion");
                logOutMessage.setMessage("Queres cerrar sesion?");
                logOutMessage.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface logOutMessage, int id) {
                        logOutMessage.dismiss();
                    }
                });
                logOutMessage.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface logOutMessage, int id) {
                        logOutMessage.dismiss();
                    }
                });
                logOutMessage.show();
                break;
        }
        startActivity(i);

    }
}
