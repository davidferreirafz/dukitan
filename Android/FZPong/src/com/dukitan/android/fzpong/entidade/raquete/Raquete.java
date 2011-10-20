package com.dukitan.android.fzpong.entidade.raquete;

import com.dukitan.android.fzpong.entidade.Bloqueavel;
import com.dukitan.android.math.Vector2D;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public abstract class Raquete extends Bloqueavel {

    final public static int LADO_ESQUERDO = 0;
    final public static int LADO_DIREITO = 784;

    public Raquete(Rect rect, Bitmap imagem, Vector2D vetor) {
        super(rect, imagem, vetor);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        sprite.draw((int) posicao.getX(), (int) posicao.getY(), canvas);
        canvas.restore();
    }

}
