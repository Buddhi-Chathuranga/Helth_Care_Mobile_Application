package com.example.agro.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agro.CustomUtils;
import com.example.agro.DeviceHistory;
import com.example.agro.Models.Schedule;
import com.example.agro.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ViewHolder> {

    ArrayList<Schedule> dataList;
    Context context;
    DatabaseReference mDatabase;
    String deviceId;
    SimpleDateFormat simpledateformat;

    public ScheduleListAdapter(Context context, ArrayList<Schedule> dataList,DatabaseReference mDatabase,String deviceId) {
        this.context = context;
        this.dataList = dataList;
        this.mDatabase = mDatabase;
        this.deviceId=deviceId;
        this.simpledateformat=new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView deviceScheduleFromRecord, deviceScheduleToRecord;
        public ImageView deviceScheduleDelete;

        public ViewHolder(View view) {
            super(view);
            deviceScheduleFromRecord = view.findViewById(R.id.deviceScheduleFromRecord);
            deviceScheduleToRecord = view.findViewById(R.id.deviceScheduleToRecord);
            deviceScheduleDelete = view.findViewById(R.id.deviceScheduleDelete);
        }
    }

    @NonNull
    @Override
    public ScheduleListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.activity_device_schedule_record, parent, false);
        ScheduleListAdapter.ViewHolder viewHolder = new ScheduleListAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleListAdapter.ViewHolder holder, int position) {
        Schedule schedule = dataList.get(position);
        holder.deviceScheduleFromRecord.setText(this.simpledateformat.format(new Date(schedule.getStart())));
        holder.deviceScheduleToRecord.setText(this.simpledateformat.format(new Date(schedule.getEnd())));

        holder.deviceScheduleDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("users").child(CustomUtils.loggedUser.getUid()).child("devices").child(deviceId).child("schedules").child(schedule.getId()).removeValue();

                notifyItemRemoved(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
