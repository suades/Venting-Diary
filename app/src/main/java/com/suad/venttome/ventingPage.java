package com.suad.venttome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;




public class ventingPage extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    //Adding Everything
    private ImageButton finalButton;
    private EditText inputFeelings;
    private EditText inputTitle;
    ImageButton backToHome;
    private TextView filenameText;
    private String textFile;
    private String textPath;
    private String rootpath;
    private Spinner chooseVentSpinner;
    private static final String[] paths = {"Text", "Audio"};


//onCreate Method

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vent);


        //Initialising things
        inputFeelings = (EditText) findViewById(R.id.inputFeelings);
        chooseVentSpinner = (Spinner) findViewById(R.id.vent_spinner);
        inputTitle = (EditText) findViewById(R.id.input_title);

        Intent intent = getIntent();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ventingPage.this,
               R.layout.simple_spinner_item_custom, paths);

        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item_custom);
        chooseVentSpinner.setAdapter(adapter);
        chooseVentSpinner.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        if (position == 0) {
            Toast.makeText(ventingPage.this, "You are currently on the Text Page", Toast.LENGTH_SHORT).show();
        } else if (position == 1) {
            Intent intent = new Intent(this, VentingPageAudio.class);
            startActivity(intent);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

//End of onCreate

    public void save2(View v){
        textFile = inputTitle.getText().toString();
        String text = inputFeelings.getText().toString();
        if (inputTitle.getText().toString().equals("")){
            Toast.makeText(this, "You have to write a title for your entry to be saved.",
                    Toast.LENGTH_LONG).show();

            new AlertDialog.Builder(ventingPage.this)
                    .setTitle("Title")
                    .setMessage("Please enter a title for your text entry.")
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }else {
            try {
                inputTitle.getText().clear();
                inputFeelings.getText().clear();
                Toast.makeText(this, "Saved to " + getFilesDir() + "/TextEntries/" + textFile,
                        Toast.LENGTH_LONG).show();
                rootpath = getApplication().getFilesDir().getAbsolutePath() + "/TextEntries/";
                File root1 = new File(rootpath);
                if (!root1.exists()) {
                    root1.mkdirs();
                }
                FileWriter out = new FileWriter(new File(rootpath,textFile));
                out.write(text);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void save1(View v){
        textFile = inputTitle.getText().toString();
        String text = inputFeelings.getText().toString();
        FileOutputStream fos = null;

        try {
            String rootPath = getFilesDir().getAbsolutePath() + "/TextEntries/";
            File root = new File(getFilesDir().getAbsolutePath() + "/TextEntries/");
            if (!root.exists()) {
                root.mkdirs();
            }else {
                fos = new FileOutputStream(rootPath + textFile);
                fos.write(text.getBytes());
                inputFeelings.getText().clear();
                Toast.makeText(this, "Saved to " + getFilesDir() + "/TextEntries/" + textFile,
                        Toast.LENGTH_LONG).show();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public void MainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {

    }


}