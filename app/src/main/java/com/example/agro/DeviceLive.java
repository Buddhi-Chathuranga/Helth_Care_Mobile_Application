package com.example.agro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.ekn.gruzer.gaugelibrary.HalfGauge;
import com.ekn.gruzer.gaugelibrary.Range;
import com.example.agro.Models.Statistic;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class DeviceLive extends AppCompatActivity {

    private ImageView liveViewBackBtn,liveViewRemoveDevice;
    private HalfGauge liveViewTempratureGauge,liveViewHumidityGauge,liveViewMoistureGauge,liveViewSmokeGauge,liveViewFalmeGauge;
    private Switch liveViewStatusSwitch;
    private String deviceId;
    private int checkStatus;
    private DatabaseReference mDatabase;

    Range rangeRed,rangeGreen,rangeRedOff,rangeGreenOn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_live);

        deviceId=getIntent().getStringExtra("device");
        checkStatus=getIntent().getIntExtra("status",2);
        initProcess();

    }

    private void initProcess() {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        rangeRed=new Range();
        rangeRed.setColor(Color.parseColor("#C62828"));
        rangeRed.setFrom(0.0);
        rangeRed.setTo(50.0);

        rangeGreen=new Range();
        rangeGreen.setColor(Color.parseColor("#1B5E20"));
        rangeGreen.setFrom(50);
        rangeGreen.setTo(100);


        liveViewBackBtn=findViewById(R.id.liveViewBackBtn);
        liveViewBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        liveViewRemoveDevice=findViewById(R.id.liveViewRemoveDevice);
        liveViewRemoveDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("devices").child(deviceId).removeValue();
                mDatabase.child("users").child(CustomUtils.loggedUser.getUid()).child("devices").child(deviceId).removeValue();
            }
        });

        liveViewTempratureGauge=findViewById(R.id.liveViewTempratureGauge);
        liveViewTempratureGauge.addRange(rangeRed);
        liveViewTempratureGauge.addRange(rangeGreen);

        liveViewHumidityGauge=findViewById(R.id.liveViewHumidityGauge);
        liveViewHumidityGauge.addRange(rangeRed);
        liveViewHumidityGauge.addRange(rangeGreen);

        liveViewMoistureGauge=findViewById(R.id.liveViewMoistureGauge);
        liveViewMoistureGauge.addRange(rangeRed);
        liveViewMoistureGauge.addRange(rangeGreen);

        liveViewSmokeGauge=findViewById(R.id.liveViewSmokeGauge);
        liveViewSmokeGauge.addRange(rangeRed);
        liveViewSmokeGauge.addRange(rangeGreen);

        liveViewFalmeGauge=findViewById(R.id.liveViewFalmeGauge);
        liveViewFalmeGauge.addRange(rangeRed);
        liveViewFalmeGauge.addRange(rangeGreen);

        mDatabase.child("users").child(CustomUtils.loggedUser.getUid()).child("devices").child(deviceId).child("live").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               if(snapshot.getValue()!=null){
                   Statistic stat=snapshot.getValue(Statistic.class);
                   liveViewTempratureGauge.setValue((float)stat.getTs());
                   liveViewHumidityGauge.setValue((float)stat.getHs());
                   liveViewMoistureGauge.setValue(getPrecentage(100, (float)stat.getSms()));
                   liveViewSmokeGauge.setValue((float)stat.getGss());
                   liveViewFalmeGauge.setValue((float)stat.getFs());
               }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        liveViewStatusSwitch=findViewById(R.id.liveViewStatusSwitch);
        liveViewStatusSwitch.setChecked((checkStatus==1)?true:false);
        liveViewStatusSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Map<String,Object> updateRecord=new HashMap<String,Object>();
                updateRecord.put("status",(b==true)?1:2);
                mDatabase.child("users").child(CustomUtils.loggedUser.getUid()).child("devices").child(deviceId).updateChildren(updateRecord);
            }
        });
    }

    private float getPrecentage(float max, float value){
        return Math.round(((value/max)*100));
    }

    private float getPrecentageBool(float value){
        return (value-1)*100;
    }
}