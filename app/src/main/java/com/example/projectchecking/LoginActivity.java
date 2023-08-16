package com.example.projectchecking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() !=null){
            finish();
            return;
        }
        Button btnLogin = findViewById(R.id.RegBtn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticateUser();
            }
        });
        Button btnRegister = findViewById(R.id.Loginbtn);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToRegister();
            }
        });


    }

    private void authenticateUser(){
        EditText LgEmail = findViewById(R.id.LgName);
        EditText LgPassword = findViewById(R.id.LgPassword);

        String Lgemail = LgEmail.getText().toString();
        String Lgpassword = LgPassword.getText().toString();

        if (Lgemail.isEmpty() || Lgpassword.isEmpty()){
            Toast.makeText(this,"Fill up all the fields", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(Lgemail, Lgpassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            showMainActivity();

                        } else {
                            Toast.makeText(LoginActivity.this,"Authentication Failed",Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
    private void showMainActivity(){
        Intent intent = new Intent(this, UserIsLogin.class);
        startActivity(intent);
        finish();
    }
    private void switchToRegister(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}