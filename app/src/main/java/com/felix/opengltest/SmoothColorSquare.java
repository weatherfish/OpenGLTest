package com.felix.opengltest;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by weatherfish on 2015/12/3.
 */
public class SmoothColorSquare extends Square {

    FloatBuffer colorBuffer;

    float[] colors = {
            1f, 0f, 0f, 1f, // vertex 0 red
            0f, 1f, 0f, 1f, // vertex 1 green
            0f, 0f, 1f, 1f, // vertex 2 blue
            1f, 0f, 1f, 1f, // vertex 3 magenta
    };

    public SmoothColorSquare() {
        super();
        // float has 4 bytes, colors (RGBA) * 4 bytes
        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        colorBuffer = cbb.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);
    }

    @Override
    public void draw(GL10 gl) {

       // gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

        /***
         * 启用颜色矩阵,可以用来写入以及调用glDrawArrays方法或者glDrawElements方法时进行渲染
         */
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        /**
         * public void glColorPointer(int size,int type,int stride,Buffer pointer)  定义一个颜色矩阵。
         *
         *      size指明每个颜色的元素数量，必须为4。
         *      type——指明每个矩阵中颜色元素的数据类型，允许的符号常量有GL_UNSIGNED_BYTE, GL_FIXED和GL_FLOAT，初始值为GL_FLOAT
         *      stride——指明连续的点之间的位偏移，如果stride为0时，颜色被紧密挤入矩阵，初始值为0。（简单矩阵存储可能在一些版本中更有效率）。
         *      pointer——指明包含颜色的缓冲区，如果pointer为null，则为设置缓冲区。
         *
         *      当一个颜色矩阵被指定，size, type, stride和pointer将被保存在客户端状态。
         */
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
        super.draw(gl);
        //关闭颜色矩阵
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }
}
