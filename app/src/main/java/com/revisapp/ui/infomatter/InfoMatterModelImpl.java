package com.revisapp.ui.infomatter;

import android.curso.revisapp.R;

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

public class InfoMatterModelImpl implements InfoMatterMVP.Model{
    private List<Matter> matters;
    private StormService service;
    private InfoMatterMVP.Presenter presenter;

    public InfoMatterModelImpl(InfoMatterMVP.Presenter presenter){
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
    public void insertMatter(String name, Calendar initial, Calendar finall){
        Matter matter = new Matter(name, initial.getTime().getTime(), finall.getTime().getTime() );

        if(!matters.contains(matter)) {
            matters.add(matter);
            try {
                service.save(matter);

            } catch (Exception e) {
                presenter.onError(presenter.getContext().getString(R.string.error_insert));
            }
        }

        presenter.onInsertedMatter(matter);
    }

}
