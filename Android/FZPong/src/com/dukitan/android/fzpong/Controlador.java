package com.dukitan.android.fzpong;

import com.dukitan.android.fzpong.entidade.bola.Bola;
import com.dukitan.android.fzpong.entidade.raquete.Raquete;
import com.dukitan.android.fzpong.entidade.raquete.RaqueteCPU;
import com.dukitan.android.fzpong.entidade.raquete.RaqueteJogador;

public class Controlador
{

    private Placar    placar;

    private Bola      bola;

    private Raquete   raqueteJogador;

    private Raquete   raqueteCPU;

    // GBF::Image::Layer::FrameLayer * cenario;

    // GBF::Kernel::Write::WriteManager * wsManager;

    // Diferença para o fim da partida
    final private int DIFERENCA_FIM_PARTIDA     = 5;

    // Diferença para determinar fim da prorrogação
    final private int DIFERENCA_FIM_PRORROGACAO = 2;

    // Limite para contar a prorrogação
    final private int LIMITE_PARA_PRORROGACAO   = 10;

    // Diferença de sets para determinar o ganhador
    final private int DIFERENCA_SET_VITORIA     = 3;

    public Controlador()
    {
        // cenario =
        // GBF::Image::Layer::LayerManager::getInstance()->getFrameLayer("background");
        // wsManager = GBF::Kernel::Write::WriteManager::getInstance();

        raqueteJogador = new RaqueteJogador(null);
        raqueteCPU = new RaqueteCPU(null);

        raqueteJogador.setLado(Raquete.LADO_DIREITO);
        raqueteCPU.setLado(Raquete.LADO_ESQUERDO);
    }

    protected void juiz()
    {
        /*
         * if (bola.getPosicao().x>=cenario->getArea().right){
         * placar.pontuarCPU(); raqueteCPU.preparar();
         * raqueteJogador.preparar();
         * 
         * bola.iniciar(raqueteCPU.saque()); } else if
         * (bola.getPosicao().x+bola.getSize().w()<=cenario->getArea().left){
         * placar.pontuarJogador(); raqueteJogador.preparar();
         * raqueteCPU.preparar();
         * 
         * bola.iniciar(raqueteJogador.saque()); }
         */
    }

    protected void setFinalizado()
    {
    }
}
