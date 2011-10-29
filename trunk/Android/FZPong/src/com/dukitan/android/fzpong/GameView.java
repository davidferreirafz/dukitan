package com.dukitan.android.fzpong;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.dukitan.android.fzpong.util.PX;

public class GameView extends SurfaceView implements SurfaceHolder.Callback
{

    private Controle controle;

    public GameView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        // create thread only; it's started in surfaceCreated()
        controle = new Controle(holder, context, new Handler() {
            @Override
            public void handleMessage(Message m)
            {
                controle.setMessage(m);
            }
        });

        setFocusable(true); // make sure we get key events
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
        controle.setSurfaceSize(width, height);
        controle.setViewSize(width, height);

        PX.set(width);

        controle.setState(Controle.STATE_LOADING);
    }

    public void surfaceCreated(SurfaceHolder holder)
    {
        controle.setRunning(true);
        controle.start();

        Log.i(getClass().getName(), "isAlive:" + controle.isAlive());
        Log.i(getClass().getName(), "isInterrupted:" + controle.isInterrupted());
        Log.i(getClass().getName(), "isDaemon:" + controle.isDaemon());
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus)
    {
        if (!hasWindowFocus) {
            if (controle != null) {
                controle.doPause();
                controle.interrupt();

                Log.i(getClass().getName(), "isAlive:" + controle.isAlive());
                Log.i(getClass().getName(), "isInterrupted:" + controle.isInterrupted());
                Log.i(getClass().getName(), "isDaemon:" + controle.isDaemon());

            }

        }

    }

    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        controle.setRunning(false);
        while (retry) {
            try {
                controle.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    public void onStart()
    {
        boolean retry = true;
        controle.setRunning(true);
        while (retry) {
            try {
                controle.start();
                retry = false;
            } catch (Exception e) {
                Log.i("onStart", e.getMessage());
            }
        }
    }

    public void onResume()
    {
        boolean retry = true;
        controle.setRunning(true);

        while (retry) {
            try {
                controle.resume();
                retry = false;
            } catch (Exception e) {
                Log.i("onResume", e.getMessage());
            }
        }
    }

    public void onPause()
    {
        boolean retry = true;
        controle.setRunning(false);
        while (retry) {
            try {
                controle.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return controle.doTouchEvent(event);
    }

    public Controle getThread()
    {
        return controle;
    }

}