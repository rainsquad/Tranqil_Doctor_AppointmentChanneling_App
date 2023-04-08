package com.example.tranquilapplication.MainActivities;

import static com.example.tranquilapplication.Services.Constants.KEY_NAME;
import static com.example.tranquilapplication.Services.Constants.PREFERENCE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tranquilapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MyAccountActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    Button btnlogout;

    TextView username;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        username = findViewById(R.id.username);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        btnlogout = findViewById(R.id.btnLogOut);

        sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);
        username.setText(name);
        bottomNavigationView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:


                        Intent a = new Intent(MyAccountActivity.this,MainMenuActivity.class);
                        startActivity(a);
                        return;

                    case  R.id.notification:
                        Intent b = new Intent(MyAccountActivity.this, NotificationActivity.class);
                        startActivity(b);
                        return;

                    case R.id.Menu:
                        Intent c = new Intent(MyAccountActivity.this, MyAccountActivity.class);
                        startActivity(c);
                        return;


                }
            }
        });

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SplashScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
            }
        });

    }
}