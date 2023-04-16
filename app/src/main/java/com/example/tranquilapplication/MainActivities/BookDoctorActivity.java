package com.example.tranquilapplication.MainActivities;

import static com.example.tranquilapplication.Services.Constants.KEY_BOOKED_DATE;

import static com.example.tranquilapplication.Services.Constants.KEY_DOC_ID;
import static com.example.tranquilapplication.Services.Constants.KEY_NAME;


import static com.example.tranquilapplication.Services.Constants.KEY_SLOTS_LEFT_EVE;
import static com.example.tranquilapplication.Services.Constants.KEY_SLOTS_LEFT_MORNING;
import static com.example.tranquilapplication.Services.Constants.PREFERENCE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlarmManager;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tranquilapplication.ResponseModels.NotificationReminderBroadcadst;
import com.example.tranquilapplication.Services.NetworkClient;
import com.example.tranquilapplication.Services.NetworkService;
import com.example.tranquilapplication.R;

import com.example.tranquilapplication.Services.Constants;
import com.example.tranquilapplication.ResponseModels.DocResponseModel;
import com.google.android.material.textfield.TextInputEditText;
import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDoctorActivity extends AppCompatActivity implements View.OnClickListener {
    TextView docName, timeSlot1, timeSlot2, UseridHidden, DateselectHidden, abcdef, txt1, txt2, setDatecheck, PrefTimeSLotHidden, statusHidden;

    TextInputEditText testDate, testMarks;
    SharedPreferences sharedPreferences;
    int imagevalue;
    int score = 0;
    ImageView imgdoc1, imgBack;

    private CalendarView calendarView;

    Date todaydate = Calendar.getInstance().getTime();
    Button btnBook, txt1Hidden, testRecycle, ansA, ansB;
    ProgressBar progressBar;
    String selectedAnswer = "", selectedDate, Userid, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_doctor);

        PrefTimeSLotHidden = findViewById(R.id.PreferredTimeSlotHidden);
        docName = findViewById(R.id.docName);
        imgdoc1 = findViewById(R.id.imgView1);
        imgBack = findViewById(R.id.imgBack);
        timeSlot1 = findViewById(R.id.timeslotMornHidden);
        timeSlot2 = findViewById(R.id.timeslotEveHidden);

        UseridHidden = findViewById(R.id.UseridHidden);
        testMarks = findViewById(R.id.testMarks);
        calendarView = findViewById(R.id.calenderView);
        DateselectHidden = findViewById(R.id.DateselectHidden);
        statusHidden = findViewById(R.id.statusHidden);
        txt2 = findViewById(R.id.txt2);
        txt1 = findViewById(R.id.txt1);
        txt1Hidden = findViewById(R.id.txt1Hidden);
        abcdef = findViewById(R.id.abcdef);
        btnBook = findViewById(R.id.btnBook);
        calendarView.setMinDate((new Date().getTime()));
        btnBook.setOnClickListener(this);


        ansA = findViewById(R.id.answerA);
        ansB = findViewById(R.id.answerB);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);

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

        createNotificationChannel();

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

        String tMarks = testMarks.getText().toString();

        if (Integer.parseInt(tMarks)!=0)
        {
        testMarks.setEnabled(false);
        }else {
            testMarks.setEnabled(true);
        }


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
                ansA.setEnabled(true);
                ansB.setEnabled(true);

                doctorcheck();
            }
        });




    }
    private  void createNotificationChannel()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Tranquil notifications";
            String descripion = "Tranquil Appointment Manager";

            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("notifypatient", name, importance);
            channel.setDescription(descripion);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    //Validating time selector
    @Override
    public void onClick(View view) {


        btnBook.setBackground(ContextCompat.getDrawable(this, R.drawable.background_time_select_round_button));

        //Validating answer selection ( User should select an answer before pressing submit button, and also user should answer all 10 questions)


        Button clickedButton = (Button) view;


        if (ansA.isPressed()) {
            PrefTimeSLotHidden.setText("9.00 am - 12.30 am");
            ansA.setBackground(ContextCompat.getDrawable(this, R.drawable.button_round));

            ansB.setBackground(ContextCompat.getDrawable(this, R.drawable.button_round_grey));
        } else if (ansB.isPressed()) {
            ansA.setBackground(ContextCompat.getDrawable(this, R.drawable.button_round_grey));

            ansB.setBackground(ContextCompat.getDrawable(this, R.drawable.button_round));
            PrefTimeSLotHidden.setText("2.00 pm - 5.00pm");
        }


        if (clickedButton.getId() == R.id.btnBook) {
            testMarks.getText().toString();

            if (PrefTimeSLotHidden.getText().toString().equals("9.00 am - 12.30 am")) {

                if (txt1.getText().toString().equals("3 SLOTS")) {
                    timeSlot1.setText("2 SLOTS");
                    timeSlot2.setText(txt2.getText().toString());
                    checktestMrks();
                } else if (txt1.getText().toString().equals("2 SLOTS")) {
                    timeSlot1.setText("1 SLOTS");
                    timeSlot2.setText(txt2.getText().toString());
                    checktestMrks();
                } else if (txt1.getText().toString().equals("1 SLOTS")) {
                    timeSlot1.setText("0 SLOTS");
                    timeSlot2.setText(txt2.getText().toString());
                    checktestMrks();
                } else {
                    Toast.makeText(this, "NO SLOTS AVAILABLE TODAY", Toast.LENGTH_SHORT).show();

                }
            } else if (PrefTimeSLotHidden.getText().toString().equals("2.00 pm - 5.00pm")) {


                if (txt2.getText().toString().equals("3 SLOTS")) {
                    timeSlot2.setText("2 SLOTS");
                    timeSlot1.setText(txt1.getText().toString());
                    checktestMrks();
                } else if (txt2.getText().toString().equals("2 SLOTS")) {
                    timeSlot2.setText("1 SLOTS");
                    timeSlot1.setText(txt1.getText().toString());
                    checktestMrks();
                } else if (txt2.getText().toString().equals("1 SLOTS")) {
                    timeSlot2.setText("0 SLOTS");
                    timeSlot1.setText(txt1.getText().toString());
                    checktestMrks();
                } else {
                    Toast.makeText(this, "NO SLOTS AVAILABLE TODAY", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(this, "Select a time slot", Toast.LENGTH_SHORT).show();
            }


        } else {
            selectedAnswer = clickedButton.getText().toString();
            // clickedButton.setBackgroundColor(Color.MAGENTA);
            // clickedButton.setBackground(ContextCompat.getDrawable(this, R.drawable.button_round));


        }


    }

    //push data to the database
    public void checktestMrks() {
        String tMarks = testMarks.getText().toString();
        score = Integer.parseInt(tMarks);
        statusHidden.setText("PENDING");


        if (testMarks.getText().toString().equals("0")) {

            Toast.makeText(this, "Enter your test results ", Toast.LENGTH_SHORT).show();
        } else if (testMarks.getText().toString().isEmpty()) {

            Toast.makeText(this, "Enter your test results ", Toast.LENGTH_SHORT).show();

        } else if (score > 30) {
            Toast.makeText(this, "Your test marks should be between 10-30", Toast.LENGTH_SHORT).show();
        } else if (score < 10) {
            Toast.makeText(this, "Your test marks should be between 10-30", Toast.LENGTH_SHORT).show();
        } else if (PrefTimeSLotHidden.getText().toString().equals("")) {
            Toast.makeText(this, "Select a time slot", Toast.LENGTH_SHORT).show();

        } else {
            UploadDoctorSchedule();



        }


    }


    public void UploadDoctorSchedule() {

        HashMap<String, String> params = new HashMap<>();
        params.put("doctorid", docName.getText().toString());
        params.put("patientid", UseridHidden.getText().toString());
        params.put("bookeddate", DateselectHidden.getText().toString());
        params.put("patienttestresults", testMarks.getText().toString());
        params.put("timeslotrequested", PrefTimeSLotHidden.getText().toString());
        params.put("slotsleftmorning", timeSlot1.getText().toString());
        params.put("slotsleftevening", timeSlot2.getText().toString());
        params.put("doctorapprovalstatus", statusHidden.getText().toString());

        doctorschedule(params);


    }
    public void notifypatient()
    {
        Toast.makeText(this, "notification set", Toast.LENGTH_SHORT).show();
        String doctor = docName.getText().toString();

        Intent intent  = new Intent(BookDoctorActivity.this, NotificationReminderBroadcadst.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(BookDoctorActivity.this,0,intent,0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        long timeAtButtonCLick   = System.currentTimeMillis();

        long tenSeconds =  1000*10;

        alarmManager.set(AlarmManager.RTC_WAKEUP,
                timeAtButtonCLick + tenSeconds,pendingIntent);

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

                        notifypatient();



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
                        editor.putString(Constants.KEY_DOC_ID, responseBody.getDocDetailObject().getDocDetails().get(0).getDoctorid());
                        editor.putString(Constants.KEY_PATIENT_ID, responseBody.getDocDetailObject().getDocDetails().get(0).getPatientid());
                        editor.putString(Constants.KEY_BOOKED_DATE, responseBody.getDocDetailObject().getDocDetails().get(0).getBookeddate());
                        editor.putString(Constants.KEY_SLOTS_LEFT_MORNING, responseBody.getDocDetailObject().getDocDetails().get(0).getSlotsleftmorning());
                        editor.putString(Constants.KEY_SLOTS_LEFT_EVE, responseBody.getDocDetailObject().getDocDetails().get(0).getSlotsleftevening());


                        editor.apply();


                        Toast.makeText(BookDoctorActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();

                        display();
                    } else if (responseBody.getSuccess().equals("0")) {
                        txt1.setText("3 SLOTS");
                        txt2.setText("3 SLOTS");

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
        String slotsmorn = sharedPreferences.getString(KEY_SLOTS_LEFT_MORNING, null);
        String slotseve = sharedPreferences.getString(KEY_SLOTS_LEFT_EVE, null);

        if (String.valueOf(slotsmorn).equals("0 SLOTS")) {
            ansA.setEnabled(false);
        } else if (String.valueOf(slotseve).equals("0 SLOTS")) {
            ansB.setEnabled(false);
        } else {

            txt1.setText(slotsmorn);
            txt2.setText(slotseve);
        }
    }


}