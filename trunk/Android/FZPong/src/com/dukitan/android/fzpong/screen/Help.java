package com.dukitan.android.fzpong.screen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dukitan.android.fzpong.FZPongActivity;
import com.dukitan.android.fzpong.R;

public class Help extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
    }

    public void onClick(View view)
    {
        Intent i = new Intent();
        i.setClass(this, FZPongActivity.class);
        startActivity(i);
    }
}
