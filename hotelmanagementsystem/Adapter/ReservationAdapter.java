package com.mustafa.hotelmanagementsystem.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mustafa.hotelmanagementsystem.Classes.Reservations;
import com.mustafa.hotelmanagementsystem.databinding.RecyclerRowReservationBinding;

import java.util.ArrayList;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationHolder> {

    ArrayList<Reservations> arrayList;

    public ReservationAdapter(ArrayList<Reservations> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ReservationAdapter.ReservationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowReservationBinding recyclerRowReservationBinding = RecyclerRowReservationBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ReservationHolder(recyclerRowReservationBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationAdapter.ReservationHolder holder, int position) {
        holder.binding.customerName.setText("Customer Name: " + arrayList.get(position).custName);
        holder.binding.customerPhone.setText("Phone Number: " + arrayList.get(position).custPhoneNumber);
        holder.binding.entryDate.setText("Entry Date: " + arrayList.get(position).entryDate);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ReservationHolder extends RecyclerView.ViewHolder {
        RecyclerRowReservationBinding binding;
        public ReservationHolder(RecyclerRowReservationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
