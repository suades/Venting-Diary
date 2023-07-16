package com.suad.venttome;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class settingsPage extends AppCompatActivity {


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch reminderSwitch;
    private Button btnDate;
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    final Calendar myCalendar = Calendar.getInstance();
    private ImageButton settingToHome;
    private Button setPass;
    private EditText numOne;
    private EditText numTwo;
    private EditText numThree;
    private EditText numFour;
    private Button enterBtn;
    private Button removePass;
    private boolean theBoolean;
    private boolean theTimeBoolean;
    private String reminderTime;
    private File[] allTextFiles;
    private Button exportAll;
    private File pictureFile;
    private Button viewHome;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);
        // initiate view's
        reminderSwitch = findViewById(R.id.reminder_switch);
        btnDate = findViewById(R.id.notif_time);
        settingToHome = findViewById(R.id.setting_to_home);
        setPass = findViewById(R.id.set_pass);
        viewHome = findViewById(R.id.home_preview);
        removePass = findViewById(R.id.remove_pass);
        exportAll = findViewById(R.id.export_all_btn);


//Export Code


        viewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(settingsPage.this, RealHomePage.class);
                startActivity(intent);
            }
        });

        exportAll.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String path = getFilesDir().getAbsolutePath()+"/TextEntries/";
                File src = new File(path);
                String dstPath = Environment.getExternalStorageDirectory() + File.separator + "Venting Diary Backup" + File.separator;
                File dst = new File(dstPath);
                Toast.makeText(settingsPage.this, "Your files have been exported to" + dstPath,Toast.LENGTH_SHORT);
                try {
                    copyFile(src, dst);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        reminderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                if(isChecked){
                    SharedPreferences prefs = getSharedPreferences("PASS_PREF", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("TimeSetOrNot", true);
                    editor.apply();
                    btnDate.setVisibility(View.VISIBLE);
                    reminderSwitch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_notifications_white_36, 0, 0, 0);

                } else {
                    SharedPreferences prefs = getSharedPreferences("PASS_PREF", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("TimeSetOrNot", false);
                    editor.apply();
                    btnDate.setVisibility(View.GONE);
                    reminderSwitch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_notifications_off_white_36, 0, 0, 0);

                }
            }

        });



        settingToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(settingsPage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        setPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog registerDialog = new Dialog(settingsPage.this);
                registerDialog.setContentView(R.layout.register_page_dialog);
                registerDialog.show();

                enterBtn = registerDialog.findViewById(R.id.enter_btn);
                numOne = registerDialog.findViewById(R.id.number_1);
                numTwo = registerDialog.findViewById(R.id.number_2);
                numThree = registerDialog.findViewById(R.id.number_3);
                numFour = registerDialog.findViewById(R.id.number_4);


                enterBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String thePass = numOne.getText().toString()+numTwo.getText().toString()+numThree.getText().toString()+numFour.getText().toString();
                        SharedPreferences prefs = getSharedPreferences("PASS_PREF", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("Digits", thePass);
                        editor.putBoolean("SetOrNot", true);
                        editor.apply();
                        Intent intent = new Intent(settingsPage.this, settingsPage.class);
                        startActivity(intent);

                    }
                });


            }
        });


