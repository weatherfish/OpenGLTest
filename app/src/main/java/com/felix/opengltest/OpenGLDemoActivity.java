package com.felix.opengltest;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import javax.microedition.khronos.opengles.GL10;

public class OpenGLDemoActivity extends Activity implements IOpenGLDemo {

    private GLSurfaceView mGLSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mGLSurfaceView = new GLSurfaceView(this);
        mGLSurfaceView.setRenderer(new OpenGLDemoRender(this));
        setContentView(mGLSurfaceView);
    }

    @Override
    public void DrawScene(GL10 gl) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);//背景设置为黑色

        // Clears the color and depth buffer.
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
    }

    @Override
    public void initScene(GL10 gl) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLSurfaceView.onPause();
    }
}
