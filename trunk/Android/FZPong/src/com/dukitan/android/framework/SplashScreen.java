package com.dukitan.android.framework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;

import com.dukitan.android.fzpong.FZPongActivity;
import com.dukitan.android.fzpong.R;

public class SplashScreen extends Activity implements Runnable
{

    ProgressBar progressBar;
    int         contagem = 0;
    Handler     handler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        progressBar = (ProgressBar) findViewById(R.id.splashProgressBar);

        handler = new Handler() {

            @Override
            public void handleMessage(Message msg)
            {
                contagem++;
                progressBar.setProgress(contagem);
            }
        };
        
        new Thread(this).start();
    }

    public void run()
    {
        while (contagem < 100) {
            try {
                handler.sendMessage(handler.obtainMessage());
                Thread.sleep(25);
            } catch (Throwable t) {
            }
        }
        startActivity(new Intent(this, FZPongActivity.class));
        finish();
    }

}
