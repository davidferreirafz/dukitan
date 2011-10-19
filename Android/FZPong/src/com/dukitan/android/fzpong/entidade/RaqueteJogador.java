package com.dukitan.android.fzpong.entidade;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class RaqueteJogador extends Raquete {

	public RaqueteJogador(Bitmap imagem) {
		super(new Rect(0, 21, 14, 80), imagem);
	}

}
