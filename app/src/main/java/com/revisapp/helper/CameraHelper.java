package com.revisapp.helper;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by juan_ on 10/09/2017.
 */

public class CameraHelper {
    public final static String TAG = "CameraHelper";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");

    /**
     * Creates the directory for a media file
     * @param context
     * @param pathToDirectory If exists, the path to directory
     * @return Directory File
     */
    private static File generateStorageDir(Context context, @Nullable String pathToDirectory) {
        File mediaStorageDir = null;
        if (pathToDirectory != null) {
            mediaStorageDir = new File(pathToDirectory);
        } else {
            mediaStorageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        }

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(TAG, "Failed to create directory.");
                return null;
            }
        }

        return mediaStorageDir;
    }

    /**
     * Creates a repository to save the media file.
     * @param context
     * @param mediaAction Type of media
     * @param pathToDirectory If exists, the path to directory
     * @param fileName If exists, the file name
     * @return File that points to the media
     */
    public static File getOutputMediaFile(Context context, String mediaAction, @Nullable String pathToDirectory, @Nullable String fileName) {
        final File mediaStorageDir = generateStorageDir(context, pathToDirectory);
        File mediaFile = null;

        if (mediaStorageDir != null) {
            if (fileName == null) {
                final String timeStamp = simpleDateFormat.format(new Date());
                if (mediaAction.equals(MediaStore.ACTION_IMAGE_CAPTURE)) {
                    fileName = "IMG_" + timeStamp;

                } else if (mediaAction.equals(MediaStore.ACTION_VIDEO_CAPTURE)) {
                    fileName = "VID_" + timeStamp;
                }

            }
            final String mediaStorageDirPath = mediaStorageDir.getPath();
            if (mediaAction.equals(MediaStore.ACTION_IMAGE_CAPTURE)) {
                mediaFile = new File(mediaStorageDirPath + File.separator + fileName + ".jpg");

            } else if (mediaAction.equals(MediaStore.ACTION_VIDEO_CAPTURE)) {
                mediaFile = new File(mediaStorageDirPath + File.separator + fileName + ".mp4");
            }

            Log.i(TAG, "file was created: " + mediaFile.getAbsolutePath());

        }

        return mediaFile;
    }

    /**
     * Returns a list with not garanted permissions for photo capture.
     * @param context
     * @return list of permissions.
     */
    public static List<String> getNotGarantedPermissions(Context context) {
        List<String> permissions = new ArrayList<>(2);
        boolean isPhotoGranted = (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);
        boolean isStorageGranted = ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)  == PackageManager.PERMISSION_GRANTED;

        if (isPhotoGranted && isStorageGranted){
        } else {

            if(!isPhotoGranted){
                permissions.add(Manifest.permission.CAMERA);
            }
            if(!isStorageGranted){
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
        }

        return permissions;
    }
}
