package com.example.outline;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends AppCompatActivity
{
    TextView timerText;
    Button stopStartButton;

    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;

    boolean timerStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerText = (TextView) findViewById(R.id.timerText);
        stopStartButton = (Button) findViewById(R.id.startStopButton);

        timer = new Timer();



        //개발자 정보 버튼 클릭시 액티비티 전환
        Button developer_info_btn = (Button) findViewById(R.id.next_btn4);
        developer_info_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
                startActivity(intent);
            }
        });



    }

    public void resetTapped(View view)
    {
        AlertDialog.Builder resetAlert = new AlertDialog.Builder(this);
        resetAlert.setTitle("Reset Timer");
        resetAlert.setMessage("타이머를 초기화 하시겠습니까?");
        resetAlert.setPositiveButton("Reset", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                if(timerTask != null)
                {
                    timerTask.cancel();
                    setButtonUI("START", R.color.white);
                    time = 0.0;
                    timerStarted = false;
                    timerText.setText(formatTime(0,0,0));

                }
            }
        });

        resetAlert.setNeutralButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                //do nothing
            }
        });

        resetAlert.show();

    }

    public void startStopTapped(View view)
    {
        if(timerStarted == false)
        {
            timerStarted = true;
            setButtonUI("STOP", R.color.white);

            startTimer();
        }
        else
        {
            timerStarted = false;
            setButtonUI("START", R.color.white);

            timerTask.cancel();
        }
    }

    private void setButtonUI(String start, int color)
    {
        stopStartButton.setText(start);
        stopStartButton.setTextColor(ContextCompat.getColor(this, color));
    }

    private void startTimer()
    {
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        time++;
                        timerText.setText(getTimerText());
                    }
                });
            }

        };
        timer.scheduleAtFixedRate(timerTask, 0 ,1000);
    }


    private String getTimerText()
    {
        int rounded = (int) Math.round(time);

        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;
        int hours = ((rounded % 86400) / 3600);

        return formatTime(seconds, minutes, hours);
    }

    private String formatTime(int seconds, int minutes, int hours)
    {
        return String.format("%02d",hours) + " : " + String.format("%02d",minutes) + " : " + String.format("%02d",seconds);
    }

}