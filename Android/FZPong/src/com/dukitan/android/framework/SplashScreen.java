package com.dukitan.android.framework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dukitan.android.fzpong.FZPongActivity;
import com.dukitan.android.fzpong.R;

public class SplashScreen extends Activity implements Runnable
{

    ProgressBar      progressBar;
    int              contagem     = 0;
    Handler          handler;
    Thread           thread;

    String           compativel[] = { "320x240", "800x480" };
    private TextView deviceAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        progressBar = (ProgressBar) findViewById(R.id.splashProgressBar);
        deviceAlert = (TextView) findViewById(R.id.deviceAlert);

        handler = new Handler() {

            @Override
            public void handleMessage(Message msg)
            {
                contagem++;
                progressBar.setProgress(contagem);
            }
        };

        thread = new Thread(this);

        thread.start();
    }

    public void run()
    {
        boolean deviceSuportado = false;
        
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        
        String detectada = metrics.widthPixels + "x" + metrics.heightPixels;

        Log.i("SplashScreen", detectada);
        Log.i("SplashScreen", "density:"+metrics.density);        
        Log.i("SplashScreen", "xdpi:"+metrics.xdpi+" ydpi"+metrics.ydpi);
        Log.i("SplashScreen", "scaledDensity:"+metrics.scaledDensity+" densityDpi:"+metrics.densityDpi);

        for (String s : compativel) {

            if (detectada.equals(s)) {
                deviceSuportado = true;
                break;
            }
        }

        if (!deviceSuportado) {
            deviceAlert.setVisibility(View.VISIBLE);
        }

        while (contagem < 100) {
            try {
                handler.sendMessage(handler.obtainMessage());
                Thread.sleep(25);
            } catch (Throwable t) {
            }
        }

        if (deviceSuportado) {
            startActivity(new Intent(this, FZPongActivity.class));
        }

        thread.interrupt();
        finish();

    }

}