//CHECKING THE BOOLEANS
        SharedPreferences thePrefs = getSharedPreferences("PASS_PREF", Context.MODE_PRIVATE);
        theBoolean = thePrefs.getBoolean("SetOrNot",false);
        theTimeBoolean = thePrefs.getBoolean("TimeSetOrNot",false);
        reminderTime = thePrefs.getString("ReminderTime","");
        btnDate.setText(reminderTime);
        if(theBoolean == false){
            removePass.setVisibility(View.GONE);
            setPass.setText("SET A PASSCODE");

        }
        if(theBoolean == true) {
            removePass.setVisibility(View.VISIBLE);
            setPass.setText("UPDATE YOUR PASSCODE");
        }

        if(theTimeBoolean == false){
            btnDate.setVisibility(View.GONE);
            reminderSwitch.setChecked(false);
            reminderSwitch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_notifications_off_white_36, 0, 0, 0);

        }
        if(theTimeBoolean == true) {
            btnDate.setVisibility(View.VISIBLE);
            reminderSwitch.setChecked(true);
            reminderSwitch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_notifications_white_36, 0, 0, 0);

        }





        removePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs2 = getSharedPreferences("PASS_PREF", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2 = prefs2.edit();
                editor2.putString("Digits", "");
                editor2.putBoolean("SetOrNot", false);
                editor2.apply();
                Toast.makeText(settingsPage.this, "Your Passcode has been DELETED. You will no longer be displayed a lockscreen", Toast.LENGTH_LONG).show();

            }
        });


    }


//NOTIFICATION CODE
    private void scheduleNotification (Notification notification , long delay) {
        Intent notificationIntent = new Intent( this, NotificationReceiver. class ) ;
        notificationIntent.putExtra(NotificationReceiver.NOTIFICATION_ID , 1 ) ;
        notificationIntent.putExtra(NotificationReceiver.NOTIFICATION , notification) ;
        PendingIntent pendingIntent = PendingIntent. getBroadcast ( this, 0 , notificationIntent , PendingIntent. FLAG_UPDATE_CURRENT ) ;
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE) ;
        assert alarmManager != null;
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, delay, pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, myCalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }
    private Notification getNotification (String content) {
        Intent myIntent = new Intent(settingsPage.this, SplashScreen.class);
        @SuppressLint("WrongConstant") PendingIntent pendingIntent = PendingIntent.getActivity(settingsPage.this, 0, myIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, default_notification_channel_id);
        if(theTimeBoolean == true) {
            builder.setContentTitle("Would you like to reflect on your day?");
            builder.setContentText("Click here to release your emotions");
            builder.setSmallIcon(R.mipmap.final_ic_launcher_venting_diary_foreground_notif);
            builder.setAutoCancel(true);
            builder.setContentIntent(pendingIntent);
            builder.setColor(Color.rgb(22, 25, 59));
            builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
            builder.setChannelId(NOTIFICATION_CHANNEL_ID);
        }
        return builder.build();
    }
    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myCalendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
            myCalendar.set(Calendar.MINUTE,minute);
            updateLabel();
        }

    } ;
    public void setDate (View view) {
        new TimePickerDialog(settingsPage.this, time,
                myCalendar.get(Calendar.HOUR_OF_DAY),
                myCalendar.get(Calendar.MINUTE),
                false
        ).show();
    }
    private void updateLabel () {
        String myFormat = "hh:mm" ; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale. getDefault ()) ;
        Date date = myCalendar.getTime();
        btnDate.setText(sdf.format(date));
        SharedPreferences prefs = getSharedPreferences("PASS_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("ReminderTime", btnDate.getText().toString());
        editor.putBoolean("TimeSetOrNot", true);
        editor.apply();
        scheduleNotification(getNotification(btnDate.getText().toString()), date.getTime());
    }


//EXPORT CODE


    public static void copyFile(File src, File dst) throws IOException
    {
        //if folder does not exist
        if (!dst.exists()) {
            dst.mkdir();
        }
        FileChannel inChannel = new FileInputStream(src).getChannel();
        FileChannel outChannel = new FileOutputStream(dst).getChannel();
        try
        {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        }
        finally
        {
            if (inChannel != null)
                inChannel.close();
            if (outChannel != null)
                outChannel.close();
        }
    }

    private File exportFile(File src, File dst) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File expFile = new File(dst.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try {
            inChannel = new FileInputStream(src).getChannel();
            outChannel = new FileOutputStream(expFile).getChannel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } finally {
            if (inChannel != null)
                inChannel.close();
            if (outChannel != null)
                outChannel.close();
        }

        return expFile;
    }


}
