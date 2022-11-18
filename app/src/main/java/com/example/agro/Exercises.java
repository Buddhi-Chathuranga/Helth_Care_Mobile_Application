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
import com.example.agro.Adapters.ExerciseAdapter;
import com.example.agro.Models.Device;
import com.example.agro.Models.Exercise;
import com.example.agro.Models.Plan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Exercises extends AppCompatActivity {

    ImageView backBtn;
    RecyclerView recyclerView;

    ArrayList<Exercise> exercisesList;
    ArrayList<Plan> plansList;

    private DatabaseReference mDatabase;

    String bodyperformance_prediction,cardio_prediction,diabetic_prediction,disoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        disoder=CustomUtils.userData.disoder;

        plansList=new ArrayList<>();
        readPlans();

        exercisesList=new ArrayList<>();
        readExercises();

        backBtn=findViewById(R.id.exercises_back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        recyclerView=findViewById(R.id.exercises_recycler_view);

        mDatabase= FirebaseDatabase.getInstance().getReference();

        System.out.println(exercisesList.size());
        System.out.println(plansList.size());

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data:snapshot.getChildren()){
                    switch (data.getKey()){
                        case "bodyperformance_prediction":
                            bodyperformance_prediction=data.getValue().toString().toLowerCase();
                            break;
                        case "cardio_prediction":
                            cardio_prediction=data.getValue().toString().toLowerCase();
                            break;
                        case "diabetic_prediction":
                            diabetic_prediction=data.getValue().toString().toLowerCase();
                            break;
                    }
                }

                //prediction
                for(Plan plan :plansList){
                    if(plan.getBody().equals(bodyperformance_prediction)
                            && plan.getCardio().equals(cardio_prediction)
                            && plan.getDisoder().equals(disoder)
                            && plan.getDiabetic().equals(diabetic_prediction)){

                        ArrayList<Exercise> selectedExercises=new ArrayList<>();

                        for(Exercise exer : exercisesList){
                            if(plan.getType().equals(exer.getType())){
                                selectedExercises.add(exer);
                            }
                        }

                        System.out.println("Exercises");
                        System.out.println(selectedExercises.size());


                        ExerciseAdapter adapter = new ExerciseAdapter(Exercises.this,selectedExercises);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Exercises.this));
                        recyclerView.setAdapter(adapter);

                        break;
                    }
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Exercises.this, "Data fetching error.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void readExercises() {

        try {
            InputStream is = getAssets().open("exercises.csv");
            InputStreamReader isr = new InputStreamReader(is);
            CSVReader reader = new CSVReader(isr);
            String [] row;
            while ((row = reader.readNext()) != null) {
                exercisesList.add(new Exercise(row[0].toLowerCase().trim(), row[1], row[2], row[3], row[4]));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void readPlans() {
        try {
            InputStream is = getAssets().open("plans.csv");
            InputStreamReader isr = new InputStreamReader(is);
            CSVReader reader = new CSVReader(isr);
            String [] row;
            while ((row = reader.readNext()) != null) {
                plansList.add(new Plan(row[1].toLowerCase().trim(), row[2].toLowerCase().trim(), row[3].toLowerCase().trim(), row[4].toLowerCase().trim(), row[5].toLowerCase().trim()));
            }
        }catch(Exception e){

            e.printStackTrace();
        }
    }
}