package com.dukitan.android.fzpong;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.dukitan.android.framework.Input;
import com.dukitan.android.fzpong.entidade.bola.Bola;
import com.dukitan.android.fzpong.entidade.parede.Parede;
import com.dukitan.android.fzpong.entidade.raquete.Raquete;
import com.dukitan.android.fzpong.entidade.raquete.RaqueteCPU;
import com.dukitan.android.fzpong.entidade.raquete.RaqueteJogador;
import com.dukitan.android.math.Vector2D;

public class GameView extends View implements Runnable
{

    private Handler         handler;
    private Bitmap          background;
    private Bitmap          sprites;

    private EntidadeManager manager;
    private Input           input;

    public GameView(Context context)
    {
        super(context);
        init();
    }

    public GameView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public GameView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init();
    }

    private void init()
    {
        input = new Input();

        manager = EntidadeManager.getInstance();

        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);

        sprites = BitmapFactory.decodeResource(getResources(), R.drawable.sprites);

        Parede pT = new Parede(new Rect(0, 0, 800, 10), null, new Vector2D(0, 1));
        pT.setPosicao(0, 0);
        manager.add(pT);

        Parede pB = new Parede(new Rect(0, 0, 800, 10), null, new Vector2D(0, -1));
        pB.setPosicao(0, 470);
        manager.add(pB);

        Parede pL = new Parede(new Rect(0, 0, 10, 480), null, new Vector2D(1, 0));
        pL.setPosicao(0, 0);
        manager.add(pL);

        Parede pR = new Parede(new Rect(0, 0, 10, 480), null, new Vector2D(-1, 0));
        pR.setPosicao(790, 0);
        manager.add(pR);

        Raquete raqueteCPU = new RaqueteCPU(sprites);
        raqueteCPU.setPosicao(0, 400);
        manager.add(raqueteCPU);

        Raquete raqueteJogador = new RaqueteJogador(sprites);
        raqueteJogador.setPosicao(Raquete.LADO_DIREITO, 240);
        manager.add(raqueteJogador);

        Bola bola = new Bola(sprites);
        bola.setPosicao(Raquete.LADO_ESQUERDO, 200);
        manager.add(bola);
    }

    // F6 + F2
    public void onDraw(Canvas canvas)
    {
        canvas.save();

        canvas.drawBitmap(background, 0, 0, null);

        manager.draw(canvas);

        canvas.restore();
    }

    public void run()
    {
        while (true) {
            try {
                manager.update(input);

                // invalidate();
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);

                Thread.sleep(10);
            } catch (Exception e) {
            }
        }
    }

    public void setCallbackHandler(Handler guiRefresher)
    {
        this.handler = guiRefresher;
    }

    public boolean onTouchEvent(MotionEvent evt)
    {
        input.setMotionEvent(evt);

        return super.onTouchEvent(evt);
    }

    public boolean onKeyDown(int keyCode, KeyEvent msg)
    {
        input.setKeyEvent(msg);

        // if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {

        return super.onKeyDown(keyCode, msg);
    }

    @Override
    protected void finalize() throws Throwable
    {
        super.finalize();

        handler = null;
        background = null;
        sprites = null;

        input = null;

        manager.clear();
        manager = null;
    }
}