package com.suad.venttome;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RecordAMood extends AppCompatActivity {
    ImageButton elatedBtn;
    ImageButton happyBtn;
    ImageButton ehBtn;
    ImageButton sadBtn;
    ImageButton angryBtn;
    private int elatedTimes;
    private int happyTimes;
    private int mehTimes;
    private int sadTimes;
    private int angryTimes;
    private MaterialCardView elatedCard;
    private MaterialCardView happyCard;
    private MaterialCardView mehCard;
    private MaterialCardView sadCard;
    private MaterialCardView angryCard;
    private LinearLayout pageBG;
    private MaterialCardView titleCard;
    private TextView moodDes;
    private Button skipBtn;
    private LinearLayout elatedBG;
    private LinearLayout happyBG;
    private LinearLayout mehBG;
    private LinearLayout sadBG;
    private LinearLayout angryBG;
    private LinearLayout linearLay;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_a_mood);

        elatedBtn = findViewById(R.id.elated_btn);
        happyBtn = findViewById(R.id.happy_btn);
        ehBtn = findViewById(R.id.eh_btn);
        sadBtn = findViewById(R.id.sad_btn);
        angryBtn = findViewById(R.id.angry_btn);

//initializing UI elements
        elatedCard = findViewById(R.id.homeElatedCard);
        happyCard = findViewById(R.id.homeHappyCard);
        mehCard = findViewById(R.id.homeMehCard);
        sadCard = findViewById(R.id.homeSadCard);
        angryCard = findViewById(R.id.homeAngryCard);
        pageBG = findViewById(R.id.full_background);
        titleCard = findViewById(R.id.homeTitleCard);
        titleCard.setVisibility(View.VISIBLE);
        moodDes = findViewById(R.id.mood_des);
        skipBtn = findViewById(R.id.skip_btn);
        elatedBG = findViewById(R.id.elated_layout);
        happyBG = findViewById(R.id.happy_layout);
        mehBG = findViewById(R.id.meh_layout);
        sadBG = findViewById(R.id.sad_layout);
        angryBG = findViewById(R.id.angry_layout);

        //Elated Button push and dialog buttons


        elatedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                elatedTimes+=1;
                SharedPreferences prefs = getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE);
                int elatedCurrentValue = prefs.getInt("Elated", 0); // get existing value
                int elatedNewValue = elatedCurrentValue + (elatedTimes - (elatedTimes-1)); //add it to session value
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("Elated", elatedNewValue); // save updated value
                editor.apply();
                //The rest
                pageBG.setBackgroundColor(getResources().getColor(R.color.elatedColor));
                elatedCard.setClickable(false);
                happyCard.setClickable(false);
                mehCard.setClickable(false);
                sadCard.setClickable(false);
                angryCard.setClickable(false);
                titleCard.setVisibility(View.INVISIBLE);
                moodDes.setText(getResources().getString(R.string.elated_text));
                


                elatedBG.setBackgroundColor(getResources().getColor(R.color.elatedColor));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    elatedBtn.setImageTintList(ColorStateList.valueOf(Color.WHITE));
                }

                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        1.5f
                );
                elatedCard.setLayoutParams(param);
                happyBG.setBackgroundColor(getResources().getColor(R.color.elatedColor));
                mehBG.setBackgroundColor(getResources().getColor(R.color.elatedColor));
                sadBG.setBackgroundColor(getResources().getColor(R.color.elatedColor));
                angryBG.setBackgroundColor(getResources().getColor(R.color.elatedColor));
            }
        });

//Happy button push

        happyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                happyTimes+=1;
                SharedPreferences prefs = getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE);
                int happyCurrentValue = prefs.getInt("Happy", 0); // get existing value
                int happyNewValue = happyCurrentValue + (happyTimes - (happyTimes-1)); //add it to session value
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("Happy", happyNewValue); // save updated value
                editor.apply();

                //The rest
                pageBG.setBackgroundColor(getResources().getColor(R.color.happyColor));
                elatedCard.setClickable(false);
                happyCard.setClickable(false);
                mehCard.setClickable(false);
                sadCard.setClickable(false);
                angryCard.setClickable(false);
                titleCard.setVisibility(View.INVISIBLE);
                moodDes.setText(getResources().getString(R.string.happy_text));
                

                happyBG.setBackgroundColor(getResources().getColor(R.color.happyColor));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    happyBtn.setImageTintList(ColorStateList.valueOf(Color.WHITE));
                }

                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        1.5f
                );
                happyCard.setLayoutParams(param);
                elatedBG.setBackgroundColor(getResources().getColor(R.color.happyColor));
                mehBG.setBackgroundColor(getResources().getColor(R.color.happyColor));
                sadBG.setBackgroundColor(getResources().getColor(R.color.happyColor));
                angryBG.setBackgroundColor(getResources().getColor(R.color.happyColor));
            }
        });

