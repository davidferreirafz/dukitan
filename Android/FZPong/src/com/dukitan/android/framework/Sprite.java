package com.dukitan.android.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

public class Sprite
{
    private Bitmap imagem;
    private Rect   dimensao;
    private Rect   clipCanvas;
    private Rect   clip;

    private void init(int left, int top, int right, int bottom, Bitmap sprite)
    {
        this.imagem = sprite;
        this.dimensao = new Rect(left, top, right, bottom);
        clipCanvas = new Rect();
        clip = new Rect(left, top, left + right, top + bottom);
    }

    public Sprite(Rect rect, Bitmap sprite)
    {
        init(rect.left, rect.top, rect.right, rect.bottom, sprite);
    }

    public Sprite(int left, int top, int right, int bottom, Bitmap sprite)
    {
        init(left, top, right, bottom, sprite);
    }

    private void update(int x, int y)
    {
        clipCanvas.left = x;
        clipCanvas.top = y;
        clipCanvas.right = x + dimensao.right;
        clipCanvas.bottom = y + dimensao.bottom;
    }

    public void draw(Point ponto, Canvas canvas){
        draw(ponto.x, ponto.y, canvas);
    }
    
    public void draw(int x, int y, Canvas canvas)
    {
        canvas.save();

        update(x, y);

        canvas.drawBitmap(imagem, clip, clipCanvas, null);

        canvas.restore();
    }

}
