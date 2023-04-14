package com.example.tranquilapplication.MainActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.tranquilapplication.R;

public class ScreenTestStartActivity extends AppCompatActivity {

    Button btnStartScreening, btnPreviousResults;
    ImageView imgBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_test_start);


        btnStartScreening = findViewById(R.id.btnstartscreening);
        btnPreviousResults = findViewById(R.id.btnpreviousresults);
        imgBack = findViewById(R.id.imgBack);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScreenTestStartActivity.super.onBackPressed();
                finish();
            }
        });



        btnStartScreening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScreenTestStartActivity.this,ScreenTestIntroActivity.class);
                startActivity(intent);
            }
        });
        btnPreviousResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ScreenTestStartActivity.this,PreviousTestResultsActivity.class);
                startActivity(intent1);
            }
        });
    }
}