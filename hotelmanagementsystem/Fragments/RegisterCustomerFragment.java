package com.mustafa.hotelmanagementsystem.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.mustafa.hotelmanagementsystem.Classes.Customers;
import com.mustafa.hotelmanagementsystem.databinding.FragmentRegisterCustomerBinding;

import java.util.ArrayList;

public class RegisterCustomerFragment extends Fragment {

    String custName,custSurname,custIdentityNumber,custPhoneNumber,roomNumber,entryDate,exitDate;
    SQLiteDatabase sqLiteDatabase;
    ArrayList<Customers> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentRegisterCustomerBinding binding = FragmentRegisterCustomerBinding.inflate(inflater,container,false);

        arrayList = new ArrayList<>();

        try {
            sqLiteDatabase = binding.getRoot().getContext().openOrCreateDatabase("HotelSystem", Context.MODE_PRIVATE,null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Customers (id INTEGER PRIMARY KEY,customerName VARCHAR," +
                    "customerSurname VARCHAR,customerIdentityNumber VARCHAR,customerPhoneNumber VARCHAR," +
                    "roomNumber INT,entryDate VARCHAR,exitDate VARCHAR)");
        }catch (Exception e){
            e.printStackTrace();
        }


        binding.registerCustomerBtn.setOnClickListener(item -> {
            custName = binding.customerNameText.getText().toString();
            custSurname = binding.customerSurnameText.getText().toString();
            custIdentityNumber = binding.customerIdentityNumberText.getText().toString();
            custPhoneNumber = binding.customerPhoneNumberText.getText().toString();
            roomNumber = binding.roomNumberText.getText().toString();
            entryDate = binding.entryDateText.getText().toString();
            exitDate = binding.exitDateText.getText().toString();

            if(!custName.equals("") && !custSurname.equals("") && !custIdentityNumber.equals("") &&
            !custPhoneNumber.equals("") && !roomNumber.equals("") && !entryDate.equals("")){
                try {
                    sqLiteDatabase.execSQL("INSERT INTO Customers(customerName,customerSurname,customerIdentityNumber," +
                            "customerPhoneNumber,roomNumber,entryDate,exitDate) VALUES('"+custName+"','"+custSurname+"'," +
                            "'"+custIdentityNumber+"','"+custPhoneNumber+"','"+roomNumber+"','"+entryDate+"','"+exitDate+"');");
                }catch (Exception e){
                    e.printStackTrace();
                }

                Toast.makeText(binding.getRoot().getContext(), "Success!", Toast.LENGTH_LONG).show();

                binding.customerNameText.setText("");
                binding.customerSurnameText.setText("");
                binding.customerIdentityNumberText.setText("");
                binding.customerPhoneNumberText.setText("");
                binding.roomNumberText.setText("");
                binding.entryDateText.setText("");
                binding.exitDateText.setText("");
            }else{
                Toast.makeText(binding.getRoot().getContext(), "Enter the required fields!", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }
}