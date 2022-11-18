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
import android.widget.Toast;

public class mealplan_input_diabetes extends AppCompatActivity {

    String[] items =  {"Yes","No"};
//    String[] gender_list =  {"Male","Female"};
    String[] Goal =  {"Lose Weight","Maintain","Gain Weight"};
    String[] FoodType = {"Veg","Non-Veg"};
    String[] Level02 = {"None","Light Active","Moderate Active","Heavy Active","Extreme Active"};

    private Button btncontinue;
    AutoCompleteTextView Gendertxt,agetxt,Heighttxt,Weighttxt,urinationtxt,thirsttxt,losstxt,weaknesstxt,hungertxt,thrushtxt,Blurringtxt,
            Skintxt,Irritabilitytxt,Healingtxt,paresistxt,stiffnesstxt,Hairtxt,ActivityLeveltxt,Goaltxt,Foodtxt;
    ArrayAdapter<String> adapterItems,genderItems,Level02Item,GoalItems,FoodTypeItems;
    String Gender,urination,thirst,loss,weakness,hunger,thrush,Blurring,Skin,Irritability,Healing,paresis,stiffness,Hair,ActivityLeve,goal, food;

    int errorcout =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealplan_input_diabetes);

        Bundle bundle = getIntent().getExtras();
//        boolean click = bundle.getBoolean("click");
        boolean value = false;
//        agetxt = findViewById(R.id.age);
//        Heighttxt =findViewById(R.id.Height);
//        Weighttxt = findViewById(R.id.Weight);
        btncontinue = (Button) findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mealplan_input_diabetes.this,mealplan_list.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("click",value);
//                if (Gender==null) {
//                    errorcout++;
//                } else {
//                    bundle.putString("Gender",Gender);
//                }

//                if(agetxt.getText().toString()==null||agetxt.getText().toString().isEmpty())
//                {
//                    errorcout++;
//                }
//                else
//                {
//                    bundle.putString("Age",agetxt.getText().toString());
//                }

//                if(Heighttxt.getText().toString()==null||Heighttxt.getText().toString().isEmpty())
//                {
//                    errorcout++;
//                }
//                else
//                {
//                    bundle.putString("Height",Heighttxt.getText().toString());
//                }

//                if(Weighttxt.getText().toString()==null||Weighttxt.getText().toString().isEmpty())
//                {
//                    errorcout++;
//                }
//                else
//                {
//                    bundle.putString("Weight",Weighttxt.getText().toString());
//                }

                if (urination==null) {
                    errorcout++;
                } else {
                    bundle.putString("urination",urination);
                }

                if (thirst==null) {
                    errorcout++;
                } else {
                    bundle.putString("thirst",thirst);
                }

                if (loss==null) {
                    errorcout++;
                } else {
                    bundle.putString("loss",loss);
                }
                if (weakness==null) {
                    errorcout++;
                } else {
                    bundle.putString("weakness",weakness);
                }

                if (hunger==null) {
                    errorcout++;
                } else {
                    bundle.putString("hunger",hunger);
                }
                if (thrush==null) {
                    errorcout++;
                } else {
                    bundle.putString("thrush",thrush);
                }

                if (Blurring==null) {
                    errorcout++;
                } else {
                    bundle.putString("Blurring",Blurring);
                }

                if (Skin==null) {
                    errorcout++;
                } else {
                    bundle.putString("Skin",Skin);
                }

                if (Irritability==null) {
                    errorcout++;
                } else {
                    bundle.putString("Irritability",Irritability);
                }

                if (Healing==null) {
                    errorcout++;
                } else {
                    bundle.putString("Healing",Healing);
                }

                if (paresis==null) {
                    errorcout++;
                } else {
                    bundle.putString("paresis",paresis);
                }

                if (stiffness==null) {
                    errorcout++;
                } else {
                    bundle.putString("stiffness",stiffness);
                }

                if (Hair==null) {
                    errorcout++;
                } else {
                    bundle.putString("Hair",Hair);
                }

                if (ActivityLeve==null) {
                    errorcout++;
                } else {
                    bundle.putString("ActivityLeve",ActivityLeve);
                }
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

        urinationtxt = findViewById(R.id.urination);
        thirsttxt = findViewById(R.id.thirst);
        losstxt = findViewById(R.id.loss);
        weaknesstxt = findViewById(R.id.weakness);
        hungertxt = findViewById(R.id.hunger);
        thrushtxt = findViewById(R.id.thrush);
        Blurringtxt = findViewById(R.id.Blurring);
        Skintxt = findViewById(R.id.Skin);
        Irritabilitytxt = findViewById(R.id.Irritability);
        Healingtxt =findViewById(R.id.Healing);
        paresistxt = findViewById(R.id.paresis);
        stiffnesstxt = findViewById(R.id.stiffness);
        Hairtxt = findViewById(R.id.Hair);
        ActivityLeveltxt = findViewById(R.id.ActivityLevel);
        Goaltxt = findViewById(R.id.Goal);
        Foodtxt = findViewById(R.id.Food);


