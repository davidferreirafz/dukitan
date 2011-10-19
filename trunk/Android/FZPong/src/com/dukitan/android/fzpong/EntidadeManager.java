package com.dukitan.android.fzpong;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;

import com.dukitan.android.fzpong.entidade.Entidade;

public class EntidadeManager {

	private List<Entidade> entidade;

	public EntidadeManager() {
		entidade = new ArrayList<Entidade>();
	}

	public void add(Entidade ent) {
		entidade.add(ent);
	}

	public void draw(Canvas canvas) {
		for (Entidade ent : entidade)
			ent.draw(canvas);
	}

	public List<Entidade> getEntidades() {
		return entidade;
	}

	public void processAI() {
		for (Entidade ent : entidade)
			ent.processAI();
	}
}