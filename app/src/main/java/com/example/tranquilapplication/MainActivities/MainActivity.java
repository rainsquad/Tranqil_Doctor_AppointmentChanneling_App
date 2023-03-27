package com.example.tranquilapplication.MainActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tranquilapplication.R;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private TextView txtLinkLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        txtLinkLogin = findViewById(R.id.lnkLogin);

//        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();


        //blur image
        btn = (Button) findViewById(R.id.btnGetStarted);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSetupProfile();


            }
        });
        txtLinkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });


    }
    public void openSetupProfile(){
        Intent intent = new Intent(this, SetupProfileActivity.class);
        startActivity(intent);

    }

}