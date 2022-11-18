package com.example.agro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import androidx.appcompat.app.AlertDialog;

public class mealplan_list extends AppCompatActivity {
    private LinearLayout meal01;
    ProgressDialog loader;
    String URL;
    List<MealPlan> mealPlanList = new ArrayList<>();
    private RecyclerView recyclerView;
    private boolean click;
    private boolean noneed_meal =false;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealplan_list2);

        loader = new ProgressDialog(mealplan_list.this);
        loader.setMessage("loading Meal Plans");
        loader.show();
        recyclerView = findViewById(R.id.Rmealplans);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bundle =  getIntent().getExtras();
        click = bundle.getBoolean("click");
        if(click)
        {
            URL = "https://rp-back-end.herokuapp.com/mealplan/heart/U10";
//            URL = "http://127.0.0.1:5000/mealplan/heart/U10";
        }
        else {
            URL = "https://rp-back-end.herokuapp.com/mealplan/diabetes/U10";
        }

        GetReuestData();

//        meal01 = (LinearLayout) findViewById(R.id.meal01);
//        meal01.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(mealplan_list.this,mealplan_details.class);
//                startActivity(i);
//            }
//        });
    }


    private JSONObject getvalues () {

           JSONObject values  =  new JSONObject();

        Set<String> keys = bundle.keySet();
        for (String key : keys) {
            try {
                // json.put(key, bundle.get(key)); see edit below
                values.put(key, JSONObject.wrap(bundle.get(key)));
            } catch(JSONException e) {
                //Handle exception here
            }
        }

        return values;
    }

    private void GetReuestData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, getvalues(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray = response.getJSONArray("result");
                    if(jsonArray.length()==0 || jsonArray==null)
                    {
                        noneed_meal=true;
                    }
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject object = jsonArray.getJSONObject(i);
                        JSONArray AMsnack,PMsnack,Earlymorning,Eveningsnack,Contains;


//                        if(object.getJSONArray("AMsnack")==null)
                        if(!object.has("AMsnack"))
                        {
                            AMsnack =  null;
                        }
                        else
                        {
                            AMsnack =  object.getJSONArray("AMsnack");
                        }
//                        Log.d("Earlymorning", String.valueOf(object.has("Earlymorning")));
                        if(!object.has("Earlymorning"))
                        {
                            Earlymorning =  null;
                        }
                        else
                        {
                            Earlymorning =   object.getJSONArray("Earlymorning");
                        }

                        if(!object.has("PMsnack"))
                        {
                            PMsnack=  null;
                        }
                        else
                        {
                            PMsnack =   object.getJSONArray("PMsnack");
                        }
                        if(!object.has("Contains"))
                        {
                            Contains =  null;
                        }
                        else
                        {
                            Contains =   object.getJSONArray("Contains");
                        }

                        if(!object.has("Eveningsnack"))
                        {
                            Eveningsnack  =  null;
                        }
                        else
                        {
                            Eveningsnack =   object.getJSONArray("Eveningsnack");
                        }

                        JSONArray  Breakfast =   object.getJSONArray("Breakfast");
                        JSONArray  Dinner =   object.getJSONArray("Dinner");
                        JSONArray  Lunch =  object.getJSONArray("Lunch");

                        MealPlan mealPlan = new MealPlan(AMsnack,Breakfast,Dinner,Earlymorning,Eveningsnack,Lunch,PMsnack,Contains);
                        mealPlanList.add(mealPlan);


                    }

                    loader.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(noneed_meal)
                {
                    messagebox();
                }
                else {
                    MealAdapter mealAdapter = new MealAdapter(mealPlanList, mealplan_list.this);
                    recyclerView.setAdapter(mealAdapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.getMessage());
                Toast.makeText(mealplan_list.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
//    };
//        });
        requestQueue.add(jsonObjectRequest);
    }

    private  void messagebox()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(mealplan_list.this);

        // Set the message show for the Alert time
        builder.setMessage("No Need Meal Plans to you! Continue your daliy  habits.");

        // Set Alert Title
        builder.setTitle("Message !");

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes button then app will close
            finish();
        });

        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
//        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
//            // If user click no then dialog box is canceled.
//            dialog.cancel();
//        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }

    public  void  goHome(View view)
    {
          Intent i = new Intent(mealplan_list.this,Dashboard.class);
          startActivity(i);

    }

    public void goback(View view) {
            finish();
    }
}