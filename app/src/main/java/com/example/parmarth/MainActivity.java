package com.example.parmarth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ActionBar actionBar;
    home_frag hf=new home_frag();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigationView = findViewById(R.id.bottomNavigationView2);
        getSupportFragmentManager().beginTransaction().replace(R.id.myframe,
                hf).commit();
        navigationView.getMenu();

        actionBar=getSupportActionBar();
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment selected=null;
                switch(id){
                    case R.id.home:
                        selected= new home_frag();
                        break;
                    case R.id.search:
                        selected=  new search_frag();
                        break;
                    case R.id.job:
                        selected=  new job_frag();
                        break;
                    case  R.id.profile:
                        selected=new profile_frag();
                        break;
                    default:
                        selected=new home_frag();


                }

                getSupportFragmentManager().beginTransaction().replace(R.id.myframe,
                        selected).commit();
                return true;
            }
        });

    }



}