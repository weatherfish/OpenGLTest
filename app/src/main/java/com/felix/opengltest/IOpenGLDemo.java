package com.felix.opengltest;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by weatherfish on 2015/12/4.
 */
public interface IOpenGLDemo {
    void DrawScene(GL10 gl);
    void initScene(GL10 gl);
}
