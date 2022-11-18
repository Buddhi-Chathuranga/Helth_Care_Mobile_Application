package com.example.agro.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.agro.CustomUtils;
import com.example.agro.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Emergency extends Service {

    private DatabaseReference mDatabase;
    private String userid;
    private String CHANNEL_ID="emergency_notification";

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("Starting Service !!!");
        userid= CustomUtils.loggedUser.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mDatabase.child("users").child(userid).child("emergency").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    System.out.println("Service Process Exists");

                    NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                    Notification notify=new Notification.Builder
                            (getApplicationContext()).setContentTitle("Agro").setContentText("Warning By AGRO").
                            setContentTitle(snapshot.getValue(com.example.agro.Models.Emergency.class).message).setSmallIcon(R.drawable.ic_baseline_security_update_warning_24).build();

                    notify.flags |= Notification.FLAG_AUTO_CANCEL;
                    notif.notify(0, notify);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
