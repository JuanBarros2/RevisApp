package com.revisapp.ui.infomatter;

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

public class MatterModelImpl implements InfoMatterMVP.Model{
    private List<Matter> matters;
    private StormService service;
    private InfoMatterMVP.Presenter presenter;

    public MatterModelImpl(InfoMatterMVP.Presenter presenter){
        this.presenter = presenter;
        service = new BaseService(presenter.getContext(), Database.class);
        try {
            matters  = service.findAll(Matter.class);
        } catch (Exception e) {
            matters = new LinkedList<>();
            e.printStackTrace();
        }
    }

    @Override
    public boolean insertMatter(String name, Calendar initial, Calendar finall){
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

    @Override
    public boolean updateMatter(String name, Calendar initial, Calendar finall) {
        return false;
    }

    @Override
    public boolean removeMatter(String name){
        Matter matter = new Matter(name, 0, 0);

       // service.delete(matter);
        return matters.remove(matter);
    }

    @Override
    public List<Matter> getMatters(){
        return matters;
    }
}
