package com.dukitan.android.fzpong.entidade.parede;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.dukitan.android.framework.Input;
import com.dukitan.android.fzpong.entidade.Bloqueavel;
import com.dukitan.android.math.Vector2D;

public class Parede extends Bloqueavel {

	private Paint wallPaint;
    
	public Parede(Rect bounds, Bitmap imagem, Vector2D vetor) {
		super(bounds, imagem, vetor);

	    wallPaint = new Paint();
	    wallPaint.setColor(Color.GRAY);
	}

	@Override
	public void draw(Canvas canvas) {
        canvas.save();
        canvas.drawRect(getArea(), wallPaint);
        canvas.restore();
		
	}

	@Override
	public void update(Input input) {		
	}

}
