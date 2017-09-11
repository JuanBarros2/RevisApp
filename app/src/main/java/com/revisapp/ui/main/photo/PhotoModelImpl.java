package com.revisapp.ui.main.photo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.revisapp.domain.Content;
import com.revisapp.helper.CameraHelper;
import com.revisapp.domain.Database;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import in.cubestack.android.lib.storm.service.BaseService;
import in.cubestack.android.lib.storm.service.StormService;

/**
 * Created by juan_ on 11/09/2017.
 */

public class PhotoModelImpl implements PhotoMVP.Model {

    private PhotoMVP.Presenter presenter;
    private List<Content> photos;
    private StormService service;

    public PhotoModelImpl(PhotoMVP.Presenter presenter) {
        if (presenter != null) {
            this.presenter = presenter;
            service = new BaseService(presenter.getContext(), Database.class);
            try {
                photos = service.findAll(Content.class);
            } catch (Exception e) {
                photos = new LinkedList<>();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void takePicture() {
        List<String> permissions = CameraHelper.getNotGarantedPermissions(presenter.getContext());

        if (permissions.size() == 0) {
            startCamera();
        } else {
            presenter.requestPermissions(permissions.toArray(new String[permissions.size()]));
        }

    }

    private void startCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Context context = presenter.getContext();

        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            File photoFile = CameraHelper.getOutputMediaFile(context, MediaStore.ACTION_IMAGE_CAPTURE,
                    null, null);

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(context,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                presenter.startCamera(takePictureIntent);
            }
        }
    }

    @Override
    public List<Content> getPhotos() {
        return photos;
    }


}
