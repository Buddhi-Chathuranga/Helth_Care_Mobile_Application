package com.example.agro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agro.Adapters.DeviceListAdapter;
import com.example.agro.Adapters.StatisticsHistoryListAdapter;
import com.example.agro.Models.Device;
import com.example.agro.Models.Statistic;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
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

public class DeviceStatistics extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntriesArrayList;

    Spinner device,factor;
    public static Date start,end;
    public static TextView from,to;
    Button historyStatisticsFilterButton;
    ImageView deviceStatisticsBack;

    private ArrayList<Device> devicesList;
    private String[] factorsList;
    private DatabaseReference mDatabase;

    public static boolean isStart=false;

    public static SimpleDateFormat simpleDateFormat;

    private int selectedDevice=1;
    private int selectedFactor=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_statistics);

        deviceStatisticsBack=findViewById(R.id.deviceStatisticsBack);
        deviceStatisticsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");

        historyStatisticsFilterButton=findViewById(R.id.historyStatisticsFilterButton);
        historyStatisticsFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBarEntries();
            }
        });

        mDatabase= FirebaseDatabase.getInstance().getReference();
        barChart = findViewById(R.id.idBarChart);

        device=findViewById(R.id.deviceStatisticsDevice);
        device.setOnItemSelectedListener(this);

        factor=findViewById(R.id.deviceStatisticsFactor);
        factor.setOnItemSelectedListener(this);

        from=findViewById(R.id.deviceStatisticsFrom);
        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isStart=true;
                DialogFragment newFragment = new DeviceStatisticsDatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "Select Start Date");
            }
        });
        to=findViewById(R.id.deviceStatisticsTo);
        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isStart=false;
                DialogFragment newFragment = new DeviceStatisticsDatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "Select End Date");
            }
        });

        historyStatisticsFilterButton=findViewById(R.id.historyStatisticsFilterButton);
        barEntriesArrayList = new ArrayList<>();
        devicesList=new ArrayList<>();
        initializeDevices();
        initializeFactors();
    }

    private void initializeFactors() {
        factorsList= new String[]{"Body temperature", "Heartrate", "Humidity", "spo2", "Temperature"};
        ArrayAdapter<String> devicesArrayAdapter = new ArrayAdapter<String>(DeviceStatistics.this, android.R.layout.simple_spinner_item,factorsList);
        devicesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        factor.setAdapter(devicesArrayAdapter);
    }

    private void initializeDevices() {
        mDatabase.child("users").child(CustomUtils.loggedUser.getUid()).child("devices").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot device:snapshot.getChildren()){
                    Device deviceTemp=device.getValue(Device.class);
                    deviceTemp.id=device.getKey();
                    devicesList.add(deviceTemp);
                }

                String[] str = new String[devicesList.size()];

                for (int i = 0; i < devicesList.size(); i++) {
                    str[i] = devicesList.get(i).code;
                }

                ArrayAdapter<String> devicesArrayAdapter = new ArrayAdapter<String>(DeviceStatistics.this, android.R.layout.simple_spinner_item,str);
                devicesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                device.setAdapter(devicesArrayAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void getBarEntries() {
        if(start!=null && end!=null && selectedDevice>0 && selectedFactor>0){

            mDatabase.child("users").child(CustomUtils.loggedUser.getUid()).child("devices").child(devicesList.get(selectedDevice-1).id).child("statistics").orderByChild("date").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    float graphId=1;

                    for(DataSnapshot stat:snapshot.getChildren()){
                        Statistic statistic=stat.getValue(Statistic.class);
                        System.out.println(statistic);
                        statistic.id=stat.getKey();
                        try {
                            if(start.before(simpleDateFormat.parse(simpleDateFormat.format(new Date(Long.parseLong(statistic.getDate()))))) && end.after(simpleDateFormat.parse(simpleDateFormat.format(new Date(Long.parseLong(statistic.getDate())))))){
                                float recordValue=0;

                                switch (selectedFactor){

                                    case 1:recordValue=Float.parseFloat(statistic.getTs()+"");
                                        System.out.println("Data 1 ok");
                                        break;
                                    case 2:recordValue=Float.parseFloat(statistic.getHs()+"");
                                        System.out.println("Data 2 ok");
                                        break;
                                    case 3:recordValue=Float.parseFloat(statistic.getGss()+"");
                                        System.out.println("Data 3 ok");
                                        break;
                                    case 4:recordValue=Float.parseFloat(statistic.getFs()+"");
                                        System.out.println("Data 4 ok");
                                        break;
                                    case 5:recordValue=Float.parseFloat(statistic.getSms()+"");
                                        System.out.println("Data 5 ok");
                                        break;
                                    default:recordValue=0;
                                        break;
                                }
                                System.out.println("Factor :" +selectedFactor);
                                System.out.println(recordValue+"");
                                barEntriesArrayList.add(new BarEntry(graphId, recordValue));
                                graphId++;
                            }
                            System.out.println("graph data "+barEntriesArrayList.size()+"");
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                        System.out.println(barEntriesArrayList.size()+"");
                        barDataSet = new BarDataSet(barEntriesArrayList, "Statistics History");
                        barData = new BarData(barDataSet);
                        barChart.setData(barData);
                        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                        barDataSet.setValueTextColor(getColor(R.color.black));
                        barDataSet.setValueTextSize(16f);
                        barChart.getDescription().setEnabled(false);
                        barChart.invalidate();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(DeviceStatistics.this, "Data fetching error.", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(this, "Please select filters first", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(view.getId()==R.id.deviceStatisticsDevice){
            selectedDevice=i+1;
        }else{
            selectedFactor=i+1;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(this, "Please select device first", Toast.LENGTH_SHORT).show();
    }

    public static class DeviceStatisticsDatePickerFragment extends DialogFragment
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
                    start=simpleDateFormat.parse(year+"/"+(month+1)+"/"+day);
                    from.setText(simpleDateFormat.format(start));
                    isStart=false;
                    System.out.println(from.toString()+" - Start");
                }else{
                    end=simpleDateFormat.parse(year+"/"+(month+1)+"/"+day);
                    to.setText(simpleDateFormat.format(end));
                    System.out.println(to.toString()+" - To");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}