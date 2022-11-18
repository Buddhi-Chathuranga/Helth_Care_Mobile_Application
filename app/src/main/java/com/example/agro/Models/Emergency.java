package com.example.agro.Models;

public class Emergency {
    public String id,message;

    public Emergency() {
    }

    public Emergency(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public Emergency(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
