package com.example.agro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agro.Adapters.ExerciseAdapter;
import com.example.agro.Models.Exercise;
import com.example.agro.Models.Plan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Predictions extends AppCompatActivity {

    private TextView pre_stress,pre_bp,pre_diabetic,pre_cardio,pre_heart;
    private ImageView pre_back;
    private DatabaseReference mDatabase;
    private Button stress_rel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predictions);

        pre_stress=findViewById(R.id.pre_stress);
        pre_bp=findViewById(R.id.pre_bp);
        pre_diabetic=findViewById(R.id.pre_diabetic);
        pre_cardio=findViewById(R.id.pre_cardio);
        pre_heart=findViewById(R.id.pre_heart);
        pre_back=findViewById(R.id.pre_back);
        stress_rel=findViewById(R.id.stress_rel);
        stress_rel.setVisibility(View.INVISIBLE);

        pre_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mDatabase= FirebaseDatabase.getInstance().getReference();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data:snapshot.getChildren()){
                    switch (data.getKey()){
                        case "bodyperformance_prediction":
                            pre_bp.setText(data.getValue().toString().toUpperCase());
                            break;
                        case "cardio_prediction":
                            pre_cardio.setText(data.getValue().toString().toUpperCase());
                            break;
                        case "diabetic_prediction":
                            pre_diabetic.setText(data.getValue().toString().toUpperCase());
                            break;
                        case "stress_prediction":
                            pre_stress.setText(data.getValue().toString().toUpperCase());

                            if(data.getValue().toString().toUpperCase().equals("HIGH")){
                                stress_rel.setVisibility(View.VISIBLE);
                                stress_rel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(new Intent(Predictions.this,PDFView.class));
                                    }
                                });
                            }

                            break;
                        case "calories_prediction":
                            pre_heart.setText(data.getValue().toString().toUpperCase());
                            break;
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Predictions.this, "Data fetching error.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}