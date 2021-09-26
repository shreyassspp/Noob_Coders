package com.example.noob_coders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.Viewholder>{

    private Context context;
    private ArrayList<ProjectModal> projectModalArrayList;

    // Constructor
    public ProjectAdapter(Context context, ArrayList<ProjectModal> projectModalArrayList) {
        this.context = context;
        this.projectModalArrayList = projectModalArrayList;
    }

    @NonNull
    @Override
    public ProjectAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        ProjectModal model = projectModalArrayList.get(position);
        holder.projectNameTV.setText(model.getProject_name());
        holder.projectDescTV.setText("" + model.getProject_desc());
        holder.projectIV.setImageResource(model.getProject_image());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return projectModalArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView projectIV;
        private TextView projectNameTV, projectDescTV;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            projectIV = itemView.findViewById(R.id.idIVProjectImage);
            projectNameTV = itemView.findViewById(R.id.idTVProjectName);
            projectDescTV = itemView.findViewById(R.id.idTVProjectDesc);
        }
    }
}
