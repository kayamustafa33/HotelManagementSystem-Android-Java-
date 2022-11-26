package com.mustafa.hotelmanagementsystem.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.mustafa.hotelmanagementsystem.databinding.ActivityRegisterUserBinding;

public class RegisterUserActivity extends AppCompatActivity {

    ActivityRegisterUserBinding binding;
    SQLiteDatabase sqLiteDatabase;
    String companyName,userEmail,userPassword,confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterUserBinding.inflate(getLayoutInflater());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());
        
        try {
            sqLiteDatabase = this.openOrCreateDatabase("HotelSystem",MODE_PRIVATE,null);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public void signUpBtn(View view){
        companyName = binding.companyNameText.getText().toString();
        userEmail = binding.userEmailText.getText().toString();
        userPassword = binding.userPasswordText.getText().toString();
        confirmPassword = binding.confirmPasswordText.getText().toString();
        
        if(!companyName.equals("") && !userEmail.equals("") && !userPassword.equals("") && !confirmPassword.equals("")){
            if(confirmPassword.equals(userPassword)){
                try {
                    sqLiteDatabase.execSQL("INSERT INTO Users(companyName,userEmail,password) VALUES(" +
                            "'"+companyName+"'," +
                            "'"+userEmail.trim()+"'," +
                            "'"+userPassword.trim()+"')");
                    Toast.makeText(this, "Successfully Sign Up", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterUserActivity.this,LoginActivity.class));
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "Fill required fields!", Toast.LENGTH_LONG).show();
        }
    }

    public void goToLoginPage(View view){
        startActivity(new Intent(RegisterUserActivity.this,LoginActivity.class));
    }
}