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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    TextView tv,t,tr;
    FirebaseAuth authi;
    EditText x,y;
    Button m;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        authi= FirebaseAuth.getInstance();
        if (authi.getCurrentUser()!=null) {
            Intent j = new Intent(Login.this,MainActivity.class);
            startActivity(j);
            finish();
        }
        else
        {
            setContentView(R.layout.activity_login);
            t=findViewById(R.id.t);
            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent k = new Intent(Login.this, Forgot.class);
                    startActivity(k);
                    finish();
                }
            });
        x=findViewById(R.id.edt1);
        y=findViewById(R.id.edt2);
        m=findViewById(R.id.btn1);
        tr=findViewById(R.id.tvreg);
        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(Login.this,Registeration.class);
                startActivity(j);
                finish();
            }


        });

        m.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View view) {
                                     String email = x.getText().toString().trim();
                                     String password = y.getText().toString().trim();
                                     if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                                         Toast.makeText(getApplicationContext(), "please enter the required fields", Toast.LENGTH_LONG).show();
                                     } else {
                                         authi.signInWithEmailAndPassword(email, password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                             @Override
                                             public void onComplete(@NonNull Task<AuthResult> task) {
                                                 if ((!task.isSuccessful())) {
                                                     Toast.makeText(getApplicationContext(), "error occured while signing", Toast.LENGTH_LONG).show();
                                                 } else {
                                                     Intent j = new Intent(Login.this, MainActivity.class);
                                                     startActivity(j);
                                                     finish();
                                                 }
                                             }


                                         });
                                     }
                                 }
                             });
        tv=findViewById(R.id.tvgoog);

        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc= GoogleSignIn.getClient(this,gso);
        GoogleSignInAccount acc=GoogleSignIn.getLastSignedInAccount(this);
        if(acc!=null)
        {
            Intent k = new Intent(Login.this, MainActivity.class);
            startActivity(k);
            finish();
        }
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin();
            }
        });
    }
}

    void signin() {
        Intent i=gsc.getSignInIntent();
        startActivityForResult(i,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {

                task.getResult(ApiException.class);
                finish();
                Intent k = new Intent(Login.this, MainActivity.class);
                startActivity(k);
                finish();

            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        }
    }
}