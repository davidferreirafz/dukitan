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

    private Game controle;

    public GameView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        // create thread only; it's started in surfaceCreated()
        controle = new Game(holder, context, new Handler() {
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

        controle.setState(Game.STATE_LOADING);
    }

    public void surfaceCreated(SurfaceHolder holder)
    {
        Log.i(getClass().getName(), "surfaceCreated");          
        controle.doStartThread();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus)
    {
        Log.i(getClass().getName(), "onWindowFocusChanged");        
        if (!hasWindowFocus) {
            if (controle != null) {
                controle.doPause();
                controle.interrupt();
            }
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder)
    {
        Log.i(getClass().getName(), "surfaceDestroyed");
        controle.doPauseThread();
    }

    public void onStart()
    {
        Log.i(getClass().getName(), "onStart");      
        //nada a fazer
    }

    public void onResume()
    {
        Log.i(getClass().getName(), "onResume");
        controle.doResume();
        controle.doStartThread();
    }

    public void onPause()
    {
        Log.i(getClass().getName(), "onPause");
        controle.doPause();
        controle.doPauseThread();
    }

    public void onStop()
    {
        Log.i(getClass().getName(), "onStop");
        controle.doStop();
        controle.doPauseThread();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return controle.onTouchEvent(event);
    }

    public Game getControl()
    {
        Log.i(getClass().getName(), "getControl");
        return controle;
    }

}