package com.dukitan.android.fzpong.entidade.bola;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import com.dukitan.android.framework.Input;
import com.dukitan.android.framework.Size;
import com.dukitan.android.framework.Util;
import com.dukitan.android.fzpong.entidade.Bloqueavel;
import com.dukitan.android.fzpong.entidade.Entidade;
import com.dukitan.android.fzpong.entidade.EntidadeManager;
import com.dukitan.android.fzpong.entidade.parede.Parede;
import com.dukitan.android.fzpong.util.PX;
import com.dukitan.android.math.Vector2D;

public class Bola extends Entidade
{
    public static final int CENTRO_VERTICAL   = PX.size.BOLA_WIDTH() / 2;
    public static final int CENTRO_HORIZONTAL = PX.size.BOLA_WIDTH() / 2;
    private Vector2D        dir;
    private float           speed             = 100f;
    private int             batidaParede      = 0;

    public Bola(Bitmap imagem)
    {
        super(new Rect(0, 0, PX.size.BOLA_WIDTH(), PX.size.BOLA_WIDTH()), imagem);
        dir = new Vector2D(1, 1);

    }

    @Override
    public void draw(Canvas canvas)
    {
        canvas.save();
        // sprite.draw((int) posicao.getX(), (int) posicao.getY(), canvas);
        sprite.draw(getPosicao(), canvas);
        canvas.restore();
    }

    private void move(double elapsed)
    {// tem tratamento de colisao aqui!!! causa o problema da parede nao blinkar
        posicao = posicao.plusMe(dir.multiply((float) (speed * elapsed)));
        /*
         * if (posicao.getY() <= PX.size.TELA_TOP()) {
         * posicao.setY(PX.size.TELA_TOP() + size.h()); dir = new
         * Vector2D(dir.getX(), 1); } else if (posicao.getY() + size.h() >=
         * PX.size.TELA_BOTTOM()) { posicao.setY(PX.size.TELA_BOTTOM() -
         * size.h()); dir = new Vector2D(dir.getX(), -1); }
         * 
         * if (posicao.getX() <= PX.size.TELA_LEFT()) {
         * posicao.setX(PX.size.TELA_LEFT() + size.w()); dir = new Vector2D(1,
         * dir.getY()); } else if (posicao.getX() + size.w() >=
         * PX.size.TELA_RIGHT()) { posicao.setX(PX.size.TELA_RIGHT() -
         * size.w()); dir = new Vector2D(-1, dir.getY()); }
         */
    }

    @Override
    public void update(Input input)
    {

        for (Entidade e : EntidadeManager.getInstance().getEntidades()) {
            if (e == this)
                continue;
            if (e instanceof Bloqueavel) {

                if (getRect().intersect(e.getRect())) {

                    Vector2D n = ((Bloqueavel) e).getNormal();
                    dir = dir.minus(n.multiply(2).multiply(dir.dot(n)));

                    if (e instanceof Parede) {
                        batidaParede++;
                        Parede p = (Parede) e;
                        p.notificar();

                        corrigirPosicao(p);
                    }

                    break;
                }
            }
        }
        move(input.getTime());

        /*
         * point.x+=int(velocidade.x); point.y+=int(velocidade.y);
         * 
         * if (point.y+getDimension().h>=getAreaTela().bottom){ velocidade.y = -
         * velocidade.y; point.y=getAreaTela().bottom-getDimension().h;
         * soundSystem->fxManager->playPanEffect("ping",point.x);
         * batidaParede++; } else if (point.y<=getAreaTela().top){ velocidade.y
         * = - velocidade.y; point.y=getAreaTela().top;
         * soundSystem->fxManager->playPanEffect("ping",point.x);
         * batidaParede++; }
         * 
         * if (point.x>=getAreaTela().right/2){
         * getMainSprite()->setDirection(GBF::Image::Sprite::DR_DIREITA); } else
         * { getMainSprite()->setDirection(GBF::Image::Sprite::DR_ESQUERDA); }
         */
        if (batidaParede > 4) {
            elevarGrauDificuldade();
            batidaParede = 0;
        }

    }

    public void iniciar()
    {
        batidaParede = 0;

        speed = 0f;

        continuar();
    }

    public void iniciar(Point saque)
    {
        batidaParede = 0;
        // velocidadeGradativa.y = 4 + rand() % 4;
        // velocidadeGradativa.x = 4 + rand() % 4;

        checkarVelocidade();

        setPosicao(saque.x, saque.y + getSize().h() / 2);
    }

    public void continuar()
    {
        batidaParede = 0;

        speed *= 1 + Util.random(9);

        checkarVelocidade();

        setPosicao((PX.size.TELA_WIDTH() / 2) - (getSize().w() / 2), PX.size.TELA_HEIGHT() / 2 - (getSize().h() / 2));
    }

    public boolean isColisao(Entidade personagem)
    {
        boolean colisao = false;// personagem.isCollision(this);

        if (colisao) {
            // soundSystem->fxManager->playPanEffect("raquete",point.x);

            int raquete = personagem.getPosicao().y;
            int areaBaixo = personagem.getSize().h() - 20;
            int areaCima = 20;
            int bola = getPosicao().y;

            // bateu em baixo
            if (bola >= raquete + areaBaixo) {
                // corrigirEixoX(personagem);
                // bateu em cima
            } else if (bola <= raquete + areaCima) {
                // corrigirEixoX(personagem);
                // bateu no meio
            } else {
                // corrigirEixoX(personagem);
            }
            batidaParede++;
        }

        return colisao;
    }

    private void elevarGrauDificuldade()
    {
        // velocidadeGradativa.y += 1 + rand() % 3;
        // velocidadeGradativa.x += 1 + rand() % 2;

        speed *= ((1 + Util.random(9)) / 10) + 1;

        checkarVelocidade();
    }

    private void checkarVelocidade()
    {

    }

    // Corrigir a posição da bola após colidir com uma raquete, evitando que a
    // bola seja desenha dentro/após a raquete
    private void corrigirPosicao(Parede parede)
    {
        Size size = getSize();

        switch (parede.getTipo()) {
            case Parede.PAREDE_TOP:
                if (posicao.getY() <= PX.size.TELA_TOP()) {
                    posicao.setY(PX.size.TELA_TOP() + size.h());
                    dir = new Vector2D(dir.getX(), 1);
                }
                break;

            case Parede.PAREDE_BOTTOM:
                if (posicao.getY() + size.h() >= PX.size.TELA_BOTTOM()) {
                    posicao.setY(PX.size.TELA_BOTTOM() - size.h());
                    dir = new Vector2D(dir.getX(), -1);
                }
                break;

            case Parede.PAREDE_LEFT:
                if (posicao.getX() <= PX.size.TELA_LEFT()) {
                    posicao.setX(PX.size.TELA_LEFT() + size.w());
                    dir = new Vector2D(1, dir.getY());
                }
                break;

            case Parede.PAREDE_RIGHT:
                if (posicao.getX() + size.w() >= PX.size.TELA_RIGHT()) {
                    posicao.setX(PX.size.TELA_RIGHT() - size.w());
                    dir = new Vector2D(-1, dir.getY());
                }
                break;
                
            default:
                break;
        }
    }
}
