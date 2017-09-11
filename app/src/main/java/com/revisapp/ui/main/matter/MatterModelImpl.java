package com.revisapp.ui.main.matter;

import android.content.Context;

import com.revisapp.domain.Database;
import com.revisapp.domain.Matter;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import in.cubestack.android.lib.storm.service.BaseService;
import in.cubestack.android.lib.storm.service.StormService;

/**
 * Created by juan_ on 05/09/2017.
 */

public class MatterModelImpl {
    private List<Matter> matters;
    private StormService service;

    public MatterModelImpl(Context context){
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
