package com.example.tranquilapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SetupProfileStep1 extends AppCompatActivity {
    ImageView imgBack;
    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_profile_step1);
        imgBack = findViewById(R.id.imageView4);
        btnNext = findViewById(R.id.btnSubmit1);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetupProfileStep1.this, MainActivity.class);
                startActivity(intent);

            }

        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SetupProfileStep1.this, SetupProfileStep2.class);
                startActivity(intent1);
            }
        });

    }
}