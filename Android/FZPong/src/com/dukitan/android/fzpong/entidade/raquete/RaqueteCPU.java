package com.dukitan.android.fzpong.entidade.raquete;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.dukitan.android.framework.Input;
import com.dukitan.android.fzpong.util.PX;
import com.dukitan.android.math.Vector2D;

public class RaqueteCPU extends Raquete
{

    public RaqueteCPU(Bitmap imagem)
    {
        super(new Rect(PX.size.RAQUETE_CPU_X(), PX.size.RAQUETE_CPU_Y(), PX.size.RAQUETE_WIDTH(), PX.size.RAQUETE_HEIGHT()), imagem, new Vector2D(-1,
                0));
    }

    public void setPosicao()
    {
        super.setPosicao(getLadoEsquerdo(), PX.size.TELA_HEIGHT() / 2);

    }    
    
    @Override
    public void update(Input input)
    {

    }

}
