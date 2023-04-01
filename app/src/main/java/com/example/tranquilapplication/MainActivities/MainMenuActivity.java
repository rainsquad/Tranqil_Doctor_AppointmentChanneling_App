package com.example.tranquilapplication.MainActivities;

import static com.example.tranquilapplication.Services.Constants.KEY_BOOKED_DATE;
import static com.example.tranquilapplication.Services.Constants.PREFERENCE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.tranquilapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainMenuActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView txtLoggedInUser;

    CardView therapy;
    private static final String KEY_NAME = "name";
    private static final String KEY_ID = "id";

    BottomNavigationView bottomNavigationView;

    LinearLayout MidWives,clickForum;
    LinearLayout layout;
    private LinearLayout selectEPDS,selectMidvives,selectDoctors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        txtLoggedInUser = findViewById(R.id.Loggedinuser);
        selectEPDS = findViewById(R.id.clickEPDS);
        selectMidvives = findViewById(R.id.ClickMidwives);
        selectDoctors = findViewById(R.id.ClickDoctors);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        MidWives = findViewById(R.id.ClickMidwives);
        clickForum = findViewById(R.id.clickForum);
        layout = findViewById(R.id.linearLayout4);
        therapy = findViewById(R.id.therapy);



        //Getting Loged-in user using shared preferences
        sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);
        String id = sharedPreferences.getString(KEY_ID,null);

        txtLoggedInUser.setText(name);

        selectEPDS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, ScreenTestStartActivity.class);
                startActivity(intent);

            }
        });

        selectDoctors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainMenuActivity.this, DoctorDetailsActivity.class);
                startActivity(intent1);

            }
        });


        bottomNavigationView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:


                        Intent a = new Intent(MainMenuActivity.this,MainMenuActivity.class);
                        startActivity(a);
                        return;

                    case  R.id.notification:
                        Intent b = new Intent(MainMenuActivity.this, NotificationActivity.class);
                        startActivity(b);
                        return;


                }
            }
        });
        MidWives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreatepopUpwindow();
            }
        });
        clickForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreatepopUpwindow();
            }
        });


    }

    private void CreatepopUpwindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.premium_feature_popup, null);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

            }
        });

        TextView  Gotit;
        ImageView iconclose;

        Gotit = popUpView.findViewById(R.id.Gotit);
        iconclose = popUpView.findViewById(R.id.iconclose);

        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PremiumPaymentPopupWindow();
                popupWindow.dismiss();

            }
        });
        iconclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();

            }
        });
        // and if you want to close popup when touch Screen
        popUpView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
//premium payment popup window
    private void PremiumPaymentPopupWindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.premium_feature_paymentgate_popup, null);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);


            }
        });

        TextView  Gotit;
        ImageView iconclose;

        Gotit = popUpView.findViewById(R.id.Gotit);
        iconclose = popUpView.findViewById(R.id.iconClosepayementGate);

        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PremiumPaymentCardDetails();popupWindow.dismiss();

            }
        });
        iconclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();

            }
        });
    }

    //Premium payment enter card details popup

    private void PremiumPaymentCardDetails() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.premium_payment_details_enter_popup, null);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

            }
        });

        TextView  Gotit;
        ImageView iconclose;

        Gotit = popUpView.findViewById(R.id.Gotit);
        iconclose = popUpView.findViewById(R.id.iconclose);

        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PremiumPaymentDone();
                popupWindow.dismiss();

            }
        });
        iconclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();

            }
        });
    }

    private void PremiumPaymentDone() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.premium_payment_approved_popup, null);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

            }
        });

        TextView  Gotit;
        ImageView iconclose;

        Gotit = popUpView.findViewById(R.id.Gotit);
        iconclose = popUpView.findViewById(R.id.iconclose);

        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();

            }
        });
        iconclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();

            }
        });
    }



    }
