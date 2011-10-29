package com.dukitan.android.fzpong.entidade.raquete;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.dukitan.android.framework.Input;
import com.dukitan.android.fzpong.util.PX;
import com.dukitan.android.math.Vector2D;

public class RaqueteJogador extends Raquete
{

    public RaqueteJogador(Bitmap imagem)
    {
        super(new Rect(PX.size.RAQUETE_JOGADOR_X(), PX.size.RAQUETE_JOGADOR_Y(), PX.size.RAQUETE_WIDTH(), PX.size.RAQUETE_HEIGHT()), imagem,
                new Vector2D(-1, 0));
    }

    public void setPosicao()
    {
        super.setPosicao(getLadoDireito(), PX.size.TELA_HEIGHT() / 2);

    }

    @Override
    public void update(Input input)
    {

        if (input.isMotion()) {

            setPosicao(getLadoDireito(), input.getMotionY() - getCentroVertical());
        }

        // NAO FUNCIONA - VERIFICAR QUESTOES REFERENTES AO EMULADOR
        /*
         * if (input.isKey()) { if (input.isKey(KeyEvent.KEYCODE_DPAD_UP)) {
         * setPosicao(getLadoDireito(), (int) (posicao.getY() - 10)); } else if
         * (input.isKey(KeyEvent.KEYCODE_DPAD_DOWN)) {
         * setPosicao(getLadoDireito(), (int) (posicao.getY() + 10)); }
         * 
         * }
         */
    }

}
