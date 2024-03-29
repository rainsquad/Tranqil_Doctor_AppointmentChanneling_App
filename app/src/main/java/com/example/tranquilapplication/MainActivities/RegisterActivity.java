package com.example.tranquilapplication.MainActivities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tranquilapplication.R;
import com.example.tranquilapplication.ResponseModels.RegistrationResponseModel;
import com.example.tranquilapplication.Services.NetworkClient;
import com.example.tranquilapplication.Services.NetworkService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {


    EditText inputName, inputPassword, inputemail, inputMobile, inputfamilynumber, txtLastName, txtPwdtwo;
    Button buttonRegister;
    TextView linklogin, abcd;
    ImageView imgBack, upload_image;
    protected TextView txtPatient;
    private Bitmap bitmap;


    private String picture;


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
        // mPicture = findViewById(R.id.picture);

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
                chooseFile();


//                Intent intent1 = new Intent(Intent.ACTION_PICK);
//                intent1.setType("image/*");
//                intent1.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                activityResultLauncher.launch(intent1);

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
                } else if (!isValidEmail(inputemail.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                }
                else if (inputPassword.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                } else if (txtPwdtwo.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Re-enter password", Toast.LENGTH_SHORT).show();
                } else if (inputMobile.getText().toString().equals("") ) {
                    Toast.makeText(RegisterActivity.this, "Enter mobile number", Toast.LENGTH_SHORT).show();
                }
                else if (inputMobile.getText().toString().length() !=10 ) {
                    Toast.makeText(RegisterActivity.this, "Enter valid mobile number", Toast.LENGTH_SHORT).show();
                }else if (inputfamilynumber.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Enter family/home mobile", Toast.LENGTH_SHORT).show();
                }
                else if (inputfamilynumber.getText().toString().length()!= 10 ) {
                    Toast.makeText(RegisterActivity.this, "Enter valid family/home mobile", Toast.LENGTH_SHORT).show();
                }
                else if (!inputPassword.getText().toString().equals(txtPwdtwo.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else if (inputPassword.getText().toString().length() < 8 && !isValidPassword(inputPassword.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Password must contain minimum 8 characters at least 1 Alphabet, 1 Number and 1 Special Character", Toast.LENGTH_SHORT).show();
                } else {
                    String picture = null;
                    if (bitmap == null) {
                        picture = "";
                    } else {
                    picture = getStringImage(bitmap);
                       // picture = getRoundedCroppedBitmap(bitmap);

                    }
                    System.out.println(picture);
                    UpdateDataSet(a, b, picture);
                }


            }

        });


    }


    public void UpdateDataSet(String a, String b, String picture) {

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
        params.put("profilepicture", picture);

        register(params);
    }

    private void chooseFile() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                upload_image.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }















//    public static String getStringImage(Bitmap bmp) {
//        Bitmap immagex=bmp;
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] b = baos.toByteArray();
//        String imageEncoded = Base64.encodeToString(b,Base64.DEFAULT);
//
//        Log.e("LOOK", imageEncoded);
//        return imageEncoded;
//    }


    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

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