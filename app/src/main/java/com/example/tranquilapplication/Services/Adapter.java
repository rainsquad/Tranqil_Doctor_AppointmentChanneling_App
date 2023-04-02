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
        holder.patientId.setText("Patient Name : "+users.get(position).getPatientid());
        holder.patientResults.setText("EPDS screening : "+users.get(position).getPatienttestresults() + " marks");
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
    public  static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView patientResults,patientId;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            patientResults = itemView.findViewById(R.id.testResults);
            patientId = itemView.findViewById(R.id.patientId);
        }
    }
}
