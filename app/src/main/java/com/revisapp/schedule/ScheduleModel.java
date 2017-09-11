package com.revisapp.schedule;

import android.content.Context;

import com.revisapp.domain.Matter;
import com.revisapp.domain.Schedule;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import in.cubestack.android.lib.storm.service.BaseService;
import in.cubestack.android.lib.storm.service.StormService;

/**
 * Created by juan_ on 05/09/2017.
 */

public class ScheduleModel {
    private List<Matter> matters;
    private StormService service;
    private Context context;

    public ScheduleModel (Context context){
        this.context = context;
        service = new BaseService(context, Database.class);
        try {
            matters  = service.findAll(Matter.class);
        } catch (Exception e) {
            matters = new LinkedList<>();
            e.printStackTrace();
        }
    }

    public boolean insertSchedule(String name, Calendar initial, Calendar finall){
        boolean inserted = false;
        Matter matter = new Matter(name, initial.getTime().getTime(), finall.getTime().getTime() );

        if(!matters.contains(matter)) {
            matters.add(matter);
            try {
                service.save(matter);
                inserted = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return inserted;
    }

    public boolean removeSchedule(String name){
        Matter matter = new Matter(name, 0, 0);

        return matters.remove(matter);
    }

    public List<Matter> getMatters(){
        return matters;
    }
}
