package com.dukitan.android.framework;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.TextView;

public abstract class ControlThread extends Thread
{

    protected SurfaceHolder mSurfaceHolder;
    protected Handler       mHandler;
    protected Context       mContext;

    /** Indicate whether the surface has been created & is ready to draw */
    private boolean         mRun = false;

    /** Pointer to the text view to display "Paused.." etc. */
    protected TextView      statusMessage;

    protected int           width;
    protected int           height;

    private Thread          thread;

    public ControlThread(SurfaceHolder surfaceHolder, Context context, Handler handler)
    {
        mSurfaceHolder = surfaceHolder;
        mHandler = handler;
        mContext = context;
    }

    public void setTextView(TextView textView)
    {
        statusMessage = textView;
    }

    public void setViewSize(int width, int height)
    {
        this.width = width;
        this.height = height;
        Log.i(getClass().getName(),"size:"+width+"x"+height);
    }

    protected void entryStateLoading()
    {

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

    protected void doRun(Canvas c)
    {
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
            Canvas canvas = null;
            try {
                canvas = mSurfaceHolder.lockCanvas(null);
                synchronized (mSurfaceHolder) {

                    doRun(canvas);

                }
            } finally {
                // do this in a finally so that if an exception is thrown
                // during the above, we don't leave the Surface in an
                // inconsistent state
                if (canvas != null) {
                    mSurfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
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
            // setState(STATE_PAUSE);
            onRestoreState(savedState);
        }
    }

    protected void onRestoreState(Bundle savedState)
    {
    }

    public Bundle onSaveInstanceState(Bundle map)
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

    public void setMessage(Message message)
    {
        statusMessage.setVisibility(message.getData().getInt("viz"));
        statusMessage.setText(message.getData().getString("text"));
    }

    public void doPauseThread()
    {
        boolean retry = true;
        setRunning(false);
        Log.w("doPauseThread", "state:" + thread.getState());
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                Log.w("doPauseThread", e.getMessage());
            }
        }
    }

    public void doStartThread()
    {
        setRunning(true);
        try {
            if (thread != null) {
                Log.w("doStartThread", "state:" + thread.getState());
            }
            if (!isAlive()) {
                thread = new Thread(this);
                thread.start();
            }
        } catch (Exception e) {
            Log.w("doStartThread", e.getMessage());
        }
    }

    public void doStopThread()
    {
        setRunning(true);
        try {
            Log.w("doStopThread", "state:" + thread.getState());
            if (!isAlive()) {
                thread.interrupt();
            }
        } catch (Exception e) {
            Log.w("doStopThread", e.getMessage());
        }
    }
}
