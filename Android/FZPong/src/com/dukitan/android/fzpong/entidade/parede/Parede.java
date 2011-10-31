package com.dukitan.android.fzpong.entidade.parede;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.dukitan.android.framework.Input;
import com.dukitan.android.fzpong.Placar;
import com.dukitan.android.fzpong.entidade.Bloqueavel;
import com.dukitan.android.math.Vector2D;

public class Parede extends Bloqueavel
{
    static public final int PAREDE_TOP    = 0;
    static public final int PAREDE_BOTTOM = 1;
    static public final int PAREDE_LEFT   = 2;
    static public final int PAREDE_RIGHT  = 3;

    private Paint           wallPaint;

    private int             blink         = 0;
    private int             parede;
    private Placar          placar;

    public Parede(Rect bounds, Bitmap imagem, Vector2D normal)
    {
        super(bounds, imagem, normal);

        wallPaint = new Paint();
        wallPaint.setColor(Color.GRAY);
    }

    @Override
    public void draw(Canvas canvas)
    {

        if (blink % 2 == 0) {
            canvas.save();
            canvas.drawRect(getArea(), wallPaint);
            canvas.restore();
        }
    }

    @Override
    public void update(Input input)
    {
        if (blink > 0) {
            blink--;
        } else {
            blink = 0;
        }
    }

    public void notificar()
    {
        blink = 10;

        if (placar != null) {
            placar.notificar(this);
        }
    }

    public void setTipo(int parede)
    {
        this.parede = parede;
    }

    public int getTipo()
    {
        return parede;
    }

    public void registrar(Placar placar)
    {
        this.placar = placar;
    }

}
