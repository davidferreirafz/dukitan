package com.dukitan.android.fzpong;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.dukitan.android.fzpong.screen.About;
import com.dukitan.android.fzpong.screen.Credit;
import com.dukitan.android.fzpong.screen.Help;

public class FZPongActivity extends Activity
{
    private GameView view;

    private Controle controle;

    /*
     * A atividade está sendo criada. O sistema chama esse método quando está
     * criando a atividade.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.main);

        view = (GameView) findViewById(R.id.gameView);
        controle = view.getThread();
        controle.setAdView(findViewById(R.id.adView));
        controle.setTextView((TextView) findViewById(R.id.statusMessage));

        if (savedInstanceState == null) {
            controle.setState(Controle.STATE_READY);
        } else {
            controle.restoreState(savedInstanceState);
        }

    }

    // A atividade está para se tornar visível
    @Override
    protected void onStart()
    {
        super.onStart();
    }

    /*
     * A atividade se tornou visível (está em modo "resumed"). O sistema chama
     * esse método como a primeira indicação que o usuário está deixando sua
     * atividade (apesar disso não significar que a atividade está sendo
     * destruída).
     */
    @Override
    protected void onResume()
    {
        super.onResume();
    }

    // Outra atividade está ganhando o foco (está em modo "paused").
    @Override
    protected void onPause()
    {
        super.onPause();
        controle.doPause();
    }

    // A atividade não está mais visível (está "stopped")
    @Override
    protected void onStop()
    {
        super.onStop();
    }

    // A atividade está para ser destruída.
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent i;
        
        switch (item.getItemId()) {
            case R.id.menu_start:
                controle.doStart();
                return true;          
            case R.id.menu_about:
                controle.doPause();                
                i = new Intent();
                i.setClass(this, About.class);
                startActivity(i);
                

                return true;
            case R.id.menu_help:

                controle.doPause();                
                i = new Intent();
                i.setClass(this, Help.class);
                startActivity(i);                

                return true;
            case R.id.menu_credit:
                controle.doPause();
                
                i = new Intent();
                i.setClass(this, Credit.class);
                startActivity(i);                
                return true;           
        }

        return false;
    }

    // Salvando o estado da Ativade
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        controle.saveState(outState);
    }

}