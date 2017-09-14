package com.revisapp.ui.main.matter.mvp;

import android.content.Context;
import android.content.Intent;
import android.curso.revisapp.R;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.revisapp.domain.Matter;
import com.revisapp.ui.infomatter.InfoMatterActivity;
import com.revisapp.ui.main.matter.ListMatterAdapter;
import com.revisapp.ui.main.matter.OnClickMatterListener;

import static android.app.Activity.RESULT_OK;
import static com.revisapp.ui.main.matter.mvp.ListMatterMVP.REQUEST_ADD_MATTER;
import static com.revisapp.ui.main.matter.mvp.ListMatterMVP.REQUEST_UPDATE_MATTER;

/**
 * Criado por Juan em 12/09/2017.
 */

public class ListMatterPresenterImpl implements ListMatterMVP.Presenter, OnClickMatterListener {

    private ListMatterMVP.Model model;
    private ListMatterMVP.View view;
    private ListMatterAdapter adapter;

    public ListMatterPresenterImpl(ListMatterMVP.View view) {
        this.view = view;
        model = new ListMatterModelImpl(this);
        setUpRecycle();
    }

    @Override
    public Context getContext() {
        return view.getContext();
    }

    @Override
    public void setUpRecycle() {
        RecyclerView recycleMatter = view.getRecycler();
        adapter = new ListMatterAdapter(model.getAll(), this);
        recycleMatter.setAdapter(adapter);
    }

    @Override
    public void onRemoved(Matter matter) {
        adapter.removeMatter(matter);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_UPDATE_MATTER && resultCode == RESULT_OK){
            updateMatterInRecycle(data);
        } else if(requestCode == REQUEST_ADD_MATTER  && resultCode == RESULT_OK){
            addMatterInRecycle(data);
        }
    }

    private void addMatterInRecycle(Intent data) {
        if(data != null && data.getExtras() != null){
            Bundle bundle = data.getExtras();
            Matter matter = (Matter) bundle.getSerializable(getContext().getString(R.string.bundle_matter));
            adapter.insertMatter(matter);
        }
    }

    private void updateMatterInRecycle(Intent data) {
        Bundle bundle = data.getExtras();
        if(bundle != null){
            Matter oldMatter = (Matter) bundle.getSerializable(getContext().getString(R.string.bundle_matter));
            Matter newMatter = (Matter) bundle.getSerializable(getContext().getString(R.string.bundle_new_matter));
            adapter.updateMatter(oldMatter, newMatter);
        }
    }

    @Override
    public void onUpdateMatter(Matter matter) {
        /*Intent intent = new Intent(view.getContext(), InfoMatterActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(getContext().getString(R.string.bundle_matter), matter);
        view.getActivity().startActivityForResult(intent, REQUEST_UPDATE_MATTER);*/
    }

    @Override
    public void onRemoveMatter(Matter matter) {
        model.removeMatter(matter);
    }
}
