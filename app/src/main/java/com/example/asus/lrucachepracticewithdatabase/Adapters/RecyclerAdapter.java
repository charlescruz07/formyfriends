package com.example.asus.lrucachepracticewithdatabase.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.lrucachepracticewithdatabase.Activities.ToDoActivity;
import com.example.asus.lrucachepracticewithdatabase.Model.ToDoModel;
import com.example.asus.lrucachepracticewithdatabase.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Acer on 16/12/2016.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<ToDoModel> arrayList;
    private Context mContext;
    public RecyclerAdapter(String arrayList, Context context){
        this.arrayList = new ArrayList<>();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ToDoModel>>(){}.getType();
        ArrayList<ToDoModel> arrays = gson.fromJson(arrayList,type);
        for(int i = 0; i<arrays.size(); i++){
            ToDoModel tdm = new ToDoModel(
                    arrays.get(i).getTitle(),
                    arrays.get(i).getDescription(),
                    arrays.get(i).getHours(),
                    arrays.get(i).getMinutes(),
                    arrays.get(i).getMonth(),
                    arrays.get(i).getDay(),
                    arrays.get(i).getYear());
            this.arrayList.add(tdm);
            mContext = context;
        }
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        holder.title.setText(arrayList.get(position).getTitle());
        holder.desc.setText(arrayList.get(position).getDescription());
        holder.date.setText(arrayList.get(position).getMonth() + "/"
                + arrayList.get(position).getDay() + "/"
                + arrayList.get(position).getYear());
        holder.time.setText(arrayList.get(position).getHours() + ":"
                +arrayList.get(position).getMinutes());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,desc,date,time;

        ImageView done,edit,delete;
        public ViewHolder(final View itemView){
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.task_title);
            desc = (TextView) itemView.findViewById(R.id.task_desc);
            date = (TextView) itemView.findViewById(R.id.todo_date);
            time = (TextView) itemView.findViewById(R.id.todo_time);
            done = (ImageView) itemView.findViewById(R.id.done);
            edit = (ImageView) itemView.findViewById(R.id.edit);
            delete = (ImageView) itemView.findViewById(R.id.delete);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    arrayList.remove(getAdapterPosition());
                    ((ToDoActivity)mContext).removeData(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(),arrayList.size());

                }
            });
        }
    }
}
