package com.felix.opengltest;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by weatherfish on 2015/12/5.
 */
public class DrawLineActivity extends OpenGLDemoActivity {

    float vertexs[] = {
            -0.8f, -0.4f * 1.732f, 0.0f,
            -0.4f, 0.4f * 1.732f, 0.0f,
            0.0f, -0.4f * 1.732f, 0.0f,
            0.4f, 0.4f * 1.732f, 0.0f,
    };
    int index;

    @Override
    public void DrawScene(GL10 gl) {
        super.DrawScene(gl);

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertexs.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        FloatBuffer vfb = vbb.asFloatBuffer();
        vfb.put(vertexs);
        vfb.position(0);

        gl.glLoadIdentity();
        gl.glTranslatef(0, 0, -4);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vfb);
        index++;
        index %= 300;
        if(index < 100){
            gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
            gl.glDrawArrays(GL10.GL_LINES, 0, 4);
        }else if( index < 200){
            gl.glColor4f(0.0f, 1.0f, 0.0f, 1.0f);
            gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, 4);
        }else {
            gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f);
            gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 4);
        }
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

    }
}
