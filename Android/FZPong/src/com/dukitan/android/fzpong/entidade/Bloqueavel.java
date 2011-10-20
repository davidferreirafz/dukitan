package com.dukitan.android.fzpong.entidade;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.dukitan.android.math.Vector2D;

public abstract class Bloqueavel extends Entidade
{
    private Vector2D normal;

    public Bloqueavel(Rect bounds, Bitmap imagem, Vector2D vetor)
    {
        super(bounds, imagem);
        this.normal = vetor;
    }

    public void setNormal(Vector2D normal)
    {
        this.normal = normal;
    }

    public Vector2D getNormal()
    {
        return normal;
    }

}