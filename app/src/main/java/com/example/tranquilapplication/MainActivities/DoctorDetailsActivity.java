package com.example.tranquilapplication.MainActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.tranquilapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DoctorDetailsActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    CardView crd1,crd2,crd3,crd4,crd5,crd6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        bottomNavigationView =findViewById(R.id.bottomNavigationView);
        crd1 = findViewById(R.id.crd1);
        crd2 = findViewById(R.id.crd2);
        crd3= findViewById(R.id.crd3);
        crd4= findViewById(R.id.crd4);
        crd5 = findViewById(R.id.crd5);
        crd6 = findViewById(R.id.crd6);


        Intent doc = getIntent();
        int score = doc.getIntExtra("testMarks", 0);

        bottomNavigationView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        Intent a = new Intent(DoctorDetailsActivity.this,MainMenuActivity.class);
                        startActivity(a);
                        return;

                    case  R.id.notification:
                        Intent b = new Intent(DoctorDetailsActivity.this, DoctorDailyScheduleActivity.class);
                        startActivity(b);
                        return;


                }
            }
        });


            crd1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentcard1  = new Intent(DoctorDetailsActivity.this,BookDoctorActivity.class);
                    intentcard1.putExtra("doc1","Dr.Sachini Jayawardane");
                    intentcard1.putExtra("doc1img",R.drawable.doctor1);
                    intentcard1.putExtra("testMarksFinal",score);

                    startActivity(intentcard1);
                }
            });


          crd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentcard1  = new Intent(DoctorDetailsActivity.this,BookDoctorActivity.class);
                intentcard1.putExtra("doc1","Dr.Ajith Kabral");
                intentcard1.putExtra("doc1img",R.drawable.doctor2);
                startActivity(intentcard1);
            }
        });

        crd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentcard1  = new Intent(DoctorDetailsActivity.this,BookDoctorActivity.class);
                intentcard1.putExtra("doc3","Dr.Shalani Jayawardane");
                intentcard1.putExtra("doc1img",R.drawable.doctor3);
                startActivity(intentcard1);
            }
        });


        crd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentcard1  = new Intent(DoctorDetailsActivity.this,BookDoctorActivity.class);
                intentcard1.putExtra("doc1","Dr. Sanjula Weerage");
                intentcard1.putExtra("doc1img",R.drawable.doctor4);
                startActivity(intentcard1);
            }
        });

        crd5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentcard1  = new Intent(DoctorDetailsActivity.this,BookDoctorActivity.class);
                intentcard1.putExtra("doc1","Dr.Thilini Kampitiya");
                intentcard1.putExtra("doc1img",R.drawable.doctor5);
                startActivity(intentcard1);
            }
        });


        crd6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentcard1  = new Intent(DoctorDetailsActivity.this,BookDoctorActivity.class);
                intentcard1.putExtra("doc1","Dr.Wageesha Ediriweera");
                intentcard1.putExtra("doc1img",R.drawable.doctor6);
                startActivity(intentcard1);
            }
        });



    }





}