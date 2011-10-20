package com.dukitan.android.fzpong.entidade.bola;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.dukitan.android.framework.Input;
import com.dukitan.android.fzpong.EntidadeManager;
import com.dukitan.android.fzpong.entidade.Entidade;
import com.dukitan.android.fzpong.entidade.Bloqueavel;
import com.dukitan.android.math.Vector2D;

public class Bola extends Entidade {

	private Vector2D dir;
	private float speed = 1.2f;

	public Bola(Bitmap imagem) {
		super(new Rect(0, 0, 20, 20), imagem);
		dir = new Vector2D(1, 0);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.save();
		sprite.draw((int) posicao.getX(), (int) posicao.getY(), canvas);
		canvas.restore();
	}

	private void move() {
		posicao.plusMe(dir.multiply(speed));
	}

	@Override
	public void update(Input input) {

		for (Entidade e : EntidadeManager.getInstance().getEntidades()) {
			if (e == this)
				continue;
			if (e instanceof Bloqueavel) {

				// if (test().intersect(e.test())) {
				if (getArea().intersect(e.getArea())) {
					Vector2D n = ((Bloqueavel) e).getNormal();
					// dir = dir.minus(n.multiply(2).multiply(dir.dot(n)));
					dir = n;
				}
			}
		}
		move();

	}

}
