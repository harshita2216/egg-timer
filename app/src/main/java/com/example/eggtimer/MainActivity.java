package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    SeekBar seekBar;
    boolean counterIsActive=false;
    Button goButton;
    CountDownTimer countDownTimer;

    public void resetTimer(){
        textView.setText("0:30");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("Go!");
        counterIsActive=false;
    }

    public void timerStart(View view) {
        if (counterIsActive) {
            resetTimer();

        } else {
            counterIsActive = true;
            seekBar.setEnabled(false);
            goButton.setText("Stop!");


            Log.i("Button Pressed", "Nice");
            countDownTimer= new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {

                    Log.i("Timer all done", "Time stopped");
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(),R.raw.pristine);
                    mplayer.start();
                    resetTimer();

                }
            }.start();
        }
    }



    public void updateTimer(int secondsLeft){
        int minutes = secondsLeft/60;
        int seconds = secondsLeft - (minutes *60);

        String secondString =Integer.toString(seconds);
        if(seconds<=9){
            secondString="0"+secondString;
        }
        textView.setText(Integer.toString(minutes)+":"+secondString);


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar=findViewById(R.id.seekBar);
        textView=findViewById(R.id.textView);
        goButton = findViewById(R.id.button);

        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}