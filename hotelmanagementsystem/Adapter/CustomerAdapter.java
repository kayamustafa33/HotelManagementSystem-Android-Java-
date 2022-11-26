package com.mustafa.hotelmanagementsystem.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mustafa.hotelmanagementsystem.Classes.Customers;
import com.mustafa.hotelmanagementsystem.databinding.RecyclerRowBinding;
import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerHolder> {
    ArrayList<Customers> arrayList;

    public CustomerAdapter(ArrayList<Customers> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CustomerAdapter.CustomerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new CustomerHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.CustomerHolder holder, int position) {
        holder.recyclerRowBinding.customerName.setText("Customer Name: " + arrayList.get(position).custName + " " + arrayList.get(position).custSurname);
        holder.recyclerRowBinding.customerIdentity.setText("Identity Number: " + arrayList.get(position).custIdentityNumber);
        holder.recyclerRowBinding.customerPhone.setText("Phone Number: " + arrayList.get(position).custPhoneNumber);
        holder.recyclerRowBinding.roomNumberEntryDateExitDate.setText("Room: "+arrayList.get(position).roomNumber +" - Entry Date: "+arrayList.get(position).entryDate+" - Exit Date: "+arrayList.get(position).exitDate);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void filteredList(ArrayList<Customers> filteredList){
        arrayList = filteredList;
        notifyDataSetChanged();
    }

    public class CustomerHolder extends RecyclerView.ViewHolder {

        RecyclerRowBinding recyclerRowBinding;

        public CustomerHolder(RecyclerRowBinding recyclerRowBinding) {
            super(recyclerRowBinding.getRoot());
            this.recyclerRowBinding = recyclerRowBinding;
        }
    }
}
