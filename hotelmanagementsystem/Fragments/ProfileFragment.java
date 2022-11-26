package com.mustafa.hotelmanagementsystem.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mustafa.hotelmanagementsystem.R;
import com.mustafa.hotelmanagementsystem.databinding.FragmentProfileBinding;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    SQLiteDatabase sqLiteDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater,container,false);

        try {
            sqLiteDatabase = binding.getRoot().getContext().openOrCreateDatabase("HotelSystem", Context.MODE_PRIVATE,null);

        }catch (SQLException e){
            e.printStackTrace();
        }

        binding.changeCompanyName.setOnClickListener(item -> {
            binding.companyEditText.setEnabled(true);
            binding.cancelText.setVisibility(View.VISIBLE);
            binding.saveBtn.setEnabled(true);
        });

        binding.changeEmailAddress.setOnClickListener(item -> {
            binding.emailEditText.setEnabled(true);
            binding.cancelText.setVisibility(View.VISIBLE);
            binding.saveBtn.setEnabled(true);
        });

        binding.changePassword.setOnClickListener(item -> {
            binding.passwordEditText.setTransformationMethod(new HideReturnsTransformationMethod());
            binding.passwordEditText.setEnabled(true);
            binding.cancelText.setVisibility(View.VISIBLE);
            binding.saveBtn.setEnabled(true);
        });

        binding.cancelText.setOnClickListener(item -> {
            binding.companyEditText.setEnabled(false);
            binding.emailEditText.setEnabled(false);
            binding.passwordEditText.setEnabled(false);
            binding.cancelText.setVisibility(View.INVISIBLE);
            binding.saveBtn.setEnabled(false);
            //binding.passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            binding.passwordEditText.setTransformationMethod(new PasswordTransformationMethod());
            checkData();
        });

        binding.saveBtn.setOnClickListener(item -> {
            if(!binding.companyEditText.getText().equals("") && !binding.emailEditText.getText().equals("")&&
            !binding.passwordEditText.getText().equals("")){
                try {
                    sqLiteDatabase.execSQL("UPDATE Users SET companyName='"+binding.companyEditText.getText().toString()+"'," +
                            "userEmail='"+binding.emailEditText.getText().toString().trim()+"'," +
                            "password='"+binding.passwordEditText.getText().toString().trim()+"'");
                    Toast.makeText(binding.getRoot().getContext(), "Successfully Updated!", Toast.LENGTH_LONG).show();
                    binding.cancelText.setVisibility(View.INVISIBLE);
                    checkData();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(binding.getRoot().getContext(), "Fill required fields!", Toast.LENGTH_LONG).show();
            }

        });

        checkData();
        return binding.getRoot();
    }

    public void checkData(){
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Users",null);
            for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                binding.companyEditText.setText(cursor.getString(1));
                binding.emailEditText.setText(cursor.getString(2));
                binding.passwordEditText.setText(cursor.getString(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}