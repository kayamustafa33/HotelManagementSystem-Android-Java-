package com.mustafa.hotelmanagementsystem.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mustafa.hotelmanagementsystem.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private SQLiteDatabase sqLiteDatabase;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());

        sqLiteDatabase = openOrCreateDatabase("HotelSystem",MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Users(id INTEGER PRIMARY KEY,companyName VARCHAR,userEmail VARCHAR,password VARCHAR);");
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getBoolean("saveLogin", false);

        checkRememberUser();
    }

    public void loginClicked(View view){
        Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM Users",null);
        String userEmail = binding.userEmailText.getText().toString();
        String userPassword = binding.userPasswordText.getText().toString();


        if(!userEmail.equals("") && !userPassword.equals("")){
            for(result.moveToFirst();!result.isAfterLast();result.moveToNext()){
                if(userEmail.equals(result.getString(2)) && userPassword.equals(result.getString(3))){

                    if(binding.checkBox.isChecked()){
                        loginPrefsEditor.putBoolean("saveLogin", true);
                        loginPrefsEditor.putString("username", userEmail);
                        loginPrefsEditor.putString("password", userPassword);
                        loginPrefsEditor.commit();
                    }else {
                        loginPrefsEditor.clear();
                        loginPrefsEditor.commit();
                    }


                    Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Users",null);
                    cursor.moveToFirst();
                    String email = loginPreferences.getString("username","");
                    String password = loginPreferences.getString("password","");

                    if(cursor.getString(1) != null && cursor.getString(2) != null){
                        if(email.equals(cursor.getString(2)) && password.equals(cursor.getString(3))){
                            String companyName2 = cursor.getString(1);
                            String userEmail2 = cursor.getString(2);
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            i.putExtra("companyName",companyName2);
                            i.putExtra("userEmail",userEmail2);
                            startActivity(i);
                            finish();
                        }

                    }


                    sqLiteDatabase.close();

                }else{
                    Toast.makeText(this, "Wrong password or e-mail!", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Toast.makeText(this, "Enter the E-mail and Password!", Toast.LENGTH_LONG).show();
        }

    }

    public void forgotClicked(View view){

    }

    public void checkRememberUser(){
        if (saveLogin) {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Users",null);
            cursor.moveToFirst();
            String email = loginPreferences.getString("username","");
            String password = loginPreferences.getString("password","");

            if(cursor.getString(1) != null && cursor.getString(2) != null){
                if(email.equals(cursor.getString(2)) && password.equals(cursor.getString(3))){
                    String companyName = cursor.getString(1);
                    String userEmail = cursor.getString(2);
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra("companyName",companyName);
                    i.putExtra("userEmail",userEmail);
                    startActivity(i);
                    finish();
                }

            }
        }
    }

    public void goToRegisterPage(View view){
        startActivity(new Intent(LoginActivity.this,RegisterUserActivity.class));
    }
}