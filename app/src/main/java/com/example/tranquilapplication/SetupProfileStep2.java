package com.example.tranquilapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SetupProfileStep2 extends AppCompatActivity {
    ImageView imgBack1;
    Button btnNext1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_profile_step2);
        imgBack1 = findViewById(R.id.imageView5);
        btnNext1 = findViewById(R.id.btnSubmit2);

        imgBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetupProfileStep2.this, SetupProfileStep1.class);
                startActivity(intent);

            }

        });
        btnNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SetupProfileStep2.this, SetupProfileStep3.class);
                startActivity(intent1);
            }
        });

    }
}