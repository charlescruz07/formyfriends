package com.example.asus.lrucachepracticewithdatabase.Model;

import java.util.Date;

/**
 * Created by asus on 13/12/2016.
 */

public class ToDoModel {
    String title;
    String description;


    public ToDoModel(String title, String description) {
        this.title = title;
        this.description = description;
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
}
