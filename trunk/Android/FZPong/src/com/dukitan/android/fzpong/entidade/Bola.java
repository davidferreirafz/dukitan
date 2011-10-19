package com.dukitan.android.fzpong.entidade;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Bola extends Entidade {

	public Bola(Bitmap imagem) {
		super(new Rect(0, 0, 20, 20), imagem);

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
