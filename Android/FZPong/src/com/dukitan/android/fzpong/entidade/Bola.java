package com.dukitan.android.fzpong.entidade;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

public class Bola extends Entidade {



	protected Bitmap sprite = null;
	protected Point posicao = new Point();
	protected Rect destino  = new Rect();
	protected Point dimensao = new Point();
	
	public Bola(Bitmap sprite) {
		super(new Rect(0,0,20,20));
		this.sprite=sprite;
		dimensao.x=20;
		dimensao.y=20;		
//		super(sprite, new Point(784,100),new Rect(0,21,14,101));
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
