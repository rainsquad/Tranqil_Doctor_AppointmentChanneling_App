package com.example.tranquilapplication.MainActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tranquilapplication.R;
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;

public class VideoCallActivityDoctor extends AppCompatActivity {

   TextView HeyUserTV;
    EditText UserIDET;
    ZegoSendCallInvitationButton call, Vcall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call_doctor);

        UserIDET = findViewById(R.id.user_id_edit_text);
        HeyUserTV = findViewById(R.id.HeyUserId);
        call = findViewById(R.id.voicecall);
        Vcall = findViewById(R.id.VideoCall);


        String userID = getIntent().getStringExtra("userID");
     String userName = getIntent().getStringExtra("UserName");
        HeyUserTV.setText("Doctor");
        UserIDET.setText(userID);


        String targetUserID = HeyUserTV.getText().toString().trim();
        setVideoCall(targetUserID);
        setVoiceCall(targetUserID);

    }
    void setVoiceCall(String targetUserID) {

        call.setIsVideoCall(false);
        call.setResourceID("zego_uikit_call");
        call.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserID)));
    }


    void setVideoCall(String targetUserID) {
        Vcall.setIsVideoCall(true);
        Vcall.setResourceID("zego_uikit_call");
        Vcall.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserID)));
    }
}
