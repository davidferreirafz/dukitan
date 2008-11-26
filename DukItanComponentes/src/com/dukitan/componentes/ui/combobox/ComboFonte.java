/*****************************************************************************/
/* DukItan         -  DukItan Software Components                            */
/* E-Mail          -  davidferreira.fz@gmail.com                             */
/* Site            -  http://davidferreirafz.wordpress.com                   */ 
/* ICQ: 21877381      MSN: davidaf@uol.com.br                                */
/* G.talk: davidferreira.fz@gmail.com                                        */
/* Copyright (C) 2008  David de Almeida Ferreira                             */
/*****************************************************************************/
/*                                                                           */
/* Este arquivo é parte do projeto DSC - DukItan Software Components         */
/*                                                                           */
/* DSC é um software livre; você pode redistribui-lo e/ou                    */
/* modifica-lo dentro dos termos da Licença Pública Geral (GPL) GNU          */
/* como publicada pela Fundação do Software Livre (FSF); na versão 2 da      */
/* Licença                                                                   */
/*                                                                           */
/*****************************************************************************/
/*                                                                           */
/* This file is part of project DSC - DukItan Software Components            */
/*                                                                           */
/* DSC is free software; you can redistribute it and/or modify               */
/* it under the terms of the GNU  Lesser General Public License (LGPL) as    */
/* published by the Free Software Foundation; either version 2 of the        */
/* License.                                                                  */
/*                                                                           */
/*****************************************************************************/
package com.dukitan.componentes.ui.combobox;

import java.awt.GraphicsEnvironment;

/**
 * Combobox, que lista as fontes instaladas no sistema operacional.
 * 
 * @author David Ferreira - davidferreira.fz@gmail.com
 */
public class ComboFonte extends ComboBox
{
	private static final long serialVersionUID = -5021290243258368931L;

	/**
	 * Construtor padrão
	 */
	public ComboFonte()
	{
	    load();
	}
	
	/**
	 * Carrega as fontes instaladas no sistema operacional.
	 */
	private void load()
	{
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        
        String fontNames[] = ge.getAvailableFontFamilyNames();

        for (int i=0; i<fontNames.length; i++) {
            addItem(fontNames[i]);
        }	    
	}
}
