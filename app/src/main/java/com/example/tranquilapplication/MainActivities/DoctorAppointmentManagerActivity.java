package com.example.tranquilapplication.MainActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tranquilapplication.R;
import com.example.tranquilapplication.ResponseModels.AppointmentResponseModel;
import com.example.tranquilapplication.ResponseModels.Users;
import com.example.tranquilapplication.Services.Adapter;
import com.example.tranquilapplication.Services.NetworkClient;
import com.example.tranquilapplication.Services.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorAppointmentManagerActivity extends AppCompatActivity {
    TextView pName, tMarks, timeSlot, ID, acceptHidden, idHidden;
    ImageView imgBack3;

    private Adapter adapter;
    private NetworkService apiInterface;
    private List<Users> users;

    Button accept, decline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointment_manager);

        pName = findViewById(R.id.pName);
        tMarks = findViewById(R.id.tMarks);
        timeSlot = findViewById(R.id.timeSlot);
        imgBack3 = findViewById(R.id.imgBack3);
        ID = findViewById(R.id.appointmentId);
        accept = findViewById(R.id.accept);
        decline = findViewById(R.id.decline);
        acceptHidden = findViewById(R.id.accepthidden);
        idHidden = findViewById(R.id.idHidden);


        Intent i = getIntent();
        String a = i.getStringExtra("pName");
        String b = i.getStringExtra("tMarks");
        String c = i.getStringExtra("tSlot");
         int id = i.getIntExtra("AppointmentId",0);


        pName.setText(a);
        tMarks.setText(b);
        timeSlot.setText(c);
        ID.setText(String.valueOf(id));
      //  idHidden.setText(String.valueOf(d));



        imgBack3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoctorAppointmentManagerActivity.super.onBackPressed();
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acceptHidden.setText("ACCEPTED");

                acceptAppoint("update",id);

            }
        });
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acceptHidden.setText("DECLINED");
                updateData("update",id);

            }
        });


    }

    private void acceptAppoint(final String key, final int id) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();


        String timeslot = timeSlot.getText().toString();
        String appAccept = acceptHidden.getText().toString().trim();


        apiInterface = NetworkClient.getClient().create(NetworkService.class);

        Call<AppointmentResponseModel> call = apiInterface.doctorappointmentaccept(key, id,appAccept,timeslot);

        call.enqueue(new Callback<AppointmentResponseModel>() {
            @Override
            public void onResponse(Call<AppointmentResponseModel> call, Response<AppointmentResponseModel> response) {

                progressDialog.dismiss();

                Log.i(DoctorAppointmentManagerActivity.class.getSimpleName(), response.toString());

                String value = response.body().getSuccess();
                String message = response.body().getMessage();

                if (value.equals("1")){
                    Toast.makeText(DoctorAppointmentManagerActivity.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DoctorAppointmentManagerActivity.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AppointmentResponseModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DoctorAppointmentManagerActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateData(final String key, final int id) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();



        String appAccept = acceptHidden.getText().toString().trim();


        apiInterface = NetworkClient.getClient().create(NetworkService.class);

        Call<AppointmentResponseModel> call = apiInterface.doctorappointmentdeclined(key, id,appAccept);

        call.enqueue(new Callback<AppointmentResponseModel>() {
            @Override
            public void onResponse(Call<AppointmentResponseModel> call, Response<AppointmentResponseModel> response) {

                progressDialog.dismiss();

                Log.i(DoctorAppointmentManagerActivity.class.getSimpleName(), response.toString());

                String value = response.body().getSuccess();
                String message = response.body().getMessage();

                if (value.equals("1")){
                    Toast.makeText(DoctorAppointmentManagerActivity.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DoctorAppointmentManagerActivity.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AppointmentResponseModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DoctorAppointmentManagerActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}