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
package com.dukitan.componentes.browser;

import com.dukitan.componentes.browser.type.Browser;

/**
 * Classe de fachada para simplificar a abertura do browser no sistema
 * operacional.
 * 
 * Design Pattern: GoF - Facade
 * 
 * @author David Ferreira - davidferreira.fz@gmail.com
 */
public class FacadeBrowser
{
    /**
     * Abre a URL no navegador
     * 
     * @param url
     *            a ser aberta no navegador.
     */
    static public void openURL(String url)
    {
        // Obtem uma instance de browser
        Browser browser = BrowserFactory.getBrowser();

        // Tenta abrir a url no navegador
        browser.open(url);
    }
}
