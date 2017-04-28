package com.example.matias.morfando;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class activityProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Inicio");
        setContentView(R.layout.activity_profile);

        Bundle datosRecividos;
        datosRecividos=this.getIntent().getExtras();

        String nombreRecivido=datosRecividos.getString("name");

        /*lastname

         */
        String lastName=datosRecividos.getString("lastname");


        /*
        email
         */
        String mail =datosRecividos.getString("email");

            /*
             password
             */

        String password =datosRecividos.getString("password");
        /*
        confirm_password
         */
        String confirm_password =datosRecividos.getString("confirm_password");

        /*
        phone
         */

        String phone =datosRecividos.getString("phone");

        TextView nombre;
        nombre=(TextView)findViewById(R.id.user_profile_name);
        nombre.getText().toString();

        nombre==nombreRecivido.toString();


    }
}
