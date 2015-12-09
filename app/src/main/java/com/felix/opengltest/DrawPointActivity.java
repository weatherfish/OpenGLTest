package com.felix.opengltest;

import android.os.Bundle;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class DrawPointActivity extends OpenGLDemoActivity {

    float[] vertexs = new float[]{
            -0.8f, -0.4f * 1.732f, 0.0f,
            0.8f, -0.4f * 1.732f, 0.0f,
            0.0f, 0.4f * 1.732f, 0.0f
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void DrawScene(GL10 gl) {
        super.DrawScene(gl);

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertexs.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        FloatBuffer vfb = vbb.asFloatBuffer();
        vfb.put(vertexs);
        vfb.position(0);

        gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
        gl.glPointSize(38f);
        gl.glLoadIdentity();
        gl.glTranslatef(0, 0, -4);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vfb);
        gl.glDrawArrays(GL10.GL_POINTS, 0, 3);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
