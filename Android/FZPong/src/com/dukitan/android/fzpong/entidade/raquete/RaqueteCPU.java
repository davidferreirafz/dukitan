package com.dukitan.android.fzpong.entidade.raquete;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.dukitan.android.framework.Converter;
import com.dukitan.android.framework.Input;
import com.dukitan.android.fzpong.entidade.Entidade;
import com.dukitan.android.fzpong.util.PX;
import com.dukitan.android.math.Vector2D;

public class RaqueteCPU extends Raquete
{
    private final int EFEITO_RANDOM  = 2;
    private final int EFEITO_SEM     = 0;
    private final int EFEITO_CIMA    = 1;
    private final int EFEITO_BAIXO   = 2;

    private final int DECISAO_NADA   = 0;
    private final int DECISAO_SUBIR  = 1;
    private final int DECISAO_DESCER = 2;

    int               raioVisao;

    int               efeito;
    Paint             paint          = new Paint();

    public RaqueteCPU(Bitmap imagem)
    {
        super(new Rect(PX.size.RAQUETE_CPU_X(), PX.size.RAQUETE_CPU_Y(), PX.size.RAQUETE_WIDTH(), PX.size.RAQUETE_HEIGHT()), imagem, new Vector2D(-1,
                0));

        iniciarVisao();
    }

    @Override
    public void update(Input input)
    {
        Rect areaVisaoBola = Converter.converterArea(getVisaoBola().getSize(), getVisaoBola().getPosicao());
        Rect visao = Converter.converterArea(getSize(), getPosicao());

        switch (pensar(visao, areaVisaoBola)) {
            case DECISAO_SUBIR:
                subir();
                break;
            case DECISAO_DESCER:
                descer();
                break;
            case DECISAO_NADA:
            default:
                // nada para fazer
                break;
        }
    }

    public Point saque()
    {
        Point saque = getPosicao();

        saque.x = saque.x + getSize().right;
        saque.y = saque.y + Converter.random(getSize().bottom - getVisaoBola().getSize().bottom);

        return saque;
    }

    protected int pensar(Rect visao, Rect areaVisaoBola)
    {
        double qx, qy, qr, qe; // para guardar o quadrado de x, y e raio
        int decisao = DECISAO_NADA;

        // quadrado da distância em x
        qx = Math.pow((areaVisaoBola.left + areaVisaoBola.right / 2) - (visao.left + visao.right / 2), 2);
        // quadrado da distância em y
        qy = Math.pow((areaVisaoBola.top + areaVisaoBola.bottom / 2) - (visao.top + visao.bottom / 2), 2);
        // quadrado da soma dos raios
        qr = Math.pow(raioVisao, 2);
        // quadrado da soma dos raios para efeito
        qe = Math.pow(visao.bottom * 1.2, 2);

        if (qx + qy <= qr) {
            if (qx + qy <= qe) {
                switch (efeito) {
                    case EFEITO_CIMA:
                        if (visao.top >= areaVisaoBola.top + areaVisaoBola.bottom) {
                            decisao = DECISAO_SUBIR;
                        } else if (visao.top + areaVisaoBola.bottom < areaVisaoBola.top + areaVisaoBola.bottom) {
                            decisao = DECISAO_DESCER;
                        }
                        break;

                    case EFEITO_BAIXO:
                        if (visao.top + visao.bottom <= areaVisaoBola.top) {
                            decisao = DECISAO_DESCER;
                        } else if (visao.top + visao.bottom >= areaVisaoBola.top + areaVisaoBola.bottom) {
                            decisao = DECISAO_SUBIR;
                        }
                        break;

                    case EFEITO_SEM:
                    default:
                        if (visao.top > areaVisaoBola.top) {
                            decisao = DECISAO_SUBIR;
                        } else if (visao.top + visao.bottom < areaVisaoBola.top + areaVisaoBola.bottom) {
                            decisao = DECISAO_DESCER;
                        }

                        break;
                }
            } else {
                if (visao.top > areaVisaoBola.top) {
                    decisao = DECISAO_SUBIR;
                } else if (visao.top + visao.bottom < areaVisaoBola.top + areaVisaoBola.bottom) {
                    decisao = DECISAO_DESCER;
                }
            }
        }

        return decisao;
    }

    public void iniciarVisao()
    {
        raioVisao = (int) PX.size.TELA_WIDTH() / 3;
    }

    public void aumentarVisao()
    {
        raioVisao += 10;

        if (raioVisao >= PX.size.TELA_HEIGHT()) {
            raioVisao = PX.size.TELA_HEIGHT();
        }
    }

    public boolean isColisao(Entidade personagem)
    {
        boolean retorno = super.isColisao(personagem);

        if (retorno) {
            efeito = Converter.random(EFEITO_RANDOM);
        }

        return retorno;
    }

    @Override
    public void draw(Canvas canvas)
    {
        Point p = getPosicao();
        
        paint.setColor(Color.GREEN);
        canvas.drawCircle(p.x, p.y, raioVisao, paint);

        paint.setColor(Color.RED);
        canvas.drawCircle(p.x, p.y, (int) (size.height() * 1.2), paint);

        paint.setColor(Color.WHITE);
        canvas.drawText(p.x+"x"+p.y, p.x, p.y+20, paint);
        
        super.draw(canvas);
    }

}
