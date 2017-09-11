package com.revisapp.schedule;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.curso.revisapp.R;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.revisapp.GlideApp;
import com.revisapp.RevisapApp;
import com.revisapp.TabFragment;
import com.revisapp.helper.CameraHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.bumptech.glide.module.AppGlideModule;

import static android.app.Activity.RESULT_OK;


public class HomeFragment extends TabFragment {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView image;
    File photoFile;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE){
            if(resultCode == RESULT_OK){
                Log.d(HomeFragment.class.getSimpleName(), "foto tirada com sucesso");
                GlideApp.with(this)
                        .load(photoFile)
                        .fitCenter()
                        .into(image);

            } else{
                Toast.makeText(getContext(), R.string.photo_error, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button btn = (Button) view.findViewById(R.id.take_picture_btn);
        image = (ImageView) view.findViewById(R.id.photo_view);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture(v);
            }
        });
        return view;
    }

    public void takePicture(View v) {
        if(!requestPermissions()){
          //  Intent intent = new Intent(getContext(), PhotoActivity.class);
          //  startActivity(intent);
            callCamera();
        }
    }

    private void callCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            photoFile = CameraHelper.getOutputMediaFile(getContext(), MediaStore.ACTION_IMAGE_CAPTURE,
                    null , null);
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private boolean requestPermissions() {
        boolean result = true;

        boolean isPhotoGranted = (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);
        boolean isStorageGranted = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)  == PackageManager.PERMISSION_GRANTED;

        if (isPhotoGranted && isStorageGranted){
            result = false;
        } else {
            List<String> permissions = new ArrayList<>(2);
            if(!isPhotoGranted){
                permissions.add(Manifest.permission.CAMERA);
            }
            if(!isStorageGranted){
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }

            ActivityCompat.requestPermissions(getActivity(), permissions.toArray(new String[permissions.size()]), REQUEST_IMAGE_CAPTURE);
        }

        return result;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int index = 0;
        boolean isGrantAll = true;
        while (index < grantResults.length && isGrantAll){
            if(grantResults[index] != PackageManager.PERMISSION_GRANTED){
                isGrantAll = false;
            }
            index++;
        }

        if(isGrantAll){
            takePicture(null);
        } else{
            Snackbar.make(this.getView(), R.string.permission_error, Snackbar.LENGTH_INDEFINITE);
            Toast.makeText(getContext(), R.string.permission_error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public String getTitle() {
        return "Home";
    }
}
