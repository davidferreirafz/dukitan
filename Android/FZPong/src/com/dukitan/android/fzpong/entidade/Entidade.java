package com.dukitan.android.fzpong.entidade;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import com.dukitan.android.framework.Input;
import com.dukitan.android.framework.Size;
import com.dukitan.android.framework.Sprite;
import com.dukitan.android.math.Vector2D;

public abstract class Entidade
{
    protected Sprite   sprite;
    protected Rect     size;
    protected Vector2D posicao;
    private Size       nsize;

    public Entidade(Rect size, Bitmap imagem)
    {
        sprite = new Sprite(size, imagem);

        this.size = size;
        // this.size.right = size.left + size.right;
        // this.size.bottom = size.top + size.bottom;
        // comentado possivel problema com dimensao

        posicao = new Vector2D(0, 0);
        nsize = new Size(size.right, size.bottom);
    }

    /**
     * Retorna posicao do objeto na tela
     * 
     * @return
     */
    public Rect getArea()
    {

        Rect r = new Rect();

        r.left = (int) posicao.getX() + size.left;
        r.top = (int) posicao.getY() + size.top;

        r.right = (int) posicao.getX() + size.right;
        r.bottom = (int) posicao.getY() + size.bottom;

        return r;
    }

    public Rect getRect()
    {

        Rect r = new Rect();

        r.left = (int) posicao.getX();
        r.top = (int) posicao.getY();

        r.right = r.left + nsize.w();
        r.bottom = r.top + nsize.h();

        return r;
    }

    /**
     * Retorna tamanho do objeto
     * 
     * @return
     */
    public Size getSize()
    {
        return nsize;
    }

    public void setPosicao(int x, int y)
    {
        posicao.set(x, y);
    }

    public Point getPosicao()
    {
        return new Point((int) posicao.getX(), (int) posicao.getY());
    }

    public abstract void draw(Canvas canvas);

    public abstract void update(Input input);
}