package com.example.parmarth;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class job_frag extends Fragment {
    Button bt;
    RecyclerView recyclerView;
    viadapter adapter;
    ArrayList<Modalclassjo> infolist;
    FirebaseFirestore db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_frag, container, false);
        recyclerView=view.findViewById(R.id.recyclerview_job);




        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        infolist = new ArrayList<>();
        adapter = new viadapter(getActivity(), infolist);

        recyclerView.setAdapter(adapter);
        db = FirebaseFirestore.getInstance();
        db.collection("Job_Profile")
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

        bt = view.findViewById(R.id.addjobbtn);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), PostJob.class);
                startActivity(intent);

            }
        });
            return  view;

    }
}
