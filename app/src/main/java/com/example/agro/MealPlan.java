package com.example.agro;

import org.json.JSONArray;

import java.sql.Array;

public class MealPlan {
    private JSONArray AMsnack;
    private JSONArray Breakfast;
    private JSONArray Dinner;
    private JSONArray Earlymorning;
    private JSONArray Eveningsnack;
    private JSONArray Lunch;
    private JSONArray PMsnack;
    private JSONArray Contains;

    public MealPlan(JSONArray AMsnack, JSONArray breakfast, JSONArray dinner, JSONArray earlymorning, JSONArray eveningsnack, JSONArray lunch, JSONArray PMsnack, JSONArray contains) {
        this.AMsnack = AMsnack;
        Breakfast = breakfast;
        Dinner = dinner;
        Earlymorning = earlymorning;
        Eveningsnack = eveningsnack;
        Lunch = lunch;
        this.PMsnack = PMsnack;
        Contains = contains;
    }

    public JSONArray getAMsnack() {
        return AMsnack;
    }

    public JSONArray getBreakfast() {
        return Breakfast;
    }

    public JSONArray getDinner() {
        return Dinner;
    }

    public JSONArray getEarlymorning() {
        return Earlymorning;
    }

    public JSONArray getEveningsnack() {
        return Eveningsnack;
    }

    public JSONArray getLunch() {
        return Lunch;
    }

    public JSONArray getPMsnack() {
        return PMsnack;
    }

    public JSONArray getContains() {
        return Contains;
    }
}
