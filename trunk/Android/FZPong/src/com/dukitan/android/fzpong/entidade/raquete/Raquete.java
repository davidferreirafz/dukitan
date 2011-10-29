package com.dukitan.android.fzpong.entidade.raquete;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.dukitan.android.fzpong.entidade.Bloqueavel;
import com.dukitan.android.fzpong.util.PX;
import com.dukitan.android.math.Vector2D;

public abstract class Raquete extends Bloqueavel
{
    public Raquete(Rect rect, Bitmap imagem, Vector2D vetor)
    {
        super(rect, imagem, vetor);
    }

    @Override
    public void draw(Canvas canvas)
    {
        canvas.save();
        sprite.draw((int) posicao.getX(), (int) posicao.getY(), canvas);
        canvas.restore();
    }

    protected int getCentroVertical()
    {
        return PX.size.RAQUETE_HEIGHT() / 2;
    }

    protected int getCentroHorizontal()
    {
        return PX.size.RAQUETE_WIDTH() / 2;
    }

    protected int getLadoEsquerdo()
    {
        return 0;
    }

    protected int getLadoDireito()
    {
        return PX.size.TELA_WIDTH() - PX.size.RAQUETE_WIDTH();
    }

    abstract public void setPosicao();
}
