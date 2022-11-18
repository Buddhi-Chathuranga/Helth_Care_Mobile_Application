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

import com.example.agro.DeviceHistory;
import com.example.agro.DeviceLive;
import com.example.agro.DeviceSchedule;
import com.example.agro.Models.Device;
import com.example.agro.Predictions;
import com.example.agro.R;

import java.util.ArrayList;

public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.ViewHolder>{

    ArrayList<Device> dataList;
    Context context;

    public DeviceListAdapter(Context context,ArrayList<Device> dataList) {
        this.context=context;
        this.dataList = dataList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView deviceRecordTitle,deviceRecordSubTitle;
        public ConstraintLayout deviceRecord;
        public ImageView liveView,historyRecords,predictions;
        public ViewHolder(View view) {
            super(view);
            deviceRecordTitle =  view.findViewById(R.id.deviceRecordTitle);
            deviceRecordSubTitle =  view.findViewById(R.id.deviceRecordSubTitle);
            deviceRecord = view.findViewById(R.id.deviceRecordLayour);
            liveView=view.findViewById(R.id.deviceRecordLive);
            historyRecords=view.findViewById(R.id.deviceRecordHistory);
            predictions=view.findViewById(R.id.predictions);
//            deviceRecordSchedule=view.findViewById(R.id.deviceRecordSchedule);
        }
    }

    @NonNull
    @Override
    public DeviceListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.activity_device_record, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceListAdapter.ViewHolder holder, int position) {
        Device device=dataList.get(position);
        holder.deviceRecordTitle.setText("Device "+(position+1));
        holder.deviceRecordSubTitle.setText(device.code);
        holder.historyRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, DeviceHistory.class).putExtra("device", device.id ));
            }
        });

        holder.liveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, DeviceLive.class).putExtra("device", device.id ).putExtra("status", device.status  ));
            }
        });
        holder.predictions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Predictions.class).putExtra("device", device.id ));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
