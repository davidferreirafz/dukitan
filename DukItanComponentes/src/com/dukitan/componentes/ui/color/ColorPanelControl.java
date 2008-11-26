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
package com.dukitan.componentes.ui.color;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JColorChooser;

/**
 * Classe de Controle para ColorPanel, encapsula as ações de seleção de cores
 * e muda a cor de ColorPanel para a cor selecionada.
 * 
 * @author David Ferreira - davidferreira.fz@gmail.com
 */
public class ColorPanelControl extends MouseAdapter
{
    private String title;

    /**
     * Constutor
     */
    ColorPanelControl()
    {
        setTitle("Choose Color");
    }

    /**
     * Informa título da janela de dialog
     * @param title título da janela
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Retorna o título da janela
     * @return título da janela
     */
    public String getTitle()
    {
        return title;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {       
        if (e.getComponent() instanceof ColorPanel) {
            execute(e.getComponent());
        }
    }
    
    /**
     * Executa a operação de abrir a caixa de dialog e selecionar a cor.
     * @param component
     */
    protected void execute(Component component)
    {
        Color cor = JColorChooser.showDialog(component,getTitle(),component.getForeground());

        component.setBackground(cor);
    }    

}
