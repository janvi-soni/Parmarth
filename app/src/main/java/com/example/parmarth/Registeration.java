package com.example.parmarth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registeration extends AppCompatActivity {
    EditText ee1, ee2, ee3;
    Button bb1;
    TextView tv;
    FirebaseAuth authiiii;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        ee1 = findViewById(R.id.ee1);
        ee2 = findViewById(R.id.ee2);
        ee3 = findViewById(R.id.ee3);
        bb1 = findViewById(R.id.bb1);
        authiiii = FirebaseAuth.getInstance();
        tv = findViewById(R.id.tvlog);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registeration.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        bb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailit = ee1.getText().toString().trim();
                String passwordit = ee2.getText().toString().trim();
                String confirmit = ee3.getText().toString().trim();
                if (TextUtils.isEmpty(emailit) || TextUtils.isEmpty(passwordit) || TextUtils.isEmpty(confirmit)) {
                    Toast.makeText(getApplicationContext(), "enter the required fields", Toast.LENGTH_LONG).show();
                } else if (!(passwordit.equals(confirmit))) {
                    Toast.makeText(getApplicationContext(), "password and confirm password does not match", Toast.LENGTH_LONG).show();
                } else if (passwordit.length() < 6) {
                    Toast.makeText(getApplicationContext(), "password must be of atleast 6 characters ", Toast.LENGTH_LONG).show();

                } else {
                    authiiii.createUserWithEmailAndPassword(emailit, passwordit).addOnCompleteListener(Registeration.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "There is some Error occured while signing in ", Toast.LENGTH_LONG).show();
                            } else {
                                startActivity(new Intent(Registeration.this, MainActivity.class));
                                finish();
                            }
                        }
                    });


                }
            }
        });
    }
}