package com.example.asus.lrucachepracticewithdatabase.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import com.example.asus.lrucachepracticewithdatabase.R;

/**
 * Created by Acer on 15/12/2016.
 */

public class ToDoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_layout);
        getSupportActionBar().setTitle("Activity Reminder");
        TabLayout tab = (TabLayout) findViewById(R.id.menu_tab);
        tab.addTab(tab.newTab().setText("To Do List"));
        tab.addTab(tab.newTab().setText("Finished"));
    }
}
