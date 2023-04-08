package com.example.tranquilapplication.MainActivities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.PoolingByteArrayOutputStream;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tranquilapplication.R;
import com.example.tranquilapplication.ResponseModels.RegistrationResponseModel;
import com.example.tranquilapplication.Services.NetworkClient;
import com.example.tranquilapplication.Services.NetworkService;
import com.example.tranquilapplication.databinding.ActivityMainBinding;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {

    EditText inputName, inputPassword, inputemail, inputMobile, inputfamilynumber, txtLastName, txtPwdtwo;
    Button buttonRegister;
    TextView linklogin, abcd;
    ImageView imgBack, upload_image;
    protected TextView txtPatient;

    Bitmap bitmap;

    ActivityMainBinding binding;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        upload_image = findViewById(R.id.upload_image);
        inputName = findViewById(R.id.txtName);
        inputemail = findViewById(R.id.txtEmail);
        inputMobile = findViewById(R.id.txtMobile);
        inputfamilynumber = findViewById(R.id.txtFamMobile);
        inputPassword = findViewById(R.id.txtPwd);
        linklogin = findViewById(R.id.lnkLogin);
        txtPatient = findViewById(R.id.txtUserPatient);
        abcd = findViewById(R.id.abcd);
        imgBack = findViewById(R.id.imgBack);
        txtLastName = findViewById(R.id.txtLastName);
        txtPwdtwo = findViewById(R.id.txtPwdtwo);
        buttonRegister = findViewById(R.id.btnregister);
        txtPatient.setText("Patient");

        //Get below details from previous Activity
        Intent intent = getIntent();
        String a = intent.getStringExtra("DepressionType");
        String b = intent.getStringExtra("DeliveryDate");

        //When back image button pressed
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RegisterActivity.super.onBackPressed();
            }
        });

        //start activity login after login link clicked
        linklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        ActivityResultLauncher<Intent> activityResultLauncher =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Uri uri = data.getData();
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                                upload_image.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();

                            }


                        }

                    }
                });


//Upload profile image - get from gallery

        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_PICK);
                intent1.setType("image/*");
                intent1.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intent1);

            }
        });




        buttonRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                if (inputName.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Enter first name", Toast.LENGTH_SHORT).show();
                } else if (txtLastName.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Enter Last name", Toast.LENGTH_SHORT).show();
                } else if (inputemail.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                } else if (inputPassword.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                } else if (txtPwdtwo.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Re-enter password", Toast.LENGTH_SHORT).show();
                } else if (inputMobile.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Enter mobile", Toast.LENGTH_SHORT).show();
                } else if (inputfamilynumber.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Enter family/home mobile", Toast.LENGTH_SHORT).show();
                } else if (!inputPassword.getText().toString().equals(txtPwdtwo.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {

                    ByteArrayOutputStream byteArrayOutputStream;
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    byte[] image = byteArrayOutputStream.toByteArray();
                     String base64Image = Base64.encodeToString(image,0);


                    HashMap<String, String> params = new HashMap<>();
                    params.put("name", inputName.getText().toString());
                    params.put("lastname", txtLastName.getText().toString());
                    params.put("email", inputemail.getText().toString());
                    params.put("password", inputPassword.getText().toString());
                    params.put("passwordtwo", txtPwdtwo.getText().toString());
                    params.put("mobile", inputMobile.getText().toString());
                    params.put("familymobile", inputfamilynumber.getText().toString());
                    params.put("userCategory", txtPatient.getText().toString());
                    params.put("depressionType", a);
                    params.put("deliverydate", b);
                    params.put("profilepicture", String.valueOf(base64Image) );


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