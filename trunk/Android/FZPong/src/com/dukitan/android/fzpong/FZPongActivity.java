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
    Thread t;

    @Override
    public void onCreate(Bundle state)
    {
        super.onCreate(state);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
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

        t = new Thread(view);
        t.setDaemon(true);
        t.start();
    }
    
    @Override
    protected void onPause() {
        super.onPause();
    }


}