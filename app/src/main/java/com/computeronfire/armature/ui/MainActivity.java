package com.computeronfire.armature.ui;

/**
 * Created by John on 2/22/2018.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.computeronfire.armature.FromXML;
import com.computeronfire.armature.R;
import com.computeronfire.armature.util.BitmapManager;
import com.computeronfire.armature.util.Log;

public class MainActivity extends Activity {

    private static final int REQ_CAMERA_IMAGE = 123;
    public static int selector = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String message = "Click the button below to start";
        if (cameraNotDetected()) {
            message = "No camera detected, clicking the button below will have unexpected behaviour.";
        }
        TextView cameraDescriptionTextView = findViewById(R.id.text_view_camera_description);
        cameraDescriptionTextView.setText(message);
    }

    private boolean cameraNotDetected() {
        return !getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    @FromXML
    public void onUseCameraClick(View button) {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivityForResult(intent, REQ_CAMERA_IMAGE);
    }

    @FromXML
    public void onSelectArmature0Click(View button) {
        selector = 0;
    }
    @FromXML
    public void onSelectArmature1Click(View button) {
        selector = 1;
    }
    @FromXML
    public void onSelectArmature2Click(View button) {
        selector = 2;
    }
    @FromXML
    public void onSelectArmature3Click(View button) {
        selector = 3;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CAMERA_IMAGE && resultCode == RESULT_OK) {
            String imgPath = data.getStringExtra(CameraActivity.EXTRA_IMAGE_PATH);
            Log.i("Got image path: " + imgPath);
            displayImage(imgPath);
        } else if (requestCode == REQ_CAMERA_IMAGE && resultCode == RESULT_CANCELED) {
            Log.i("User didn't take an image");
        }
    }

    private void displayImage(String path) {
        ImageView imageView = findViewById(R.id.image_view_captured_image);
        imageView.setImageBitmap(BitmapManager.decodeSampledBitmap(path, 300, 250));
    }
}