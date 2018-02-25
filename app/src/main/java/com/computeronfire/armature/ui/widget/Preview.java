package com.computeronfire.armature.ui.widget;

/**
 * Created by John on 2/22/2018.
 */

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.computeronfire.armature.util.Log;

public class Preview extends SurfaceView implements SurfaceHolder.Callback {

    public Camera camera;
    public SurfaceHolder holder;

    public Preview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public Preview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Preview(Context context) {
        super(context);
    }

    public void init(Camera camera) {
        this.camera = camera;
        initSurfaceHolder();
    }

    @SuppressWarnings("deprecation")
    private void initSurfaceHolder() {
        holder = getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        initCamera(holder);
    }

    private void initCamera(SurfaceHolder holder) {
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (Exception e) {
            Log.d("Error setting camera preview", e);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // not-used
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // not-used
    }
}