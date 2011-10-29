package com.dukitan.android.fzpong;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
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

    private Bitmap          background;
    private Bitmap          sprites;
    private Input           input;

    private EntidadeManager manager;
    private Resources       res;

    public Controle(SurfaceHolder surfaceHolder, Context context, Handler handler)
    {
        super(surfaceHolder, context, handler);

        res = context.getResources();
        input = new Input();

    }

    protected void entryStateLoading()
    {
        Log.i(getClass().getName(), "entryStateLoading");
        background = BitmapFactory.decodeResource(res, R.drawable.background);
        sprites = BitmapFactory.decodeResource(res, R.drawable.sprites);

        manager = EntidadeManager.getInstance();
        manager.clear();

        Parede left = new Parede(new Rect(0, 0, 10, height), null, new Vector2D(1, 0));
        left.setPosicao(0, 0);
        manager.add(left);

        Parede right = new Parede(new Rect(0, 0, 10, height), null, new Vector2D(-1, 0));
        right.setPosicao(width - 10, 0);
        manager.add(right);

        Parede top = new Parede(new Rect(0, 0, width, 10), null, new Vector2D(0, 1));
        top.setPosicao(0, 0);
        manager.add(top);

        Parede bottom = new Parede(new Rect(0, 0, width, 10), null, new Vector2D(0, -1));
        bottom.setPosicao(0, height - 10);
        manager.add(bottom);

        Raquete raqueteCPU = new RaqueteCPU(sprites);
        raqueteCPU.setPosicao();
        manager.add(raqueteCPU);

        Raquete raqueteJogador = new RaqueteJogador(sprites);
        raqueteJogador.setPosicao();
        manager.add(raqueteJogador);

        Bola bola = new Bola(sprites);
        bola.setPosicao(width / 2, height / 2);
        manager.add(bola);

        setState(Controle.STATE_READY);
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
