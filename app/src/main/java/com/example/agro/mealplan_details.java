package com.example.agro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class mealplan_details extends AppCompatActivity {
      private  LinearLayout Earlymorning;
      private  LinearLayout breakfask;
      private  LinearLayout amSnack;
      private  LinearLayout lunch;
      private  LinearLayout pmSnack;
      private  LinearLayout dinner;
      private  LinearLayout EveningSnack;
      private  LinearLayout contain;

    TextView txtEarlymorning,txtbreakfask,txtamSnack,txtlunch,txtpmsnack,txtdinner,txteveninigSnack,txtcontain;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealplan_details2);

        Earlymorning = findViewById(R.id.Earlymorning);
        breakfask = findViewById(R.id.breakfask);
        amSnack = findViewById(R.id.amSnack);
        lunch = findViewById(R.id.lunch);
        pmSnack =findViewById(R.id.pmSnack);
        dinner=findViewById(R.id.dinner);
        EveningSnack = findViewById(R.id.EveningSnack);
        contain = findViewById(R.id.contain);

      txtEarlymorning = findViewById(R.id.txtEarlymorning);
      txtbreakfask = findViewById(R.id.txtbreakfask);
      txtamSnack = findViewById(R.id.txtamSnack);
      txtlunch = findViewById(R.id.txtlunch);
      txtpmsnack = findViewById(R.id.txtpmsnack);
      txtdinner =findViewById(R.id.txtdinner);
      txteveninigSnack = findViewById(R.id.txteveninigSnack);
      txtcontain = findViewById(R.id.txtcontain);


        Bundle bundle = getIntent().getExtras();
         if(!bundle.getString("Breakfast").isEmpty())
         {
            txtbreakfask.setText(bundle.getString("Breakfast"));
         }
         else
         {
             breakfask.setVisibility(View.GONE);
         }

        if(!bundle.getString("Lunch").isEmpty())
        {
            txtlunch.setText(bundle.getString("Lunch"));
        }
        else
        {
            lunch.setVisibility(View.GONE);
        }

        if(!bundle.getString("Dinner").isEmpty())
        {
            txtdinner.setText(bundle.getString("Dinner"));
        }
        else
        {
            dinner.setVisibility(View.GONE);
        }

        if(!bundle.getString("EarlyMorning").isEmpty())
        {
            txtEarlymorning.setText(bundle.getString("EarlyMorning"));
        }
        else
        {
            Earlymorning.setVisibility(View.GONE);
        }

        if(!bundle.getString("AMsnack").isEmpty())
        {
            txtamSnack.setText(bundle.getString("AMsnack"));
        }
        else
        {
            amSnack.setVisibility(View.GONE);
        }

        if(!bundle.getString("PMsnack").isEmpty())
        {
            txtpmsnack.setText(bundle.getString("PMsnack"));
        }
        else
        {
            pmSnack.setVisibility(View.GONE);
        }

        if(!bundle.getString("Eveningsnack").isEmpty())
        {
            txteveninigSnack.setText(bundle.getString("Eveningsnack"));
        }
        else
        {
            EveningSnack.setVisibility(View.GONE);
        }

        if(!bundle.getString("Contain").isEmpty())
        {
            txtcontain.setText(bundle.getString("Contain"));
        }
        else
        {
            contain.setVisibility(View.GONE);
        }



//        Toast.makeText(mealplan_details.this, intetBF, Toast.LENGTH_SHORT).show();


    }

    public  void  goHome(View view)
    {
        Intent i = new Intent(mealplan_details.this,Dashboard.class);
        startActivity(i);

    }

    public void goback(View view) {
        finish();
    }
}