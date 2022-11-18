package com.example.agro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class mealplan_input_heart extends AppCompatActivity {
    String[] items =  {"Yes","No"};
//    String[] gender_list =  {"Male","Female"};
    String[] Goal =  {"Lose Weight","Maintain","Gain Weight"};
    String[] FoodType = {"Veg","Non-Veg"};
    String[] Level01 = {"Normal","Above normal","Well above normal"};
    String[] Level02 = {"None","Light Active","Moderate Active","Heavy Active","Extreme Active"};


    private Button btncontinue;
    AutoCompleteTextView Gendertxt,Agetxt,Systolictxt,Diastolictxt,Cholesteroltxt,Gluecosetxt,Alcoholictxt,Activetxt,ActiveLeveltxt,Heighttxt,Weighttxt,Goaltxt,Foodtxt;
    ArrayAdapter<String> adapterItems,genderItems,Level01Items,Level02Item,GoalItems,FoodTypeItems;
    String Gender,Age,Systolic,Diastolic,Cholesterol,Gluecose,Smoker,Alcoholic,Active,ActiveLevel,Height,Weight,goal,food;
    int errorcout=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealplan_input);

        btncontinue = (Button) findViewById(R.id.btncontinue);
        Bundle bundle = getIntent().getExtras();
//        boolean click = bundle.getBoolean("click");
        boolean value = true;
//        Agetxt = findViewById(R.id.age);
//        Heighttxt = findViewById(R.id.Height);
//        Weighttxt = findViewById(R.id.Weight);
        Systolictxt = findViewById(R.id.Systolic);
        Diastolictxt = findViewById(R.id.Diastolic);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mealplan_input_heart.this,mealplan_list.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("click",value);
//                if (Gender==null) {
//                    errorcout++;
//                } else {
//                    bundle.putString("gender",Gender);
//                }

//                if(Agetxt.getText().toString()==null||Agetxt.getText().toString().isEmpty())
//                {
//                    errorcout++;
//                }
//                else
//                {
//                    bundle.putString("age",Agetxt.getText().toString());
//                }
                if(Systolictxt.getText().toString()==null||Systolictxt.getText().toString().isEmpty())
                {
                    errorcout++;
                }
                else
                {
                    bundle.putString("systolic",Systolictxt.getText().toString());
                }


                if(Diastolictxt.getText().toString()==null||Diastolictxt.getText().toString().isEmpty())
                {
                    errorcout++;
                }
                else
                {
                    bundle.putString("diastolic",Diastolictxt.getText().toString());
                }

                if (Cholesterol==null) {
                    errorcout++;
                } else {
                    bundle.putString("cholesterol",Cholesterol);
                }

                if (Gluecose==null) {
                    errorcout++;
                } else {
                    bundle.putString("gluecose",Gluecose);
                }

//                if (Smoker==null) {
//                    errorcout++;
//                } else {
//                    bundle.putString("smoker",Smoker);
//                }

                if (Alcoholic==null) {
                    errorcout++;
                } else {
                    bundle.putString("alcoholic",Alcoholic);
                }

                if (Active==null) {
                    errorcout++;
                } else {
                    bundle.putString("active",Active);
                }
                if (ActiveLevel==null) {
                    errorcout++;
                } else {
                    bundle.putString("activeLevel",ActiveLevel);
                }

//                if(Heighttxt.getText().toString()==null||Heighttxt.getText().toString().isEmpty())
//                {
//                    errorcout++;
//                }
//                else
//                {
//                    bundle.putString("height",Heighttxt.getText().toString());
//                }
//
//                if(Weighttxt.getText().toString()==null||Weighttxt.getText().toString().isEmpty())
//                {
//                    errorcout++;
//                }
//                else
//                {
//                    bundle.putString("weight",Weighttxt.getText().toString());
//                }


                if (goal==null) {
                    errorcout++;
                } else {
                    bundle.putString("goal",goal);
                }

                if (food==null) {
                    errorcout++;
                } else {
                    bundle.putString("food",food);
                }


                if(errorcout>0)
                {
                    Log.d("error count", String.valueOf(errorcout));
                    getmessage();
                }
                else {
                    i.putExtras(bundle);
                    startActivity(i);
                }
            }
        });


