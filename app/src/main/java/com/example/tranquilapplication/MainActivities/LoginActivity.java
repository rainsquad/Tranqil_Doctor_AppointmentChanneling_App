package com.example.tranquilapplication.MainActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tranquilapplication.R;
import com.example.tranquilapplication.Services.Constants;
import com.example.tranquilapplication.ResponseModels.LoginResponseModel;
import com.example.tranquilapplication.Services.NetworkClient;
import com.example.tranquilapplication.Services.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText inputEmail, inputPassword;
    Button buttonLogin;
    TextView textCreateAccount;

    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textCreateAccount = findViewById(R.id.lnkRegister);
        textCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SetupProfileActivity.class));
            }
        });


        inputEmail = findViewById(R.id.txtloginEmail);
        inputEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    hideSoftKeyboard(LoginActivity.this);
                }
            }
        });
        inputPassword = findViewById(R.id.txtloginPwd);
        inputPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    hideSoftKeyboard(LoginActivity.this);
                }
            }
        });

        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            LoginActivity.super.onBackPressed();
            }
        });

        buttonLogin = findViewById(R.id.btnlogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputEmail.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
//                } else if (!emailValidator(inputEmail.getText().toString())) {
//                    Toast.makeText(LoginActivity.this, "Please Field Valid Email", Toast.LENGTH_SHORT).show();
//                } else if (inputPassword.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                } else {
                    login();
                }
            }
        });



    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }


    private void login() {

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Logging in...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
        Call<LoginResponseModel> login = networkService.login(inputEmail.getText().toString(), inputPassword.getText().toString());
        login.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponseModel> call, @NonNull Response<LoginResponseModel> response) {
                LoginResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCE_NAME, MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean(Constants.KEY_ISE_LOGGED_IN, true);
                        editor.putString(Constants.KEY_NAME, responseBody.getUserDetailObject().getUserDetails().get(0).getName());
                        editor.putString(Constants.KEY_ID, responseBody.getUserDetailObject().getUserDetails().get(0).getId() );
                        editor.putString(Constants.KEY_DEPRESSION_TYPE, responseBody.getUserDetailObject().getUserDetails().get(0).getDepressionType() );
                        editor.putString(Constants.KEY_EMAIL, responseBody.getUserDetailObject().getUserDetails().get(0).getEmail());
                        editor.putString(Constants.KEY_USER_CATEGORY, responseBody.getUserDetailObject().getUserDetails().get(0).getUserCategory());
                        editor.putString(Constants.KEY_PROFILE_PIC, responseBody.getUserDetailObject().getUserDetails().get(0).getProfilepicture());

                        editor.apply();


                        if (responseBody.getUserDetailObject().getUserDetails().get(0).getUserCategory().equals("Doctor")) {
                            startActivity(new Intent(getApplicationContext(), DoctorDailyScheduleActivity.class));
                            finish();

                        }else
                        {
                            startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
                            finish();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponseModel> call, @NonNull Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

//    public boolean emailValidator(String email) {
//        Pattern pattern;
//        Matcher matcher;
//        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//        pattern = Pattern.compile(EMAIL_PATTERN);
//        matcher = pattern.matcher(email);
//        return matcher.matches();
//
//    }
}