package com.dukitan.android.framework;

import android.content.Context;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.View;


public abstract class ControlAdMob extends ControlState
{

    private View adView;

    public ControlAdMob(SurfaceHolder surfaceHolder, Context context, Handler handler)
    {
        super(surfaceHolder, context, handler);
    }

    public void setAdView(View adView)
    {
        this.adView = adView;
    }

    protected void entryStateWin()
    {
        adView.setVisibility(View.VISIBLE);
    }

    protected void entryStateLose()
    {
        adView.setVisibility(View.VISIBLE);
    }

    protected void entryStatePause()
    {
        adView.setVisibility(View.VISIBLE);
    }

    protected void entryStateReady()
    {
        adView.setVisibility(View.VISIBLE);
    }

    protected void entryStateRunning()
    {
        adView.setVisibility(View.INVISIBLE);
    }

}
