package com.revisapp.ui.main.matter;

import android.content.Intent;
import android.curso.revisapp.R;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.revisapp.ui.infomatter.InfoMatterActivity;
import com.revisapp.ui.main.matter.mvp.ListMatterMVP;
import com.revisapp.ui.main.matter.mvp.ListMatterPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListMatterFragment extends Fragment implements ListMatterMVP.View {

    private ListMatterMVP.Presenter presenter;

    RecyclerView recycleMatter;

    public ListMatterFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = setUp(inflater, container);
        presenter = new ListMatterPresenterImpl(this);
        return view;
    }

    @NonNull
    private View setUp(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        FloatingActionButton actionButton = (FloatingActionButton) view.findViewById(R.id.add_schedule_btn);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), InfoMatterActivity.class);
                startActivityForResult(intent, ListMatterMVP.REQUEST_ADD_MATTER);
            }
        });
        recycleMatter = (RecyclerView) view.findViewById(R.id.matters_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recycleMatter.addItemDecoration(itemDecoration);
        recycleMatter.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void loading() {

    }

    @Override
    public void loaded() {

    }

    @Override
    public RecyclerView getRecycler() {
        return recycleMatter;
    }
}
