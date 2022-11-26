package com.mustafa.hotelmanagementsystem.Fragments;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.mustafa.hotelmanagementsystem.databinding.FragmentReservationsBinding;

public class ReservationsFragment extends Fragment {

    SQLiteDatabase sqLiteDatabase;
    String custName,custSurname,custPhoneNumber,entryDate,exitDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentReservationsBinding binding = FragmentReservationsBinding.inflate(inflater,container,false);

        try {
            sqLiteDatabase = binding.getRoot().getContext().openOrCreateDatabase("HotelSystem", Context.MODE_PRIVATE,null);

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Reservations(id INTEGER PRIMARY KEY," +
                    "customerName VARCHAR,customerSurname VARCHAR,customerPhoneNumber VARCHAR,entryDate VARCHAR,exitDate VARCHAR)");
        }catch (SQLException e){
            e.printStackTrace();
        }


        binding.createReservationBtn.setOnClickListener(item -> {
            custName = binding.customerNameText.getText().toString();
            custSurname = binding.customerSurnameText.getText().toString();
            custPhoneNumber = binding.customerPhoneNumberText.getText().toString();
            entryDate = binding.entryDateText.getText().toString();
            exitDate = binding.exitDateText.getText().toString();

            if(!custName.equals("") && !custSurname.equals("") && !custPhoneNumber.equals("") && !entryDate.equals("")){
                sqLiteDatabase.execSQL("INSERT INTO Reservations(customerName,customerSurname,customerPhoneNumber," +
                        "entryDate,exitDate) VALUES('"+custName.trim()+"','"+custSurname.trim()+"'," +
                        "'"+custPhoneNumber.trim()+"','"+entryDate+"','"+exitDate+"');");
                Toast.makeText(binding.getRoot().getContext(), "Success!", Toast.LENGTH_LONG).show();

                binding.customerNameText.setText("");
                binding.customerPhoneNumberText.setText("");
                binding.customerSurnameText.setText("");
                binding.entryDateText.setText("");
                binding.exitDateText.setText("");
                binding.roomNumberText.setText("");

            }else{
                Toast.makeText(binding.getRoot().getContext(), "Enter the required fields!", Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }
}