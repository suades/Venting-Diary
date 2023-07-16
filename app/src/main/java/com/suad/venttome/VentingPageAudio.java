package com.suad.venttome;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class VentingPageAudio extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    private ImageButton recordBtn;
    private Chronometer timer;
    private TextView filenameText;
    private Spinner chooseVentSpinner2;
    private EditText inputTitle;
    private static final String[] paths = {"Audio", "Text"};
    private Boolean isRecording = false;
    private String recordPermission = Manifest.permission.RECORD_AUDIO;
    private int PERMISSION_CODE = 21;
    private MediaRecorder mediaRecorder;
    private String recordFile;
    private String recordPath;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vent_audio);


        //Initialising things
        recordBtn = (ImageButton) findViewById(R.id.record_btn);
        timer = (Chronometer) findViewById(R.id.record_timer);
        filenameText = (TextView) findViewById(R.id.record_filename);
        chooseVentSpinner2 = (Spinner) findViewById(R.id.vent_spinner_audio);
        inputTitle = (EditText) findViewById(R.id.input_title_audio);

        recordBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.record_btn:
                        if (isRecording) {
                            //Stop
                            stopRecording();
                            recordBtn.setImageResource(R.drawable.baseline_mic_off_black_48);
                            recordBtn.setBackgroundResource(R.drawable.round_button);
                            isRecording = false;
                        } else{
                            //Start
                            if (checkPermission()) {
                                startRecording();
                                recordBtn.setImageResource(R.drawable.baseline_mic_black_48);
                                recordBtn.setBackgroundResource(R.drawable.record_round_btn);
                                isRecording = true;
                            }
                        }
                        break;
                }
            }

        });


        Intent intent = getIntent();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(VentingPageAudio.this,
                R.layout.simple_spinner_item_custom, paths);

        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item_custom);
        chooseVentSpinner2.setAdapter(adapter);
        chooseVentSpinner2.setOnItemSelectedListener(this);

    }


    //Stop Recording method
    private void stopRecording() {
        timer.stop();
        recordBtn.setBackgroundColor(getResources().getColor(R.color.recordStopColor));

        inputTitle.getText().clear();
        filenameText.setText("Stopped Recording-File Saved : " + recordFile);

        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;

    }


    //Start Recording Method
    private void startRecording() {
        //Starting timer
        recordFile = inputTitle.getText().toString();
        if (inputTitle.getText().toString().equals("")){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_hh_mm", Locale.US);
            Date now = new Date();
            recordFile = "VD." + formatter.format(now) + ".3gp";
        }
        recordBtn.setBackgroundColor(getResources().getColor(R.color.recordGoColor));
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();

        //File Path and name of file
        recordPath = getApplication().getExternalFilesDir("/").getAbsolutePath();


        filenameText.setText("Recording-File Name : " + recordFile);

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(recordPath + "/" + recordFile);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaRecorder.start();

    }

    private boolean checkPermission() {
        if(ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.RECORD_AUDIO)== PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            ActivityCompat.requestPermissions(this,new String[]{recordPermission}, PERMISSION_CODE);
            return false;
        }
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        if (position == 0) {
            Toast.makeText(VentingPageAudio.this, "You are currently on the Audio Page", Toast.LENGTH_SHORT).show();
        } else if (position == 1) {
            Intent intent = new Intent(this, ventingPage.class);
            startActivity(intent);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

    public void MainActivity2(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
