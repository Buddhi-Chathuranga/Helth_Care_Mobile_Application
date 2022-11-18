package com.example.agro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.agro.Adapters.DeviceListAdapter;
import com.example.agro.Models.Device;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Devices extends AppCompatActivity {

    ImageView backButton;
    RecyclerView deviceList;

    private DatabaseReference mDatabase;

    ArrayList<Device> deviceDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);

        initProcess();
    }

    private void initProcess() {

        mDatabase=FirebaseDatabase.getInstance().getReference();

        backButton=findViewById(R.id.devicesBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        deviceList=findViewById(R.id.deviceList);
        deviceDataList=new ArrayList<>();
        mDatabase.child("users").child(CustomUtils.loggedUser.getUid()).child("devices").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());
                for(DataSnapshot device:snapshot.getChildren()){
                    Device deviceTemp=device.getValue(Device.class);
                    deviceTemp.id=device.getKey();
                    deviceDataList.add(deviceTemp);
                }

                DeviceListAdapter adapter = new DeviceListAdapter(Devices.this,deviceDataList);
                deviceList.setHasFixedSize(true);
                deviceList.setLayoutManager(new LinearLayoutManager(Devices.this));
                deviceList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Devices.this, "Data fetching error.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}