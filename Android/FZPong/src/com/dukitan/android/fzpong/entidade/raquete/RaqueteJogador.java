package com.dukitan.android.fzpong.entidade.raquete;

import com.dukitan.android.framework.Input;
import com.dukitan.android.math.Vector2D;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.KeyEvent;

public class RaqueteJogador extends Raquete {


    
    public RaqueteJogador(Bitmap imagem) {
        super(new Rect(0, 21, 14, 80), imagem, new Vector2D(-1, 0));
    }

    @Override
    public void update(Input input) {

        if (input.isMotion()) {

            setPosicao(LADO_DIREITO, input.getMotionY() - CENTRO_VERTICAL);
        }

        //NAO FUNCIONA - VERIFICAR QUESTOES REFERENTES AO EMULADOR
        if (input.isKey()) {
            if (input.isKey(KeyEvent.KEYCODE_DPAD_UP)) {
                setPosicao(LADO_DIREITO, (int) (posicao.getY() - 10));
            } else if (input.isKey(KeyEvent.KEYCODE_DPAD_DOWN)) {
                setPosicao(LADO_DIREITO, (int) (posicao.getY() + 10));
            }

        }
    }

}
