package com.revisapp.ui.main.photo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.curso.revisapp.R;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;

/**
 * Created by juan_ on 11/09/2017.
 */

public class PhotoPresenterImpl implements PhotoMVP.Presenter{

    private PhotoMVP.View view;
    private PhotoMVP.Model model;
    private final int REQUEST_PERMISSION = 100;
    private final int REQUEST_CAMERA = 101;

    public PhotoPresenterImpl(PhotoMVP.View view){
        this.view = view;
        model = new PhotoModelImpl(this);
    }

    @Override
    public Context getContext() {
        return view.getContext();
    }

    @Override
    public void takePicture() {
        model.takePicture();
    }

    @Override
    public void requestPermissions(String[] permissions) {
        ActivityCompat.requestPermissions(view.getActivity(),
                permissions,
                REQUEST_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int resultCode, String[] permissions, int[] grantResults) {
        if(resultCode == REQUEST_PERMISSION){
            int index = 0;
            boolean isGrantAll = true;
            while (index < grantResults.length && isGrantAll){
                if(grantResults[index] != PackageManager.PERMISSION_GRANTED){
                    isGrantAll = false;
                }
                index++;
            }

            if(isGrantAll){
                takePicture();
            } else{
                view.showMessageWarning(getContext().getString(R.string.permission_error));
            }
        }

    }

    @Override
    public void startCamera(Intent intent) {
        view.getActivity().startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CAMERA){
            if(resultCode == RESULT_OK){
                Log.d(PhotoPresenterImpl.class.getSimpleName(), "foto tirada com sucesso");

            } else{
                Toast.makeText(getContext(), R.string.photo_error, Toast.LENGTH_LONG).show();
            }
        }
    }


}
