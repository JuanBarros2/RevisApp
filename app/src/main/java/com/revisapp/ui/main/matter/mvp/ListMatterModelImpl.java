package com.revisapp.ui.main.matter.mvp;

import android.curso.revisapp.R;

import com.revisapp.domain.Database;
import com.revisapp.domain.Matter;
import com.revisapp.ui.main.matter.mvp.ListMatterMVP;

import java.util.ArrayList;
import java.util.List;

import in.cubestack.android.lib.storm.service.BaseService;
import in.cubestack.android.lib.storm.service.StormService;

/**
 * Criado por Juan em 12/09/2017.
 */

public class ListMatterModelImpl implements ListMatterMVP.Model {

    private StormService service;
    private ListMatterMVP.Presenter presenter;

    public ListMatterModelImpl(ListMatterMVP.Presenter presenter){
        this.presenter = presenter;
        service = new BaseService(presenter.getContext(), Database.class);
    }

    @Override
    public List<Matter> getAll() {
        try {
            return service.findAll(Matter.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void removeMatter(Matter matter) {
        try {
            service.delete(matter);
            presenter.onRemoved(matter);
        } catch (Exception e) {
            presenter.onError(presenter.getContext().getString(R.string.remove_matter));
        }
    }
}
