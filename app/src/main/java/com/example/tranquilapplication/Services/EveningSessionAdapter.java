package com.example.tranquilapplication.Services;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tranquilapplication.MainActivities.DoctorAppointmentManagerActivity;
import com.example.tranquilapplication.R;
import com.example.tranquilapplication.ResponseModels.Users;

import java.util.List;

public class EveningSessionAdapter extends RecyclerView.Adapter<EveningSessionAdapter.MyViewHolder> {

    private List<Users> users;
    private Context context;


    public EveningSessionAdapter(List<Users> users, Context context) {
        this.users = users;

        this.context = context;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_item, parent, false);
        MyViewHolder evh = new MyViewHolder(view);
        return evh;

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String timeslot;
        holder.patientId.setText("PATIENT NAME : " + users.get(position).getPatientid());
        holder.patientResults.setText("EPDS SCORE : " + users.get(position).getPatienttestresults());
        holder.appointmentId.setText(""+users.get(position).getId());
        holder.status.setText("APPOINTMENT STATUS : "+users.get(position).getDoctorapprovalstatus());
        holder.TimeslotRequested.setText(""+users.get(position).getTimeslotrequested());




        if (position == 0) {
            holder.indexhide.setText("TIME SLOT : 2.00 pm - 3.00 pm");
            timeslot = "TIME SLOT : 2.00 pm - 3.00 pm";
        } else if (position == 1) {
            holder.indexhide.setText("TIME SLOT : 3.00 pm - 4.00 pm");
            timeslot = "TIME SLOT : 3.00 pm - 4.00 pm";
        } else {
            holder.indexhide.setText("TIME SLOT : 4.00 pm - 5.00 pm");
            timeslot = "TIME SLOT : 4.00 pm - 5.00 pm";
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DoctorAppointmentManagerActivity.class);
                i.putExtra("pName", holder.patientId.getText().toString());
                i.putExtra("tMarks", holder.patientResults.getText().toString());
                i.putExtra("tSlot", holder.indexhide.getText().toString());
                i.putExtra("AppointmentId",Integer.parseInt(holder.appointmentId.getText().toString()));
                i.putExtra("Status",holder.status.getText().toString());

                context.startActivity(i);
            }
        });


    }


    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView patientResults, patientId, indexhide, appointmentId, status,TimeslotRequested;


        public MyViewHolder(@NonNull View itemView
        ) {
            super(itemView);
            patientResults = itemView.findViewById(R.id.testResults);
            patientId = itemView.findViewById(R.id.patientId);
            indexhide = itemView.findViewById(R.id.indexhide);
            appointmentId = itemView.findViewById(R.id.aId);
            status = itemView.findViewById(R.id.status);
            TimeslotRequested = itemView.findViewById(R.id.timeslotrequest);



        }
    }
}
