package com.example.projectchecking;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser()!= null){
            finish();
            return;
        }

        Button btnReg = findViewById(R.id.RegBtn);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registeruser();
            }
        });

        Button btnRegister = findViewById(R.id.Loginbtn);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToLogin();
            }
        });

    }
    private void registeruser(){
        EditText FN = findViewById(R.id.FN);
        EditText LN = findViewById(R.id.LN);
        EditText EM = findViewById(R.id.EM);
        EditText PW = findViewById(R.id.PW);

        String fN = FN.getText().toString();
        String lN = LN.getText().toString();
        String eM = EM.getText().toString();
        String pW = PW.getText().toString();

        if (fN.isEmpty() || lN.isEmpty() || eM.isEmpty() || pW.isEmpty()){
            Toast.makeText(this, "Fill all the fields", Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(eM, pW)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Users users = new Users(fN, lN, eM);
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            showMainMenu();
                                        }
                                    });
                        } else {
                            Toast.makeText(RegisterActivity.this,"Authentication Failed",Toast.LENGTH_LONG).show();

                        }
                    }
                });

    }
    private void showMainMenu(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
        finish();
    }
    private void switchToLogin(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}