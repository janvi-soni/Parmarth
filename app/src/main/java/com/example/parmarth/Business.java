package com.example.parmarth;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Business extends AppCompatActivity {
    RecyclerView recyclerView;
    viadapter adapter;
    ArrayList<Modalclassjo> infolist;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        recyclerView = findViewById(R.id.rvbusiness);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        infolist = new ArrayList<>();
        adapter = new viadapter(Business.this, infolist);

        recyclerView.setAdapter(adapter);
        db = FirebaseFirestore.getInstance();
        db.collection("Job_Profile").whereEqualTo("Job_Title","Business")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            //if (progressDialog.isShowing())
                            //   progressDialog.dismiss();
                            Log.e("Firestore errror", error.getMessage());
                            return;

                        }
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                infolist.add(dc.getDocument().toObject(Modalclassjo.class));
                            }
                            adapter.notifyDataSetChanged();
                            // if (progressDialog.isShowing())
                            //   progressDialog.dismiss();
                        }
                    }


                });
    }

}

