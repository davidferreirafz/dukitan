package com.dukitan.android.framework;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;

import com.dukitan.android.fzpong.R;

public abstract class ControlState extends ControlThread
{

    /*
     * State-tracking constants
     */
    public static final int STATE_LOADING = 0;
    public static final int STATE_LOSE    = 1;
    public static final int STATE_PAUSE   = 2;
    public static final int STATE_READY   = 3;
    public static final int STATE_RUNNING = 4;
    public static final int STATE_WIN     = 5;

    /** The state of the game. One of READY, RUNNING, PAUSE, LOSE, or WIN */
    private int             mMode;

    private long            mLastTime;
    
    public ControlState(SurfaceHolder surfaceHolder, Context context, Handler handler)
    {
        super(surfaceHolder, context, handler);
    }

    protected int getMode()
    {
        return mMode;
    }

   protected void doRun(Canvas c){
       switch (mMode) {
           case STATE_LOADING:
               doLoad();
               break;

           case STATE_RUNNING:
               doUpdate();

           default:
               doDraw(c);
               break;
       }
   }
   
   abstract protected void draw(Canvas canvas);

   protected void doDraw(Canvas canvas)
   {
       draw(canvas);
   }   
    
   
   abstract protected void update(double elapsed);

   protected void doUpdate()
   {

       long now = System.currentTimeMillis();

       // Do nothing if mLastTime is in the future.
       // This allows the game-start to delay the start of the physics
       // by 100ms or whatever.
       if (mLastTime > now)
           return;

       double elapsed = (now - mLastTime) / 1000.0;

       update(elapsed);

       mLastTime = now;
   }
   
    public void setState(int mode)
    {
        synchronized (mSurfaceHolder) {
            setState(mode, null);
        }
    }

    /**
     * Sets the game mode. That is, whether we are running, paused, in the
     * failure state, in the victory state, etc.
     * 
     * @param mode
     *            one of the STATE_* constants
     * @param message
     *            string to add to screen or null
     */
    public void setState(int mode, CharSequence message)
    {
        /*
         * This method optionally can cause a text message to be displayed to
         * the user when the mode changes. Since the View that actually renders
         * that text is part of the main View hierarchy and not owned by this
         * thread, we can't touch the state of that View. Instead we use a
         * Message + Handler to relay commands to the main thread, which updates
         * the user-text View.
         */
        synchronized (mSurfaceHolder) {
            mMode = mode;

            if (mMode == STATE_RUNNING) {

                Message msg = mHandler.obtainMessage();
                Bundle b = new Bundle();
                b.putString("text", "");
                b.putInt("viz", View.INVISIBLE);
                msg.setData(b);
                mHandler.sendMessage(msg);

                entryStateRunning();
            } else {

                Resources res = mContext.getResources();
                CharSequence str = "";
                if (mMode == STATE_READY) {
                    str = res.getText(R.string.mode_ready);
                    entryStateReady();
                } else if (mMode == STATE_PAUSE) {
                    str = res.getText(R.string.mode_pause);
                    entryStatePause();
                } else if (mMode == STATE_LOSE) {
                    str = res.getText(R.string.mode_lose);
                    entryStateLose();
                } else if (mMode == STATE_WIN) {
                    // str = res.getString(R.string.mode_win_prefix) +
                    // res.getString(R.string.mode_win_suffix);
                    entryStateWin();
                } else if (mMode == STATE_LOADING) {
                    Log.i(getClass().getName(), "setState:" + mMode);
                    str = res.getText(R.string.mode_load);
                    entryStateLoading();
                    return;
                    // retornando devido problema de mudança do texto para PAUSE
                    // depois verificar com detalhes como mudar automaticamente
                    // de
                    // loading para ready
                }

                if (message != null) {
                    str = message + "\n" + str;
                }

                Message msg = mHandler.obtainMessage();
                Bundle b = new Bundle();
                b.putString("text", str.toString());
                b.putInt("viz", View.VISIBLE);
                msg.setData(b);
                mHandler.sendMessage(msg);
            }
        }
    }

    public void doLoad()
    {
        synchronized (mSurfaceHolder) {
            mLastTime = System.currentTimeMillis() + 100;
            setState(STATE_LOADING);
        }
    }

    public void doStart()
    {
        synchronized (mSurfaceHolder) {
            mLastTime = System.currentTimeMillis() + 100;
            setState(STATE_RUNNING);
        }
    }

    /**
     * Pauses the physics update & animation.
     */
    public void doPause()
    {
        synchronized (mSurfaceHolder) {
            if (mMode == STATE_RUNNING) {
                setState(STATE_PAUSE);
            }
        }
    }

    /**
     * Resumes from a pause.
     */
    public void doResume()
    {
        // Move the real time clock up to now
        synchronized (mSurfaceHolder) {
            if (mMode == STATE_PAUSE) {
                mLastTime = System.currentTimeMillis() + 100;
                setState(STATE_RUNNING);
            }

        }
    }

    /**
     * Resumes from a pause.
     */
    public void doStop()
    {
        synchronized (mSurfaceHolder) {
            setState(STATE_LOSE);
        }

    }
}
