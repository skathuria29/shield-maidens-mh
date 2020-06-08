package com.example.shieldmaidens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenActivity extends AppCompatActivity {
    List<String> questions = new ArrayList<>();
    List<List<String>> questionnaire = new ArrayList<>();
    private TextView optionA;
    private TextView optionB;
    private TextView optionC;
    private TextView optionD;
    int count = 0;
    private TextView question;
    private TextView counter;
    private ProgressBar progressBarH;
    private ImageView iv_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        optionA = findViewById(R.id.record_audio);
        optionB = findViewById(R.id.record_video);
        optionC = findViewById(R.id.option3);
        optionD = findViewById(R.id.option4);
        question = findViewById(R.id.question);
        counter = findViewById(R.id.counter);
        progressBarH = findViewById(R.id.progressBar);
        iv_back = findViewById(R.id.iv_back);
        progressBarH.setMax(6);

        List<String> list1 = new ArrayList<>();
        list1.add("I eat more than usual");
        list1.add("I eat less than usual ");
        list1.add("I am eating the same amounts");
        list1.add("I dont feel hungry at all");

        List<String> list2 = new ArrayList<>();
        list2.add("Never ");
        list2.add("Sometimes ");
        list2.add("Often");
        list2.add("Always");

        List<String> list3 = new ArrayList<>();
        list3.add("Tackle stress");
        list3.add("Live healthier");
        list3.add("Beat anxiety");
        list3.add("Feel happier");

        List<String> list4 = new ArrayList<>();
        list4.add("Randomly during the day");
        list4.add("Randomly during evening");
        list4.add("Set specific time");
        list4.add("Before sleep");

        List<String> list5 = new ArrayList<>();
        list5.add("Not really");
        list5.add("Maybe a little");
        list5.add("Quite a bit");
        list5.add("A lot");

        List<String> list6 = new ArrayList<>();
        list6.add("Not really");
        list6.add("Maybe a little");
        list6.add("Quite a bit");
        list6.add("A lot");

        questionnaire.add(list1);
        questionnaire.add(list2);
        questionnaire.add(list3);
        questionnaire.add(list4);
        questionnaire.add(list5);
        questionnaire.add(list6);

        questions.add("How has your appetite been over the last few weeks?");
        questions.add("I find it hard to communicate with others");
        questions.add("Tell us what you want to achieve?");
        questions.add("What would be the best time to work on this?");
        questions.add("Have you been less active than usual?");
        questions.add("Have you been sleeping well lately?");

        updateQuestions();
        progressBarH.setProgress(1);
        counter.setText("1/6");

        optionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestions();
            }
        });

        optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestions();
            }
        });

        optionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestions();
            }
        });

        optionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestions();
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void updateQuestions() {
        if (count < 6) {
            progressBarH.setProgress(count + 1);
            question.setText(questions.get(count));
            counter.setText(count + 1 + "/6");
            optionA.setText(questionnaire.get(count).get(0));
            optionB.setText(questionnaire.get(count).get(1));
            optionC.setText(questionnaire.get(count).get(2));
            optionD.setText(questionnaire.get(count).get(3));
        }
        if (count == 6) {
            Intent intent = new Intent(HomeScreenActivity.this, RecordVideoAudio.class);
            startActivity(intent);
        }
        count++;


    }
}
