package com.suad.venttome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RealHomePage extends AppCompatActivity {
    private Button toVent;
    private Button toRecord;
    private Button toTracker;
    private Button toDiary;
    private Button toSettings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        Intent intent = getIntent();

        toVent= findViewById(R.id.home_btn_1);
        toRecord= findViewById(R.id.home_btn_2);
        toTracker = findViewById(R.id.home_btn_4);
        toDiary= findViewById(R.id.home_btn_3);
        toSettings= findViewById(R.id.home_btn_5);

        //Doing all the buttons
        toVent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(RealHomePage.this, ventingPage.class);
                startActivity(intent);
            }
        });
        toSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(RealHomePage.this, settingsPage.class);
                startActivity(intent);
            }
        });
        toTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(RealHomePage.this, MoodTracker.class);
                startActivity(intent);
            }
        });
        toDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(RealHomePage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        toRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(RealHomePage.this, RecordAMood.class);
                startActivity(intent);
            }
        });
    }
}
