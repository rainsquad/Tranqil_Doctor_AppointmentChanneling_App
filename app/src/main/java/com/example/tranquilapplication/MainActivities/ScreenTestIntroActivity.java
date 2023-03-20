package com.example.tranquilapplication.MainActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.tranquilapplication.R;

public class ScreenTestIntroActivity extends AppCompatActivity {

    Button btnNext;
    ImageView imgBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_test_intro);
        btnNext = findViewById(R.id.btnstartscreening);
        imgBack = findViewById(R.id.imgBack);



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(ScreenTestIntroActivity.this,QuestionnaireActivity.class);
                startActivity(intent2);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ScreenTestIntroActivity.this,ScreenTestStartActivity.class);
            }
        });

    }




}