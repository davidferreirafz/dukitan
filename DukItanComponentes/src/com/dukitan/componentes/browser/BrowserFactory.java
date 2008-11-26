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

import com.dukitan.componentes.browser.type.BrowserLinux;
import com.dukitan.componentes.browser.type.BrowserMsWindows;
import com.dukitan.componentes.browser.type.Browser;

/**
 * Classe Factory para determinar qual implementação concreta de Browser deve
 * ser utilizada.
 * 
 * Design Pattern: GoF - Factory Method
 * 
 * @author David Ferreira - davidferreira.fz@gmail.com
 */
public class BrowserFactory
{
    /**
     * Retorna uma implementação concreta da Interface Browser
     * 
     * @return instância de Browser
     */
    public static Browser getBrowser()
    {
        // Pega a propriedade com o nome do Sistema Operacional
        String so = System.getProperty("os.name");
        Browser browser;

        // Se o nome do sistema operacional começa com Windows
        if (so.startsWith("Windows")) {
            browser = new BrowserMsWindows();
        } else {
            // Nos outros casos, assume-se que o sistema é Linux
            System.out.println(so);
            browser = new BrowserLinux();
        }

        return browser;
    }

}
