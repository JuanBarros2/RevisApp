package com.revisapp.ui.main.photo;

import android.content.Intent;
import android.curso.revisapp.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.revisapp.ui.main.TabFragment;


public class ListPhotoFragment extends TabFragment implements PhotoMVP.View{

    private PhotoMVP.Presenter presenter;

    public ListPhotoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PhotoPresenterImpl(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button btn = (Button) view.findViewById(R.id.take_picture_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture(v);
            }
        });
        return view;
    }

    public void takePicture(View v) {
        presenter.takePicture();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    public String getTitle() {
        return "Home";
    }

    @Override
    public void showMessageInfo(String message) {
        Snackbar.make(this.getView(), message, Snackbar.LENGTH_INDEFINITE);
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageWarning(String message) {
        Snackbar.make(this.getView(), message, Snackbar.LENGTH_INDEFINITE);
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
