package com.example.tranquilapplication.Services;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tranquilapplication.MainActivities.PatientAppointmentManagerActivity;
import com.example.tranquilapplication.R;
import com.example.tranquilapplication.ResponseModels.Users;

import java.util.List;

public class AdapterPreviousResults extends RecyclerView.Adapter <AdapterPreviousResults.MyViewHolder>{

    private List<Users> users;
    private Context context;

    public AdapterPreviousResults(List<Users> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_item_previous_test_results, parent, false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bookedDate.setText(""+users.get(position).getBookeddate());
        holder.patientResults.setText("" + users.get(position).getPatienttestresults());





    }

    @Override
    public int getItemCount() {
        return users.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView bookedDate,patientResults;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            patientResults = itemView.findViewById(R.id.testResults);
            bookedDate = itemView.findViewById(R.id.date);





        }


    }




}
