package com.example.third_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView no_of_questions_view;
    TextView question_view;
    Button optionA,optionB,optionC,optionD,submit_button;


    int score = 0;
    int currentquestionindex = 0;
    int total_no_of_questions = QuestionAnswers.questions.length;
    String selectedanswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        no_of_questions_view =  findViewById(R.id.total_questions);
        question_view = findViewById(R.id.question_view);
        optionA = findViewById(R.id.b_a);
        optionB = findViewById(R.id.b_b);
        optionC = findViewById(R.id.b_c);
        optionD = findViewById(R.id.b_d);
        submit_button = findViewById(R.id.submit_button);

        optionA.setOnClickListener(this);
        optionB.setOnClickListener(this);
        optionC.setOnClickListener(this);
        optionD.setOnClickListener(this);
        submit_button.setOnClickListener(this);

        no_of_questions_view.setText("TOTAL QUESTIONS :  " +total_no_of_questions );

        loadnewquestion();

    }

    private void loadnewquestion() {

        if(currentquestionindex == total_no_of_questions)
        {
            finishquiz();
            return;
        }

        question_view.setText(QuestionAnswers.questions[currentquestionindex]);
        optionA.setText(QuestionAnswers.options[currentquestionindex][0]);
        optionB.setText(QuestionAnswers.options[currentquestionindex][1]);
        optionC.setText(QuestionAnswers.options[currentquestionindex][2]);
        optionD.setText(QuestionAnswers.options[currentquestionindex][3]);
    }

    private void finishquiz() {

        new AlertDialog.Builder(this)
                .setTitle("Pass Staus")
                .setMessage("total score "+ score)
                .setPositiveButton("Restart", (dialogInterface,i) -> restartQuiz() )
                .show();


    }

    void restartQuiz()
    {
        score = 0;
        currentquestionindex = 0;
        loadnewquestion();
    }


    @Override
    public void onClick(View view) {

        optionA.setBackgroundColor(Color.WHITE);
        optionB.setBackgroundColor(Color.WHITE);
        optionC.setBackgroundColor(Color.WHITE);
        optionD.setBackgroundColor(Color.WHITE);

        Button clickedbutton = (Button)view;
        if(clickedbutton.getId() == R.id.submit_button)
        {
            if(selectedanswer.equals(QuestionAnswers.correctAnswers[currentquestionindex]))
            {
                score++;
            }
               currentquestionindex++;
               loadnewquestion();

        }else
        {
            selectedanswer = clickedbutton.getText().toString();
            clickedbutton.setBackgroundColor(Color.MAGENTA);
        }

    }
}