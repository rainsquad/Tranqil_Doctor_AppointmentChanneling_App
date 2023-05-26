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

import com.example.tranquilapplication.MainActivities.DoctorAppointmentManagerActivity;
import com.example.tranquilapplication.MainActivities.PatientAppointmentManagerActivity;
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
        holder.patientResults.setText("EPDS SCORE : " + users.get(position).getPatienttestresults());
        holder.status.setText("APPOINTMENT STATUS : " + users.get(position).getDoctorapprovalstatus());
        holder.appointmentId.setText(""+users.get(position).getId());
        holder.appointId2.setText("APPOINTMENT ID "+users.get(position).getId());




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, PatientAppointmentManagerActivity.class);
                i.putExtra("dName", holder.DoctorName.getText().toString());
                i.putExtra("AppointmentId",Integer.parseInt(holder.appointmentId.getText().toString()));
                i.putExtra("Status",holder.status.getText().toString());
                i.putExtra("Date",holder.bookedDate.getText().toString());
                i.putExtra("tMarks",holder.patientResults.getText().toString());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView bookedDate,DoctorName,status,appointmentId,patientResults,appointId2;
        Button btnVideoCall;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            patientResults = itemView.findViewById(R.id.testResults);
            bookedDate = itemView.findViewById(R.id.date);
            DoctorName = itemView.findViewById(R.id.doctorId);
            status = itemView.findViewById(R.id.status);
            appointmentId = itemView.findViewById(R.id.appID);
            btnVideoCall = itemView.findViewById(R.id.startVC);
            appointId2 = itemView.findViewById(R.id.appID2);
    }


    }

}
