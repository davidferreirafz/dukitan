package com.dukitan.android.fzpong.entidade;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;

public class RaqueteJogador extends Raquete {

	public RaqueteJogador(Bitmap sprite) {
		super(sprite, new Point(784,100),new Rect(0,21,14,101));
	}

}
