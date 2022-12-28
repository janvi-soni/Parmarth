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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class home_frag extends Fragment implements RecyclerViewInterface{
    RecyclerView recyclerView;
    JobOpenAdapter adapter;
    ArrayList<Modalclassjo> infolist;
    FirebaseFirestore db;
    ImageView ivvi,ivhoh,ivwb,ivpwa,ivdad,ivdp,ivm,ivd,ivg,ivp,ivb,ivf;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home_frag, container, false);


        ivvi=view.findViewById(R.id.ivvi);
        ivhoh=view.findViewById(R.id.ivhoh);
        ivwb=view.findViewById(R.id.ivwb);
        ivpwa=view.findViewById(R.id.ivpwa);
        ivdad=view.findViewById(R.id.ivdad);
        ivdp=view.findViewById(R.id.ivdp);

        ivm=view.findViewById(R.id.ivm);
        ivd=view.findViewById(R.id.ivd);
        ivg=view.findViewById(R.id.ivg);
        ivp=view.findViewById(R.id.ivp);
        ivb=view.findViewById(R.id.ivb);
        ivf=view.findViewById(R.id.ivf);

        ivm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k=new Intent(getActivity(),Marketing.class);
                startActivity(k);

            }
        });

        ivd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k=new Intent(getActivity(),Design.class);
                startActivity(k);

            }
        });

        ivg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k=new Intent(getActivity(),Government.class);
                startActivity(k);

            }
        });

        ivp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k=new Intent(getActivity(),project.class);
                startActivity(k);

            }
        });

        ivb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k=new Intent(getActivity(),Business.class);
                startActivity(k);

            }
        });

        ivf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k=new Intent(getActivity(),Finance.class);
                startActivity(k);

            }
        });

        ivvi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k=new Intent(getActivity(),Visuallyimpaired.class);
                startActivity(k);

            }
        });
        ivhoh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k=new Intent(getActivity(),HardOfHearing.class);
                startActivity(k);

            }
        });
        ivwb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k=new Intent(getActivity(),Wheelchair.class);
                startActivity(k);

            }
        });
        ivpwa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k=new Intent(getActivity(),Autism.class);
                startActivity(k);

            }
        });
        ivdad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k=new Intent(getActivity(),DumbandDeaf.class);
                startActivity(k);

            }
        });
        ivdp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k=new Intent(getActivity(),Disabled.class);
                startActivity(k);

            }
        });

        recyclerView = view.findViewById(R.id.recyclerview_companydetails);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        infolist = new ArrayList<>();
        adapter = new JobOpenAdapter(getActivity(),infolist,this);

        recyclerView.setAdapter(adapter);


        db = FirebaseFirestore.getInstance();
        db.collection("Job_Profile").addSnapshotListener(new EventListener<QuerySnapshot>() {
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


        return view;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent(getActivity(),DetailsActivity.class);
        intent.putExtra("Company_Name",infolist.get(position).getCompany_Name());
        intent.putExtra("Job_Title",infolist.get(position).getJob_Title());
        intent.putExtra("Job_Type",infolist.get(position).getJob_Type());
        intent.putExtra("Job_Location",infolist.get(position).getJob_Location());
        intent.putExtra("About_Company",infolist.get(position).getAbout_Company());
        intent.putExtra("Responsibilities",infolist.get(position).getResponsibilities());
        intent.putExtra("Skills",infolist.get(position).getSkills());
        intent.putExtra("Perks",infolist.get(position).getPerks());
        startActivity(intent);

    }
}