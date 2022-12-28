package com.example.parmarth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class PostJob extends AppCompatActivity {
    EditText company_name,job_title,job_type,job_location,about_company,responsibilities,skills,perks;
    Button gobtn;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);
        company_name=findViewById(R.id.pjed1);
        job_title=findViewById(R.id.pjed3);
        job_type=findViewById(R.id.pjed4);
        job_location=findViewById(R.id.pjed5);

        about_company=findViewById(R.id.pjed01);
        responsibilities=findViewById(R.id.pjed02);
        skills=findViewById(R.id.pjed03);
        perks=findViewById(R.id.pjed04);





        db = FirebaseFirestore.getInstance();

        gobtn=findViewById(R.id.gobtn);
        gobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Company_Name=company_name.getText().toString();
                String Job_Title=job_title.getText().toString();
                String Job_Type=job_type.getText().toString();
                String Job_Location=job_location.getText().toString();

                String About_Company=about_company.getText().toString();
                String Responsibilities=responsibilities.getText().toString();
                String Skills=skills.getText().toString();
                String Perks=perks.getText().toString();



                if (TextUtils.isEmpty(Company_Name) || TextUtils.isEmpty(Job_Title) ||
                        TextUtils.isEmpty(Job_Type) || TextUtils.isEmpty(Job_Location) ||
                        TextUtils.isEmpty(About_Company) || TextUtils.isEmpty(Responsibilities) ||
                        TextUtils.isEmpty(Skills) || TextUtils.isEmpty(Perks)

                ) {
                    Toast.makeText(getApplicationContext(), "enter the required fields", Toast.LENGTH_LONG).show();
                } else {
                    Map<String, Object> user = new HashMap<>();
                    user.put("Company_Name", Company_Name);
                    user.put("Job_Title", Job_Title);
                    user.put("Job_Type", Job_Type);
                    user.put("Job_Location", Job_Location);

                    user.put("About_Company", About_Company);
                    user.put("Responsibilities", Responsibilities);
                    user.put("Skills", Skills);
                    user.put("Perks", Perks);


                    db.collection("Job_Profile")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Intent l = new Intent(PostJob.this, home_frag.class);
                                    startActivity(l);

                                    Toast.makeText(PostJob.this, "Successfully added data to firestore", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {

                                    Toast.makeText(PostJob.this, "Failed", Toast.LENGTH_SHORT).show();


                                }
                            });

                }
            }
        });

    }
}