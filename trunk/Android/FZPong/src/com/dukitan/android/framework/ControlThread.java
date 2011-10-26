package com.dukitan.android.framework;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;

import com.dukitan.android.fzpong.R;

public abstract class ControlThread extends Thread
{

    /*
     * State-tracking constants
     */
    public static final int STATE_LOSE    = 1;
    public static final int STATE_PAUSE   = 2;
    public static final int STATE_READY   = 3;
    public static final int STATE_RUNNING = 4;
    public static final int STATE_WIN     = 5;

    protected SurfaceHolder mSurfaceHolder;
    protected Handler       mHandler;
    protected Context       mContext;

    private long            mLastTime;

    /** Indicate whether the surface has been created & is ready to draw */
    private boolean         mRun          = false;

    /** The state of the game. One of READY, RUNNING, PAUSE, LOSE, or WIN */
    private int             mMode;

    public ControlThread(SurfaceHolder surfaceHolder, Context context, Handler handler)
    {
        mSurfaceHolder = surfaceHolder;
        mHandler = handler;
        mContext = context;
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
                /*
                 * Message msg = mHandler.obtainMessage(); Bundle b = new
                 * Bundle(); b.putString("text", ""); b.putInt("viz",
                 * View.INVISIBLE); msg.setData(b); mHandler.sendMessage(msg);
                 * //adView.setVisibility(View.INVISIBLE);
                 */
                entryStateRunning();
            } else {

                Resources res = mContext.getResources();
                CharSequence str = "";
                if (mMode == STATE_READY) {
                    str = res.getText(R.string.mode_ready);
                    // adView.setVisibility(View.VISIBLE);
                    entryStateReady();
                } else if (mMode == STATE_PAUSE) {
                    str = res.getText(R.string.mode_pause);
                    // adView.setVisibility(View.VISIBLE);
                    entryStatePause();
                } else if (mMode == STATE_LOSE) {
                    str = res.getText(R.string.mode_lose);
                    // adView.setVisibility(View.VISIBLE);
                    entryStateLose();
                } else if (mMode == STATE_WIN) {
                    // str = res.getString(R.string.mode_win_prefix) +
                    // res.getString(R.string.mode_win_suffix);
                    entryStateWin();
                }

                if (message != null) {
                    str = message + "\n" + str;
                }
                /*
                 * Message msg = mHandler.obtainMessage(); Bundle b = new
                 * Bundle(); b.putString("text", str.toString());
                 * b.putInt("viz", View.VISIBLE); msg.setData(b);
                 * mHandler.sendMessage(msg);
                 */
            }
        }
    }

    protected void entryStateWin()
    {
    }

    protected void entryStateLose()
    {
    }

    protected void entryStatePause()
    {
    }

    protected void entryStateReady()
    {
    }

    protected void entryStateRunning()
    {
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
    public void unpause()
    {
        // Move the real time clock up to now
        synchronized (mSurfaceHolder) {
            mLastTime = System.currentTimeMillis() + 100;
        }
        setState(STATE_RUNNING);
    }

    /**
     * Used to signal the thread whether it should be running or not. Passing
     * true allows the thread to run; passing false will shut it down if it's
     * already running. Calling start() after this was most recently called with
     * false will result in an immediate shutdown.
     * 
     * @param b
     *            true to run, false to shut down
     */
    public void setRunning(boolean active)
    {
        mRun = active;
    }

    /* Callback invoked when the surface dimensions change. */
    public void setSurfaceSize(int width, int height)
    {
        // synchronized to make sure these all change atomically
        synchronized (mSurfaceHolder) {
            // para mudar background
        }
    }

    @Override
    public void run()
    {
        while (mRun) {
            Canvas c = null;
            try {
                c = mSurfaceHolder.lockCanvas(null);
                synchronized (mSurfaceHolder) {
                    if (mMode == STATE_RUNNING) {
                        doUpdate();
                    }
                    doDraw(c);
                }
            } finally {
                // do this in a finally so that if an exception is thrown
                // during the above, we don't leave the Surface in an
                // inconsistent state
                if (c != null) {
                    mSurfaceHolder.unlockCanvasAndPost(c);
                }
            }
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

    /**
     * Restores game state from the indicated Bundle. Typically called when the
     * Activity is being restored after having been previously destroyed.
     * 
     * @param savedState
     *            Bundle containing the game state
     */
    public synchronized void restoreState(Bundle savedState)
    {
        synchronized (mSurfaceHolder) {
            setState(STATE_PAUSE);
            onRestoreState(savedState);
        }
    }

    protected void onRestoreState(Bundle savedState)
    {
    }

    public Bundle saveState(Bundle map)
    {
        synchronized (mSurfaceHolder) {
            if (map != null) {
                onSaveState(map);
            }
        }
        return map;
    }

    protected void onSaveState(Bundle map)
    {
    }
    
    protected int getMode(){
        return mMode;
    }
}
