package com.felix.opengltest;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by weatherfish on 2015/12/3.
 */
public class Square {
    // Our vertices.
    private float vertices[] = {
            -1.0f, 1.0f, 0.0f,  // 0, Top Left
            -1.0f, -1.0f, 0.0f,  // 1, Bottom Left
            1.0f, -1.0f, 0.0f,  // 2, Bottom Right
            1.0f, 1.0f, 0.0f,  // 3, Top Right
    };

    // The order we like to connect them.
    private short[] indices = {0, 1, 2, 0, 2, 3};

    // Our vertex buffer.
    protected FloatBuffer vertexBuffer;

    // Our index buffer.
    private ShortBuffer indexBuffer;

    public Square() {
        // a float is 4 bytes, therefore we
        // multiply the number if
        // vertices with 4.
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        // short is 2 bytes, therefore we multiply
        //the number if
        // vertices with 2.
        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }

    /**
     * This function draws our square on screen.
     *
     * @param gl
     */
    public void draw(GL10 gl) {
        /**
         * void glFrontFace(GLenum mode):   用来指定多边形在窗口坐标中的方向是逆时针还是顺时针的。
         *      GL_CCW  说明逆时针多边形为正面,默认是逆时针多边形为正面
         *      GL_CW   说明顺时针多边形为正面。
         */
        gl.glFrontFace(GL10.GL_CCW);
        /**
         * 开启和关闭剔除功能可以调用带GL_CULL_FACE参数的glEnable和glDisable函数。
         * 默认剔除功能是关闭的。
         */
        gl.glEnable(GL10.GL_CULL_FACE);

        /**
         * GL_FRONT/GL_BACK 禁用多边形正面/背面上的光照、阴影和颜色计算及操作，消除不必要的渲染计算。
         */
        gl.glCullFace(GL10.GL_BACK);

        /**
         * public void glEnableClientState(int array) 启用客户端的某项功能
         *
         *  glEnableClientState和glDisableClientState启用或禁用客户端的单个功能。默认的，所有客户端功能禁用。
         *
         *  array可以是下列符号常量：
         *      GL_COLOR_ARRAY——如果启用，颜色矩阵可以用来写入以及调用glDrawArrays方法或者glDrawElements方法时进行渲染。详见glColorPointer。
         *      GL_NORMAL_ARRAY——如果启用，法线矩阵可以用来写入以及调用glDrawArrays方法或者glDrawElements方法时进行渲染。详见glNormalPointer。
         *      GL_TEXTURE_COORD_ARRAY——如果启用，纹理坐标矩阵可以用来写入以及调用glDrawArrays方法或者glDrawElements方法时进行渲染。详见glTexCoordPointer。
         *      GL_VERTEX_ARRAY——如果启用，顶点矩阵可以用来写入以及调用glDrawArrays方法或者glDrawElements方法时进行渲染。详见glVertexPointer。
         *      GL_POINT_SIZE_ARRAY_OES(OES_point_size_arrayextension)——如果启用，点大小矩阵控制大小以渲染点和点sprites。
         *              这时由glPointSize定义的点大小将被忽略，由点大小矩阵提供的大小将被用来渲染点和点sprites。详见glPointSize。
         *
         *   启用和禁用GL_TEXTURE_COORD_ARRAY将会影响活动的客户纹理单元，活动的客户纹理单元由glClientActiveTexture控制。
         */
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        /**
         * public void glVertexPointer(int size,int type,int stride,Buffer pointer)   定义一个顶点坐标矩阵,指明当渲染时一个顶点坐标矩阵的存储单元和数据。
         *          当一个顶点矩阵被指明时，size, type, stride和pointer保存为客户端状态。
         *
         *  parameters：
         *      size:   每个顶点有几个数字描述。必须是2，3  ，4 之一，初始值是4.
         *      type:   数组中每个顶点的坐标类型。初始值 GL_FLOAT
         *              取值：GL_BYTE, GL_SHORT , GL_FIXED , GL_FLOAT, 分别对应数组类型byte[],short[],int[],float[]；
         *      stride：数组中每个顶点间的间隔，步长（字节位移）。取值若为0，表示数组是连续的   初始值为0
         *      pointer 存储着每个顶点的坐标值。初始值为0
         *
         *  如果顶点矩阵功能启用，当调用glDrawArrays方法或glDrawElements方法时会使用。
         *
         *  调用glDrawArrays方法根据事先指明的点和顶点属性矩阵创建一系列图元（都有相同的类型）。调用glDrawElements方法根据顶点索引和顶点属性创建一系列图元。
         */
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

        /**
         * public void glDrawElements(int mode,int count,int type,Buffer indices) 由矩阵数据渲染图元。
         *
         *  mode指定绘制图元的类型，它应该是下列值之一，GL_POINTS, GL_LINE_STRIP, GL_LINE_LOOP, GL_LINES, GL_TRIANGLE_STRIP, GL_TRIANGLE_FAN, GL_TRIANGLES, GL_QUAD_STRIP, GL_QUADS, and GL_POLYGON.
         *   count为绘制图元的数量乘上一个图元的顶点数。
         *  type为索引值的类型，只能是下列值之一：GL_UNSIGNED_BYTE, GL_UNSIGNED_SHORT, or GL_UNSIGNED_INT。
         *   indices：指向索引存贮位置的指针。
         *
         *  glDrawElements用少量调用指明多重几何图元，你可以事先指明独立的顶点、法线、颜色和纹理坐标矩阵并且可以通过调用glDrawElements方法来使用它们创建序列图元。
         *
         *  当glDrawElements被调用，它会使用有序索引来查询可用矩阵中的元素，并以此创建序列几何图元，如果GL_VERTEX_ARRAY被禁用，则不会创建。
         *
         *  顶点属性由glDrawElements修改，glDrawElements在返回后会有一个未指明的值。
         *  举一个例子，如果GL_COLOR_ARRAY启用，当执行glDrawElements方法后，当前颜色的值是未定义的，属性不会维持它之前的值。
         */
        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);

        /**
         * 见glEnableClientState
         */
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        /**
         * 见glEnable
         */
        gl.glDisable(GL10.GL_CULL_FACE);
    }
}
