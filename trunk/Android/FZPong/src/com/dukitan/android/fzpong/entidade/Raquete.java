package com.dukitan.android.fzpong.entidade;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Raquete extends EntidadeBloqueavel {

	public Raquete(Rect rect, Bitmap imagem) {
		super(rect, imagem, null);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.save();
		sprite.draw(posicao.x, posicao.y, canvas);
		canvas.restore();
	}

	@Override
	public void processAI() {

	}

}
