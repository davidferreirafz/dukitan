package com.dukitan.android.fzpong.util;

public class PX
{
    static public UnidadePixel size;

    public static void set(int resolucao)
    {
        if (resolucao == 320) {
            size = new W320px();
        } else {
            size = new W800px();
        }
    }
}
