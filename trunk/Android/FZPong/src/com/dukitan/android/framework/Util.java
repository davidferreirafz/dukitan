package com.dukitan.android.framework;

import java.util.Random;

import android.graphics.Point;
import android.graphics.Rect;

public class Util
{
    public static Rect montarArea(Size size, Point ponto)
    {
        Rect area = new Rect();
        area.left = ponto.x;
        area.top = ponto.y;
        area.right = size.w();
        area.bottom = size.h();

        return area;
    }

    public static int random(int total)
    {
        Random r = new Random();
        return r.nextInt(total);
    }
}
