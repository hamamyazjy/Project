package com.example.hamam.project.Model;

public class Note {

    String id;
    String date;
    String time;
    String description;
    String name;

    public Note() {
    }

    public Note(String id, String date, String time, String description, String name) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.description = description;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
