package com.example.tranquilapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CreateAccount extends AppCompatActivity {
    Button btnNext3;
    ImageView imgBack3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        btnNext3 = findViewById(R.id.btnSubmit4);
        imgBack3 = findViewById(R.id.imageView7);



        imgBack3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAccount.this, SetupProfileStep3.class);
                startActivity(intent);

            }

        });
        btnNext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(CreateAccount.this, SetupProfileStep3.class);
                startActivity(intent1);
            }
        });

    }
}