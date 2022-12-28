package com.example.parmarth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot extends AppCompatActivity {
    EditText re;
    Button btnn;
    FirebaseAuth authenti;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        authenti= FirebaseAuth.getInstance();
        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailss=re.getText().toString().trim();
                if(TextUtils.isEmpty(emailss))
                {
                    Toast.makeText(getApplicationContext(),"enter email",Toast.LENGTH_LONG).show();
                }
                else
                {

                    authenti.sendPasswordResetEmail(emailss).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getApplicationContext(), "password mailed at your email", Toast.LENGTH_LONG).show();
                        }
                    });
                }


            }
        });
    }
}