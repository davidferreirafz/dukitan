package com.dukitan.android.fzpong.entidade;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;

public class CPU extends Raquete {

	public CPU(Bitmap sprite) {
		super(sprite, new Point(0,100),new Rect(15,21,29,101));
		
	}

}
