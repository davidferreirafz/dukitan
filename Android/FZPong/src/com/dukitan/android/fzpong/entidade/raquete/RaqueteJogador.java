package com.dukitan.android.fzpong.entidade.raquete;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.dukitan.android.framework.Converter;
import com.dukitan.android.framework.Input;
import com.dukitan.android.fzpong.util.PX;
import com.dukitan.android.math.Vector2D;

public class RaqueteJogador extends Raquete
{
    private int   ultimoY = 0;
    private Paint paint   = new Paint();

    public RaqueteJogador(Bitmap imagem)
    {
        super(new Rect(PX.size.RAQUETE_JOGADOR_X(), PX.size.RAQUETE_JOGADOR_Y(), PX.size.RAQUETE_WIDTH(), PX.size.RAQUETE_HEIGHT()), imagem,
                new Vector2D(-1, 0));
    }

    @Override
    public void update(Input input)
    {

        if (input.isMotion()) {

            int y = input.getMotionY();

            if (y < ultimoY) {
                subir();
            } else if (y > ultimoY) {
                descer();
            }

            // setPosicao(getLadoDireito(), input.getMotionY() -
            // getCentroVertical());

            ultimoY = input.getMotionY();
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

    public Point saque()
    {
        Point saque = getPosicao();

        saque.x = saque.x - getVisaoBola().getSize().right;
        saque.y = saque.y + Converter.random(getSize().bottom - getVisaoBola().getSize().bottom);

        return saque;
    }

    @Override
    public void draw(Canvas canvas)
    {
        Point p = getPosicao();

        paint.setColor(Color.WHITE);
        canvas.drawText(p.x + "x" + p.y, p.x - 100, p.y + 20, paint);

        super.draw(canvas);
    }

}
