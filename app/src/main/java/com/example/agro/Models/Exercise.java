package com.example.agro.Models;

public class Exercise {
    String type,name,duration,description,image;

    public Exercise(String type, String name, String duration, String description, String image) {
        this.type = type;
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
}
