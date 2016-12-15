package com.example.asus.lrucachepracticewithdatabase.Activities;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import java.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.asus.lrucachepracticewithdatabase.Adapters.RecyclerAdapter;
import com.example.asus.lrucachepracticewithdatabase.Model.ToDoModel;
import com.example.asus.lrucachepracticewithdatabase.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Acer on 15/12/2016.
 */

public class CreateToDoActivity extends AppCompatActivity {
    EditText titleInput,descInput;
    TimePicker timePicker;
    Button selectDate,timeBtn;
    TextView mm,dd,yy,cancel,finish;
    ToDoModel tdm;
    LruCache <String,ToDoModel>mMemoryCache;
    ArrayList<ToDoModel> arrayList;
    int m,d,y,hrs,min;

    String [] dates = {
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorStatusBar));
        }
        setContentView(R.layout.add_to_do_activity);

        titleInput = (EditText) findViewById(R.id.titleEdit);
        descInput = (EditText) findViewById(R.id.descriptionEdit);
        selectDate = (Button) findViewById(R.id.dateBtn);
        timePicker = (TimePicker)findViewById(R.id.timeEdit) ;
        timeBtn = (Button) findViewById(R.id.timeBtn);
        cancel = (TextView) findViewById(R.id.cancel);
        finish = (TextView) findViewById(R.id.finish);

        mm = (TextView) findViewById(R.id.month);
        dd = (TextView) findViewById(R.id.day);
        yy = (TextView) findViewById(R.id.year);

        arrayList = new ArrayList<>();

        //LRU ZONE
        final int memClass = ((ActivityManager) this.getSystemService(
                Context.ACTIVITY_SERVICE)).getMemoryClass();

        final int cacheSize = 1024 * 1024 * memClass / 4;
        mMemoryCache = new LruCache<String,ToDoModel>(cacheSize){
        };
        //LRU ZONE


        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                hrs = calendar.get(Calendar.HOUR_OF_DAY);
                min = calendar.get(Calendar.MINUTE);
                    TimePickerDialog tpd = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hrs = hourOfDay;
                        min = minute;
                        timePicker.setCurrentHour(hrs);
                        timePicker.setCurrentMinute(min);
                    }
                },hrs,min,false);
                tpd.show();
                }
        });


        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                m = calendar.get(Calendar.MONTH);
                d = calendar.get(Calendar.DAY_OF_MONTH);
                y = calendar.get(Calendar.YEAR);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            m = month;
                            d = dayOfMonth;
                            y = year;
                            mm.setText(dates[m]);
                            dd.setText(""+d);
                            yy.setText(""+y);
                        }
                    },y,m,d);
                    datePickerDialog.show();
                Toast.makeText(CreateToDoActivity.this, "" + hrs + "/" + min, Toast.LENGTH_SHORT).show();
                }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tdm = new ToDoModel(
                            titleInput.getText().toString(),
                            descInput.getText().toString(),
                            hrs,
                            min,
                            m+1,
                            d,
                            y
                    );
                    mMemoryCache.put("keys",tdm);
                    ToDoModel finalTdm = new ToDoModel(mMemoryCache.get("keys"));
                    arrayList.add(tdm);
                    Gson gson = new Gson();
                    String json = gson.toJson(arrayList);
                    Intent intent = new Intent();
                    intent.putExtra("pass",json);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
            }
        });
    }
}
