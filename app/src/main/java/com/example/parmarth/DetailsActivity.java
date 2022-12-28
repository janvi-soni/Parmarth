package com.example.parmarth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    TextView t01,t02,t03,t04,t05,t06,t07,t08;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);




        String Company_Name=getIntent().getStringExtra("Company_Name");
        String Job_Title=getIntent().getStringExtra("Job_Title");
        String Job_Type=getIntent().getStringExtra("Job_Type");
        String Job_Location=getIntent().getStringExtra("Job_Location");
        String About_Company=getIntent().getStringExtra("About_Company");
        String Responsibilities=getIntent().getStringExtra("Responsibilities");
        String Skills=getIntent().getStringExtra("Skills");
        String Perks=getIntent().getStringExtra("Perks");

        t01=findViewById(R.id.t1);
        t02=findViewById(R.id.t2);
        t03=findViewById(R.id.t3);
        t04=findViewById(R.id.t4);
        t05=findViewById(R.id.t5);
        t06=findViewById(R.id.t6);
        t07=findViewById(R.id.t7);
        t08=findViewById(R.id.t8);


        t01.setText(Company_Name);
        t02.setText(Job_Title);
        t03.setText(Job_Type);
        t04.setText(Job_Location);
        t05.setText(About_Company);
        t06.setText(Responsibilities);
        t07.setText(Skills);
        t08.setText(Perks);





    }
}