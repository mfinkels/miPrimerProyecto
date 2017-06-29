package com.morfando.android.morfando;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.support.v7.widget.RecyclerView;

import com.morfando.android.morfando.Class.Promotion;
import com.morfando.android.morfando.Class.User;
import com.morfando.android.morfando.Class.Utility;
import com.morfando.android.morfando.Profile.profileFrag;
import com.morfando.android.morfando.Registration.logInFrag;
import com.morfando.android.morfando.Registration.resetPasswordFrag;
import com.morfando.android.morfando.Registration.signUpFrag;
import com.morfando.android.morfando.Restaurant.Adapter.RestaurantAdapter;
import com.morfando.android.morfando.Restaurant.restaurantFrag;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    FragmentManager adminFragment;
    FragmentTransaction trans;

    private boolean userLoggedIn = false;
    private User myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        adminFragment = getSupportFragmentManager();

        Fragment fragLogIn;
        fragLogIn = new logInFrag();

        trans=adminFragment.beginTransaction();
        trans.replace(R.id.fragmentConteiner, fragLogIn);
        trans.commit();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_favorites) {
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                }
            }
        });
    }

    public void viewPressed(View v) {
        switch (v.getId()){
            case R.id.resetPassword:
                Fragment reset;
                reset = new resetPasswordFrag();
                updateFragment(reset);
                break;
            case R.id.register:
                Fragment signUp;
                signUp = new signUpFrag();
                updateFragment(signUp);
                break;
            case R.id.facebook:

                break;

            //Reset Password
            case R.id.sendReset:

                break;

            //Profile
            case R.id.preferences:

                break;
            case R.id.promotions:

                break;
            case R.id.payments:

                break;
            case R.id.califications:

                break;
            case R.id.logOut:

                break;


        }
    }

    private void updateFragment(Fragment newFragment) {
        trans=adminFragment.beginTransaction();
        trans.replace(R.id.fragmentConteiner, newFragment);
        trans.commit();
    }


    private void errorAlert(String title, String message) {
        AlertDialog.Builder errorDisplay = new AlertDialog.Builder(this);
        errorDisplay.setTitle(title);
        errorDisplay.setMessage(message);
        errorDisplay.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface errorDisplay, int id) {
                errorDisplay.dismiss();
            }
        });
        errorDisplay.show();
    }

    public void signUp(String name, String lastName, String email, String password, String confirmPassword, String phone) {
        String result = "";
        User user = new User();
        user.name = name;
        user.lastName = lastName;

        if(Utility.isValidEmail(email)) {
            user.email = email;
        } else {
            result = "Correo Electronico invalido";

        }

        if (password.compareTo(confirmPassword) == 0) {
            user.password = password;
        }else {
            result += "\t" + "Las contraseñas no coinciden";
        }

        if (Utility.isValidPhone(phone))
        {
            user.phone = phone;
        }else {
            result += "\t" + "El Telefono Movil es incorrecto";
        }
        if (result.length() == 0) {
            // crear usuario.
            Fragment profile;
            profile = new profileFrag();
            trans=adminFragment.beginTransaction();
            trans.replace(R.id.fragmentConteiner, profile);
            trans.commit();
        } else {
            errorAlert("Error", result);
        }
    }


    public void logInPressed(String email, String password) {
        new LogInUser().execute(email, password);
    }

    public class LogInUser extends AsyncTask<String, Void, User> {

        protected void onPostExecute(User datos) {
            super.onPostExecute(datos);
            if (datos != null){
                userLoggedIn = true;
                myUser = datos;
                Fragment resto;
                resto = new restaurantFrag();
                updateFragment(resto);
            } else {
                Toast.makeText(getApplicationContext(),"Email or password incorrect", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected User doInBackground(String... parametros) {
            String email = parametros[0];
            String password = parametros[1];


            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://apimorfandoort.azurewebsites.net/api/user/" + email + "/" + password)
                    .build();
            try {
                Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                String resultado = response.body().string();
                try {
                    User myUser = new User();
                    JSONObject json = new JSONObject(resultado);
                    myUser.idUser = json.getInt("idUser");
                    myUser.name = json.getString("name");
                    myUser.lastName = json.getString("lastName");
                    myUser.password = json.getString("password");
                    myUser.latitude = json.getString("latitude");
                    myUser.longitude = json.getString("longitude");
                    myUser.photo = json.getString("photo");
                    myUser.phone = json.getString("phone");
                    myUser.email = json.getString("email");

                    return myUser;
                }
                catch (JSONException e){
                    Log.d("Error JSON",e.getMessage());
                    String a = "puta";
                    return null;
                }
            } catch (IOException e) {
                Log.d("Error",e.getMessage());             // Error de Network
                return null;
            }
        }

    }
}
