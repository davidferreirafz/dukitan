package com.dukitan.android.fzpong;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class FZPongActivity extends Activity {
    /** Called when the activity is first created. */

    GameView view;
    AdView adView;
    private Handler guiRefresher;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.main);

        view = (GameView) findViewById(R.id.gameView);

        guiRefresher = new Handler() {
            public void handleMessage(Message msg) {
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

        adView = (AdView) this.findViewById(R.id.adView);

    }

    public void onDestroy() {
        // adView.destroy();

        super.onDestroy();
    }

}