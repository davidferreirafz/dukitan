package com.dukitan.android.fzpong;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.dukitan.android.framework.ControlAdMob;
import com.dukitan.android.framework.Input;
import com.dukitan.android.fzpong.entidade.EntidadeManager;
import com.dukitan.android.fzpong.entidade.bola.Bola;
import com.dukitan.android.fzpong.entidade.parede.Parede;
import com.dukitan.android.fzpong.entidade.raquete.Raquete;
import com.dukitan.android.fzpong.entidade.raquete.RaqueteCPU;
import com.dukitan.android.fzpong.entidade.raquete.RaqueteJogador;
import com.dukitan.android.math.Vector2D;

public class Controle extends ControlAdMob
{

    final static private int SCREEN_WIDTH  = 800;
    final static private int SCREEN_HEIGHT = 480;

    private Bitmap           background;
    private Bitmap           sprites;
    private Input            input;

    private EntidadeManager  manager;

    public Controle(SurfaceHolder surfaceHolder, Context context, Handler handler)
    {
        super(surfaceHolder, context, handler);

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

    }

    protected void draw(Canvas canvas)
    {
        canvas.drawBitmap(background, 0, 0, null);
        manager.draw(canvas);
        canvas.save();
    }

    protected void update(double elapsed)
    {
        input.setTime(elapsed);
        manager.update(input);
    }

    public boolean doTouchEvent(MotionEvent event)
    {
        synchronized (mSurfaceHolder) {
            boolean okStart = false;

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                okStart = true;
            }

            if (okStart && (getMode() == STATE_READY || getMode() == STATE_LOSE || getMode() == STATE_WIN)) {
                // ready-to-start -> start
                doStart();
                return true;
            } else if (getMode() == STATE_PAUSE && okStart) {
                // paused -> running
                doResume();
                return true;
            } else if (getMode() == STATE_RUNNING) {
                input.setMotionEvent(event);
                return true;
            }

            return false;
        }
    }

}
