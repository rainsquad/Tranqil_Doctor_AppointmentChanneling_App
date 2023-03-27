package com.example.tranquilapplication.MainActivities;

import static com.example.tranquilapplication.ResponseModels.Constants.KEY_ID;
import static com.example.tranquilapplication.ResponseModels.Constants.KEY_NAME;
import static com.example.tranquilapplication.ResponseModels.Constants.PREFERENCE_NAME;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.tranquilapplication.Models.NetworkClient;
import com.example.tranquilapplication.Models.NetworkService;
import com.example.tranquilapplication.R;

import com.example.tranquilapplication.ResponseModels.Constants;
import com.example.tranquilapplication.ResponseModels.DoctorScheduleResponseModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDoctorActivity extends AppCompatActivity implements View.OnClickListener {
    TextView docName, timeSlot, UseridHidden, DateselectHidden;
    SharedPreferences sharedPreferences;
    int imagevalue;
    ImageView imgdoc1, imgBack;

    private CalendarView calendarView;
    Date todaydate = Calendar.getInstance().getTime();
    Button btnBook, txt1, txt2, txt3, txt4, txt5, txt6;

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
        DateselectHidden = findViewById(R.id.DateselectHidden);

        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);
        txt5 = findViewById(R.id.txt5);
        txt6 = findViewById(R.id.txt6);
        btnBook = findViewById(R.id.btnBook);

        txt1.setOnClickListener(this);
        txt2.setOnClickListener(this);
        txt3.setOnClickListener(this);
        txt4.setOnClickListener(this);
        txt5.setOnClickListener(this);
        txt6.setOnClickListener(this);
        btnBook.setOnClickListener(this);

        //Getting Loged-in user using shared preferences
        sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);
        String Userrid = sharedPreferences.getString(KEY_ID, null);
        UseridHidden.setText(Userrid);


        calendarView = findViewById(R.id.calenderView);
        calendarView.setMinDate((new Date().getTime()));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String selectedDate = sdf.format(new Date(calendarView.getDate()));

        OnDateChangeListener();


        String doc1name = getIntent().getStringExtra("doc1");
        docName.setText(doc1name);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            imagevalue = bundle.getInt("doc1img");
        }
        imgdoc1.setImageResource(imagevalue
        );

        //Get Calender view selected date to a String value
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookDoctorActivity.super.onBackPressed();
            }
        });


    }


    private void OnDateChangeListener() {

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
                String msg = dayOfMonth + "/" + (month + 1) + "/" + year;
                Toast.makeText(BookDoctorActivity.this, msg, Toast.LENGTH_SHORT).show();
                DateselectHidden.setText(msg);
            }
        });
    }

    //Validating time selector
    @Override
    public void onClick(View view) {


        txt1.setBackground(ContextCompat.getDrawable(this, R.drawable.time_select_round_button));
        txt2.setBackground(ContextCompat.getDrawable(this, R.drawable.time_select_round_button));
        txt3.setBackground(ContextCompat.getDrawable(this, R.drawable.time_select_round_button));
        txt4.setBackground(ContextCompat.getDrawable(this, R.drawable.time_select_round_button));
        txt5.setBackground(ContextCompat.getDrawable(this, R.drawable.time_select_round_button));
        txt6.setBackground(ContextCompat.getDrawable(this, R.drawable.time_select_round_button));
        btnBook.setBackground(ContextCompat.getDrawable(this, R.drawable.time_select_round_button));

        //Validating answer selection ( User should select an answer before pressing submit button, and also user should answer all 10 questions)

        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.btnBook) {

            if (selectedAnswer.equals("08.00 am - 09.00 am")) {

                String time = txt1.getText().toString();
                timeSlot.setText(time);
                UploadDoctorSchedule();
            } else if (selectedAnswer.equals("09.00 am - 10.00 am")) {
                String time = txt2.getText().toString();
                timeSlot.setText(time);
                UploadDoctorSchedule();

            } else if (selectedAnswer.equals("10.00 am - 11.00 am")) {
                String time = txt3.getText().toString();
                timeSlot.setText(time);
                UploadDoctorSchedule();

            } else if (selectedAnswer.equals("01.00 pm - 02.00 pm")) {
                String time = txt4.getText().toString();
                timeSlot.setText(time);
                UploadDoctorSchedule();

            } else if (selectedAnswer.equals("02.00 pm - 03.00 pm")) {
                String time = txt5.getText().toString();
                timeSlot.setText(time);
                UploadDoctorSchedule();

            } else if (selectedAnswer.equals("03.00 pm - 04.00 pm")) {
                String time = txt6.getText().toString();
                timeSlot.setText(time);
                UploadDoctorSchedule();

            } else {
                Toast.makeText(this, "select time slot", Toast.LENGTH_SHORT).show();
            }


        } else {
            selectedAnswer = clickedButton.getText().toString();
            // clickedButton.setBackgroundColor(Color.MAGENTA);
            clickedButton.setBackground(ContextCompat.getDrawable(this, R.drawable.time_select_round_button_grey));

        }
    }

//push data to the database
    public void UploadDoctorSchedule() {
        HashMap<String, String> params = new HashMap<>();
        params.put("doctorid", docName.getText().toString());
        params.put("patientid", UseridHidden.getText().toString());
        params.put("bookeddate", DateselectHidden.getText().toString());
        params.put("bookedtime", timeSlot.getText().toString());
        doctorschedule(params);
    }


    private void doctorschedule(HashMap<String, String> params) {
        final ProgressDialog progressDialog = new ProgressDialog(BookDoctorActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Booking...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
        Call<DoctorScheduleResponseModel> doc = networkService.doctorschedule(params);
        doc.enqueue(new Callback<DoctorScheduleResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<DoctorScheduleResponseModel> call, @NonNull Response<DoctorScheduleResponseModel> response) {
                DoctorScheduleResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCE_NAME, MODE_PRIVATE);
                        Intent intent = new Intent(BookDoctorActivity.this, DoctorDetailsActivity.class);

                        startActivity(intent);

                        finish();
                    } else {
                        Toast.makeText(BookDoctorActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(@NonNull Call<DoctorScheduleResponseModel> call, @NonNull Throwable t) {

                Toast.makeText(BookDoctorActivity.this, "Connection Failed", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}