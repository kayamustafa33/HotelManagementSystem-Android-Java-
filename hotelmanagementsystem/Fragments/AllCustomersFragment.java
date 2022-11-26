package com.mustafa.hotelmanagementsystem.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.mustafa.hotelmanagementsystem.Adapter.CustomerAdapter;
import com.mustafa.hotelmanagementsystem.Classes.Customers;
import com.mustafa.hotelmanagementsystem.R;
import com.mustafa.hotelmanagementsystem.databinding.FragmentAllCustomersBinding;
import java.util.ArrayList;

public class AllCustomersFragment extends Fragment {

    ArrayList<Customers> arrayList;
    CustomerAdapter adapter;
    SQLiteDatabase sqLiteDatabase;
    FragmentAllCustomersBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAllCustomersBinding.inflate(inflater,container,false);
        sqLiteDatabase = binding.getRoot().getContext().openOrCreateDatabase("HotelSystem", Context.MODE_PRIVATE,null);

        arrayList = new ArrayList<>();

        binding.recyclerViewCustomers.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        adapter = new CustomerAdapter(arrayList);
        binding.recyclerViewCustomers.setAdapter(adapter);


        getData();
        return binding.getRoot();
    }

    public void getData(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Customers",null);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            Customers customers = new Customers(cursor.getString(1), cursor.getString(2),
                    cursor.getString(3),cursor.getString(4), cursor.getString(5),
                    cursor.getString(6),cursor.getString(7));
            arrayList.add(customers);

        }

        if(arrayList.isEmpty()){
            Toast.makeText(binding.getRoot().getContext(), "There is no customer!", Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();
    }
}