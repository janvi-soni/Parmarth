package com.example.parmarth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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

public class Profile extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5;
    ImageView i1;
    Button b;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    Uri filepath;
    Bitmap bitmap;
    String UserId="";
    public static String m;

    TextView t1,t2,t3,t4,t5;
    ImageView i;
//    profile_frag pf=new profile_frag();
//    TextView textView=pf.test;
//    String i="janvi";

    //public String xname,xabout,xjobtype,xexperience,xdescription,ximage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        m="janvi";
        t1=profile_frag.tname;
        t2=profile_frag.tabout;
        t3=profile_frag.tjobtype;
        t4=profile_frag.texperience;
        t5=profile_frag.tdescription;
        i=profile_frag.dp;


        e1 = findViewById(R.id.ped1);
        e2 = findViewById(R.id.ped3);
        e3 = findViewById(R.id.ped4);
        e4 = findViewById(R.id.ped5);
        e5 = findViewById(R.id.ped6);
        i1 = findViewById(R.id.imgv);
        b = findViewById(R.id.bt);

//        textView.setText(i.toString().trim());
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserId = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("userprofile");
        storageReference = FirebaseStorage.getInstance().getReference();
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withContext(getApplicationContext()).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent i=new Intent();
                                i.setType("image/*");
                                i.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(Intent.createChooser(i,"Please select file"),101);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                                Toast.makeText(Profile.this, "error aa rhi h", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateonfirebase();


            }
        });
    }
    //now the mentioned lines is to get the url in filepath variable and to show the selected image in imageview and after then you click on the button to upload
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==101 && resultCode==RESULT_OK && data!=null)
        {
            filepath=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(filepath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                i1.setImageBitmap(bitmap);
            }
            catch (Exception e)
            {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);

    }
    private void updateonfirebase() {
        String Pname = e1.getText().toString().trim();
        String Pabout = e2.getText().toString().trim();
        String Pjobtype = e3.getText().toString().trim();
        String Pexperience = e4.getText().toString().trim();
        String Pdescription = e5.getText().toString().trim();


        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("File Uploder");
        pd.show();
        final StorageReference uploader=storageReference.child("profileimages/"+"img"+System.currentTimeMillis());
        uploader.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        final Map<String,Object> map=new HashMap<>();
                        map.put("Image",uri.toString());
                        map.put("Names",Pname);
                        map.put("About",Pabout);
                        map.put("Job_Type",Pjobtype);
                        map.put("Experience",Pexperience);
                        map.put("Description",Pdescription);
                        databaseReference.child(UserId).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists())
                                {
                                    databaseReference.child(UserId).updateChildren(map);

                                }
                                else
                                {
                                    databaseReference.child(UserId).setValue(map);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        pd.dismiss();
                        Toast.makeText(Profile.this, "Updated Successfully", Toast.LENGTH_SHORT).show();


                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                float percent=(100*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                pd.setMessage("Uploading...");
            }
        });

    }
    protected void onStart()
    {

        super.onStart();
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        UserId= user.getUid();
        databaseReference.child(UserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
////                    xname= snapshot.child("Names").getValue().toString();
////                    xabout=snapshot.child("About").getValue().toString();
////                    xjobtype=snapshot.child("Job_Type").getValue().toString();
////                    xexperience=snapshot.child("Experience").getValue().toString();
////                    xdescription=snapshot.child("Description").getValue().toString();
////                    ximage=snapshot.child("Image").getValue().toString();
//
////                    profile_frag f=new profile_frag();
//////                    Bundle args=new Bundle();
//////                    args.putString("xname",snapshot.child("Names").getValue().toString());
//////                    args.putString("xabout",snapshot.child("About").getValue().toString());
//////                    args.putString("xjobtype",snapshot.child("Job_Type").getValue().toString());
//////                    args.putString("xexperience",snapshot.child("Experience").getValue().toString());
//////                    args.putString("xdescription",snapshot.child("Description").getValue().toString());
//////                    args.putString("ximage",snapshot.child("Image").getValue().toString());
//////                    f.setArguments(args);
//////                    FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
//////                    ft.replace(R.id.myframe,f);
//////                    ft.commit();
//                    //can't pass data from activity to fragment by using intent
//                    Intent intent=new Intent(Profile.this,profile_frag.class);
//                    intent.putExtra("xname",snapshot.child("Names").getValue().toString());
//                    intent.putExtra("xabout",snapshot.child("About").getValue().toString());
//                    intent.putExtra("xjobtype",snapshot.child("Job_Type").getValue().toString());
//                    intent.putExtra("xexperience",snapshot.child("Experience").getValue().toString());
//                    intent.putExtra("xdescription",snapshot.child("Description").getValue().toString());
//                    intent.putExtra("ximage",snapshot.child("Image").getValue().toString());
//                    getActivity.startActivity(intent);
//
//
//
                    t1.setText(snapshot.child("Names").getValue().toString());
                    t2.setText(snapshot.child("About").getValue().toString());
                    t3.setText(snapshot.child("Job_Type").getValue().toString());
                    t4.setText(snapshot.child("Experience").getValue().toString());
                    t5.setText(snapshot.child("Description").getValue().toString());
                    Glide.with(getApplicationContext()).load(snapshot.child("Image").getValue().toString()).into(i);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"error occureddd",Toast.LENGTH_SHORT).show();

            }
        });
    }
}