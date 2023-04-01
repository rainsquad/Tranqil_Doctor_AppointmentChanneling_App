package com.example.tranquilapplication.Services;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tranquilapplication.R;
import com.example.tranquilapplication.ResponseModels.Users;

import java.util.List;

public class Adapter extends RecyclerView.Adapter <Adapter.MyViewHolder>{

    private List<Users> users;
    private Context context;

    public Adapter(List<Users> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bookedDate.setText(users.get(position).getBookeddate());
        holder.bookedTime.setText(users.get(position).getBookedtime());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
    public  static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView bookedDate,bookedTime;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookedDate = itemView.findViewById(R.id.date);
            bookedTime = itemView.findViewById(R.id.time);
        }
    }
}
