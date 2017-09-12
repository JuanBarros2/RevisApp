package com.revisapp.ui.infomatter;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.curso.revisapp.R;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import com.revisapp.ui.main.matter.MatterModelImpl;

import java.util.Calendar;

public class InfoScheduleActivity extends AppCompatActivity {
    private Calendar initial_schedule;
    private Calendar final_schedule;
    private MatterModelImpl model;
    private EditText name_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_schedule);

        Toolbar toolbar = (Toolbar) findViewById(R.id.info_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name_field = (EditText) findViewById(R.id.name_field);
        if (savedInstanceState == null){
            initVars();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void initVars() {
        initial_schedule = Calendar.getInstance();
        final_schedule = Calendar.getInstance();
        final_schedule.set(Calendar.HOUR, initial_schedule.get(Calendar.HOUR) + 1);

        model = new MatterModelImpl(this);
    }


    public void changeFirstValue(View view){
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                initial_schedule.set(Calendar.HOUR, hourOfDay);
                initial_schedule.set(Calendar.MINUTE, minute);
            }
        }, initial_schedule.get(Calendar.HOUR), initial_schedule.get(Calendar.MINUTE), true).show();
    }

    public void changeSecondValue(View view){
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                final_schedule.set(Calendar.HOUR, hourOfDay);
                final_schedule.set(Calendar.MINUTE, minute);
            }
        }, final_schedule.get(Calendar.HOUR), final_schedule.get(Calendar.MINUTE), true).show();
    }

    public void doRegister(View view){
        if(model.insertSchedule(name_field.getText().toString(),initial_schedule, final_schedule)){
            setResult(RESULT_OK, getIntent());
            finish();
        };
    }
}
