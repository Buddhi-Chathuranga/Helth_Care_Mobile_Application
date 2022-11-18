package com.example.agro.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agro.Models.Exercise;
import com.example.agro.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExerciseAdapter  extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {

    ArrayList<Exercise> dataList;
    Context context;

    public ExerciseAdapter(Context context, ArrayList<Exercise> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, duration, description;
        public ImageView image;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.ex_name);
            duration = view.findViewById(R.id.ex_duration);
            description = view.findViewById(R.id.ex_desc);
            image = view.findViewById(R.id.ex_img);
        }
    }

    @NonNull
    @Override
    public ExerciseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.activity_exercise_record, parent, false);
        ExerciseAdapter.ViewHolder viewHolder = new ExerciseAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseAdapter.ViewHolder holder, int position) {
        Exercise data = dataList.get(position);
        holder.name.setText(data.getName());
        holder.duration.setText(data.getDuration());
        holder.description.setText(data.getDescription());
        Picasso.get().load(data.getImage()) .fit()
                .centerCrop().into(holder.image);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
