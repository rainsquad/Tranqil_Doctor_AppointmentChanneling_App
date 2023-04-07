package com.example.tranquilapplication.MainActivities;

import static com.example.tranquilapplication.Services.Constants.KEY_BOOKED_DATE;
import static com.example.tranquilapplication.Services.Constants.KEY_SLOTS_LEFT;
import static com.example.tranquilapplication.Services.Constants.KEY_DOC_ID;
import static com.example.tranquilapplication.Services.Constants.KEY_NAME;

import static com.example.tranquilapplication.Services.Constants.KEY_SLOTS_LEFT;
import static com.example.tranquilapplication.Services.Constants.PREFERENCE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tranquilapplication.Services.NetworkClient;
import com.example.tranquilapplication.Services.NetworkService;
import com.example.tranquilapplication.R;

import com.example.tranquilapplication.Services.Constants;
import com.example.tranquilapplication.ResponseModels.DocResponseModel;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDoctorActivity extends AppCompatActivity implements View.OnClickListener {
    TextView docName, timeSlot, UseridHidden, DateselectHidden, abcdef, txt1, setDatecheck;

    TextInputEditText testDate, testMarks;
    SharedPreferences sharedPreferences;
    int imagevalue;
    int score = 0;
    ImageView imgdoc1, imgBack;

    private CalendarView calendarView;

    Date todaydate = Calendar.getInstance().getTime();
    Button btnBook, txt1Hidden, testRecycle;
    ProgressBar progressBar;
    String selectedAnswer = "", selectedDate, Userid, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_doctor);


        docName = findViewById(R.id.docName);
        imgdoc1 = findViewById(R.id.imgView1);
        imgBack = findViewById(R.id.imgBack);
        timeSlot = findViewById(R.id.timeslotHidden);
        UseridHidden = findViewById(R.id.UseridHidden);
        testMarks = findViewById(R.id.testMarks);

        calendarView = findViewById(R.id.calenderView);

        DateselectHidden = findViewById(R.id.DateselectHidden);
        txt1 = findViewById(R.id.txt1);
        txt1Hidden = findViewById(R.id.txt1Hidden);
        abcdef = findViewById(R.id.abcdef);
        btnBook = findViewById(R.id.btnBook);

        calendarView.setMinDate((new Date().getTime()));
        btnBook.setOnClickListener(this);


        //Getting Loged-in user using shared preferences
        sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        //   String name = sharedPreferences.getString(KEY_NAME, null);
        String Userrid = sharedPreferences.getString(KEY_NAME, null);
        UseridHidden.setText(Userrid);


        // String sdf = String.format("%04d.%02d.%04d",year, month + 1,dayOfMonth);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String selectedDate = sdf.format(new Date(calendarView.getDate()));
        DateselectHidden.setText(selectedDate);


        //  OnDateChangeListener();


        //get doctor name from previous activity
        String doc1name = getIntent().getStringExtra("doc1");
        docName.setText(doc1name);


        //Get test marks from previous activity
        Intent intentcard1 = getIntent();
        int score = intentcard1.getIntExtra("testMarksFinal", 0);
        testMarks.setText(String.valueOf(score));

        //GetImage of the selected doctor from previous Activity
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            imagevalue = bundle.getInt("doc1img");
        }
        imgdoc1.setImageResource(imagevalue);


        doctorcheck();


        //Get Calender view selected date to a String value
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookDoctorActivity.super.onBackPressed();
            }
        });


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//                String sd = sdf.format(new Date(calendarView.getDate()));

                String sd = String.format("%04d/%02d/%02d", year, month + 1, dayOfMonth);
                DateselectHidden.setText(sd);

                doctorcheck();
            }
        });

    }

