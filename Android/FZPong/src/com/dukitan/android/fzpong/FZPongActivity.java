package com.dukitan.android.fzpong;

import android.app.Activity;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FZPongActivity extends Activity
{
    private GameView view;

    private Game controle;

    /*
     * A atividade está sendo criada. O sistema chama esse método quando está
     * criando a atividade.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.i(getClass().getName(),"onCreate");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.main);

        view = (GameView) findViewById(R.id.gameView);
        controle = view.getControl();
        controle.setAdView(findViewById(R.id.adView));
        controle.setTextView((TextView) findViewById(R.id.statusMessage));

        if (savedInstanceState == null) {     
            //controle.setState(Controle.STATE_LOADING);
        } else {
            controle.restoreState(savedInstanceState);
        }
    }

    // A atividade está para se tornar visível
    @Override
    protected void onStart()
    {
        super.onStart();
        Log.i(getClass().getName(),"onStart");
        view.onStart();
    }

    /*
     * A atividade se tornou visível (está em modo "resumed").
     */
    @Override
    protected void onResume()
    {
        super.onResume();
        Log.i(getClass().getName(),"onResume");    
    }

    // Outra atividade está ganhando o foco (está em modo "paused").
    @Override
    protected void onPause()
    {
        super.onPause();
        Log.i(getClass().getName(),"onPause");               
        view.onPause();
        
    }

    // A atividade não está mais visível (está "stopped")
    @Override
    protected void onStop()
    {
        super.onStop();
        Log.i(getClass().getName(),"onStop");  
        view.onStop();
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
        switch (item.getItemId()) {
            case R.id.menu_start:
                controle.doStart();
                return true;

            case R.id.menu_about:
                controle.doPause();
                showCustomDialog(R.layout.about, R.string.dialogAboutTitle);
                return true;

            case R.id.menu_help:
                controle.doPause();
                showCustomDialog(R.layout.help, R.string.dialogHelpTitle);
                return true;

            case R.id.menu_credit:
                controle.doPause();
                showCustomDialog(R.layout.credit, R.string.dialogCreditTitle);
                return true;
        }

        return false;
    }

    // Salvando o estado da Ativade
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        controle.onSaveInstanceState(outState);
    }

    private void showCustomDialog(int layoutResID, int titleResID)
    {

        final Dialog dialog = new Dialog(this);

        dialog.setContentView(layoutResID);

        dialog.setTitle(titleResID);

        final Button ok = (Button) dialog.findViewById(R.id.button_ok);

        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}