package com.dukitan.android.fzpong.entidade;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.dukitan.android.framework.Input;
import com.dukitan.android.framework.Sprite;
import com.dukitan.android.math.Vector2D;

public abstract class Entidade {
	protected Sprite sprite;
	protected Rect size;
	protected Vector2D posicao;

	public Entidade(Rect size, Bitmap imagem) {
		sprite = new Sprite(size, imagem);

		this.size = size;
		this.size.right = size.left + size.right;
		this.size.bottom = size.top + size.bottom;

		posicao = new Vector2D(0,0);
	}

	/**
	 * Retorna posicao do objeto na tela
	 * @return
	 */
	public Rect getArea(){
		
		Rect r = new Rect();
		
		r.left=(int)posicao.getX()+size.left;
		r.top=(int)posicao.getY()+size.top;
		
		r.right=(int)posicao.getX()+size.right;
		r.bottom=(int)posicao.getY()+size.bottom;		
		
		return r;
	}

	/**
	 * Retorna tamanho do objeto
	 * @return
	 */
	public Rect getSize() {
		return size;
	}


	public void setPosicao(int x, int y) {
		posicao.set(x, y);
	}

	public abstract void draw(Canvas canvas);

	public abstract void update(Input input);
}