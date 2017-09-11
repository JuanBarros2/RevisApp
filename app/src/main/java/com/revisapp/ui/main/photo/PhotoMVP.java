package com.revisapp.ui.main.photo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.revisapp.domain.Content;

import java.util.List;

/**
 * Created by juan_ on 11/09/2017.
 */

public interface PhotoMVP {
    interface Model{
        List<Content> getPhotos();
        void takePicture();

    }

    interface View{
        Context getContext();
        Activity getActivity();
        void showMessageInfo(String message);
        void showMessageWarning(String message);
    }

    interface Presenter{
        Context getContext();
        void takePicture();
        void requestPermissions(String[] permissions);
        void onRequestPermissionsResult(int resultCode, String[] permissions, int[] grantResults);
        void startCamera(Intent intent);
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }
}
