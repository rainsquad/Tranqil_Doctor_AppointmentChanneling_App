package com.example.tranquilapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SetupProfileStep3 extends AppCompatActivity {

    ImageView imgBack2;
    Button btnNext2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_profile_step3);
        imgBack2 = findViewById(R.id.imageView6);
        btnNext2 = findViewById(R.id.btnSubmit3);

        imgBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetupProfileStep3.this, SetupProfileStep2.class);
                startActivity(intent);

            }

        });
        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SetupProfileStep3.this, CreateAccount.class);
                startActivity(intent1);
            }
        });

    }
}