package com.revisapp.ui.infomatter;

import android.content.Context;

import java.util.Calendar;

/**
 * Criado por Juan em 12/09/2017.
 */

public class InfoMatterPresenterImpl implements InfoMatterMVP.Presenter {
    private InfoMatterMVP.Model model;
    private InfoMatterMVP.View view;

    public InfoMatterPresenterImpl(InfoMatterMVP.View view) {
        this.view = view;
        model = new MatterModelImpl(this);
    }

    @Override
    public Context getContext() {
        return view.getContext();
    }

    @Override
    public void insertMatter(String name, Calendar initial, Calendar finall) {

    }

    @Override
    public void updateMatter(String oldName, String newName, Calendar newInitial, Calendar newFinall) {

    }
}
