package com.revisapp.ui.infomatter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.curso.revisapp.R;
import android.os.Bundle;

import com.revisapp.domain.Matter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Criado por Juan em 12/09/2017.
 */

public class InfoMatterPresenterImpl implements InfoMatterMVP.Presenter {
    private InfoMatterMVP.Model model;
    private InfoMatterMVP.View view;

    public InfoMatterPresenterImpl(InfoMatterMVP.View view) {
        this.view = view;
        model = new InfoMatterModelImpl(this);
    }

    @Override
    public Context getContext() {
        return view.getContext();
    }


    @Override
    public void insertMatter(String name, String initial, String finall) {
        if (name != null && initial != null && finall != null){
            try {
                Calendar initial_calendar = Calendar.getInstance();
                SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
                initial_calendar.setTime(parser.parse(initial));

                Calendar final_calendar = Calendar.getInstance();
                final_calendar.setTime(parser.parse(finall));

                model.insertMatter(name, initial_calendar, final_calendar);
            } catch (ParseException e) {
                onError(getContext().getString(R.string.error_converter_time));
            }
        } else{
            onError(getContext().getString(R.string.empty_field));
        }
    }

    @Override
    public void onError(String message) {
        view.onError(message);
    }

    @Override
    public void onInsertedMatter(Matter matter) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();

        bundle.putSerializable(getContext().getString(R.string.bundle_matter), matter);
        intent.putExtras(bundle);
        view.getActivity().setResult(Activity.RESULT_OK, intent);
        view.getActivity().finish();
    }

}
