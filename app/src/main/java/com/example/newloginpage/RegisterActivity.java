package com.example.newloginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {


    TextView alreadyHaveaacount;
    EditText inputEmail,inputPassword,inputConfirmPassword;
    Button btnRegister;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;


    FirebaseAuth mAuth;
    FirebaseUser mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        alreadyHaveaacount = findViewById(R.id.alreadyHaveaccount);

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        progressDialog = new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        alreadyHaveaacount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(RegisterActivity.this,MainActivity.class));


            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PerformAuth();
            }
        });
    }

    private void PerformAuth() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmPassword = inputConfirmPassword.getText().toString();


        if(!email.matches(emailPattern))
        {
            inputEmail.setError("Enter correct Email");
            inputEmail.requestFocus();

        }
        else if(password.isEmpty()|| password.length()<6)
        {
            inputPassword.setError("Enter correct password");
        }
        else if(!password.equals(confirmPassword))
        {
            inputConfirmPassword.setError("password didn't match");

        }
        else
        {
            progressDialog.setMessage("Please wait while Registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        User user = new User(email);
                        FirebaseDatabase.getInstance().getReference("investors")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(RegisterActivity.this, "User has been registered successfully", Toast.LENGTH_LONG).show();
                                            FirebaseDatabase.getInstance().getReference("users").child("fmNKSTflBTfaghFzhvo5pDQdTbM2").child("clients").push().setValue(email);



//


                                        }else {
                                            Toast.makeText(RegisterActivity.this, "Failed to Register Try Again!", Toast.LENGTH_LONG).show();

                                        }
                                    }
                                });

                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(RegisterActivity.this,"Registration Successful", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }

                }
            });




        }
    }

    private void sendUserToNextActivity() {
        Intent intent=new Intent(RegisterActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
