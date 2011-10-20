package com.dukitan.android.fzpong;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;

import com.dukitan.android.framework.Input;
import com.dukitan.android.fzpong.entidade.Entidade;

public class EntidadeManager
{

    private List<Entidade>         entidade;

    private static EntidadeManager instance = null;

    public static EntidadeManager getInstance()
    {

        if (instance == null) {
            instance = new EntidadeManager();
        }

        return instance;
    }

    private EntidadeManager()
    {
        entidade = new ArrayList<Entidade>();
    }

    public void add(Entidade ent)
    {
        entidade.add(ent);
    }

    public void draw(Canvas canvas)
    {
        for (Entidade ent : entidade)
            ent.draw(canvas);
    }

    public List<Entidade> getEntidades()
    {
        return entidade;
    }

    public void update(Input input)
    {
        for (Entidade ent : entidade) {
            ent.update(input);
        }
    }

    public void clear()
    {
        entidade.clear();
    }
}