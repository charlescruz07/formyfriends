package com.example.asus.lrucachepracticewithdatabase.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by asus on 13/12/2016.
 */

public class ToDoModel implements Serializable{
    String title;
    String description;
    int hours,minutes;
    int month,day,year;


    public ToDoModel(String title, String description, int hours, int minutes, int month, int day, int year) {
        this.title = title;
        this.description = description;
        this.hours = hours;
        this.minutes = minutes;
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public ToDoModel(ToDoModel tdm){
        this.title = tdm.getTitle();
        this.description = tdm.getDescription();
        this.hours = tdm.getHours();
        this.minutes = tdm.getMinutes();
        this.month = tdm.getMonth();
        this.day = tdm.getDay();
        this.year = tdm.getYear();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
