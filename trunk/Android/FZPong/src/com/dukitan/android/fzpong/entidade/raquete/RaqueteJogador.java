package com.dukitan.android.fzpong.entidade.raquete;

import com.dukitan.android.framework.Input;
import com.dukitan.android.math.Vector2D;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class RaqueteJogador extends Raquete {

    public RaqueteJogador(Bitmap imagem) {
        super(new Rect(0, 21, 14, 80), imagem, new Vector2D(-1, 0));
    }

    @Override
    public void update(Input input) {

        if (input.isMotion()) {

            setPosicao(LADO_DIREITO, input.getY() - 40);
        }
    }

}
