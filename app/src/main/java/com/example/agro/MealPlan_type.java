package com.example.agro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MealPlan_type extends AppCompatActivity {
    private LinearLayout heat,diabetes;
    boolean isheart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mealplan_type);

        heat = (LinearLayout) findViewById(R.id.heart);
        diabetes=(LinearLayout) findViewById(R.id.diabetes);
        heat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isheart=true;
                Intent i = new Intent(MealPlan_type.this, mealplan_input_heart.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("click",isheart);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        diabetes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isheart =false;
                Intent i = new Intent(MealPlan_type.this, mealplan_input_diabetes.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("click",isheart);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }

    public  void  goHome(View view)
    {
        Intent i = new Intent(MealPlan_type.this,Dashboard.class);
        startActivity(i);

    }

    public void goback(View view) {
        finish();
    }
}