package com.dukitan.android.framework;

import java.util.Random;

import android.graphics.Point;
import android.graphics.Rect;

public class Converter
{
    public static Rect converterArea(Rect dimensao, Point ponto)
    {
        Rect area = new Rect();
        area.left = ponto.x;
        area.top = ponto.y;
        area.right = dimensao.right;
        area.bottom = dimensao.bottom;

        return area;
    }

    public static int random(int total)
    {
        Random r = new Random();
        return r.nextInt(total);
    }
}
