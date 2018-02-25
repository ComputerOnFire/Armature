package com.computeronfire.armature.util;

/**
 * Created by John on 2/22/2018.
 */

import android.app.Activity;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Media extends Activity {


    public static File getOutputMediaFile() {
        File mediaStorageDir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Armature");
        } else {
            mediaStorageDir = null;
        }
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("failed to create directory");
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
    }

    public static boolean saveToFile(byte[] bytes, File file) {
        boolean saved = false;
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.close();
            saved = true;
        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException", e);
        } catch (IOException e) {
            Log.e("IOException", e);
        }
        return saved;
    }
}