//        Gendertxt = findViewById(R.id.Gender);
        Cholesteroltxt= findViewById(R.id.Cholesterol);
        Gluecosetxt = findViewById(R.id.Gluecose);
        Alcoholictxt = findViewById(R.id.Alcoholic);
        Activetxt =findViewById(R.id.Active);
        ActiveLeveltxt = findViewById(R.id.ActiveLevel);
        Goaltxt =findViewById(R.id.Goal);
        Foodtxt = findViewById(R.id.Food);

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
//        genderItems = new ArrayAdapter<String>(this,R.layout.list_item,gender_list);
        Level01Items = new ArrayAdapter<String>(this,R.layout.list_item,Level01);
        Level02Item = new ArrayAdapter<String>(this,R.layout.list_item,Level02);
        GoalItems = new ArrayAdapter<String>(this,R.layout.list_item,Goal);
        FoodTypeItems =  new ArrayAdapter<String>(this,R.layout.list_item,FoodType);

//        Gendertxt.setAdapter(genderItems);

        Alcoholictxt.setAdapter(adapterItems);
        Activetxt.setAdapter(adapterItems);
        Cholesteroltxt.setAdapter(Level01Items);
        Gluecosetxt.setAdapter((Level01Items));
        ActiveLeveltxt.setAdapter(Level02Item);
        Goaltxt.setAdapter(GoalItems);
        Foodtxt.setAdapter(FoodTypeItems);


//        Gendertxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                int Posision = position;
////                Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
//                String Seleteditem = parent.getItemAtPosition(position).toString();
//                Gender= Seleteditem;
////                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
//            }
//
//        });

//        Smokertxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                int Posision = position;
////                Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
//                String Seleteditem = parent.getItemAtPosition(position).toString();
////                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
//                Smoker = Seleteditem;
//            }
//
//        });

        Alcoholictxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int Posision = position;
//                Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
                String Seleteditem = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
                Alcoholic = Seleteditem;
            }

        });

        Activetxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int Posision = position;
//                Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
                String Seleteditem = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
                Active = Seleteditem;
            }

        });



        Cholesteroltxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int Posision = position;
//                Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
                String Seleteditem = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
                Cholesterol = Seleteditem;
            }


        });


        Gluecosetxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int Posision = position;
//                Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
                String Seleteditem = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
                Gluecose = Seleteditem;
            }

        });


        ActiveLeveltxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int Posision = position;
//                Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
                String Seleteditem = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
                ActiveLevel = Seleteditem;
            }

        });


        Goaltxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Seleteditem = parent.getItemAtPosition(position).toString();
                goal = Seleteditem;
            }

        });

        Foodtxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int Posision = position;
//                Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
                String Seleteditem = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
                 food = Seleteditem;
            }

        });


        ImageView Helpbtn = findViewById(R.id.helpbtn);
        Helpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHelpMessage();
            }
        });


    }

    private void getmessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mealplan_input_heart.this);

        // Set the message show for the Alert time
        builder.setMessage("You need to fill all details !");

        // Set Alert Title
        builder.setTitle("Sorry !");

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click OK button then app will close
            errorcout =0;
            dialog.cancel();
        });


        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }

    private void getHelpMessage()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(mealplan_input_heart.this);

        // Set the message show for the Alert time

//        builder.setMessage("Systolic blood pressure \nmeasures the pressure in your arteries when your heart beats \n\nDiastolic blood pressure\nmeasures the pressure in your arteries when your heart rests between beats.");
        builder.setMessage("Please meet the doctor to get the following details\n" +
                "\n" +
                "- Systolic blood pressure\n" +
                "- Diastolic blood pressure\n" +
                "- Glucose level\n" +
                "- Cholesterol level\n" +
                "\n" +
                "Other details can be filled out as you wish.");
        // Set Alert Title
        builder.setTitle("Information");

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click OK button then app will close
            errorcout =0;
            dialog.cancel();
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }


    public  void  goHome(View view)
    {
        Intent i = new Intent(mealplan_input_heart.this,Dashboard.class);
        startActivity(i);

    }

    public void goback(View view) {
        finish();
    }
}