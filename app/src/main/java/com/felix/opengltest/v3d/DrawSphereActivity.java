package com.felix.opengltest.v3d;

import android.opengl.GLU;

import com.felix.opengltest.OpenGLDemoActivity;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by weatherfish on 2015/12/7.
 */
public class DrawSphereActivity extends OpenGLDemoActivity {
    Sphere sphere = new Sphere();

    @Override
    public void initScene(GL10 gl) {
        float[] mat_amb = {0.2f * 1.0f, 0.2f * 0.4f, 0.2f * 0.4f, 1.0f,};
        float[] mat_diff = {1.0f, 0.4f, 0.4f, 1.0f,};
        float[] mat_spec = {1.0f, 1.0f, 1.0f, 1.0f,};

        ByteBuffer mabb = ByteBuffer.allocateDirect(mat_amb.length * 4);
        mabb.order(ByteOrder.nativeOrder());
        FloatBuffer mat_ambBuf = mabb.asFloatBuffer();
        mat_ambBuf.put(mat_amb);
        mat_ambBuf.position(0);

        ByteBuffer mdbb = ByteBuffer.allocateDirect(mat_diff.length * 4);
        mdbb.order(ByteOrder.nativeOrder());
        FloatBuffer mat_diffBuf = mdbb.asFloatBuffer();
        mat_diffBuf.put(mat_diff);
        mat_diffBuf.position(0);

        ByteBuffer msbb = ByteBuffer.allocateDirect(mat_spec.length * 4);
        msbb.order(ByteOrder.nativeOrder());
        FloatBuffer mat_specBuf = msbb.asFloatBuffer();
        mat_specBuf.put(mat_spec);
        mat_specBuf.position(0);


        gl.glClearColor(0.8f, 0.8f, 0.8f, 0.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glShadeModel(GL10.GL_SMOOTH);

        gl.glEnable(GL10.GL_LIGHTING);
        gl.glEnable(GL10.GL_LIGHT0);

        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, mat_ambBuf);   //设置环境光，mat_ambBuf为光的强度
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, mat_diffBuf);  //设置漫射光，mat_diffBuf为光的散射强度
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, mat_specBuf); //设置镜面光，mat_specBuf为镜面强度
        gl.glMaterialf(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, 64.0f);       //设置镜面反射指数，0-128，值越大，光的散射越小。

        gl.glLoadIdentity();
        //gluLookAt()共有九个参数，分别是眼睛的位置，眼睛朝向的位置，以及相机朝向的方向。
        GLU.gluLookAt(gl, 0.0f, 0.0f, 10.0f,
                0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f);

    }

    public void DrawScene(GL10 gl) {
        super.DrawScene(gl);
        initScene(gl);
        sphere.draw(gl);
    }
}
