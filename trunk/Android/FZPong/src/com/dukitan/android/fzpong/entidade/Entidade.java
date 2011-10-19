package com.dukitan.android.fzpong.entidade;

import android.graphics.Canvas;
import android.graphics.Rect;

public abstract class Entidade {
	protected Rect area;

	public Entidade(Rect bounds) {
		this.area = bounds;
	}

	public Rect getBounds() {
		return area;
	}

	public void setBounds(Rect rect) {
		this.area = rect;
	}

	public abstract void draw(Canvas canvas);

	public abstract void processAI();
}