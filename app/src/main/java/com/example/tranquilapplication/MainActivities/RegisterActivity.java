package com.example.tranquilapplication.MainActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tranquilapplication.R;
import com.example.tranquilapplication.ResponseModels.RegistrationResponseModel;
import com.example.tranquilapplication.NetworkModel.NetworkClient;
import com.example.tranquilapplication.NetworkModel.NetworkService;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {

    EditText inputName, inputPassword, inputemail,inputMobile, inputfamilynumber;
    Button buttonRegister;
    TextView linklogin,abcd;

    protected TextView txtPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputName = findViewById(R.id.txtName);
        inputemail = findViewById(R.id.txtEmail);
        inputMobile = findViewById(R.id.txtMobile);
        inputfamilynumber = findViewById(R.id.txtFamMobile);
        inputPassword = findViewById(R.id.txtPwd);
        linklogin = findViewById(R.id.lnkLogin);
        txtPatient =findViewById(R.id.txtUserPatient);
        abcd = findViewById(R.id.abcd);
        txtPatient.setText("Patient");

        Intent intent= getIntent();
        String a = intent.getStringExtra("DepressionType");
        String b = intent.getStringExtra("DeliveryDate");











        linklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        buttonRegister = findViewById(R.id.btnregister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (inputName.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Enter first name", Toast.LENGTH_SHORT).show();
                } else if (inputemail.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                } else if (inputPassword.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                }
                else if (inputMobile.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Enter mobile", Toast.LENGTH_SHORT).show();
                } else if (inputfamilynumber.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Enter family/home mobile", Toast.LENGTH_SHORT).show();
                }
                else {

                    HashMap<String, String> params = new HashMap<>();
                    params.put("name", inputName.getText().toString());
                    params.put("email", inputemail.getText().toString());
                    params.put("password", inputPassword.getText().toString());
                    params.put("mobile", inputMobile.getText().toString());
                    params.put("familymobile", inputfamilynumber.getText().toString());
                    params.put("userCategory", txtPatient.getText().toString());
                    params.put("depressionType",a);
                    params.put("deliverydate",b);

                    register(params);
                }

            }

        });

    }

    private void register(HashMap<String, String> params) {

        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Registering...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
        Call<RegistrationResponseModel> registerCall = networkService.register(params);
        registerCall.enqueue(new Callback<RegistrationResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<RegistrationResponseModel> call, @NonNull Response<RegistrationResponseModel> response) {
                RegistrationResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        Toast.makeText(RegisterActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<RegistrationResponseModel> call, @NonNull Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}