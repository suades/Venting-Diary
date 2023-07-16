package com.suad.venttome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import androidx.appcompat.app.AppCompatActivity;

public class DiaryEntry extends AppCompatActivity {
    private ImageButton backToDiary;
    private TextView  readText2;
    private Button loadDiaryEntry;
    private String textFileToRead;
    private String txtFile2;
    private static final String FILE_NAME = "ventingDiary.txt";


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_entry);
        Intent intent = getIntent();
        txtFile2 = intent.getStringExtra(Intent.EXTRA_TEXT);



        //Initialising things
        backToDiary = (ImageButton) findViewById(R.id.back_to_diary);
        readText2 = (TextView) findViewById(R.id.read_text);
        loadDiaryEntry = (Button) findViewById(R.id.load_diary_entry);
    }

    public void load(View view) {
        FileInputStream fis = null;
        try {
            fis = openFileInput(txtFile2);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            readText2.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void backToTextList(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