//    //What happens when u select a date from the calender
//    private void OnDateChangeListener() {
//
//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//
//             //   String str_selectedDate = String.format("%02d.%02d.%04d", dayOfMonth, month + 1,year );
//          //      SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
//             //   String selectedDate = sdf.format(new Date(calendarView.getDate()));
//             //   DateselectHidden.setText(selectedDate);
//              //  Toast.makeText(BookDoctorActivity.this, selectedDate, Toast.LENGTH_SHORT).show();
//
////                String sdf = String.format("%04d.%02d.%04d",year, month + 1,dayOfMonth);
////
////                String selectedDate = sdf.format(String.valueOf(new Date(calendarView.getDate())));
//
//
//
//
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//                String selectedDate = sdf.format(new Date(calendarView.getDate()));
//                DateselectHidden.setText(selectedDate);
//
//                // Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
//
//
//                doctorcheck();
//            }
//        });
//    }

    //Validating time selector
    @Override
    public void onClick(View view) {


        txt1.setBackground(ContextCompat.getDrawable(this, R.drawable.background_time_select_round_button));

        btnBook.setBackground(ContextCompat.getDrawable(this, R.drawable.background_time_select_round_button));

        //Validating answer selection ( User should select an answer before pressing submit button, and also user should answer all 10 questions)

        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.btnBook) {
            testMarks.getText().toString();
            if (txt1.getText().toString().equals("6 SLOTS")) {
                timeSlot.setText("5 SLOTS");
                UploadDoctorSchedule();
            } else if (txt1.getText().toString().equals("5 SLOTS")) {
                timeSlot.setText("4 SLOTS");
                UploadDoctorSchedule();
            } else if (txt1.getText().toString().equals("4 SLOTS")) {
                timeSlot.setText("3 SLOTS");
                UploadDoctorSchedule();
            } else if (txt1.getText().toString().equals("3 SLOTS")) {
                timeSlot.setText("2 SLOTS");
                UploadDoctorSchedule();
            } else if (txt1.getText().toString().equals("2 SLOTS")) {
                timeSlot.setText("1 SLOTS");
                UploadDoctorSchedule();
            } else if (txt1.getText().toString().equals("1 SLOTS")) {
                timeSlot.setText("0 SLOTS");
                UploadDoctorSchedule();
            } else {
                Toast.makeText(this, "NO SLOTS AVAILABLE TODAY", Toast.LENGTH_SHORT).show();

            }


        } else {
            selectedAnswer = clickedButton.getText().toString();
            // clickedButton.setBackgroundColor(Color.MAGENTA);
            clickedButton.setBackground(ContextCompat.getDrawable(this, R.drawable.background_time_select_round_button_grey));

        }
    }

    //push data to the database
    public void UploadDoctorSchedule() {
        String tMarks = testMarks.getText().toString();
        score = Integer.parseInt(tMarks);
        if (testMarks.getText().toString().equals("0")) {
            Toast.makeText(this, "Enter your test results ", Toast.LENGTH_SHORT).show();
        } else if (testMarks.getText().toString().isEmpty()) {

            Toast.makeText(this, "Enter your test results ", Toast.LENGTH_SHORT).show();

        } else if (score > 30) {
            Toast.makeText(this, "Your test marks should be between 10-30", Toast.LENGTH_SHORT).show();
        } else if (score < 10) {
            Toast.makeText(this, "Your test marks should be between 10-30", Toast.LENGTH_SHORT).show();
        } else {


            HashMap<String, String> params = new HashMap<>();
            params.put("doctorid", docName.getText().toString());
            params.put("patientid", UseridHidden.getText().toString());
            params.put("bookeddate", DateselectHidden.getText().toString());
            params.put("slotsleft", timeSlot.getText().toString());
            params.put("patienttestresults", testMarks.getText().toString());

            doctorschedule(params);
        }
    }


    private void doctorschedule(HashMap<String, String> params) {
        final ProgressDialog progressDialog = new ProgressDialog(BookDoctorActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Booking...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
        Call<DocResponseModel> doc = networkService.doctorschedule(params);
        doc.enqueue(new Callback<DocResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<DocResponseModel> call, @NonNull Response<DocResponseModel> response) {
                DocResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCE_NAME, MODE_PRIVATE);
                        Intent i = new Intent(BookDoctorActivity.this, AppointmentConfirmedActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(BookDoctorActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(@NonNull Call<DocResponseModel> call, @NonNull Throwable t) {


                progressDialog.dismiss();
            }
        });
    }

    private void doctorcheck() {

        final ProgressDialog progressDialog = new ProgressDialog(BookDoctorActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Getting doctor schedule ready...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
        Call<DocResponseModel> doctorcheck = networkService.doctorcheck(docName.getText().toString(), DateselectHidden.getText().toString());
        doctorcheck.enqueue(new Callback<DocResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<DocResponseModel> call, @NonNull Response<DocResponseModel> response) {
                DocResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean(Constants.KEY_ISE_LOGGED_IN, true);
                        editor.putString(KEY_DOC_ID, responseBody.getDocDetailObject().getDocDetails().get(0).getDoctorid());
                        editor.putString(Constants.KEY_PATIENT_ID, responseBody.getDocDetailObject().getDocDetails().get(0).getPatientid());
                        editor.putString(KEY_BOOKED_DATE, responseBody.getDocDetailObject().getDocDetails().get(0).getBookeddate());
                        editor.putString(KEY_SLOTS_LEFT, responseBody.getDocDetailObject().getDocDetails().get(0).getSlotsleft());


                        editor.apply();


                        Toast.makeText(BookDoctorActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();

                        display();
                    } else if (responseBody.getSuccess().equals("0")) {
                        txt1.setText("6 SLOTS");
                        Toast.makeText(BookDoctorActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {

                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<DocResponseModel> call, @NonNull Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    public void display() {
        sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_DOC_ID, null);
        String date = sharedPreferences.getString(KEY_BOOKED_DATE, null);
        String time = sharedPreferences.getString(KEY_SLOTS_LEFT, null);


        txt1.setText(time);
    }

}