package com.suad.venttome;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo, slogan;

    private static int SPLASH_SCREEN=3400;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        image = findViewById(R.id.splash_image);
        slogan = findViewById(R.id.splash_text);
        logo= findViewById(R.id.splash_title);

        image.setAnimation(topAnim);
        slogan.setAnimation(bottomAnim);
        logo.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                SharedPreferences prefs = getSharedPreferences("PASS_PREF", Context.MODE_PRIVATE);
                String thePIN = prefs.getString("Digits", "");
                if(!thePIN.equals("")){
                    Intent intent = new Intent(SplashScreen.this, LoginPage.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(SplashScreen.this, RecordAMood.class);
                    startActivity(intent);
                }
                finish();
            }
        },SPLASH_SCREEN);

    }



}
