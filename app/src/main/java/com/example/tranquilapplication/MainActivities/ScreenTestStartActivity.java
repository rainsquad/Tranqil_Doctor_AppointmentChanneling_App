package com.example.tranquilapplication.MainActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tranquilapplication.R;

public class ScreenTestStartActivity extends AppCompatActivity {

    Button btnStartScreening, btnPreviousResults, btn1, btn2, btn3;
    ImageView imgBack;
    TextView txtPara1, txtPara2, question;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_test_start);


        btnStartScreening = findViewById(R.id.btnstartscreening);
        btnPreviousResults = findViewById(R.id.btnpreviousresults);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        question = findViewById(R.id.question);
        txtPara1 = findViewById(R.id.textView5);
        txtPara2 = findViewById(R.id.textView6);

        imgBack = findViewById(R.id.imgBack);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScreenTestStartActivity.super.onBackPressed();
                finish();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1.setBackground(ContextCompat.getDrawable(ScreenTestStartActivity.this, R.drawable.button_round));
                btn2.setBackground(ContextCompat.getDrawable(ScreenTestStartActivity.this, R.drawable.button_round_white));
                btn3.setBackground(ContextCompat.getDrawable(ScreenTestStartActivity.this, R.drawable.button_round_white));
                txtPara1.setText("EPDS is the internationally recognized screening test for the detection of the vulnerability for postpartum depression which is currently being used in Sri Lanka as well. This questionaire contains 10 standard questions that must be answered based on your experiance during past week.");
                txtPara2.setText("This screening test requires no professional expertise unless your test feedback suggests you to consult a psychiatrist");
                question.setText("Edinburgh Postnatal Depression Scale");
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1.setBackground(ContextCompat.getDrawable(ScreenTestStartActivity.this, R.drawable.button_round_white));
                btn2.setBackground(ContextCompat.getDrawable(ScreenTestStartActivity.this, R.drawable.button_round));
                btn3.setBackground(ContextCompat.getDrawable(ScreenTestStartActivity.this, R.drawable.button_round_white));
                txtPara1.setText("EPDS යනු දැනට ශ්රී ලංකාවේ ද භාවිතා වන පශ්චාත් ප්රසව විෂාදය සඳහා ඇති අවදානම හඳුනාගැනීම සඳහා ජාත්යන්තරව පිළිගත් පරීක්ෂණ පරීක්ෂණයයි. මෙම ප්රශ්නාවලියෙහි පසුගිය සතියේ ඔබේ අත්දැකීම් මත පදනම්ව පිළිතුරු සැපයිය යුතු සම්මත ප්රශ්න 10ක් අඩංගු වේ.");
                txtPara2.setText("ඔබේ පරීක්ෂණ ප්රතිපෝෂණය ඔබට මනෝ වෛද්යවරයෙකුගෙන් උපදෙස් ලබා ගැනීමට යෝජනා කරන්නේ නම් මිස මෙම පිරික්සුම් පරීක්ෂණයට වෘත්තීය විශේෂඥතාවයක් අවශ්ය නොවේ");
                question.setText("එඩින්බරෝ පශ්චාත් ප්\u200Dරසව අවපාත පරිමාණය");
            }
        });


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1.setBackground(ContextCompat.getDrawable(ScreenTestStartActivity.this, R.drawable.button_round_white));
                btn2.setBackground(ContextCompat.getDrawable(ScreenTestStartActivity.this, R.drawable.button_round_white));
                btn3.setBackground(ContextCompat.getDrawable(ScreenTestStartActivity.this, R.drawable.button_round));
                txtPara1.setText("EPDS என்பது மகப்பேற்றுக்கு பிறகான மனச்சோர்வுக்கான பாதிப்பைக் கண்டறிவதற்கான சர்வதேச அங்கீகாரம் பெற்ற ஸ்கிரீனிங் சோதனையாகும், இது தற்போது இலங்கையிலும் பயன்படுத்தப்படுகிறது. இந்த வினாத்தாளில் கடந்த வாரத்தில் உங்கள் அனுபவத்தின் அடிப்படையில் பதிலளிக்க வேண்டிய 10 நிலையான கேள்விகள் உள்ளன.");
                txtPara2.setText("இந்த ஸ்கிரீனிங் சோதனைக்கு தொழில்முறை நிபுணத்துவம் தேவையில்லை, உங்கள் சோதனை பின்னூட்டம் மனநல மருத்துவரை அணுகுமாறு பரிந்துரைக்கும் வரை");
                question.setText("எடின்பர்க் பிரசவத்திற்கு முந்தைய மனச்சோர்வு அளவுகோல்");
            }
        });


        btnStartScreening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScreenTestStartActivity.this, ScreenTestIntroActivity.class);
                startActivity(intent);
            }
        });
        btnPreviousResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ScreenTestStartActivity.this, PreviousTestResultsActivity.class);
                startActivity(intent1);
            }
        });
    }
}