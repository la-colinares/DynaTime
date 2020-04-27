package com.lacolinares.sample;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lacolinares.dynatime.DynaTime;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText txtTimeMillis, txtInterval;
    private TextView txtTime;
    private Button btnStart, btnPause, btnResume, btnStop;

    private String str_time_millis, str_interval;

    private DynaTime dynaTime = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTimeMillis = findViewById(R.id.txt_time_millis);
        txtInterval = findViewById(R.id.txt_interval);

        txtTime = findViewById(R.id.txt_time);

        btnStart = findViewById(R.id.btn_start);
        btnPause = findViewById(R.id.btn_pause);
        btnResume = findViewById(R.id.btn_resume);
        btnStop = findViewById(R.id.btn_stop);

        setButtonOnClickListener();
    }

    private void initDynaTime() {
        dynaTime = new DynaTime() {
            @Override
            public void onTimerStart(long timeRemaining) {
                String min = (timeRemaining % 60000 / 1000) != 0 ? (timeRemaining % 60000 / 1000) + "" : (timeRemaining % 60000 / 1000) + "0";
                String time = (timeRemaining / 60000) + ":" + min;
                txtTime.setText("Time: " + time);
            }

            @Override
            public void onFinish() {
                txtTime.setText("Time: 00:00");
            }
        };
    }

    private void startDynaTime(int timeMillis, int interval) {
        dynaTime.setTime(timeMillis);
        dynaTime.setInterval(interval);
        dynaTime.startTimer();
    }

    private void pauseTime() {
        if (dynaTime.isRunning()) dynaTime.pauseTimer();
    }

    private void resumeTime() {
        if (dynaTime.isPaused()) dynaTime.resumeTimer();
    }

    private void stopTime() {
        if (dynaTime.isRunning()) {
            dynaTime.stopTimer();
            dynaTime = null;
            txtTime.setText("Time: 00:00");
        }
    }

    private void showMessage(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void setButtonOnClickListener() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int timeInMillis = Integer.parseInt(txtTimeMillis.getText().toString());
                int interval = Integer.parseInt(txtInterval.getText().toString());
                initDynaTime();
                startDynaTime(timeInMillis, interval);
                showMessage("DynaTime Start");

            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTime();
                showMessage("DynaTime Pause");
            }
        });
        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resumeTime();
                showMessage("DynaTime Resume");
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTime();
                showMessage("DynaTime Stop");
            }
        });

    }


}
