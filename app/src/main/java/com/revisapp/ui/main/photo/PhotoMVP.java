package com.revisapp.ui.main.photo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.revisapp.domain.Photo;
import com.revisapp.ui.MVP;

import java.util.List;

/**
 * Created by juan_ on 11/09/2017.
 */

public interface PhotoMVP {
    interface Model extends MVP.Model{
        List<Photo> getPhotos();
        void takePicture();
        void savePicture();
    }

    interface View extends MVP.View{
        void showMessageInfo(String message);
        void showMessageWarning(String message);
    }

    interface Presenter extends MVP.Presenter{
        void takePicture();
        void startCamera(Intent intent);
        void requestPermissions(String[] permissions);
        void onRequestPermissionsResult(int resultCode, String[] permissions, int[] grantResults);
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }
}
