package com.felix.opengltest;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by weatherfish on 2015/12/4.
 */
public class OpenGLDemoRender implements GLSurfaceView.Renderer {

    private IOpenGLDemo mOpenGLDemo;

    public OpenGLDemoRender(IOpenGLDemo openGLDemo) {
        this.mOpenGLDemo = openGLDemo;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);  //设置背景为黑色（RGBA）
        gl.glShadeModel(GL10.GL_SMOOTH);    //启用smooth shading，也就是颜色平滑过渡
        gl.glClearDepthf(1.0f); //设置depth buffer
        gl.glEnable(GL10.GL_DEPTH_TEST);//开启深度测试
        /**
         *  void glDepthFunc(GLenum func)
         *      func:指定“目标像素与当前像素在z方向上值大小比较”的函数，符合该函数关系的目标像素才进行绘制，否则对目标像素不予绘制。
         *
         *   GL_NEVER：永不绘制
         *   GL_LESS：如果目标像素z值<当前像素z值，则绘制目标像素
         *   GL_EQUAL：如果目标像素z值＝当前像素z值，则绘制目标像素
         *   GL_LEQUAL：如果目标像素z值<＝当前像素z值，则绘制目标像素
         *   GL_GREATER ：如果目标像素z值>当前像素z值，则绘制目标像素
         *    GL_NOTEQUAL：如果目标像素z值<>当前像素z值，则绘制目标像素
         *     GL_GEQUAL：如果目标像素z值>=当前像素z值，则绘制目标像素
         *     GL_ALWAYS：总是绘制
         *
         */
        gl.glDepthFunc(GL10.GL_LEQUAL);

        /**
         *  void glHint(GLenum target,GLenum mod) 该函数控制OpenGL在某一方面有解释的余地时，所采取的操作行为。
         *
         *       target：指定所控制行为的符号常量，可以是以下值
         *          GL_FOG_HINT：指定雾化计算的精度。如果OpenGL实现不能有效的支持每个像素的雾化计算，则GL_DONT_CARE和GL_FASTEST雾化效果中每个定点的计算。
         *          GL_LINE_SMOOTH_HINT：指定反走样线段的采样质量。如果应用较大的滤波函数，GL_NICEST在光栅化期间可以生成更多的像素段。
         *          GL_PERSPECTIVE_CORRECTION_HINT：指定颜色和纹理坐标的差值质量。如果OpenGL不能有效的支持透视修正参数差值，那么GL_DONT_CARE 和 GL_FASTEST可以执行颜色、纹理坐标的简单线性差值计算。
         *          GL_POINT_SMOOTH_HINT：指定反走样点的采样质量，如果应用较大的滤波函数，GL_NICEST在光栅化期间可以生成更多的像素段。
         *          GL_POLYGON_SMOOTH_HINT：指定反走样多边形的采样质量，如果应用较大的滤波函数，GL_NICEST在光栅化期间可以生成更多的像素段。
         *      mod：指定所采取行为的符号常量，可以是以下值
         *          GL_FASTEST：选择速度最快选项。
         *          GL_NICEST：选择最高质量选项。
         *          GL_DONT_CARE：对选项不做考虑。
         *
         */
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();

        /**
         *
         * void gluPerspective(     //函数作用：设置透视投影矩阵
         * GLdouble fovy,           //指定视景体的视野的角度，以度数为单位，y轴的上下方向
         * GLdouble aspect,         //指定视景体的宽高比（x 平面上）
         * GLdouble zNear,          //指定观察者到视景体的最近的裁剪面的距离（必须为正数），沿z轴方向的两裁面之间的距离的近处
         * GLdouble zFar            //指定观察者到视景体的最远的裁剪面的距离（必须为正数），沿z轴方向的两裁面之间的距离的远处
         * )
         */
        GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f, 100.0f);
        // 表示接下来的矩阵操作都是针对模型视景矩阵堆栈
        gl.glMatrixMode(GL10.GL_MODELVIEW);

        // 在调用过glMatrixMode()命令后，总是要调用该命令
        gl.glLoadIdentity();

        if (mOpenGLDemo != null) {
            mOpenGLDemo.initScene(gl);
        }
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        if (mOpenGLDemo != null)
            mOpenGLDemo.DrawScene(gl);
    }
}
