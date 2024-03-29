package com.example.asus.lrucachepracticewithdatabase.Activities;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.asus.lrucachepracticewithdatabase.Fragments.ToDoListItemsFragment;
import com.example.asus.lrucachepracticewithdatabase.Model.ToDoModel;
import com.example.asus.lrucachepracticewithdatabase.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Acer on 15/12/2016.
 */

public class ToDoActivity extends AppCompatActivity {

    FloatingActionButton newToDO;
    ArrayList<ToDoModel> arr;
    ArrayList<ToDoModel> doneActivities;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_layout);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorStatusBar));
        }
        TabLayout tab = (TabLayout) findViewById(R.id.menu_tab);
        tab.addTab(tab.newTab().setText("To Do List"));
        tab.addTab(tab.newTab().setText("Finished"));

        arr = new ArrayList<>();

        newToDO = (FloatingActionButton) findViewById(R.id.addToDo);
        newToDO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(ToDoActivity.this,CreateToDoActivity.class),1);
            }
        });

        retriveData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK)
        {
            ArrayList<ToDoModel> arrayList = new ArrayList<>();
            String tempArrayList = data.getStringExtra("pass");


            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<ToDoModel>>(){}.getType();
            ArrayList<ToDoModel> arrays = gson.fromJson(tempArrayList,type);
            ToDoModel tdm = new ToDoModel(
                    arrays.get(0).getTitle(),
                    arrays.get(0).getDescription(),
                    arrays.get(0).getHours(),
                    arrays.get(0).getMinutes(),
                    arrays.get(0).getMonth(),
                    arrays.get(0).getDay(),
                    arrays.get(0).getYear());

            arr.add(tdm);

            Gson gson2 = new Gson();
            String json = gson2.toJson(arr);


            FragmentManager fragmentManager = getFragmentManager();
            Bundle bundle = new Bundle();
            bundle.putString("arrayList", json);
            ToDoListItemsFragment fragobj = new ToDoListItemsFragment();
            fragobj.setArguments(bundle);

            fragmentManager.beginTransaction().replace(R.id.frameContainer,fragobj).commit();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("resume",0);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arr);
        editor.putString("getThis",json);
        editor.commit();
    }

    public void retriveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("resume",0);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("getThis",null);
        Type type = new TypeToken<ArrayList<ToDoModel>>(){}.getType();
        ArrayList<ToDoModel> arrayList = gson.fromJson(json, type);
        if (arrayList!= null) {
            for (int i = 0; i < arrayList.size(); i++) {
                ToDoModel tdm = new ToDoModel(
                        arrayList.get(i).getTitle(),
                        arrayList.get(i).getDescription(),
                        arrayList.get(i).getHours(),
                        arrayList.get(i).getMinutes(),
                        arrayList.get(i).getMonth(),
                        arrayList.get(i).getDay(),
                        arrayList.get(i).getYear());
                arr.add(tdm);
            }
            FragmentManager fm = getFragmentManager();
            Bundle bundle = new Bundle();
            bundle.putString("arrayList", json);
            ToDoListItemsFragment fragobj = new ToDoListItemsFragment();
            fragobj.setArguments(bundle);

            fm.beginTransaction().replace(R.id.frameContainer,fragobj).commit();
        }
    }

    public void removeData(int position){
        arr.remove(position);
    }
}
