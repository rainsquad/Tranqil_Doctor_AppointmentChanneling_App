package com.example.tranquilapplication.MainActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tranquilapplication.R;

public class DoctorDetailsExpandActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    int imagevalue;
    ImageView DoctorPic,backbuttonimg;
    TextView docName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details_expand);
        docName = findViewById(R.id.DocName);
        DoctorPic = findViewById(R.id.DoctorPic);
        backbuttonimg = findViewById(R.id.imgBack2);


        String doc1name = getIntent().getStringExtra("doc1");
        docName.setText(doc1name);





        //GetImage of the selected doctor from previous Activity
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            imagevalue = bundle.getInt("doc1img");
        }
        DoctorPic.setImageResource(imagevalue
        );


        backbuttonimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoctorDetailsExpandActivity.super.onBackPressed();
            }
        });

    }
}