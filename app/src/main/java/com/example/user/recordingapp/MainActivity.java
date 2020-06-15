package com.example.user.recordingapp;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RadioButton rblow,rbhigh;
    RadioGroup radioGroup;
    boolean isRec=false;
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    String path,text;
    int a;
    //int g;

    Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rblow=(RadioButton)this.findViewById(R.id.radioButton2);
        rbhigh=(RadioButton)this.findViewById(R.id.radioButton);
        rblow.setOnClickListener(this);
        rbhigh.setOnClickListener(this);
        radioGroup=(RadioGroup)this.findViewById(R.id.radioGroup);
        b1=(Button)this.findViewById(R.id.button);
        b2=(Button)this.findViewById(R.id.button2);
        b3=(Button)this.findViewById(R.id.button3);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
       // b1.setEnabled(false);
        //b2.setEnabled(false);
        //b3.setEnabled(false);

        //g=radioGroup.getCheckedRadioButtonId();
        if(rblow.isChecked()){
            text=".3gp";
            a=MediaRecorder.OutputFormat.THREE_GPP;
            Toast.makeText(this, "3 gp selected", Toast.LENGTH_SHORT).show();
        }
        else{
            text=".mpeg";
             a=MediaRecorder.OutputFormat.MPEG_4;

            Toast.makeText(this, "mpeg selected", Toast.LENGTH_SHORT).show();
        }
        path = Environment.getExternalStorageDirectory().getPath() + "/my_recording"+text;


    }

    @Override
    public void onClick(View v){

            if (v == b1) {

                if (isRec == false) {
                    mediaRecorder = new MediaRecorder();
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mediaRecorder.setOutputFormat(a);
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mediaRecorder.setOutputFile(path);
                    try {
                        mediaRecorder.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaRecorder.start();
                    isRec = true;
                    b1.setEnabled(false);
                    b2.setEnabled(true);
                    rblow.setEnabled(false);
                    rbhigh.setEnabled(false);
                    Toast.makeText(this, "Recording Start..", Toast.LENGTH_SHORT).show();
                }
            }
            if (v == b2) {
                if (isRec == true) {
                    mediaRecorder.stop();
                    isRec = false;
                    b1.setEnabled(true);
                    b2.setEnabled(false);
                    rbhigh.setEnabled(true);
                    rblow.setEnabled(true);

                    Toast.makeText(this, "Recording Stopped", Toast.LENGTH_SHORT).show();


                }
            }
            if (v == b3) {
                mediaPlayer = new MediaPlayer();
                try {
                    File f = new File(path);
                    if (!f.exists()) {
                        Toast.makeText(this, "file not found", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    mediaPlayer.setDataSource(path);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Toast.makeText(this, "Recording is playing", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        }


