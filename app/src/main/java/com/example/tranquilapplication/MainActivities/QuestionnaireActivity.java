package com.example.tranquilapplication.MainActivities;

import static com.example.tranquilapplication.ResponseModels.Constants.KEY_DEPRESSION_TYPE;
import static com.example.tranquilapplication.ResponseModels.Constants.PREFERENCE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.tranquilapplication.NetworkModel.NetworkClient;
import com.example.tranquilapplication.NetworkModel.NetworkService;
import com.example.tranquilapplication.QuestionsandAnswers.QuestionAnswersClass;
import com.example.tranquilapplication.R;
import com.example.tranquilapplication.ResponseModels.Constants;
import com.example.tranquilapplication.ResponseModels.LoginResponseModel;
import com.example.tranquilapplication.ResponseModels.QuestionnaireResponseModel;
import com.example.tranquilapplication.ResponseModels.RegistrationResponseModel;
import com.google.android.material.color.utilities.QuantizerWsmeans;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QuestionnaireActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView, userid;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    int score = 0;
    int totalQuestion = QuestionAnswersClass.question.length;
    int currentQuestionIndex = 0;
    SharedPreferences sharedPreferences;

    private static final String KEY_ID = "id";

    private  static  final  String  KEY_DEPRESSION_TYPE= "depressionType";
    Date todaydate = Calendar.getInstance().getTime();

    RelativeLayout layout;

    String message;
    String depressionType;


    String selectedAnswer = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire1);
        userid = findViewById(R.id.userid);

        // layout = findViewById(R.id.layoutQuestionarie);

        /* Get user details using shared preferances*/
        sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String id = sharedPreferences.getString(KEY_ID, null);
        String Depressiontype = sharedPreferences.getString(KEY_DEPRESSION_TYPE,null);
        userid.setText(Depressiontype);


        totalQuestionsTextView = findViewById(R.id.total_question);
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

        totalQuestionsTextView.setText("Total questions : " + totalQuestion);

        loadNewQuestion();


    }

    @Override
    public void onClick(View view) {


        ansA.setBackground(ContextCompat.getDrawable(this, R.drawable.roundbtn));
        ansB.setBackground(ContextCompat.getDrawable(this, R.drawable.roundbtn));
        ansC.setBackground(ContextCompat.getDrawable(this, R.drawable.roundbtn));
        ansD.setBackground(ContextCompat.getDrawable(this, R.drawable.roundbtn));

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

    void loadNewQuestion() {

        if (currentQuestionIndex == totalQuestion) {

            finishQuiz();
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
//        String passStatus = "";
//        if (score > totalQuestion * 0.60) {
//            passStatus = "Passed";
//        } else {
//            passStatus = "Failed";
//        }
//
//        new AlertDialog.Builder(this).setTitle(passStatus).setMessage("Score is " + score + " out of " + totalQuestion * 3).setPositiveButton("Restart", (dialogInterface, i) -> restartQuiz()).setCancelable(false).show();


    }

    private void CreatepopUpwindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.mainpopup, null);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(layout, Gravity.BOTTOM, 0, 0);

            }
        });
        TextView Skip, Gotit;

        Gotit = popUpView.findViewById(R.id.Gotit);

        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // write code anything you want
            }
        });
        // and if you want to close popup when touch Screen
        popUpView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

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
                        Toast.makeText(QuestionnaireActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(QuestionnaireActivity.this, ScreenTestResutsActivity.class);
                        intent.putExtra("message",score);
                        intent.putExtra("depressionType",userid.getText().toString());
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