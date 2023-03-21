package com.example.tranquilapplication.MainActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.tranquilapplication.R;

public class ScreenTestResutsActivity extends AppCompatActivity {

    TextView txtView,txtNextStep,txtResultsSuggest;

    Button btnNextStep;

    LinearLayout popupbg;
    Dialog mDialog;
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_test_resuts);
        txtView = findViewById(R.id.txtScore);
        txtNextStep = findViewById(R.id.txtNextStep);
        btnNextStep = findViewById(R.id.btnNextStep);
        btnNextStep.setVisibility(View.INVISIBLE);
        mDialog = new Dialog(this);
        layout = findViewById(R.id.testpopup);


       //Get the score value from previous activity
        Intent myintent = getIntent();
        int score = myintent.getIntExtra("message", 0);
        String depressionType = myintent.getStringExtra("depressionType");

        txtView.setText("YOUR SCORE IS : " + score + "/30  ");


        txtResultsSuggest = findViewById(R.id.txtResultsSuggest);
        if (score <= 8)
        {
            txtResultsSuggest.setText("YOUR TEST SCORE OF " + score+"/30 SUGGESTS THAT YOU HAVE NO RISK OF "+depressionType+" DEPRESSION AT THE TIME. WHAT YPU ARE EXPERIENCING COULD BE A MILD DISCOMFORT");
            txtNextStep.setText("IN CASE YOU CONTINUE EXPERIENCING ANY DISCOMFORTING SYMPTOMS IT IS RECOMMENDED THAT YOU CONDUCT ANOTHER SCREENING TEST IN 2 WEEKS TIME.");

        }
        else
        {
            txtResultsSuggest.setText("YOUR TEST SCORE OF " + score+"/30 SUGGESTS THAT YOU MAY BE HAVING A RISK OF "+depressionType+" DEPRESSION. YOU ABSOLUTELY HAVE NOTHING TO WORRY ABOUT SINCE "+depressionType+" DEPRESSION IS A COMPLETELY NORMAL AND TREATABLE CONDITION!");
            txtNextStep.setText("SINCE YOUR TEST SCORE REFLECTS A RISK FOR "+ depressionType+" DEPRESSION, WE SUGGEST THAT YOU PROCEED TO CLINICAL DIAGNOSIS WITH A PSYCHIATRIST.");
            btnNextStep.setText("CLICK HERE");
            btnNextStep.setVisibility(View.VISIBLE);
        }
        btnNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//               Intent intentmy = new Intent(ScreenTestResutsActivity.this,Testpopupwindow.class);
//               startActivity(intentmy);
                CreatepopUpwindow();
            }
        });


    }
    private void CreatepopUpwindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.mainpopup, null);

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
        TextView Skip, Gotit;

        Gotit = popUpView.findViewById(R.id.Gotit);

        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // write code anything you want
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



}