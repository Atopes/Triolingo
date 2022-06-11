package com.example.triolingo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    private EditText emailEditText;
    private TextView resetPasswordButton;
    private ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailEditText = (EditText) findViewById(R.id.email);
        resetPasswordButton = (TextView) findViewById(R.id.resetPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        auth= FirebaseAuth.getInstance();

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }
    private void resetPassword(){
        String email = emailEditText.getText().toString().trim();

        if (email.isEmpty()){
            emailEditText.setError("E-mail je povinné pole!");
            emailEditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Prosím zadajte platný e-mail!");
            emailEditText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ForgotPassword.this,"Pozrite si Váš mail na zmenu hesla!",Toast.LENGTH_LONG).show();
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ForgotPassword.this,"Nastala chyba! Skúste to znova!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}