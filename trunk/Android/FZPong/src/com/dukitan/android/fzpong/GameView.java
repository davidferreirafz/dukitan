package com.dukitan.android.fzpong;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.dukitan.android.fzpong.entidade.Bola;
import com.dukitan.android.fzpong.entidade.Raquete;
import com.dukitan.android.fzpong.entidade.RaqueteCPU;
import com.dukitan.android.fzpong.entidade.RaqueteJogador;

public class GameView extends View implements Runnable {

	private Handler handler;
	private Bitmap background;
	private Bitmap sprites;

	private Paint pB;
	private EntidadeManager manager;
	private Raquete raqueteCPU;
	private Raquete raqueteJogador;
	private Bola bola;

	public GameView(Context context) {
		super(context);
		init();
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {

		manager = new EntidadeManager();

		background = BitmapFactory.decodeResource(getResources(),
				R.drawable.background);

		sprites = BitmapFactory.decodeResource(getResources(),
				R.drawable.sprites);

		pB = new Paint();
		pB.setColor(Color.WHITE);

		/*
		 * Vector2D normal = new Vector2D(0, -1); Rect r = new Rect(0, 0,
		 * background.getWidth(), 6); manager.add(new Parede(r, normal));
		 * 
		 * normal = new Vector2D(0, 1);// ou inverter r = new Rect(0,
		 * background.getHeight() - 6, background.getWidth(),
		 * background.getHeight()); manager.add(new Parede(r, normal));
		 */

		raqueteCPU = new RaqueteCPU(sprites);
		raqueteCPU.setPosicao(0, 200);

		raqueteJogador = new RaqueteJogador(sprites);
		raqueteJogador.setPosicao(784, 200);

		bola = new Bola(sprites);
		bola.setPosicao(400, 200);
	}

	// F6 + F2
	public void onDraw(Canvas canvas) {
		canvas.save();

		canvas.drawBitmap(background, 0, 0, null);

		raqueteCPU.draw(canvas);
		raqueteJogador.draw(canvas);
		bola.draw(canvas);

		// manager.draw(canvas);
		canvas.drawText("w:" + String.valueOf(getWidth()), getWidth() - 80, 10,
				pB);
		canvas.drawText("h:" + String.valueOf(getHeight()), 10,
				getHeight() - 40, pB);

		canvas.drawText("0", 400, 240, pB);
		canvas.drawText(
				"D:"
						+ String.valueOf(getResources().getDisplayMetrics().densityDpi),
				400, 260, pB);

		canvas.restore();
	}

	public void run() {
		while (true) {
			try {
				manager.processAI();

				// invalidate();
				Message msg = new Message();
				msg.what = 1;
				handler.sendMessage(msg);

				Thread.sleep(10);
			} catch (Exception e) {
			}
		}
	}

	public void setCallbackHandler(Handler guiRefresher) {
		this.handler = guiRefresher;
	}

	public boolean onTouchEvent(MotionEvent evt) {

		int y = (int) evt.getY();

		raqueteJogador.setPosicao(784, y - 40);

		return true;
	}
}