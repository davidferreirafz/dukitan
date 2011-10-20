package com.dukitan.android.fzpong.entidade.raquete;

import com.dukitan.android.framework.Input;
import com.dukitan.android.math.Vector2D;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class RaqueteCPU extends Raquete
{

    public RaqueteCPU(Bitmap imagem)
    {
        super(new Rect(15, 21, 14, 80), imagem, new Vector2D(-1, 0));
    }

    @Override
    public void update(Input input)
    {

    }

}
