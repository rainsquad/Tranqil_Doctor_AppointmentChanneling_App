package com.example.tranquilapplication.MainActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tranquilapplication.R;
import com.example.tranquilapplication.ResponseModels.Constants;
import com.example.tranquilapplication.ResponseModels.LoginResponseModel;
import com.example.tranquilapplication.ResponseModels.NetworkClient;
import com.example.tranquilapplication.ResponseModels.NetworkService;
import com.example.tranquilapplication.ResponseModels.RegistrationResponseModel;
import com.example.tranquilapplication.ResponseModels.SetupProfileResponseModel;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetupProfileStep1 extends AppCompatActivity {


    //Declaring variables
    private ImageView imgBack;
    private Button btnSubmit;
    private RadioButton radioButton, rbAntepartum, rbPostpartum;
    private RadioGroup radioGroup;
    private CalendarView calView;
    private TextView txtHidden;

    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_profile_step1);

        //Initiating variables

        imgBack = findViewById(R.id.imageView4);
        btnSubmit = findViewById(R.id.btnSubmit1);
        rbAntepartum = findViewById(R.id.rbAntepartum);
        rbPostpartum = findViewById(R.id.rbPostpartum);
        calView = findViewById(R.id.calendarView);

        calendarView = findViewById(R.id.calendarView);
        txtHidden = findViewById(R.id.txtHidden);
        addListenerOnButton();
        OnDateChangeListener();

        //Go to Previous page
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetupProfileStep1.this, MainActivity.class);
                startActivity(intent);

            }

        });
    }


    // Select date from calender View and get it to a string
    private void OnDateChangeListener() {

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
                String msg = dayOfMonth + "/" + (month + 1) + "/" + year;
                Toast.makeText(SetupProfileStep1.this, msg, Toast.LENGTH_SHORT).show();
                txtHidden.setText(msg);
            }
        });
    }

    // Display loading bar and parsing the user input data to the server
    private void profile(HashMap<String, String> params) {

        final ProgressDialog progressDialog = new ProgressDialog(SetupProfileStep1.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("do not close the app");
        progressDialog.setCancelable(false);
        progressDialog.show();
        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
        Call<SetupProfileResponseModel> profile = networkService.profile(params);
        profile.enqueue(new Callback<SetupProfileResponseModel>() {


            @Override
            public void onResponse(@NonNull Call<SetupProfileResponseModel> call, @NonNull Response<SetupProfileResponseModel> response) {
                SetupProfileResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        Toast.makeText(SetupProfileStep1.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SetupProfileStep1.this, RegisterActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SetupProfileStep1.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<SetupProfileResponseModel> call, @NonNull Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    ;


//Check for empty fields  and get data from ui and parse them as string values
    

    private void addListenerOnButton() {


        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        btnSubmit = (Button) findViewById(R.id.btnSubmit1);


        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(SetupProfileStep1.this, "Please Select which phase you are going through!", Toast.LENGTH_SHORT).show();
                }  else if (txtHidden.getText().toString().equals("")) {
                    Toast.makeText(SetupProfileStep1.this, "Select Date", Toast.LENGTH_SHORT).show();


                } else {
                    // get selected radio button from radioGroup
                    int selectedId = radioGroup.getCheckedRadioButtonId();

                    // find the radiobutton by returned id
                    radioButton = (RadioButton) findViewById(selectedId);


                    Toast.makeText(SetupProfileStep1.this, radioButton.getText(), Toast.LENGTH_SHORT).show();

                    HashMap<String, String> params = new HashMap<>();
                    params.put("depressionType", radioButton.getText().toString());
                    params.put("deliverydate", txtHidden.getText().toString());
                    profile(params);
                }

            }

        });


    }
}


