package com.example.agro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.agro.Adapters.ScheduleListAdapter;
import com.example.agro.Adapters.StatisticsHistoryListAdapter;
import com.example.agro.Models.Schedule;
import com.example.agro.Models.Statistic;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DeviceSchedule extends AppCompatActivity {

    private ArrayList<Schedule> dataList;
    private RecyclerView dataListView;
    public static boolean isStart;
    public static SimpleDateFormat simpleDateFormat;
    private DatabaseReference mDatabase;
    private String deviceId;

    public static TextView deviceScheduleStart,deviceScheduleEnd;
    private Button deviceScheduleSaveBtn;
    private ImageView deviceScheduleBack;

    public static String selectedDate1,selectedDate2;

    public int datakey=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_schedule);
        deviceId=getIntent().getStringExtra("device");
        initProcess();
    }

    private void initProcess() {
        dataList=new ArrayList<>();
        simpleDateFormat=new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");
        dataListView=findViewById(R.id.deviceScheduleRecycleView);
        isStart=false;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        deviceScheduleBack=findViewById(R.id.deviceScheduleBack);
        deviceScheduleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        deviceScheduleStart=findViewById(R.id.deviceScheduleStart);

        deviceScheduleStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDate1=null;
                isStart=true;
                DialogFragment newFragment = new DeviceSheduleDatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "Select Start Schedule");
            }
        });

        deviceScheduleEnd=findViewById(R.id.deviceScheduleEnd);
        deviceScheduleEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDate2=null;
                isStart=false;
                DialogFragment newFragment = new DeviceSheduleDatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "Select Start Schedule");
            }
        });

        deviceScheduleSaveBtn=findViewById(R.id.deviceScheduleSaveBtn);
        deviceScheduleSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    System.out.println(datakey);
                    if(datakey<5){
                        mDatabase.child("users").child(CustomUtils.loggedUser.getUid()).child("devices").child(deviceId).child("schedules").push().setValue(new Schedule(simpleDateFormat.parse(selectedDate1).getTime(),simpleDateFormat.parse(selectedDate2).getTime()));
                        selectedDate1=null;
                        selectedDate2=null;
                        deviceScheduleStart.setText("");
                        deviceScheduleEnd.setText("");
                    }else{
                        Toast.makeText(DeviceSchedule.this, "Maximum schedule count limited to five records", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        mDatabase.child("users").child(CustomUtils.loggedUser.getUid()).child("devices").child(deviceId).child("schedules").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                dataList.clear();
                datakey=0;
                for(DataSnapshot schedule:snapshot.getChildren()){
                    Schedule scheduleRecord=schedule.getValue(Schedule.class);
                    scheduleRecord.id=schedule.getKey();
                    dataList.add(scheduleRecord);
                    datakey++;
                }

                ScheduleListAdapter adapter = new ScheduleListAdapter(DeviceSchedule.this,dataList,mDatabase,deviceId);
                dataListView.setHasFixedSize(true);
                dataListView.setLayoutManager(new LinearLayoutManager(DeviceSchedule.this));
                dataListView.setAdapter(adapter);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static class DeviceSheduleDatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            try {
                if(isStart==true){
                    selectedDate1=year+"/"+(month+1)+"/"+day;
                    DialogFragment newFragment = new TimePickerFragment();
                    newFragment.show(getActivity().getSupportFragmentManager(), "Start Time Picker");
                }else{
                    selectedDate2=year+"/"+(month+1)+"/"+day;
                    DialogFragment newFragment = new TimePickerFragment();
                    newFragment.show(getActivity().getSupportFragmentManager(), "End Time Picker");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
           try {
               if(isStart==true){
                   selectedDate1+=" "+hourOfDay+":"+minute+":00";
                   deviceScheduleStart.setText(simpleDateFormat.format(simpleDateFormat.parse(selectedDate1)));
               }else{
                   selectedDate2+=" "+hourOfDay+":"+minute+":00";
                   deviceScheduleEnd.setText(simpleDateFormat.format(simpleDateFormat.parse(selectedDate2)));

               }
           }catch(Exception e){
               e.printStackTrace();
           }
        }
    }

}