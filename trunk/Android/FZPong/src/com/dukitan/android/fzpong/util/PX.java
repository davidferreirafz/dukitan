package com.dukitan.android.fzpong.util;

public class PX
{
    static public UnidadePixel size;

    public static void set(int resolucao)
    {
        if (resolucao >= 800) {
            size = new W800px();
        } else if (resolucao == 480) {
            size = new W480px();
        } else {
            size = new W320px();
        }
    }
}
