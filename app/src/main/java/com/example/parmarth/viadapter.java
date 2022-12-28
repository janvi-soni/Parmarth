package com.example.parmarth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class viadapter extends RecyclerView.Adapter<viadapter.jobprofViewHolder> {


    Context context;
    ArrayList<Modalclassjo> infoList;

    public viadapter(Context context, ArrayList<Modalclassjo> infoList ) {
        this.context = context;
        this.infoList = infoList;

    }


    @NonNull
    @Override
    public viadapter.jobprofViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.layout_jobprof, parent, false);
        return new viadapter.jobprofViewHolder(v);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull viadapter.jobprofViewHolder holder, int position) {
        Modalclassjo info = infoList.get(position);

        holder.textViewcomp_name.setText(info.Company_Name);
        holder.textViewjob_title.setText(info.Job_Title);
        holder.textViewCjob_type.setText(info.Job_Type);
        holder.textViewcomp_location.setText(info.Job_Location);



    }
    @Override
    public int getItemCount() {
        return infoList.size();
    }

    public static class jobprofViewHolder extends RecyclerView.ViewHolder {
        TextView textViewcomp_name, textViewjob_title, textViewCjob_type, textViewcomp_location,textViewabout_comp,
                textViewresponsibilities, textViewskills,textViewperks;

        public jobprofViewHolder(View itemView) {
            super(itemView);

            textViewcomp_name = itemView.findViewById(R.id.ltv1);
            textViewjob_title = itemView.findViewById(R.id.ltv2);
            textViewCjob_type = itemView.findViewById(R.id.ltv3);
            textViewcomp_location = itemView.findViewById(R.id.ltv4);


        }
    }
}