//Meh button press

        mehCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mehTimes+=1;
                SharedPreferences prefs = getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE);
                int mehCurrentValue = prefs.getInt("Meh", 0); // get existing value
                int mehNewValue = mehCurrentValue + (mehTimes - (mehTimes-1)); //add it to session value
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("Meh", mehNewValue); // save updated value
                editor.apply();


                //The rest
                pageBG.setBackgroundColor(getResources().getColor(R.color.mehColor));
                elatedCard.setClickable(false);
                happyCard.setClickable(false);
                mehCard.setClickable(false);
                sadCard.setClickable(false);
                angryCard.setClickable(false);
                titleCard.setVisibility(View.INVISIBLE);
                moodDes.setText(getResources().getString(R.string.meh_text));
                

                mehBG.setBackgroundColor(getResources().getColor(R.color.mehColor));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ehBtn.setImageTintList(ColorStateList.valueOf(Color.WHITE));
                }

                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        1.5f
                );
                mehCard.setLayoutParams(param);
                elatedBG.setBackgroundColor(getResources().getColor(R.color.mehColor));
                happyBG.setBackgroundColor(getResources().getColor(R.color.mehColor));
                sadBG.setBackgroundColor(getResources().getColor(R.color.mehColor));
                angryBG.setBackgroundColor(getResources().getColor(R.color.mehColor));
                
            }
        });

//Sad Button Press

        sadCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sadTimes+=1;
                SharedPreferences prefs = getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE);
                int sadCurrentValue = prefs.getInt("Sad", 0); // get existing value
                int sadNewValue = sadCurrentValue + (sadTimes - (sadTimes-1)); //add it to session value
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("Sad", sadNewValue); // save updated value
                editor.apply();


                //The rest
                pageBG.setBackgroundColor(getResources().getColor(R.color.sadColor));
                elatedCard.setClickable(false);
                happyCard.setClickable(false);
                mehCard.setClickable(false);
                sadCard.setClickable(false);
                angryCard.setClickable(false);
                titleCard.setVisibility(View.INVISIBLE);
                moodDes.setText(getResources().getString(R.string.sad_text));
                

                sadBG.setBackgroundColor(getResources().getColor(R.color.sadColor));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    sadBtn.setImageTintList(ColorStateList.valueOf(Color.WHITE));
                }

                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        1.5f
                );
                sadCard.setLayoutParams(param);
                elatedBG.setBackgroundColor(getResources().getColor(R.color.sadColor));
                happyBG.setBackgroundColor(getResources().getColor(R.color.sadColor));
                mehBG.setBackgroundColor(getResources().getColor(R.color.sadColor));
                angryBG.setBackgroundColor(getResources().getColor(R.color.sadColor));
            }
        });

//Angry Button Press

        angryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                angryTimes+=1;
                SharedPreferences prefs = getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE);
                int angryCurrentValue = prefs.getInt("Angry", 0); // get existing value
                int angryNewValue = angryCurrentValue + (angryTimes - (angryTimes-1)); //add it to session value
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("Angry", angryNewValue); // save updated value
                editor.apply();

                //The rest
                pageBG.setBackgroundColor(getResources().getColor(R.color.angryColor));
                elatedCard.setClickable(false);
                happyCard.setClickable(false);
                mehCard.setClickable(false);
                sadCard.setClickable(false);
                angryCard.setClickable(false);
                titleCard.setVisibility(View.INVISIBLE);
                moodDes.setText(getResources().getString(R.string.angry_text));
                

                angryBG.setBackgroundColor(getResources().getColor(R.color.angryColor));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    angryBtn.setImageTintList(ColorStateList.valueOf(Color.WHITE));
                }

                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        1.5f
                );
                angryCard.setLayoutParams(param);
                elatedBG.setBackgroundColor(getResources().getColor(R.color.angryColor));
                happyBG.setBackgroundColor(getResources().getColor(R.color.angryColor));
                mehBG.setBackgroundColor(getResources().getColor(R.color.angryColor));
                sadBG.setBackgroundColor(getResources().getColor(R.color.angryColor));

            }
        });

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(RecordAMood.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }//END OF ONCREATE




}

