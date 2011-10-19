package com.dukitan.android.fzpong.entidade;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import com.dukitan.android.framework.Sprite;

public abstract class Entidade {
	protected Sprite sprite;
	protected Rect area;
	protected Point posicao;

	public Entidade(Rect bounds, Bitmap imagem) {
		this.area = bounds;
		sprite = new Sprite(bounds, imagem);
		posicao = new Point();
	}

	public Rect getBounds() {
		return area;
	}

	public void setBounds(Rect rect) {
		this.area = rect;
	}

	public void setPosicao(int x, int y) {
		posicao.x = x;
		posicao.y = y;
	}

	public abstract void draw(Canvas canvas);

	public abstract void processAI();
}