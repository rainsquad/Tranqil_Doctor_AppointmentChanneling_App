package com.example.tranquilapplication.MainActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tranquilapplication.R;
import com.example.tranquilapplication.ResponseModels.AppointmentResponseModel;
import com.example.tranquilapplication.ResponseModels.Users;
import com.example.tranquilapplication.Services.MorningSessionAdapter;
import com.example.tranquilapplication.Services.NetworkClient;
import com.example.tranquilapplication.Services.NetworkService;
import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorAppointmentManagerActivity extends AppCompatActivity {
    TextView pName, tMarks, timeSlot, ID, acceptHidden, idHidden,txtStatus,CallIdPatient;

//    EditText UserID;

    LinearLayout VideoCall;
    ImageView imgBack3;

    private MorningSessionAdapter adapter;
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
        VideoCall = findViewById(R.id.startVC);
        txtStatus = findViewById(R.id.status);
        CallIdPatient = findViewById(R.id.callIdPatient);
      //  UserID = findViewById(R.id.VidCallUserId);

        Intent i = getIntent();
        String a = i.getStringExtra("pName");
        String b = i.getStringExtra("tMarks");
        String c = i.getStringExtra("tSlot");
        int id = i.getIntExtra("AppointmentId",0);
        //String id = i.getStringExtra("AppointmentId");
        String d = i.getStringExtra("Status");

        pName.setText(a);
        tMarks.setText(b);
        timeSlot.setText(c);
        ID.setText("APPOINTMENT ID "+String.valueOf(id));
        txtStatus.setText(d);
        CallIdPatient.setText("Doctor");


        if (txtStatus.getText().toString().equals("APPOINTMENT STATUS : ACCEPTED"))
        {
         accept.setVisibility(View.INVISIBLE);
         VideoCall.setVisibility(View.VISIBLE);
        //    accept.setClickable(false);
        }else if(txtStatus.getText().toString().equals("APPOINTMENT STATUS : DECLINED"))
        {
            decline.setVisibility(View.INVISIBLE);
            VideoCall.setVisibility(View.INVISIBLE);
        }


        //Button clicked Activities
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
        VideoCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           // String  userID = UserID.getText().toString().trim();

//                //String userID = String.valueOf(id);
//                String userID = ID.getText().toString();
            String userName = pName.getText().toString().trim();

                String userID = CallIdPatient.getText().toString().trim();
            if (userID.isEmpty())
            {
                return;
            }

                startService(userID);
                Intent intent = new Intent(DoctorAppointmentManagerActivity.this, VideoCallActivityDoctor.class);
                intent.putExtra("userID",userID);
                intent.putExtra("UserName",userName );
                startActivity(intent);
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

                    onBackPressed();
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
                    onBackPressed();
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
    void startService(String userID)
    {
        Application application = getApplication() ; // Android's application context
        long appID = 322567899;   // yourAppID
        String appSign ="627cb0f4278939a5ac3021af6de632daf7f992ea0235fe4f588a5b0078bf5b58";  // yourAppSign
       // yourUserID, userID should only contain numbers, English characters, and '_'.
        String userName =userID;   // yourUserName
        System.out.println(userID);
        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();
        callInvitationConfig.notifyWhenAppRunningInBackgroundOrQuit = true;
        ZegoNotificationConfig notificationConfig = new ZegoNotificationConfig();
        notificationConfig.sound = "zego_uikit_sound_call";
        notificationConfig.channelID = "CallInvitation";
        notificationConfig.channelName = "CallInvitation";
        ZegoUIKitPrebuiltCallInvitationService.init(getApplication(), appID, appSign, userID, userName,callInvitationConfig);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ZegoUIKitPrebuiltCallInvitationService.unInit();
    }
}