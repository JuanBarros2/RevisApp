package com.revisapp.ui.infomatter;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.curso.revisapp.R;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoMatterActivity extends AppCompatActivity implements InfoMatterMVP.View {
    private InfoMatterMVP.Presenter presenter;
    @BindView(R.id.name_field)
    public EditText name_field;

    @BindView(R.id.init_schedule_field)
    public EditText init_schedule_field;

    @BindView(R.id.end_schedule_field)
    public EditText end_schedule_field;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_matter);
        presenter = new InfoMatterPresenterImpl(this);
        setUp();
    }

    private void setUp() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.info_toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @OnClick(R.id.add_matter_btn)
    public void doRegister(){
        String name = name_field.getText().toString();
        String init = init_schedule_field.getText().toString();
        String end = end_schedule_field.getText().toString();

        presenter.insertMatter(name, init, end);

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
