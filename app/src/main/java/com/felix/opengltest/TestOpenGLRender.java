package com.felix.opengltest;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by weatherfish on 2015/12/2.
 */
public class TestOpenGLRender implements GLSurfaceView.Renderer {
    Square square = new SmoothColorSquare();
    private float angle;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        /**设置背景颜色 RGBA */
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);

        /**
         *  mode   指明使用哪种着色技术，可以取值GL_FLAT和GL_SMOOTH。默认取值是GL_SMOOTH。
         *
         *  GL_SMOOTH，每个顶点使用对应的顶点颜色来着色，而顶点之间的片元颜色则使用差值的方式来计算获得，结果就是渐变色
         *  GL_FLAT，假设几何图形由n个三角形构成，则只会使用顶点颜色数组中最后n个颜色进行着色,也就是每个三角形一个颜色。
         */
        gl.glShadeModel(GL10.GL_SMOOTH);

        /**
         * 指定深度缓冲区的清除值,初始值为1。该值将被用于glClear函数清理深度缓冲区。
         * 被glClearDepthf指明的值会被clamp至区间[0, 1]（小于0则置为0，大于1则置为1）。
         */
        gl.glClearDepthf(1.0f);

        /**开启GL_DEPTH_TEST模式，开启这个模式后，opengl 会起用深度测试模式
         *
         *用法：先开启深度测试模式，再画图，最后关闭此模式。
         */
        gl.glEnable(GL10.GL_DEPTH_TEST);

        /**
         * 指定“目标像素与当前像素在z方向上值大小比较”的函数，符合该函数关系的目标像素才进行绘制，否则对目标像素不予绘制。
         * 该函数只有启用“深度测试”时才有效
         *
         *  参数为深度比较函数：
         * GL_NEVER：永不绘制
         * GL_LESS：如果目标像素z值<当前像素z值，则绘制目标像素
         * GL_EQUAL：如果目标像素z值＝当前像素z值，则绘制目标像素
         * GL_LEQUAL：如果目标像素z值<＝当前像素z值，则绘制目标像素
         * GL_GREATER ：如果目标像素z值>当前像素z值，则绘制目标像素
         * GL_NOTEQUAL：如果目标像素z值<>当前像素z值，则绘制目标像素
         * GL_GEQUAL：如果目标像素z值>=当前像素z值，则绘制目标像素\
         * GL_ALWAYS：总是绘制
         */
        gl.glDepthFunc(GL10.GL_LEQUAL);

        /**
         *  void glHint(GLenum target,GLenum mod)   该函数控制OpenGL在某一方面有解释的余地时，所采取的操作行为。
         *
         *  target：指定所控制行为的符号常量，可以是以下值
         *      GL_FOG_HINT：    指定雾化计算的精度。如果OpenGL实现不能有效的支持每个像素的雾化计算，则GL_DONT_CARE和GL_FASTEST雾化效果中每个定点的计算。
         *      GL_LINE_SMOOTH_HINT：    指定反走样线段的采样质量。如果应用较大的滤波函数，GL_NICEST在光栅化期间可以生成更多的像素段。
         *      GL_PERSPECTIVE_CORRECTION_HINT： 指定颜色和纹理坐标的差值质量。如果OpenGL不能有效的支持透视修正参数差值，
         *                                      那么GL_DONT_CARE 和 GL_FASTEST可以执行颜色、纹理坐标的简单线性差值计算。
         *      GL_POINT_SMOOTH_HINT：   指定反走样点的采样质量，如果应用较大的滤波函数，GL_NICEST在光栅化期间可以生成更多的像素段。
         *      GL_POLYGON_SMOOTH_HINT： 指定反走样多边形的采样质量，如果应用较大的滤波函数，GL_NICEST在光栅化期间可以生成更多的像素段
         *
         *  mod：指定所采取行为的符号常量，可以是以下值
         *      GL_FASTEST：选择速度最快选项。
         *      GL_NICEST：选择最高质量选项。
         *      GL_DONT_CARE：对选项不做考虑。
         */
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        /**glViewport主要负责把视景体截取的图像按照怎样的高和宽显示到屏幕上。*/
        gl.glViewport(0, 0, width, height);

        /**
         *
         * void glMatrixMode（GLenum mode） 告诉我们这个当前矩阵是什么矩阵
         *
         * mode 告诉计算机哪一个矩阵堆栈将是下面矩阵操作的目标,即将什么矩阵设置为当前矩阵，他的可选值有:
         *      GL_MODELVIEW:   表示接下来的矩阵操作都是针对模型视景矩阵堆栈,直到下一次调用这个函数并更改参数为止。
         *      GL_PROJECTION： 表示接下来的矩阵操作都是针对投影矩阵堆栈 ，直到下一次调用这个函数并更改参数为止。
         *      GL_TEXTURE ：   表示接下来的矩阵操作都是针对纹理矩阵堆栈 ，直到下一次调用这个函数并更改参数为止。
         */

        gl.glMatrixMode(GL10.GL_PROJECTION);
        /**
         * 用一个4×4的单位矩阵来替换当前矩阵，实际上就是对当前矩阵进行初始化。
         * 也就是说，无论以前进行了多少次矩阵变换，在该命令执行后，当前矩阵均恢复成一个单位矩阵，即相当于没有进行任何矩阵变换状态。
         *
         * 在调用过glMatrixMode()命令后，总是要调用该命令的原因。由于glMatrixMode()命令本身也是一种矩阵变换，
         * 它将当前矩阵变成命令参数所规定的形式，若不用单位矩阵来替换它，在此矩阵下绘制出的图形将是难以预计的
         */
        gl.glLoadIdentity();

        /**
         *
         * gluPerspective(GLdouble fovy,GLdouble aspect,GLdouble zNear,GLdouble zFar)
         *
         * fovy,    视角的大小,如果设置为0,相当你闭上眼睛了,所以什么也看不到,如果为180,那么可以认为你的视界很广阔,
         * aspect   实际窗口的纵横比,即x/y(width/height)
         * zNear,   表示你近处,的裁面,
         * zFar     表示远处的裁面,
         *
         */
        GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f, 100.0f);
        // 表示接下来的矩阵操作都是针对模型视景矩阵堆栈
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        // 在调用过glMatrixMode()命令后，总是要调用该命令
        gl.glLoadIdentity();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        /***
         *  void glClear(GLbitfield mask) 用当前缓冲区清除值，
         *  也就是glClearColor或者glClearDepth、glClearIndex、glClearStencil、glClearAccum
         *  等函数所指定的值来清除指定的缓冲区，也可以使用glDrawBuffer一次清除多个颜色缓存。
         *
         *  GLbitfield：可以使用 | 运算符组合不同的缓冲标志位，表明需要清除的缓冲
         *      GL_COLOR_BUFFER_BIT:    当前可写的颜色缓冲
         *      GL_DEPTH_BUFFER_BIT:    深度缓冲
         *      GL_ACCUM_BUFFER_BIT:   累积缓冲
         *      GL_STENCIL_BUFFER_BIT: 模板缓冲
         */
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(0, 0, -10);

        //squareA
        gl.glPushMatrix();
        gl.glRotatef(angle, 0, 0, 1);
        square.draw(gl);
        gl.glPopMatrix();

        //squareB
        gl.glPushMatrix();
        gl.glRotatef(-angle, 0, 0, 1);
        gl.glTranslatef(2, 0, 0);
        gl.glScalef(.5f, .5f, .5f);
        square.draw(gl);

        //squareC
        gl.glPushMatrix();
        gl.glRotatef(-angle, 0, 0, 1);
        gl.glTranslatef(1.5f, 0, 0);
        gl.glScalef(.5f, .5f, .5f);
        gl.glRotatef(angle * 10, 0, 0, 1);
        square.draw(gl);

        gl.glPopMatrix();
        // Restore to the matrix as it was before B.
        gl.glPopMatrix();

        angle++;
    }
}
