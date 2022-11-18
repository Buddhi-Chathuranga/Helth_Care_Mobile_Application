package com.example.agro.Models;

public class Plan {
    String body,cardio,diabetic,disoder,type;

    public Plan(String body, String cardio, String diabetic, String disoder, String type) {
        this.body = body;
        this.cardio = cardio;
        this.diabetic = diabetic;
        this.disoder = disoder;
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public String getCardio() {
        return cardio;
    }

    public String getDiabetic() {
        return diabetic;
    }

    public String getDisoder() {
        return disoder;
    }

    public String getType() {
        return type;
    }
}
