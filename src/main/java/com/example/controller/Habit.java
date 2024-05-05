package com.example.controller;

import java.util.Date;

public class Habit {
    private int id;
    private String name;
    private String description;
    private Date startDate;
    private int streak;

    public Habit() {
    }

    public Habit(String name, String description) {
        this.name = name;
        this.description = description;
        this.startDate = new Date();
        this.streak = 0;
    }

    public Habit(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }
}

