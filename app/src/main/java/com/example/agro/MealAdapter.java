package com.example.agro;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MyViewHolder> {

    private List<MealPlan> mealPlanList;
    Context context;

    public MealAdapter(List<MealPlan> mealPlanList,Context context) {
        this.mealPlanList = mealPlanList;
        this.context = context;
    }

    @NonNull
    @Override
    public MealAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.meal_list_item,parent,false);

        return new MealAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealAdapter.MyViewHolder holder, int position) {

        int no = position+1;
        holder.mealNO.setText("Meal Plan 0"+ no);
        MealPlan mealPlan = mealPlanList.get(position);
         holder.linearLayout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(context,mealplan_details.class);
                 Bundle bundle =  new Bundle();
                 String Breakfast = new String();
                 String Lunch =new String();
                 String Dinner=new String();
                 String EarlyMorning =new String();
                 String AMsnack =new String();
                 String PMsnack=new String();
                 String Eveningsnack=new String();
                 String Contain =new String();
                 JSONArray breakfast =  mealPlan.getBreakfast();
                 for (int i=0;i<breakfast.length()&& breakfast.length()>0;i++)
                 {
                     try {
                         Breakfast +=breakfast.getString(i)+"\n";
                     } catch (JSONException e) {
                         e.printStackTrace();
                     }
                 }
                 JSONArray lunch =  mealPlan.getLunch();
                 for (int i=0;i<lunch.length()&& lunch.length()>0;i++)
                 {
                     try {
                         Lunch +=lunch.getString(i)+"\n";
                     } catch (JSONException e) {
                         e.printStackTrace();
                     }
                 }

                 JSONArray dinner = mealPlan.getDinner();
                 for (int i=0;i<dinner.length()&& dinner.length()>0;i++)
                 {
                     try {
                         Dinner +=dinner.getString(i)+"\n";
                     } catch (JSONException e) {
                         e.printStackTrace();
                     }
                 }

                 JSONArray EMorning = mealPlan.getEarlymorning();
                 if(EMorning!=null) {

                     for (int i = 0; i < EMorning.length() && EMorning.length() > 0; i++) {
                         try {
                             EarlyMorning += EMorning.getString(i) + "\n";
                         } catch (JSONException e) {
                             e.printStackTrace();
                         }
                     }
                 }


                 JSONArray amsnak = mealPlan.getAMsnack();
                 if(amsnak!=null) {
                     for (int i = 0; i < amsnak.length() && amsnak.length() > 0; i++) {
                         try {
                             AMsnack += amsnak.getString(i) + "\n";
                         } catch (JSONException e) {
                             e.printStackTrace();
                         }
                     }
                 }

                 JSONArray pmsnak = mealPlan.getPMsnack();
                 if(pmsnak!=null) {
                     for (int i = 0; i < pmsnak.length() && pmsnak.length() > 0; i++) {
                         try {
                             PMsnack += pmsnak.getString(i) + "\n";
                         } catch (JSONException e) {
                             e.printStackTrace();
                         }
                     }
                 }

                 JSONArray evesnack =  mealPlan.getEveningsnack();
                 if(evesnack!=null) {
                     for (int i = 0; i < evesnack.length() && evesnack.length() > 0; i++) {
                         try {
                             Eveningsnack += evesnack.getString(i) + "\n";
                         } catch (JSONException e) {
                             e.printStackTrace();
                         }
                     }
                 }
                 JSONArray contain = mealPlan.getContains();
                 if(contain!=null) {
                     for (int i = 0; i < contain.length() && contain.length() > 0; i++) {
                         try {
                             Contain += contain.getString(i) + "\n";
                         } catch (JSONException e) {
                             e.printStackTrace();
                         }
                     }
                 }


                 bundle.putString("Breakfast",Breakfast);
                 bundle.putString("Lunch",Lunch);
                 bundle.putString("Dinner",Dinner);
                 bundle.putString("EarlyMorning",EarlyMorning);
                 bundle.putString("AMsnack",AMsnack);
                 bundle.putString("PMsnack",PMsnack);
                 bundle.putString("Eveningsnack",Eveningsnack);
                 bundle.putString("Contain",Contain);

                 intent.putExtras(bundle);
                 context.startActivity(intent);
             }
         });
    }

    @Override
    public int getItemCount() {
        return mealPlanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mealNO;
        LinearLayout linearLayout;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            mealNO = itemView.findViewById(R.id.mealplan_no);
            linearLayout=itemView.findViewById(R.id.mealItem);

        }
    }
}
