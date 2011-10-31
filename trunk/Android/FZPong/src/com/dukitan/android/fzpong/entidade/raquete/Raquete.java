package com.dukitan.android.fzpong.entidade.raquete;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import com.dukitan.android.framework.Size;
import com.dukitan.android.fzpong.entidade.Bloqueavel;
import com.dukitan.android.fzpong.entidade.Entidade;
import com.dukitan.android.fzpong.entidade.bola.Bola;
import com.dukitan.android.fzpong.util.PX;
import com.dukitan.android.math.Vector2D;

public abstract class Raquete extends Bloqueavel
{
    // Lado direito da Tela
    public static final int LADO_DIREITO  = 1;
    // Lado esquerdo da Tela
    public static final int LADO_ESQUERDO = 0;

    private int             velocidade;
    private int             lado;
    private static Bola     visaoBola;

    public Raquete(Rect rect, Bitmap imagem, Vector2D vetor)
    {
        super(rect, imagem, vetor);
        velocidade = 10;
    }

    // Inicia raquete
    public void iniciar()
    {
        preparar();
    }

    public void preparar()
    {
        // Se for do lado direito da tela
        if (lado == LADO_DIREITO) {
            setPosicao(PX.size.TELA_WIDTH() - getSize().w(), (PX.size.TELA_HEIGHT() / 2) - (getSize().h() / 2));
            // Se for do lado esquerdo da tela
        } else {
            setPosicao(0, (PX.size.TELA_HEIGHT() / 2) - (getSize().h() / 2));
        }
    }

    abstract public Point saque();

    public static void setBola(Bola bola)
    {
        visaoBola = bola;
    }

    public void setLado(int lado)
    {
        this.lado = lado;
    }

    @Override
    public void draw(Canvas canvas)
    {
        canvas.save();
        // sprite.draw((int) posicao.getX(), (int) posicao.getY(), canvas);
        sprite.draw(getPosicao(), canvas);
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

    public int getVelocidade()
    {
        return velocidade;
    }

    public void setVelocidade(int valor)
    {
        velocidade = valor;
    }

    protected void subir()
    {
        Point p = getPosicao();

        p.y = p.y - getVelocidade();

        if (p.y <= PX.size.TELA_TOP()) {
            p.y = PX.size.TELA_TOP();
        }

        setPosicao(p.x, p.y);
    }

    protected void descer()
    {
        Point p = getPosicao();

        p.y = p.y + getVelocidade();

        if (p.y + getSize().h() >= PX.size.TELA_BOTTOM()) {
            p.y = PX.size.TELA_BOTTOM() - getSize().h();
        }

        setPosicao(p.x, p.y);
    }

    protected boolean isBateuParede()
    {
        boolean bateu = false;

        Point p = getPosicao();
        p.y = p.y + getVelocidade();

        if ((p.y + getSize().h() >= PX.size.TELA_BOTTOM()) || (p.y <= PX.size.TELA_TOP())) {
            bateu = true;
        }

        return bateu;
    }

    public boolean isColisao(Entidade personagem)
    {
        boolean retorno = false;

        Point point = getPosicao();
        Point perPoint = personagem.getPosicao();
        Size size = getSize();
        Size perSize = personagem.getSize();

        // Se raquete no lado direito da tela
        if (lado == LADO_DIREITO) {
            if ((point.x + size.w() >= perPoint.x) && (point.x <= perPoint.x + perSize.w()) && (point.y + size.h() >= perPoint.y)
                    && (point.y <= perPoint.y + perSize.h())) {
                retorno = true;
            }
            // Se raquete no lado esquerdo da tela
        } else {
            if ((point.x + size.w() >= perPoint.x) && (point.x <= perPoint.x + perSize.w()) && (point.y + size.h() >= perPoint.y)
                    && (point.y <= perPoint.y + perSize.h())) {
                retorno = true;
            }
        }

        return retorno;
    }

    protected Bola getVisaoBola()
    {
        return visaoBola;
    }
}
