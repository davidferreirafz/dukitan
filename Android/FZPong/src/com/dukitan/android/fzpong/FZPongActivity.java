package com.dukitan.android.fzpong;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class FZPongActivity extends Activity
{
    /** Called when the activity is first created. */

    GameView        view;
    private Handler guiRefresher;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.main);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        view = (GameView) findViewById(R.id.gameView1);

        guiRefresher = new Handler() {
            public void handleMessage(Message msg)
            {
                if (msg.what == 1) {
                    view.invalidate();
                }
                super.handleMessage(msg);
            }
        };

        view.setCallbackHandler(guiRefresher);

        Thread t = new Thread(view);
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void finish()
    {
        super.finish();
    }

}