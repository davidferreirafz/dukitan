package com.dukitan.android.fzpong;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class FZPongActivity extends Activity
{
    private GameView view;

    private Controle controle;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
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

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.menu_start:
                controle.doStart();
                return true;
            case R.id.menu_stop:
                controle.setState(Controle.STATE_LOSE, getText(R.string.message_stopped));
                return true;
            case R.id.menu_pause:
                controle.pause();
                return true;
            case R.id.menu_resume:
                controle.unpause();
                return true;
        }

        return false;
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        controle.pause();
    }

    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        controle.saveState(outState);
    }

}