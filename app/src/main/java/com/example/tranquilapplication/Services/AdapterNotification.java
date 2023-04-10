package com.example.tranquilapplication.Services;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tranquilapplication.R;
import com.example.tranquilapplication.ResponseModels.Users;

import java.util.List;

public class AdapterNotification extends RecyclerView.Adapter <AdapterNotification.MyViewHolder>{

    private List<Users> users;
    private Context context;

    public AdapterNotification(List<Users> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_item_notification, parent, false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bookedDate.setText("BOOKED DATE : "+users.get(position).getBookeddate());
        holder.DoctorName.setText("CONSULTANT : "+users.get(position).getDoctorid() );
        holder.status.setText("APPOINTMENT STATUS : " + users.get(position).getDoctorapprovalstatus());



    }

    @Override
    public int getItemCount() {
        return users.size();
    }
    public  static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView bookedDate,DoctorName,status,date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookedDate = itemView.findViewById(R.id.date);
            DoctorName = itemView.findViewById(R.id.doctorId);
            status = itemView.findViewById(R.id.status);


        }
    }
}
