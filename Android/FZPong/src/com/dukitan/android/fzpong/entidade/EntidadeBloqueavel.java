package com.dukitan.android.fzpong.entidade;

import android.graphics.Rect;

import com.dukitan.android.math.Vector2D;

public abstract class EntidadeBloqueavel extends Entidade {
	private Vector2D normal;

	public EntidadeBloqueavel(Rect bounds, Vector2D vec) {
		super(bounds);
		this.normal = vec;
	}

	public EntidadeBloqueavel(Vector2D vec) {
		super(new Rect());
		this.normal = vec;
	}

	public void setNormal(Vector2D normal) {
		this.normal = normal;
	}

	public Vector2D getNormal() {
		return normal;
	}
}