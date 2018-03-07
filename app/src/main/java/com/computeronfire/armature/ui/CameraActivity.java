package com.computeronfire.armature.ui;

/**
 * Created by John on 2/22/2018.
 */

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.view.View;

import com.computeronfire.armature.FromXML;
import com.computeronfire.armature.R;
import com.computeronfire.armature.ui.widget.Preview;
import com.computeronfire.armature.util.Log;

import java.io.File;

import static com.computeronfire.armature.util.CameraManager.cameraAvailable;
import static com.computeronfire.armature.util.CameraManager.getCameraInstance;
import static com.computeronfire.armature.util.Media.getOutputMediaFile;
import static com.computeronfire.armature.util.Media.saveToFile;

public class CameraActivity extends Activity implements PictureCallback {

    protected static final String EXTRA_IMAGE_PATH = "com.computeronfire.armature.cameraoverlay.ui.CameraActivity.EXTRA_IMAGE_PATH";

    private Camera camera;

    private static String savePictureToFileSystem(byte[] data) {
        File file = getOutputMediaFile();
        saveToFile(data, file);
        return file.getAbsolutePath();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Integer contentArray[] = new Integer[11]; //TODO: Make sure you add to these when adding new armatures
        contentArray[0] = R.layout.a0;
        contentArray[1] = R.layout.a1;
        contentArray[2] = R.layout.a2;
        contentArray[3] = R.layout.a3;
        contentArray[4] = R.layout.a4;
        contentArray[5] = R.layout.a5;
        contentArray[6] = R.layout.a6;
        contentArray[7] = R.layout.a7;
        contentArray[8] = R.layout.a8;
        contentArray[9] = R.layout.a9;
        contentArray[10] = R.layout.a10;

        setContentView(contentArray[MainActivity.selector]);
        setResult(RESULT_CANCELED);
        camera = getCameraInstance();
        if (cameraAvailable(camera)) {
            initCameraPreview();
        } else {
            finish();
        }
    }

    private void initCameraPreview() {
        Preview preview = findViewById(R.id.camera_preview);
        preview.init(camera);
    }

    @FromXML
    public void onCaptureClick(View button) {
        camera.takePicture(null, null, this);
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        Log.d("Picture taken");
        String path = savePictureToFileSystem(data);
        setResult(path);
        finish();
    }

    private void setResult(String path) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_IMAGE_PATH, path);
        setResult(RESULT_OK, intent);
    }

    // ALWAYS remember to release the camera when you are finished
    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

    private void releaseCamera() {
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }
}