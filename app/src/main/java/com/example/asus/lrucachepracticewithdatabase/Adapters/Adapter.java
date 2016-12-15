package com.example.asus.lrucachepracticewithdatabase.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.asus.lrucachepracticewithdatabase.Model.ToDoModel;
import com.example.asus.lrucachepracticewithdatabase.R;

import java.util.List;

/**
 * Created by asus on 13/12/2016.
 */

public class Adapter extends ArrayAdapter<ToDoModel> {
    private Context mContenxt;
    private List<ToDoModel> toDo;
    private int resource;


    public Adapter(Context context, int resource, List<ToDoModel> todo) {
        super(context, resource, todo);

        mContenxt = context;
        toDo = todo;
        this.resource = resource;
    }

    @Nullable
    @Override
    public ToDoModel getItem(int position) {
        return toDo.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContenxt).inflate(resource,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        ToDoModel model = toDo.get(position);
        if(model != null){
            viewHolder.desc.setText(model.getDescription());
            viewHolder.tit.setText(model.getTitle());
        }

        return convertView;

    }
    public class ViewHolder{
        TextView tit;
        TextView desc;

        public ViewHolder(View view){
            tit = (TextView)view.findViewById(R.id.title);
            desc = (TextView)view.findViewById(R.id.description);
        }

    }
    public void add(ToDoModel toDoModel){
        if(toDoModel == null){
            return;
        }
        toDo.add(toDoModel);
        notifyDataSetChanged();
    }

    public void addAll(List<ToDoModel> todo){
        if(todo == null || todo.size() <= 0){
            return;
        }
        toDo.addAll(todo);
        notifyDataSetChanged();
    }



}
