package com.revisapp.ui.main.matter;

import android.content.Context;
import android.content.Intent;
import android.curso.revisapp.R;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.revisapp.ui.infomatter.InfoScheduleActivity;


public class MatterFragment extends Fragment {

    private MatterModelImpl model;
    private MatterAdapter adapter;

    public MatterFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        FloatingActionButton actionButton = (FloatingActionButton) view.findViewById(R.id.add_schedule_btn);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), InfoScheduleActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        model = new MatterModelImpl(getContext());

        RecyclerView recycleMatter = (RecyclerView) view.findViewById(R.id.matter_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recycleMatter.addItemDecoration(itemDecoration);
        recycleMatter.setLayoutManager(layoutManager);
        MatterAdapter adapter = new MatterAdapter(model.getMatters());
        recycleMatter.setAdapter(adapter);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
