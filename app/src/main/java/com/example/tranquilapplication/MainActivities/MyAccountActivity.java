package com.example.tranquilapplication.MainActivities;

import static com.example.tranquilapplication.Services.Constants.KEY_NAME;
import static com.example.tranquilapplication.Services.Constants.PREFERENCE_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tranquilapplication.R;

public class MyAccountActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        username = findViewById(R.id.username);


        sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);
        username.setText(name);

    }
}