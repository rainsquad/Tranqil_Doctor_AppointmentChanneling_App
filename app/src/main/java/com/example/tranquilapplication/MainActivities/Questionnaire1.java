package com.example.tranquilapplication.MainActivities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.tranquilapplication.QuestionsandAnswers.QuestionAnswers;
import com.example.tranquilapplication.R;


    public class Questionnaire1 extends AppCompatActivity implements View.OnClickListener{

        TextView totalQuestionsTextView;
        TextView questionTextView;
        Button ansA, ansB, ansC, ansD;
        Button submitBtn;

        int score=0;
        int totalQuestion = QuestionAnswers.question.length;
        int currentQuestionIndex = 0;
        String selectedAnswer = "";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_questionnaire1);

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

            totalQuestionsTextView.setText("Total questions : "+totalQuestion);

            loadNewQuestion();




        }

        @Override
        public void onClick(View view) {

            ansA.setBackgroundColor(Color.WHITE);
            ansB.setBackgroundColor(Color.WHITE);
            ansC.setBackgroundColor(Color.WHITE);
            ansD.setBackgroundColor(Color.WHITE);

            Button clickedButton = (Button) view;
            if(clickedButton.getId()==R.id.submit_btn){
                if(selectedAnswer.equals(QuestionAnswers.Answer1[currentQuestionIndex])){
                    score = new Integer(score+3);
                }
                else if(selectedAnswer.equals(QuestionAnswers.Answer2[currentQuestionIndex])){
                    score = new Integer(score +2);
                }
               else if(selectedAnswer.equals(QuestionAnswers.Answer3[currentQuestionIndex])){
                    score = new Integer(score +1);
                }
                currentQuestionIndex++;
                loadNewQuestion();


            }else{
                //choices button clicked
                selectedAnswer  = clickedButton.getText().toString();
                clickedButton.setBackgroundColor(Color.MAGENTA);

            }

        }

        void loadNewQuestion(){

            if(currentQuestionIndex == totalQuestion ){
                finishQuiz();
                return;
            }

            questionTextView.setText(QuestionAnswers.question[currentQuestionIndex]);
            ansA.setText(QuestionAnswers.choices[currentQuestionIndex][0]);
            ansB.setText(QuestionAnswers.choices[currentQuestionIndex][1]);
            ansC.setText(QuestionAnswers.choices[currentQuestionIndex][2]);
            ansD.setText(QuestionAnswers.choices[currentQuestionIndex][3]);

        }

        void finishQuiz(){
            String passStatus = "";
            if(score > totalQuestion*0.60){
                passStatus = "Passed";
            }else{
                passStatus = "Failed";
            }

            new AlertDialog.Builder(this)
                    .setTitle(passStatus)
                    .setMessage("Score is "+ score+" out of "+ totalQuestion)
                    .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                    .setCancelable(false)
                    .show();


        }

        void restartQuiz(){
            score = 0;
            currentQuestionIndex =0;
            loadNewQuestion();
        }

    }