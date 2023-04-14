package com.example.tranquilapplication.MainActivities;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tranquilapplication.R;
import com.example.tranquilapplication.ResponseModels.Users;
import com.example.tranquilapplication.Services.Adapter;
import com.example.tranquilapplication.Services.NetworkService;
import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

import java.util.List;

public class PatientAppointmentManagerActivity extends AppCompatActivity {
    TextView pName, tMarks, timeSlot, ID, acceptHidden, idHidden,txtStatus,callIdDoctor;

//    EditText UserID;

    LinearLayout VideoCall;
    ImageView imgBack3;

    private Adapter adapter;
    private NetworkService apiInterface;
    private List<Users> users;

    Button accept, decline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_appointment_manager);

        pName = findViewById(R.id.pName);
        tMarks = findViewById(R.id.tMarks);
        timeSlot = findViewById(R.id.timeSlot);
        imgBack3 = findViewById(R.id.imgBack3);
        ID = findViewById(R.id.appointmentId);

        acceptHidden = findViewById(R.id.accepthidden);
        idHidden = findViewById(R.id.idHidden);
        VideoCall = findViewById(R.id.startVC);
        txtStatus = findViewById(R.id.status);
        callIdDoctor = findViewById(R.id.callIdDoctor);
      //  UserID = findViewById(R.id.VidCallUserId);

        Intent i = getIntent();
        String a = i.getStringExtra("dName");
        String b = i.getStringExtra("tMarks");
        String c = i.getStringExtra("Date");
        int id = i.getIntExtra("AppointmentId",0);
        //String id = i.getStringExtra("AppointmentId");
        String d = i.getStringExtra("Status");

        pName.setText(a);
        tMarks.setText(b);
        timeSlot.setText(c);
        ID.setText("APPOINTMENT ID "+String.valueOf(id));
        txtStatus.setText(d);
        callIdDoctor.setText("Doctor");




        //Button clicked Activities
        imgBack3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PatientAppointmentManagerActivity.super.onBackPressed();
            }
        });


        VideoCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           // String  userID = UserID.getText().toString().trim();

             //   String userID = String.valueOf(id);
//                String userID = ID.getText().toString();
              String userName = pName.getText().toString().trim();
                String userID =callIdDoctor.getText().toString().trim();

            if (userID.isEmpty())
            {
                return;
            }

                startService(userID);
                Intent intent = new Intent(PatientAppointmentManagerActivity.this, PatientVideoCallActivity.class);
                intent.putExtra("userID",userID);
                 intent.putExtra("UserName", userName);
                startActivity(intent);
            }
        });


    }

    void startService(String userID)
    {
        Application application = getApplication() ; // Android's application context
        long appID = 705018974;   // yourAppID
        String appSign ="c83717bc58d5fb0a2207999d980bb831d1f6305e2f6a90e6faf5e1a8b4061821";  // yourAppSign
       // yourUserID, userID should only contain numbers, English characters, and '_'.
        String userName =userID;   // yourUserName

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