package com.example.agro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agro.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity  implements Validator.ValidationListener {

    TextView forget,loginGoRegister;

    @NotEmpty
    @Email
    EditText email;

    @NotEmpty
    EditText password;

    Button login;
    float v=0;

    Validator validator;

    private FirebaseAuth mAuth;

    private DatabaseReference mDatabase;

    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initProcess();
    }

    private void initProcess() {
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        forget = findViewById(R.id.login_recover_password);

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,RecoverForgetPassword.class));
            }
        });

        loginGoRegister=findViewById(R.id.loginGoRegister);

        loginGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Signup.class));
            }
        });

        login = findViewById(R.id.login_button);

        validator = new Validator(this);
        validator.setValidationListener(this);

        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });

        loading = new ProgressDialog(Login.this);
        loading.setMessage("Loading..");
        loading.setTitle("Please wait");

        mDatabase = FirebaseDatabase.getInstance().getReference();

        email.setText("saman@gmail.com");
        password.setText("Saman@1234");
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onValidationSucceeded() {
        try {
            loading.show();
            mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            loading.hide();
                            if (task.isSuccessful()) {
                                CustomUtils.loggedUser = mAuth.getCurrentUser();

                                mDatabase.child("users").child(CustomUtils.loggedUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        System.out.println("Gettting user data");
                                        CustomUtils.userData=snapshot.getValue(User.class);
                                        if(CustomUtils.userData!=null){
                                            startActivity(new Intent(Login.this,Profile.class));
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });


                            } else {
                                Toast.makeText(Login.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }catch(Exception ex){
            Toast.makeText(Login.this, "Something wrong", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            if (view instanceof EditText) {
                ((EditText) view).setError(error.getCollatedErrorMessage(Login.this));
            }
        }
    }
}