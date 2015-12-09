package com.felix.opengltest.v3d;

import android.opengl.GLU;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.felix.opengltest.OpenGLDemoActivity;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by weatherfish on 2015/12/7.
 */
public class DrawSolarSystemActivity extends OpenGLDemoActivity {

    private Star sun = new Star();
    private Star earth = new Star();
    private Star moon = new Star();

    private int angle = 0;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    public void DrawScene(GL10 gl) {
        super.DrawScene(gl);

        gl.glLoadIdentity();
        GLU.gluLookAt(gl, 0.0f, 0.0f, 15.0f,
                0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f);

        gl.glPushMatrix();
        gl.glRotatef(angle, 0, 0, 1);
        gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);

        sun.draw(gl);

        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glRotatef(-angle, 0, 0, 1);
        gl.glTranslatef(3, 0, 0);

        gl.glScalef(.5f, .5f, .5f);
        gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f);
        earth.draw(gl);

        gl.glPushMatrix();
        gl.glRotatef(-angle, 0, 0, 1);
        gl.glTranslatef(2, 0, 0);
        gl.glScalef(.5f, .5f, .5f);

        gl.glRotatef(angle * 10, 0, 0, 1);
        gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        moon.draw(gl);

        gl.glPopMatrix();
        gl.glPopMatrix();
        angle++;
    }
}
