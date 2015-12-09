package com.felix.opengltest;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by weatherfish on 2015/12/3.
 */
public class SingleColorSquare extends Square {
    @Override
    public void draw(GL10 gl) {
        gl.glColor4f(0.5f, 0.5f, 1.0f, 0.8f);
        super.draw(gl);
    }
}
