package com.dukitan.android.fzpong.util;

public class W800px implements UnidadePixel
{
    static private final int PAREDE            = 10;

    static private final int TELA_WIDTH        = 800;
    static private final int TELA_HEIGHT       = 480;

    static private final int TELA_TOP          = 0 + PAREDE;
    static private final int TELA_BOTTOM       = TELA_HEIGHT - PAREDE;
    static private final int TELA_LEFT         = 0 + PAREDE;
    static private final int TELA_RIGHT        = TELA_WIDTH - PAREDE;

    static private final int BOLA_WIDTH        = 20;
    static private final int BOLA_HEIGHT       = 20;

    static private final int RAQUETE_WIDTH     = 14;
    static private final int RAQUETE_HEIGHT    = 80;

    static private final int RAQUETE_CPU_X     = 15;
    static private final int RAQUETE_CPU_Y     = 21;

    static private final int RAQUETE_JOGADOR_X = 0;
    static private final int RAQUETE_JOGADOR_Y = 21;

    public int TELA_TOP()
    {
        return TELA_TOP;
    }

    public int TELA_BOTTOM()
    {
        return TELA_BOTTOM;
    }

    public int TELA_LEFT()
    {
        return TELA_LEFT;
    }

    public int TELA_RIGHT()
    {
        return TELA_RIGHT;
    }

    public int TELA_WIDTH()
    {
        return TELA_WIDTH;
    }

    public int TELA_HEIGHT()
    {
        return TELA_HEIGHT;
    }

    public int BOLA_WIDTH()
    {
        return BOLA_WIDTH;
    }

    public int BOLA_HEIGHT()
    {
        return BOLA_HEIGHT;
    }

    public int RAQUETE_WIDTH()
    {
        return RAQUETE_WIDTH;
    }

    public int RAQUETE_HEIGHT()
    {
        return RAQUETE_HEIGHT;
    }

    public int RAQUETE_CPU_X()
    {
        return RAQUETE_CPU_X;
    }

    public int RAQUETE_CPU_Y()
    {
        return RAQUETE_CPU_Y;
    }

    public int RAQUETE_JOGADOR_X()
    {
        return RAQUETE_JOGADOR_X;
    }

    public int RAQUETE_JOGADOR_Y()
    {
        return RAQUETE_JOGADOR_Y;
    }

    public int PAREDE()
    {
        return PAREDE;
    }
}
