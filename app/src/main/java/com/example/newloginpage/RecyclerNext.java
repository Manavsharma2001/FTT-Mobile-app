package com.example.newloginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RecyclerNext extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_next);


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        String abb = getIntent().getStringExtra("abb");

        TextView tv4 = findViewById(R.id.textView36);

        tv4.setText(mUser.getUid());



        Button button = findViewById(R.id.button45);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}