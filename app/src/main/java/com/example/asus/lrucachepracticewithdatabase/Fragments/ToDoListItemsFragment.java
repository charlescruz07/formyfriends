package com.example.asus.lrucachepracticewithdatabase.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.lrucachepracticewithdatabase.Adapters.RecyclerAdapter;
import com.example.asus.lrucachepracticewithdatabase.Model.ToDoModel;
import com.example.asus.lrucachepracticewithdatabase.R;

/**
 * Created by Acer on 16/12/2016.
 */

public class ToDoListItemsFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_layout,container,false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        String arrayList = getArguments().getString("arrayList");
        adapter = new RecyclerAdapter(arrayList,getActivity());
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}
