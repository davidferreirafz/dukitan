package com.dukitan.android.framework;

import android.view.KeyEvent;
import android.view.MotionEvent;

public class Input {
    private MotionEvent motion;
    private KeyEvent key;
    private double elapsed;

    public Input() {
        motion = null;
        key = null;
        elapsed = 0;
    }

    public void setMotionEvent(MotionEvent event) {
        motion = event;
    }

    public boolean isMotion() {
        return motion != null;
    }

    public int getMotionX() {
        return (int) motion.getX();
    }

    public int getMotionY() {
        return (int) motion.getY();
    }

    public void setKeyEvent(KeyEvent event) {
        key = event;
    }

    public boolean isKey() {
        return key != null;
    }

    public boolean isKey(int keyCode) {
        return this.key.getKeyCode() == keyCode;
    }

    public void setTime(double elapsed) {
        this.elapsed = elapsed;

    }

    public double getTime() {
        return elapsed;
    }

}
