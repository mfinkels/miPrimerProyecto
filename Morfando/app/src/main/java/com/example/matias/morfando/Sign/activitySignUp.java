package com.example.matias.morfando;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.os.Vibrator;



public class activitySignUp extends AppCompatActivity {

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private Button btnSelect, btnSignUp;
    private String userChoosenTask;
    private boolean photoExist= false;
    private EditText name, lastname, email, password, confirm_password, phone;
    private Bitmap profilePhoto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnSelect = (Button) findViewById(R.id.btnSelectPhoto);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSelect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        name=(EditText)findViewById(R.id.nameUser);
        lastname=(EditText)findViewById(R.id.lastnameUser);
        email=(EditText)findViewById(R.id.emailUser);
        password=(EditText)findViewById(R.id.passwordUser);
        confirm_password=(EditText)findViewById(R.id.confirm_passwordUser);
        phone=(EditText)findViewById(R.id.photneUser);




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Tomar Foto"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Elegir desde Libreria"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = { "Tomar Foto", "Elegir desde Libreria",
                "Cancelar" };

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activitySignUp.this);
        builder.setTitle("Agregar Foto");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result=Utility.checkPermission(activitySignUp.this);

                if (items[item].equals("Tomar Foto")) {
                    userChoosenTask ="Tomar Foto";
                    photoExist=true;
                    if(result)
                        cameraIntent();

                } else if (items[item].equals("Elegir desde Libreria")) {
                    userChoosenTask ="Elegir desde Libreria";
                    photoExist=true;
                    if(result)
                        galleryIntent();

                } else if (items[item].equals("Cancelar")) {
                    userChoosenTask = "Cancelar";
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Seleccionar Archivo"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                profilePhoto = onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                profilePhoto = onCaptureImageResult(data);
        }
    }

    private Bitmap onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return thumbnail;
    }

    @SuppressWarnings("deprecation")
    private Bitmap onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

       return bm;
    }

    private void signUp () {
        Intent intent = new Intent(this, activityProfile.class);

        Bundle PackageOfData;
        PackageOfData= new Bundle();
        String result ="";

        PackageOfData.putString("name", name.getText().toString());
        PackageOfData.putString("lastname", lastname.getText().toString());

        String emailValidate = email.getText().toString();

        if(Utility.isValidEmail(emailValidate)) {
            PackageOfData.putString("email", emailValidate);
        } else {
            result = "Correo Electronico invalido";

        }

        String passwordText = password.getText().toString();
        String confirm_passwordText=confirm_password.getText().toString();

        if (passwordText.compareTo(confirm_passwordText) == 0)
        {
            PackageOfData.putString("password", passwordText);
        }else {
            result += "\t" + "Las contraseñas no coinciden";
        }
        String phoneText = phone.getText().toString();

        if (Utility.isValidPhone(phoneText))
        {
            PackageOfData.putInt("phone", Integer.parseInt(phoneText));
        }else {
            result += "\t" + "El Telefono Movil es incorrecto";
        }

        if (photoExist){
            byte[] profilePhotoBytes = Utility.convertImageToByte(profilePhoto);
            PackageOfData.putByteArray("profilePhoto", profilePhotoBytes);
        }else {
            result += "\t" + "No seleccionaste foto de perfil";
        }



        if (result == "") {
            intent.putExtras(PackageOfData);
            startActivity(intent);
        } else {

            AlertDialog.Builder errorDisplay = new AlertDialog.Builder(this);
            errorDisplay.setTitle("Error");
            errorDisplay.setMessage(result);
            errorDisplay.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface errorDisplay, int id) {
                    errorDisplay.dismiss();
                }
            });
            errorDisplay.show();
        }

        }
    }
