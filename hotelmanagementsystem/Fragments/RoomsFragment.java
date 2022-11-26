package com.mustafa.hotelmanagementsystem.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.DropBoxManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mustafa.hotelmanagementsystem.R;
import com.mustafa.hotelmanagementsystem.databinding.FragmentRoomsBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.function.ObjIntConsumer;


public class RoomsFragment extends Fragment {
    SQLiteDatabase sqLiteDatabase;
    FragmentRoomsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRoomsBinding.inflate(inflater,container,false);

        try {
            sqLiteDatabase = binding.getRoot().getContext().openOrCreateDatabase("HotelSystem", Context.MODE_PRIVATE,null);
        }catch (SQLException e){
            e.printStackTrace();
        }

        checkRoom();

        return binding.getRoot();
    }

    public void checkRoom(){

        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Customers",null);
            for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                for(int i=101;i<=112;i++){
                    if(Objects.equals(cursor.getString(5), String.valueOf(i))){
                        if(i == 101){
                           binding.room1open.setVisibility(View.INVISIBLE);
                           binding.room1close.setVisibility(View.VISIBLE);
                        }else if(i == 102){
                            binding.room2open.setVisibility(View.INVISIBLE);
                            binding.room2close.setVisibility(View.VISIBLE);
                        }else if(i == 103){
                            binding.room3open.setVisibility(View.INVISIBLE);
                            binding.room3close.setVisibility(View.VISIBLE);
                        }else if(i == 104){
                            binding.room4open.setVisibility(View.INVISIBLE);
                            binding.room4close.setVisibility(View.VISIBLE);
                        }else if(i == 105){
                            binding.room5open.setVisibility(View.INVISIBLE);
                            binding.room5close.setVisibility(View.VISIBLE);
                        }else if(i == 106){
                            binding.room6open.setVisibility(View.INVISIBLE);
                            binding.room6close.setVisibility(View.VISIBLE);
                        }else if(i == 107){
                            binding.room7open.setVisibility(View.INVISIBLE);
                            binding.room7close.setVisibility(View.VISIBLE);
                        }else if(i == 108){
                            binding.room8open.setVisibility(View.INVISIBLE);
                            binding.room8close.setVisibility(View.VISIBLE);
                        }else if(i == 109){
                            binding.room9open.setVisibility(View.INVISIBLE);
                            binding.room9close.setVisibility(View.VISIBLE);
                        }else if(i == 110){
                            binding.room10open.setVisibility(View.INVISIBLE);
                            binding.room10close.setVisibility(View.VISIBLE);
                        }else if(i == 111){
                            binding.room11open.setVisibility(View.INVISIBLE);
                            binding.room11close.setVisibility(View.VISIBLE);
                        }else if(i == 112){
                            binding.room12open.setVisibility(View.INVISIBLE);
                            binding.room12close.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}