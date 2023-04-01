package com.example.tranquilapplication.MainActivities;

import static com.example.tranquilapplication.Services.Constants.PREFERENCE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.tranquilapplication.Services.NetworkClient;
import com.example.tranquilapplication.Services.NetworkService;
import com.example.tranquilapplication.QuestionsandAnswers.QuestionAnswersClass;
import com.example.tranquilapplication.R;
import com.example.tranquilapplication.Services.Constants;
import com.example.tranquilapplication.ResponseModels.QuestionnaireResponseModel;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QuestionnaireActivity extends AppCompatActivity implements View.OnClickListener {

    TextView userid, questionTextView;
    ImageView imgBack;
    Button ansA, ansB, ansC, ansD, submitBtn, btn_restart;
    int totalQuestion = QuestionAnswersClass.question.length;
    int currentQuestionIndex, score = 0;
    SharedPreferences sharedPreferences;
    private static final String KEY_ID = "id";

    private static final String KEY_DEPRESSION_TYPE = "depressionType";
    Date todaydate = Calendar.getInstance().getTime();
    RelativeLayout layout;
    String selectedAnswer = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire1);
        userid = findViewById(R.id.userid);
        imgBack = findViewById(R.id.imgBack);
        btn_restart = findViewById(R.id.btn_restart);


        /* Get user details using shared preferances*/
        sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String id = sharedPreferences.getString(KEY_ID, null);
        String Depressiontype = sharedPreferences.getString(KEY_DEPRESSION_TYPE, null);
        userid.setText(id);



        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.answerA);
        ansB = findViewById(R.id.answerB);
        ansC = findViewById(R.id.answerC);
        ansD = findViewById(R.id.answerD);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);


        loadNewQuestion();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionnaireActivity.super.onBackPressed();
            }
        });
        btn_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartQuiz();
            }
        });

    }


    @Override
    public void onClick(View view) {


        ansA.setBackground(ContextCompat.getDrawable(this, R.drawable.roundbtn));
        ansB.setBackground(ContextCompat.getDrawable(this, R.drawable.roundbtn));
        ansC.setBackground(ContextCompat.getDrawable(this, R.drawable.roundbtn));
        ansD.setBackground(ContextCompat.getDrawable(this, R.drawable.roundbtn));

     //Validating answer selection ( User should select an answer before pressing submit button, and also user should answer all 10 questions)

        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.submit_btn) {
            if (selectedAnswer.equals(QuestionAnswersClass.Answer1[currentQuestionIndex])) {
                score = new Integer(score + 3);
                currentQuestionIndex++;
                loadNewQuestion();
            } else if (selectedAnswer.equals(QuestionAnswersClass.Answer2[currentQuestionIndex])) {
                score = new Integer(score + 2);
                currentQuestionIndex++;
                loadNewQuestion();
            } else if (selectedAnswer.equals(QuestionAnswersClass.Answer3[currentQuestionIndex])) {
                score = new Integer(score + 1);
                currentQuestionIndex++;
                loadNewQuestion();
            } else if (selectedAnswer.equals(QuestionAnswersClass.Answer4[currentQuestionIndex])) {
                score = new Integer(score + 0);
                currentQuestionIndex++;
                loadNewQuestion();
            } else if (selectedAnswer.equals("")) {
                Toast.makeText(QuestionnaireActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();

            }


        } else {
            //choices button clicked
            selectedAnswer = clickedButton.getText().toString();
            // clickedButton.setBackgroundColor(Color.MAGENTA);
            clickedButton.setBackground(ContextCompat.getDrawable(this, R.drawable.roundbtngrey));

        }

    }


    //Loading the next question from data set
    void loadNewQuestion() {

        if (currentQuestionIndex == totalQuestion) {

            finishQuiz();
            submitBtn.setVisibility(View.INVISIBLE);
            //   CreatepopUpwindow();

            return;
        }

        questionTextView.setText(QuestionAnswersClass.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswersClass.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswersClass.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswersClass.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswersClass.choices[currentQuestionIndex][3]);

    }

    void finishQuiz() {
        uploadDataset();

    }


// upload the test marks, date, user id server database
    void uploadDataset() {
        HashMap<String, String> params = new HashMap<>();
        params.put("testmarks", String.valueOf(score));
        params.put("date", String.valueOf(todaydate));
        params.put("userid", userid.getText().toString());
        upload(params);
    }

    public void upload(HashMap<String, String> params) {


        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
        Call<QuestionnaireResponseModel> login = networkService.upload(params);
        login.enqueue(new Callback<QuestionnaireResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<QuestionnaireResponseModel> call, @NonNull Response<QuestionnaireResponseModel> response) {
                QuestionnaireResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCE_NAME, MODE_PRIVATE);
                      //  Toast.makeText(QuestionnaireActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(QuestionnaireActivity.this, ScreenTestResutsActivity.class);


                        //get total marks and type of depression from previous activities
                        intent.putExtra("message", score);
                        intent.putExtra("depressionType", userid.getText().toString());



                        startActivity(intent);
                        finish();


                    } else {
                        Toast.makeText(QuestionnaireActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<QuestionnaireResponseModel> call, @NonNull Throwable t) {

            }
        });
    }

    void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }

}