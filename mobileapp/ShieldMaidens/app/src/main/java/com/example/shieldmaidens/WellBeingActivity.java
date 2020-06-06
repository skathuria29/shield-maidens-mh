package com.example.shieldmaidens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class WellBeingActivity extends AppCompatActivity {

    private ImageView startassistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_well_being);

        startassistance = findViewById(R.id.startassistance);

        startassistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WellBeingActivity.this, HomeScreenActivity.class);
                startActivity(intent);
            }
        });
    }
}
