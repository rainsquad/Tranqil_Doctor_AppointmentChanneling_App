package com.example.tranquilapplication.MainActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.tranquilapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DoctorDetailsActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    CardView crd1,crd2,crd3,crd4,crd5,crd6;

    LinearLayout book1,book2,book3,book4,book5,book6,clickID1,clickID2,clickID3,clickID4,clickID5,clickID6;

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

        book1 = findViewById(R.id.book1);
        book2 = findViewById(R.id.book2);
        book3= findViewById(R.id.book3);
        book4= findViewById(R.id.book4);
        book5 = findViewById(R.id.book5);
        book6 = findViewById(R.id.book6);
        clickID1 = findViewById(R.id.clickID1);
        clickID2 = findViewById(R.id.clickId2);
        clickID3 = findViewById(R.id.clickId3);
        clickID4 = findViewById(R.id.clickId4);
        clickID5 = findViewById(R.id.clickId5);
        clickID6 = findViewById(R.id.clickId6);



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


        clickID1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentcard1 = new Intent(DoctorDetailsActivity.this, DoctorDetailsExpandActivity.class);
                intentcard1.putExtra("doc1", "Dr.Sachini Jayawardane");
                intentcard1.putExtra("doc1img", R.drawable.icon_doctor1);
                intentcard1.putExtra("testMarksFinal", score);

                startActivity(intentcard1);
            }
        });


        clickID2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentcard1 = new Intent(DoctorDetailsActivity.this, DoctorDetailsExpandActivity.class);
                intentcard1.putExtra("doc1", "Dr.Ajith Kabral");
                intentcard1.putExtra("doc1img", R.drawable.icon_doctor2);
                intentcard1.putExtra("testMarksFinal", score);
                startActivity(intentcard1);
            }
        });

        clickID3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentcard1 = new Intent(DoctorDetailsActivity.this, DoctorDetailsExpandActivity.class);
                intentcard1.putExtra("doc1", "Dr.Shalani Jayawardane");
                intentcard1.putExtra("doc1img", R.drawable.icon_doctor3);
                intentcard1.putExtra("testMarksFinal", score);
                startActivity(intentcard1);
            }
        });


        clickID4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentcard1 = new Intent(DoctorDetailsActivity.this, DoctorDetailsExpandActivity.class);
                intentcard1.putExtra("doc1", "Dr. Sanjula Weerage");
                intentcard1.putExtra("doc1img", R.drawable.icon_doctor4);
                intentcard1.putExtra("testMarksFinal", score);
                startActivity(intentcard1);
            }
        });

        clickID5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentcard1 = new Intent(DoctorDetailsActivity.this, DoctorDetailsExpandActivity.class);
                intentcard1.putExtra("doc1", "Dr.Thilini Kampitiya");
                intentcard1.putExtra("doc1img", R.drawable.icon_doctor5);
                intentcard1.putExtra("testMarksFinal", score);
                startActivity(intentcard1);
            }
        });


        clickID6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentcard1 = new Intent(DoctorDetailsActivity.this, DoctorDetailsExpandActivity.class);
                intentcard1.putExtra("doc1", "Dr.Wageesha Ediriweera");
                intentcard1.putExtra("doc1img", R.drawable.icon_doctor6);
                intentcard1.putExtra("testMarksFinal", score);
                startActivity(intentcard1);
            }
        });








        book1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentcard1 = new Intent(DoctorDetailsActivity.this, BookDoctorActivity.class);
                intentcard1.putExtra("doc1", "Dr.Sachini Jayawardane");
                intentcard1.putExtra("doc1img", R.drawable.icon_doctor1);
                intentcard1.putExtra("testMarksFinal", score);

                startActivity(intentcard1);
            }
        });


        book2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentcard1 = new Intent(DoctorDetailsActivity.this, BookDoctorActivity.class);
                intentcard1.putExtra("doc1", "Dr.Ajith Kabral");
                intentcard1.putExtra("doc1img", R.drawable.icon_doctor2);
                intentcard1.putExtra("testMarksFinal", score);
                startActivity(intentcard1);
            }
        });

        book3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentcard1 = new Intent(DoctorDetailsActivity.this, BookDoctorActivity.class);
                intentcard1.putExtra("doc1", "Dr.Shalani Jayawardane");
                intentcard1.putExtra("doc1img", R.drawable.icon_doctor3);
                intentcard1.putExtra("testMarksFinal", score);
                startActivity(intentcard1);
            }
        });


        book4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentcard1 = new Intent(DoctorDetailsActivity.this, BookDoctorActivity.class);
                intentcard1.putExtra("doc1", "Dr. Sanjula Weerage");
                intentcard1.putExtra("doc1img", R.drawable.icon_doctor4);
                intentcard1.putExtra("testMarksFinal", score);
                startActivity(intentcard1);
            }
        });

        book5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentcard1 = new Intent(DoctorDetailsActivity.this, BookDoctorActivity.class);
                intentcard1.putExtra("doc1", "Dr.Thilini Kampitiya");
                intentcard1.putExtra("doc1img", R.drawable.icon_doctor5);
                intentcard1.putExtra("testMarksFinal", score);
                startActivity(intentcard1);
            }
        });


        book6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentcard1 = new Intent(DoctorDetailsActivity.this, BookDoctorActivity.class);
                intentcard1.putExtra("doc1", "Dr.Wageesha Ediriweera");
                intentcard1.putExtra("doc1img", R.drawable.icon_doctor6);
                intentcard1.putExtra("testMarksFinal", score);
                startActivity(intentcard1);
            }
        });



    }





}