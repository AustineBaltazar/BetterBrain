package com.example.projectchecking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TutorialActivity extends AppCompatActivity {
    private Button skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        ViewPager viewPager = findViewById(R.id.viewPager);
        ImageAdapterTutorial imageAdapterTutorial = new ImageAdapterTutorial(this);
        viewPager.setAdapter(imageAdapterTutorial);

        skip = (Button) findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                returnMainMenu();
            }
        });
    }
    private void returnMainMenu(){
        Intent intent = new Intent(this,    MainMenu.class);
        startActivity(intent);
    }
}