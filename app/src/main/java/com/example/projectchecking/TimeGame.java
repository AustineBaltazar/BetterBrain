package com.example.projectchecking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TimeGame extends AppCompatActivity {


    Button b_start, b_main;

    TextView tv_info;

    long startTime, endTime, currentTime, bestTime = 10000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_game);
        b_start = findViewById(R.id.b_start);
        b_main = findViewById(R.id.b_main);
        tv_info = findViewById(R.id.tv_info);
        b_start.setEnabled(true);
        b_main.setEnabled(false);

        tv_info.setText("BEST TIME:"+ bestTime + "ms");

        b_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b_start.setEnabled(false);
                b_main.setText("");

                Handler handler = new Handler();
                handler.postDelayed (new Runnable() {
                    @Override
                    public void run() {
                        startTime = System.currentTimeMillis();
                        b_main.setBackgroundColor(
                                ContextCompat.getColor(getApplicationContext(), R.color.green));
                        b_main.setText("PRESS");
                        b_main.setEnabled(true);
                    }
                },2000);
            }
        });
        b_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endTime = System.currentTimeMillis();
                currentTime = endTime - startTime;
                b_main.setBackgroundColor(
                        ContextCompat.getColor(getApplicationContext(),R.color.light_purple));
                b_main.setText(currentTime + "ms");
                b_start.setEnabled(true);
                b_main.setEnabled(false);

                if(currentTime < bestTime){
                    bestTime = currentTime;
                    tv_info.setText("BEST TIME:"+ bestTime + "ms");
                }
            }
        });

    }

}