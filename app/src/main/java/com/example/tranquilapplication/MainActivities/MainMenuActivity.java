package com.example.tranquilapplication.MainActivities;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.tranquilapplication.Fragments.HomeFragment;
import com.example.tranquilapplication.Fragments.MenuFragment;
import com.example.tranquilapplication.Fragments.NotificationFragment;
import com.example.tranquilapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainMenuActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    HomeFragment mainMenuFragment = new HomeFragment();
    NotificationFragment notificationFragment = new NotificationFragment();
    MenuFragment menuFragment = new MenuFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);



        getSupportFragmentManager().beginTransaction().replace(R.id.container,mainMenuFragment).commit();
        bottomNavigationView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected( MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,mainMenuFragment).commit();
                        return;




                    case R.id.notification:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,notificationFragment).commit();
                        return;


                    case R.id.Menu:

                        getSupportFragmentManager().beginTransaction().replace(R.id.container,menuFragment).commit();
                        return;


                }
               return ;
            }
        });



    }




}
