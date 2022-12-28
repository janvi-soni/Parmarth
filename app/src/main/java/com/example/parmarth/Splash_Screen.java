package com.example.parmarth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ProgressBar;

public class Splash_Screen extends AppCompatActivity {
  ProgressBar progressBar;
    private Context context=this;

    private static int ss=1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);


        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        final boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        progressBar=findViewById(R.id.pp);
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                try {

                    for(int i=0;i<=100;i++)
                    {
                        progressBar.setProgress(i);
                        sleep(20);
                    }
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    if (isConnected) {
                        Intent intent = new Intent(Splash_Screen.this, Login.class);
                        startActivity(intent);
                        finish();
                    }

                }
            }
        };
        thread.start();

    }
}