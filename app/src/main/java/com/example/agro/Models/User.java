package com.example.agro.Models;

public class User {
    public String name;
    public String email,disoder;

    public User() {
    }

    public User(String name, String email,String disoder) {
        this.name = name;
        this.email = email;
        this.disoder=disoder;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisoder() {
        return disoder;
    }

    public void setDisoder(String disoder) {
        this.disoder = disoder;
    }
}
