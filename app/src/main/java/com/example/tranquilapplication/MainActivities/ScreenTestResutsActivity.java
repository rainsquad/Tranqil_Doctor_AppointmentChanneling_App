package com.example.tranquilapplication.MainActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tranquilapplication.R;

public class ScreenTestResutsActivity extends AppCompatActivity {

    TextView txtView;

    TextView txtResultsSuggest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_test_resuts);
        txtView = findViewById(R.id.txtScore);


       //Get the score value from previous activity
        Intent myintent = getIntent();
        int score = myintent.getIntExtra("message", 0);
        txtView.setText("SCORE IS : " + score + "/30");


        txtResultsSuggest = findViewById(R.id.txtResultsSuggest);
        if (score <= 8)
        {
            txtResultsSuggest.setText("Your test score of <score>/30 suggests that you have no risk for <Postpartum or Antepartum> Depression at the moment. What you are experiancing could be a mild temporary discomfort.");
        }
        else
        {
            txtResultsSuggest.setText(" Your test score of <score>/30 suggests that you may be having  a risk for <Postpartum or Antepartum> Depression. You absolutely have nothing to worry about since <Postpartum or Antepartum> Depression is a completely normal and treatable condition!");
        }



    }
}