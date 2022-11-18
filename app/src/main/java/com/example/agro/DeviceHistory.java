package com.example.agro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agro.Adapters.StatisticsHistoryListAdapter;
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

public class DeviceHistory extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private String deviceId;
    private ArrayList<Statistic> dataList;
    private RecyclerView dataListView;
    private ImageView deviceHistoryBack;
    public static TextView deviceHistoryStartDate,deviceHistoryEndDate;
    public static Date start,end;
    Button deviceHistoryFilterButton;
    public static boolean isStart=false;
    public static SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_history);

        deviceId=getIntent().getStringExtra("device");

        initProcess();
    }

    private void initProcess() {
        simpleDateFormat=new SimpleDateFormat("yyyy/mm/dd");

        deviceHistoryFilterButton=findViewById(R.id.deviceHistoryFilterButton);

        deviceHistoryFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataFetchProcess(true);
            }
        });
        
        deviceHistoryStartDate=findViewById(R.id.deviceHistoryStartDate);
        deviceHistoryStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isStart=true;
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "Select Start Date");
            }
        });


        deviceHistoryEndDate=findViewById(R.id.deviceHistoryEndDate);
        deviceHistoryEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isStart=false;
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "Select End Date");
            }
        });

        deviceHistoryBack=findViewById(R.id.deviceHistoryBack);
        deviceHistoryBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference();
        dataListView=findViewById(R.id.deviceHistoryRecyclerView);
        dataList=new ArrayList<>();
        dataFetchProcess(false);
    }

    private void dataFetchProcess(boolean useFilters) {
        dataList.clear();
        mDatabase.child("users").child(CustomUtils.loggedUser.getUid()).child("devices").child(deviceId).child("statistics").orderByChild("date").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot stat:snapshot.getChildren()){

                    if(useFilters==true && start!=null && end!=null){
                        Statistic statistic=stat.getValue(Statistic.class);
                        statistic.id=stat.getKey();
                        try {
                            if(start.before(simpleDateFormat.parse(simpleDateFormat.format(new Date(Long.parseLong(statistic.getDate()))))) && end.after(simpleDateFormat.parse(simpleDateFormat.format(new Date(Long.parseLong(statistic.getDate())))))){
                                System.out.println("Added");
                                dataList.add(statistic);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Statistic statistic=stat.getValue(Statistic.class);
                        statistic.id=stat.getKey();
                        dataList.add(statistic);
                    }


                }

                StatisticsHistoryListAdapter adapter = new StatisticsHistoryListAdapter(DeviceHistory.this,dataList);
                dataListView.setHasFixedSize(true);
                dataListView.setLayoutManager(new LinearLayoutManager(DeviceHistory.this));
                dataListView.setAdapter(adapter);
                System.out.println(dataList.size()+"");
                if(useFilters==true){
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DeviceHistory.this, "Data fetching error.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            try {
                if(isStart==true){
                    start=new SimpleDateFormat("yyyy/mm/dd").parse(year+"/"+(month+1)+"/"+day);
                    deviceHistoryStartDate.setText(new SimpleDateFormat("yyyy/mm/dd").format(start));
                    isStart=false;
                    System.out.println(start.getTime()+"");
                }else{
                    end=new SimpleDateFormat("yyyy/mm/dd").parse(year+"/"+(month+1)+"/"+day);
                    deviceHistoryEndDate.setText(new SimpleDateFormat("yyyy/mm/dd").format(end));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}