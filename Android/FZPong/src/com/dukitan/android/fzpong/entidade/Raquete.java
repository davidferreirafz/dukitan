package com.dukitan.android.fzpong.entidade;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import com.dukitan.android.math.Vector2D;

public class Raquete extends EntidadeBloqueavel {

	protected Bitmap sprite = null;
	protected Point posicao = new Point();
	protected Rect destino  = new Rect();
	protected Point dimensao = new Point();
	

	public Raquete(Bitmap sprite, Point posicao, Rect area) {
		super(area, new Vector2D(0,1));


		this.sprite=sprite;
		dimensao.x=14;
		dimensao.y=80;
		
		setPosicao(posicao.x,posicao.y);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.save();
		canvas.drawBitmap(sprite, area, destino, null);
		canvas.restore();
	}

	@Override
	public void processAI() {


	}

	public void setPosicao(int x, int y) {
		posicao.x=x;
		posicao.y=y;
		
		destino.left=posicao.x;
		destino.top=posicao.y;
		destino.right=posicao.x+dimensao.x;
		destino.bottom=posicao.y+dimensao.y;
	}
	

}
