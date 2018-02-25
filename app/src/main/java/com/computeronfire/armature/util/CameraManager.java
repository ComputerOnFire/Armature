package com.computeronfire.armature.util;

/**
 * Created by John on 2/22/2018.
 */

import android.hardware.Camera;

public class CameraManager {
    public static boolean cameraAvailable(Camera camera) {
        return camera != null;
    }

    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e) {
            Log.d("getCamera failed", e);
        }
        return c;
    }
}
