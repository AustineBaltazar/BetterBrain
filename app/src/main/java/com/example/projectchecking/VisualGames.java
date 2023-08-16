//package com.example.projectchecking;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//
//public class VisualGames extends AppCompatActivity {
//    private Button colmBtn, colsBtn;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.visual_games);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        colmBtn = (Button) findViewById(R.id.cmBtn);
//        colmBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                openColorMatchingGame();
//            }
//        });
//        colsBtn = (Button) findViewById(R.id.csBtn);
//        colsBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                openColorSequenceGame();
//            }
//        });
//    }
//    public void openColorMatchingGame() {
//        Intent intent = new Intent(this, ColorMatchingGame.class);
//        startActivity(intent);
//    }
//
//    public void openColorSequenceGame() {
//        Intent intent = new Intent(this, ColorSequenceGame.class);
//        startActivity(intent);
//    }
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                this.finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}