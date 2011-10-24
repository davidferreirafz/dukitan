package com.dukitan.android.fzpong;

import com.dukitan.android.framework.Input;
import com.dukitan.android.fzpong.entidade.bola.Bola;
import com.dukitan.android.fzpong.entidade.parede.Parede;
import com.dukitan.android.fzpong.entidade.raquete.Raquete;
import com.dukitan.android.fzpong.entidade.raquete.RaqueteCPU;
import com.dukitan.android.fzpong.entidade.raquete.RaqueteJogador;
import com.dukitan.android.math.Vector2D;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.TextView;

public class Controle extends Thread
{

    /*
     * State-tracking constants
     */
    public static final int  STATE_LOSE    = 1;
    public static final int  STATE_PAUSE   = 2;
    public static final int  STATE_READY   = 3;
    public static final int  STATE_RUNNING = 4;
    public static final int  STATE_WIN     = 5;

    final static private int SCREEN_WIDTH  = 800;
    final static private int SCREEN_HEIGHT = 480;

    private SurfaceHolder    mSurfaceHolder;
    private Handler          mHandler;
    private Context          mContext;

    private Bitmap           background;
    private Bitmap           sprites;
    private Input            input;

    private EntidadeManager  manager;
    private long             mLastTime;

    /** The state of the game. One of READY, RUNNING, PAUSE, LOSE, or WIN */
    private int              mMode;

    /** Indicate whether the surface has been created & is ready to draw */
    private boolean          mRun          = false;
    
    /** Pointer to the text view to display "Paused.." etc. */
    public TextView mStatusText;    

    public Controle(SurfaceHolder surfaceHolder, Context context, Handler handler)
    {
        mSurfaceHolder = surfaceHolder;
        mHandler = handler;
        mContext = context;

        input = new Input();
        manager = EntidadeManager.getInstance();
        manager.clear();

        Resources res = context.getResources();

        background = BitmapFactory.decodeResource(res, R.drawable.background);

        sprites = BitmapFactory.decodeResource(res, R.drawable.sprites);

        Parede pL = new Parede(new Rect(0, 0, 10, 480), null, new Vector2D(1, 0));
        pL.setPosicao(0, 0);
        manager.add(pL);

        Parede pR = new Parede(new Rect(0, 0, 10, 480), null, new Vector2D(-1, 0));
        pR.setPosicao(790, 0);
        manager.add(pR);

        Parede pT = new Parede(new Rect(0, 0, 800, 10), null, new Vector2D(0, 1));
        pT.setPosicao(0, 0);
        manager.add(pT);

        Parede pB = new Parede(new Rect(0, 0, 800, 10), null, new Vector2D(0, -1));
        pB.setPosicao(0, 470);
        manager.add(pB);

        Raquete raqueteCPU = new RaqueteCPU(sprites);
        raqueteCPU.setPosicao(Raquete.LADO_ESQUERDO, SCREEN_HEIGHT / 2 - Raquete.CENTRO_VERTICAL);
        manager.add(raqueteCPU);

        Raquete raqueteJogador = new RaqueteJogador(sprites);
        raqueteJogador.setPosicao(Raquete.LADO_DIREITO, SCREEN_HEIGHT / 2 - Raquete.CENTRO_VERTICAL);
        manager.add(raqueteJogador);

        Bola bola = new Bola(sprites);
        bola.setPosicao(400, 240);
        manager.add(bola);
        
        //provisorio
        mMode = STATE_RUNNING;
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
            } else {

                Resources res = mContext.getResources();
                CharSequence str = "";
                if (mMode == STATE_READY)
                    str = res.getText(R.string.mode_ready);
                else if (mMode == STATE_PAUSE)
                    str = res.getText(R.string.mode_pause);
                else if (mMode == STATE_LOSE)
                    str = res.getText(R.string.mode_lose);
                else if (mMode == STATE_WIN)
                    str = res.getString(R.string.mode_win_prefix) + res.getString(R.string.mode_win_suffix);

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
    public void pause()
    {
        synchronized (mSurfaceHolder) {
            if (mMode == STATE_RUNNING) {
                setState(STATE_PAUSE);
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
            setState(STATE_PAUSE);
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
                        updatePhysics();
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

    public Bundle saveState(Bundle map) {
        synchronized (mSurfaceHolder) {
            if (map != null) {
            }
        }
        return map;
    }    
    private void doDraw(Canvas canvas)
    {
        // Draw the background image. Operations on the Canvas accumulate
        // so this is like clearing the screen.
        canvas.drawBitmap(background, 0, 0, null);

        manager.draw(canvas);
        canvas.save();

    }

    /**
     * Figures the lander state (x, y, fuel, ...) based on the passage of
     * realtime. Does not invalidate(). Called at the start of draw(). Detects
     * the end-of-game and sets the UI to the next state.
     */
    private void updatePhysics()
    {
        long now = System.currentTimeMillis();

        // Do nothing if mLastTime is in the future.
        // This allows the game-start to delay the start of the physics
        // by 100ms or whatever.
        if (mLastTime > now)
            return;

        double elapsed = (now - mLastTime) / 1000.0;

        manager.update(input);

        mLastTime = now;

    }

    /* Callback invoked when the surface dimensions change. */
    public void setSurfaceSize(int width, int height)
    {
        // synchronized to make sure these all change atomically
        synchronized (mSurfaceHolder) {
            // para mudar background
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

    
    public boolean onTouchEvent(MotionEvent event)
    {
//        return super.onTouchEvent(event);
        input.setMotionEvent(event);
        
        return true;
    }    
    /**
     * Handles a key-down event.
     * 
     * @param keyCode
     *            the key that was pressed
     * @param msg
     *            the original event object
     * @return true
     */
    boolean doKeyDown(int keyCode, KeyEvent msg)
    {
        synchronized (mSurfaceHolder) {
            boolean okStart = false;
            if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
                okStart = true;
            }
            if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                okStart = true;
            }
            if (keyCode == KeyEvent.KEYCODE_S) {
                okStart = true;
            }

            if (okStart && (mMode == STATE_READY || mMode == STATE_LOSE || mMode == STATE_WIN)) {
                // ready-to-start -> start
                doStart();
                return true;
            } else if (mMode == STATE_PAUSE && okStart) {
                // paused -> running
                unpause();
                return true;
            } else if (mMode == STATE_RUNNING) {
                input.setKeyEvent(msg);
                return true;
            }

            return false;
        }
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
    public void setRunning(boolean b)
    {
        mRun = b;
    }

}
