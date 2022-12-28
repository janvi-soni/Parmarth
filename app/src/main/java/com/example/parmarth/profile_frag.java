package com.example.parmarth;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class profile_frag extends Fragment {

    public static ImageView i,dp;
    public static TextView tname,tabout,tjobtype,texperience,tdescription,test;
    Button bt;


    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_profile_frag, container, false);


//        String x= String.valueOf(Profile.m);

//        Toast.makeText(getActivity(),x,Toast.LENGTH_LONG).show();
//
//        test=view.findViewById(R.id.test);
//
//        test.setText(x);
//


        dp=view.findViewById(R.id.dp);

        tname=view.findViewById(R.id.tname);
        tabout=view.findViewById(R.id.tabout);
        tjobtype=view.findViewById(R.id.tjob);
        texperience=view.findViewById(R.id.texp);
        tdescription=view.findViewById(R.id.tdesc);
        bt=view.findViewById(R.id.btn);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent k=new Intent(getActivity().getApplicationContext(),Login.class);
                getActivity().startActivity(k);
                getActivity().finish();

            }
        });

        i=view.findViewById(R.id.iupdate);
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k=new Intent(getActivity(),Profile.class);
                getActivity().startActivity(k);


            }
        });

//        Profile myActivity = (Profile) getActivity();
//        String yname = myActivity.xname;
//        String yabout = myActivity.xabout;
//        String yjob = myActivity.xjobtype;
//        String yex = myActivity.xexperience;
//        String ydesc = myActivity.xdescription;
//        String yimg = myActivity.ximage;


//        String yname=getArguments().getString("xname");
//        String yabout=getArguments().getString("xabout");
//        String yjob=getArguments().getString("xjobtype");
//        String yex=getArguments().getString("xexperience");
//        String ydesc=getArguments().getString("xdescription");
//        String yimg=getArguments().getString("ximage");

//        String yname=getActivity().getIntent().getStringExtra("xname");
//        String yabout=getActivity().getIntent().getStringExtra("xabout");
//        String yjob=getActivity().getIntent().getStringExtra("xjobtype");
//        String yex=getActivity().getIntent().getStringExtra("xexperience");
//        String ydesc=getActivity().getIntent().getStringExtra("xdescription");
//        String yimg=getActivity().getIntent().getStringExtra("ximage");


//        tname.setText(yname);
//        tabout.setText(yabout);
//        tjobtype.setText(yjob);
//        texperience.setText(yex);
//        tdescription.setText(ydesc);
//        Glide.with(getActivity().getApplicationContext()).load(yimg).into(dp);

        return view;
    }


}