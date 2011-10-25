package com.dukitan.android.fzpong;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class FZPongActivity extends Activity {

    private static final int MENU_PAUSE = 1;

    private static final int MENU_RESUME = 2;

    private static final int MENU_START = 3;

    private static final int MENU_STOP = 4;

    private GameView view;

    private Controle controle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.main);

        view = (GameView) findViewById(R.id.gameView);
        controle = view.getThread();
        controle.setAdView(findViewById(R.id.adView));

        if (savedInstanceState == null) {
            // we were just launched: set up a new game
            controle.setState(Controle.STATE_READY);
            Log.w(this.getClass().getName(), "SIS is null");
        } else {
            // we are being restored: resume a previous game
            controle.restoreState(savedInstanceState);
            Log.w(this.getClass().getName(), "SIS is nonnull");
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, MENU_START, 0, R.string.menu_start);
        menu.add(0, MENU_STOP, 0, R.string.menu_stop);
        menu.add(0, MENU_PAUSE, 0, R.string.menu_pause);
        menu.add(0, MENU_RESUME, 0, R.string.menu_resume);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case MENU_START:
            controle.doStart();
            return true;
        case MENU_STOP:
            controle.setState(Controle.STATE_LOSE, getText(R.string.message_stopped));
            return true;
        case MENU_PAUSE:
            controle.pause();
            return true;
        case MENU_RESUME:
            controle.unpause();
            return true;
        }

        return false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        view.getThread().pause(); // pause game when Activity pauses
    }

    protected void onSaveInstanceState(Bundle outState) {
        // just have the View's thread save its state into our Bundle
        super.onSaveInstanceState(outState);
        controle.saveState(outState);
        Log.w(this.getClass().getName(), "SIS called");
    }

}