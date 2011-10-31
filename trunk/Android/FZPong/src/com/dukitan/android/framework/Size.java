package com.dukitan.android.framework;

import android.graphics.Point;

public class Size implements Cloneable
{

    private int w;
    private int h;

    public Size(int w, int h)
    {
        this.w = w;
        this.h = h;
    }

    public Size(Point point)
    {
        this.w = point.x;
        this.h = point.y;
    }

    public Size(Size size)
    {
        this.w = size.w;
        this.h = size.h;
    }

    @Override
    public Size clone()
    {
        return new Size(this);
    }

    public int w()
    {
        return w;
    }

    public int h()
    {
        return h;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Size other = (Size) obj;
        return w == other.w && h == other.h;
    }

    @Override
    public String toString()
    {
        return String.format("w: %d h: %d", w, h);
    }

}
