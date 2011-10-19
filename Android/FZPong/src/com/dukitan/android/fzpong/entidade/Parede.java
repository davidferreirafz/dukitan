package com.dukitan.android.fzpong.entidade;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.dukitan.android.math.Vector2D;

public class Parede extends EntidadeBloqueavel {
	private Paint wallPaint;

	public Parede(Rect bounds, Vector2D normal) {
		super(bounds, normal);
		wallPaint = new Paint();
		wallPaint.setColor(Color.YELLOW);
	}

	public void draw(Canvas canvas) {
		canvas.save();
		canvas.drawRect(getBounds(), wallPaint);
		canvas.restore();
	}

	public void processAI() {
		// Do nothing, walls don't think
	}
}