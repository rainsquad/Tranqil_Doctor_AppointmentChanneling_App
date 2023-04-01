package com.example.tranquilapplication.MainActivities;


import static com.example.tranquilapplication.Services.Constants.KEY_BOOKED_DATE;
import static com.example.tranquilapplication.Services.Constants.KEY_BOOKED_TIME;
import static com.example.tranquilapplication.Services.Constants.KEY_DOC_ID;
import static com.example.tranquilapplication.Services.Constants.PREFERENCE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tranquilapplication.R;
import com.example.tranquilapplication.ResponseModels.DocResponseModel;
import com.example.tranquilapplication.Services.Constants;
import com.example.tranquilapplication.Services.NetworkClient;
import com.example.tranquilapplication.Services.NetworkService;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    Button submit;
    EditText doc,patient;


    TextView bookeddate, bookedtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        doc = findViewById(R.id.docid);
        patient = findViewById(R.id.patientid);
        bookeddate = findViewById(R.id.Bookeddate);
        bookedtime = findViewById(R.id.Bookedtime);
        submit = findViewById(R.id.submit);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doctorcheck() ;
            }
        });

    }

    //Display doctor schedule Details


    private void doctorcheck() {

        final ProgressDialog progressDialog = new ProgressDialog(NotificationActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Logging in...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
        Call<DocResponseModel> doctorcheck = networkService.doctorcheck(doc.getText().toString(), patient.getText().toString());
        doctorcheck.enqueue(new Callback<DocResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<DocResponseModel> call, @NonNull Response<DocResponseModel> response) {
                DocResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean(Constants.KEY_ISE_LOGGED_IN, true);
                        //   editor.putString(Constants.KEY_USERNAME, responseBody.getUserDetailObject().getUserDetails().get(0).getFirstName() + " " + responseBody.getUserDetailObject().getUserDetails().get(0).getMobile());
                        /*editor.putString(Constants.KEY_LASTNAME, responseBody.getUserDetailObject().getUserDetails().get(0).getLastName());*/
//                        editor.putString(Constants.KEY_EMAIL, responseBody.getUserDetailObject().getUserDetails().get(0).getEmail());
//                        editor.putString(Constants.KEY_ID, responseBody.getUserDetailObject().getUserDetails().get(0).getId());

                        editor.putString(KEY_DOC_ID, responseBody.getDocDetailObject().getDocDetails().get(0).getDoctorid());
                        editor.putString(Constants.KEY_PATIENT_ID, responseBody.getDocDetailObject().getDocDetails().get(0).getPatientid());
                        editor.putString(KEY_BOOKED_DATE, responseBody.getDocDetailObject().getDocDetails().get(0).getBookeddate());
                        editor.putString(KEY_BOOKED_TIME, responseBody.getDocDetailObject().getDocDetails().get(0).getBookedtime());



                        editor.apply();
                        Toast.makeText(NotificationActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();

                        display();
                    } else {
                        Toast.makeText(NotificationActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();
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

    public   void display()
    {
        sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_DOC_ID, null);
        String date = sharedPreferences.getString(KEY_BOOKED_DATE,null);
        String time = sharedPreferences.getString(KEY_BOOKED_TIME,null);

        bookeddate.setText(date);
        bookedtime.setText(time);
    }


}