//        genderItems = new ArrayAdapter<String>(this,R.layout.list_item,gender_list);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
        Level02Item = new ArrayAdapter<String>(this,R.layout.list_item,Level02);
        GoalItems = new ArrayAdapter<String>(this,R.layout.list_item,Goal);
        FoodTypeItems =  new ArrayAdapter<String>(this,R.layout.list_item,FoodType);

//        Gendertxt.setAdapter(genderItems);
        urinationtxt.setAdapter(adapterItems);
        thirsttxt.setAdapter(adapterItems);
        losstxt.setAdapter(adapterItems);
        weaknesstxt.setAdapter(adapterItems);
        hungertxt.setAdapter(adapterItems);
        Blurringtxt.setAdapter(adapterItems);
        Skintxt.setAdapter(adapterItems);
        Irritabilitytxt.setAdapter(adapterItems);
        thrushtxt.setAdapter(adapterItems);
        Healingtxt.setAdapter(adapterItems);
        paresistxt.setAdapter(adapterItems);
        stiffnesstxt.setAdapter(adapterItems);
        Hairtxt.setAdapter(adapterItems);
        ActivityLeveltxt.setAdapter(Level02Item);
        Goaltxt.setAdapter(GoalItems);
        Foodtxt.setAdapter(FoodTypeItems);




//        Gendertxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                int Posision = position;
////                Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
//               String Seleteditem = parent.getItemAtPosition(position).toString();
////                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
//                Gender = Seleteditem;
//            }
//
//        });

        urinationtxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int Posision = position;
//                Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
                String Seleteditem = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
                urination = Seleteditem;
            }

        });

        thirsttxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int Posision = position;
//                Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
                String Seleteditem = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
                thirst = Seleteditem;
            }

        });


        losstxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int Posision = position;
//                Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
                String Seleteditem = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
                loss = Seleteditem;
            }

        });
        weaknesstxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int Posision = position;
//                Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
                String Seleteditem = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
                weakness = Seleteditem;
            }

        });
        hungertxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int Posision = position;
//                Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
                String Seleteditem = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
                hunger = Seleteditem;
            }

        });
        Blurringtxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int Posision = position;
//                Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
                String Seleteditem = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
                Blurring = Seleteditem;
            }

        });
        Skintxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int Posision = position;
//                Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
                String Seleteditem = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
                Skin = Seleteditem;
            }

        });
        Irritabilitytxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//              int Posision = position;
//              Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
                String Seleteditem = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
                Irritability = Seleteditem;
            }

        });
        Healingtxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//              int Posision = position;
//              Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
                String Seleteditem = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
                Healing = Seleteditem;
            }

        });
        paresistxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//              int Posision = position;
//              Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
                String Seleteditem = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
                paresis = Seleteditem;
            }

        });

        stiffnesstxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//              int Posision = position;
//                Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
                String Seleteditem = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
                 stiffness = Seleteditem;
            }

        });
        Hairtxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//               int Posision = position;
//               Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
                String Seleteditem = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
                Hair = Seleteditem;
            }

        });

        thrushtxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int Posision = position;
//                Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
                String Seleteditem = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
                thrush = Seleteditem;
            }

        });

        ActivityLeveltxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//              int Posision = position;
//              Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
                String Seleteditem = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
                ActivityLeve = Seleteditem;
            }

        });

        Goaltxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int Posision = position;
//                Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
                String Seleteditem = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
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




    }


    private void getmessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mealplan_input_diabetes.this);

        // Set the message show for the Alert time
        builder.setMessage("You need to fill all details !");

        // Set Alert Title
        builder.setTitle("Sorry !");

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes button then app will close
            errorcout =0;
            dialog.cancel();
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
        Intent i = new Intent(mealplan_input_diabetes.this,Dashboard.class);
        startActivity(i);

    }

    public void goback(View view) {
        finish();
    }
}