package com.example.shieldmaidens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class AssesmentResultScreen extends AppCompatActivity {

    private ProgressBar progressBar;
    private ImageView btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assesment_result_screen);

        Double score = getIntent().getDoubleExtra("percentage", 0.0);

        Double finalScore = Double.parseDouble(String.format("%.2f", score)) *100;

        Integer intScore = Integer.valueOf(finalScore.intValue());

        progressBar = findViewById(R.id.progressBar);
        btnContinue = findViewById(R.id.btnContinue);

        Drawable drawable = getResources().getDrawable(R.drawable.result_progress_drawable);
        progressBar.setProgressDrawable(drawable);
        progressBar.setProgress(intScore);
        progressBar.setMax(100);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssesmentResultScreen.this, SessionsActivity.class);
                startActivity(intent);
            }
        });


    }
}
