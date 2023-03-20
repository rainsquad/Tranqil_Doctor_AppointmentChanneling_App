package com.example.tranquilapplication.MainActivities;

import static com.example.tranquilapplication.ResponseModels.Constants.KEY_ID;
import static com.example.tranquilapplication.ResponseModels.Constants.PREFERENCE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tranquilapplication.R;


public class MainMenuActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView txtLoggedInUser;
    private static final String KEY_NAME = "name";
    private static final String KEY_ID = "id";

    private LinearLayout selectEPDS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        txtLoggedInUser = findViewById(R.id.Loggedinuser);
        selectEPDS = findViewById(R.id.clickEPDS);


        //Getting Loged-in user using shared preferences
        sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);
        String id = sharedPreferences.getString(KEY_ID,null);

        txtLoggedInUser.setText("user " + name + "id :" +id );


        selectEPDS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, ScreenTestStartActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

}
