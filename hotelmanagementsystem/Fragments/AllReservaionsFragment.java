package com.mustafa.hotelmanagementsystem.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mustafa.hotelmanagementsystem.Adapter.ReservationAdapter;
import com.mustafa.hotelmanagementsystem.Classes.Reservations;
import com.mustafa.hotelmanagementsystem.R;
import com.mustafa.hotelmanagementsystem.databinding.FragmentAllReservaionsBinding;

import java.util.ArrayList;

public class AllReservaionsFragment extends Fragment {

    FragmentAllReservaionsBinding binding;
    SQLiteDatabase sqLiteDatabase;
    ArrayList<Reservations> arrayList;
    ReservationAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAllReservaionsBinding.inflate(inflater,container,false);

        arrayList = new ArrayList<>();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        adapter = new ReservationAdapter(arrayList);
        binding.recyclerView.setAdapter(adapter);

        try {
            sqLiteDatabase = binding.getRoot().getContext().openOrCreateDatabase("HotelSystem", Context.MODE_PRIVATE,null);

        }catch (SQLException e){
            e.printStackTrace();
        }

        getData();
        return binding.getRoot();
    }

    public void getData(){

        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Reservations",null);
            for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                Reservations reservations = new Reservations(cursor.getString(1),cursor.getString(2),cursor.getString(3),
                        cursor.getString(4), cursor.getString(5));
                arrayList.add(reservations);
            }

            if(arrayList.isEmpty()){
                Toast.makeText(binding.getRoot().getContext(), "There is no reservation!", Toast.LENGTH_SHORT).show();
            }
            adapter.notifyDataSetChanged();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}