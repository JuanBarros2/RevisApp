package com.revisapp.helper;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by juan_ on 10/09/2017.
 */

public class CameraHelper {
    public final static String TAG = "CameraHelper";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");

    public static File generateStorageDir(Context context, @Nullable String pathToDirectory) {
        File mediaStorageDir = null;
        if (pathToDirectory != null) {
            mediaStorageDir = new File(pathToDirectory);
        } else {
            mediaStorageDir =
                    context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                 //   new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), context.getPackageName());
        }

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(TAG, "Failed to create directory.");
                return null;
            }
        }

        return mediaStorageDir;
    }

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
        }

        return mediaFile;
    }
}
