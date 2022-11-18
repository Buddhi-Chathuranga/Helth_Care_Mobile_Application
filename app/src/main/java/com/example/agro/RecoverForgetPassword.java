package com.example.agro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

public class RecoverForgetPassword extends AppCompatActivity implements Validator.ValidationListener {

    TextView forgetPasswordBackLogin;

    @NotEmpty
    @Email
    EditText forgetPasswordEmail;

    Validator validator;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_forget_password);

        initProcess();
    }

    private void initProcess() {
        forgetPasswordBackLogin=findViewById(R.id.forgetPasswordBackLogin);
        forgetPasswordBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        forgetPasswordEmail=findViewById(R.id.forgetPasswordEmail);

        validator = new Validator(this);
        validator.setValidationListener(this);

    }

    @Override
    public void onValidationSucceeded() {
        try {
            mAuth.sendPasswordResetEmail(forgetPasswordEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(RecoverForgetPassword.this, "Please check your email for reset link.", Toast.LENGTH_SHORT).show();
                }
            });
        }catch(Exception ex){
            Toast.makeText(RecoverForgetPassword.this, "Something wrong", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            if (view instanceof EditText) {
                ((EditText) view).setError(error.getCollatedErrorMessage(RecoverForgetPassword.this));
            }
        }
    }
}