package com.felix.opengltest.v3d;

import android.opengl.GLU;
import android.os.Bundle;

import com.felix.opengltest.OpenGLDemoActivity;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by weatherfish on 2015/12/8.
 */
public class LightingActivity extends OpenGLDemoActivity {

    Sphere sphere = new Sphere();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initScene(GL10 gl) {
        float[] amb = {1.0f, 1.0f, 1.0f, 1.0f,};
        float[] diff = {1.0f, 1.0f, 1.0f, 1.0f,};
        float[] spec = {1.0f, 1.0f, 1.0f, 1.0f,};
        float[] pos = {0.0f, 5.0f, 5.0f, 1.0f,};
        float[] spot_dir = {0.0f, -1.0f, 0.0f,};
        gl.glEnable(GL10.GL_DEPTH_TEST);//打开深度测试
        gl.glEnable(GL10.GL_CULL_FACE);//打开忽略面开关

        gl.glEnable(GL10.GL_LIGHTING);//打开光照总开关
        gl.glEnable(GL10.GL_LIGHT0);//打开光源0
        ByteBuffer abb = ByteBuffer.allocateDirect(amb.length * 4);
        abb.order(ByteOrder.nativeOrder());
        FloatBuffer ambBuf = abb.asFloatBuffer();
        ambBuf.put(amb);
        ambBuf.position(0);

        ByteBuffer dbb = ByteBuffer.allocateDirect(diff.length * 4);
        dbb.order(ByteOrder.nativeOrder());
        FloatBuffer diffBuf = dbb.asFloatBuffer();
        diffBuf.put(diff);
        diffBuf.position(0);

        ByteBuffer sbb = ByteBuffer.allocateDirect(spec.length * 4);
        sbb.order(ByteOrder.nativeOrder());
        FloatBuffer specBuf = sbb.asFloatBuffer();
        specBuf.put(spec);
        specBuf.position(0);

        ByteBuffer pbb = ByteBuffer.allocateDirect(pos.length * 4);
        pbb.order(ByteOrder.nativeOrder());
        FloatBuffer posBuf = pbb.asFloatBuffer();
        posBuf.put(pos);
        posBuf.position(0);

        ByteBuffer spbb = ByteBuffer.allocateDirect(spot_dir.length * 4);
        spbb.order(ByteOrder.nativeOrder());
        FloatBuffer spot_dirBuf = spbb.asFloatBuffer();
        spot_dirBuf.put(spot_dir);
        spot_dirBuf.position(0);


        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, ambBuf);//设置环境光，ambBuf为光的强度
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, diffBuf);//设置漫射光，diffBuf为光的散射强度
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, specBuf); //设置镜面光，specBuf为镜面强度
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, posBuf);//设置光源位置
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPOT_DIRECTION, spot_dirBuf); //设置聚光灯光源方向
        gl.glLightf(GL10.GL_LIGHT0, GL10.GL_SPOT_EXPONENT, 0.0f); //聚光指数
        gl.glLightf(GL10.GL_LIGHT0, GL10.GL_SPOT_CUTOFF, 45.0f);//聚光灯的切脚

        gl.glLoadIdentity();

        // gluLookAt()共有九个参数，分别是眼睛的位置，眼睛朝向的位置，以及相机朝向的方向。
        GLU.gluLookAt(gl, 0.0f, 4.0f, 4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);

    }

    @Override
    public void DrawScene(GL10 gl) {
        super.DrawScene(gl);

        float[] mat_amb = {0.2f * 0.4f, 0.2f * 0.4f, 0.2f * 1.0f, 1.0f,};
        float[] mat_diff = {0.4f, 0.4f, 1.0f, 1.0f,};
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

        /**
         * void glMaterial{if}v(GLenum face, GLenum pname, TYPE *param);
         *
         *  pname指出要设置的哪种材质属性。
         *  param为要设置的属性值，是一个指向数组的指针（向量版本）或一个数值（非向量版本）。只有设置参数值是GL_SHININESS时，才能使用非向量版本。
         *      GL_AMBIENT      默认值（0.2，0.2，0.2，1.0）       材质的环境颜色
         *      GL_DIFFUSE      默认值（0.8，0.8，0.8，1.0）       材质的散射颜色
         *      GL_AMBIENT_AND_DIFFUSE                     材质的环境颜色和散射颜色
         *      GL_SPECULAR     默认值（0.0，0.0，0.0，1.0）       材质的镜面反射颜色
         *      GL_SHININESS    默认值0.0                        镜面反射指数
         *      GL_EMISSION     默认值（0.0，0.0，0.1，1.0）       材质的发射光颜色
         *      GL_COLOR_INDEXES    默认值（0， 1， 1）           环境颜色索引、散射颜色索引和镜面反射颜色索引
         *
         */
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, mat_ambBuf);
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, mat_diffBuf);
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, mat_specBuf);
        gl.glMaterialf(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, 64.0f);

        sphere.draw(gl);
    }
}
