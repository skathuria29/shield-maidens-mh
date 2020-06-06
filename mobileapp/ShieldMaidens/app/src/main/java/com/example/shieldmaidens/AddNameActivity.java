package com.example.shieldmaidens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class AddNameActivity extends AppCompatActivity {

    private ImageView btn_continue;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_name);

        btn_continue = findViewById(R.id.btn_continue);
        editText = findViewById(R.id.editextname);



        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SensumApp.username = editText.getText().toString()+  "\uD83D\uDE04";
                Intent intent = new Intent(AddNameActivity.this, WellBeingActivity.class);
                startActivity(intent);
            }
        });


    }
}
