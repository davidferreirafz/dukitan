package com.dukitan.android.framework;

import android.view.MotionEvent;

public class Input {

	private MotionEvent motion;


	public Input() {

	}

	public void setMotionEvent(MotionEvent evt) {
		motion = evt;
	}

	
	public boolean isMotion(){
		return motion!=null;
	}


	public int getX() {
		return (int) motion.getX();
	}

	public int getY() {
		return (int) motion.getY();
	}
}
