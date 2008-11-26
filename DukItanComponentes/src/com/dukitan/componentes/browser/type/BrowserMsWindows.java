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
package com.dukitan.componentes.browser.type;

import java.io.IOException;

/**
 * Implementação da Interface Browser para abertura
 * do navegador no MS-Windows
 * 
 * Design Pattern: GoF - Factory Method
 *  
 * @author David Ferreira - davidferreira.fz@gmail.com
 */
public class BrowserMsWindows implements Browser
{   
    /**
     * {@inheritDoc}
     */
    public boolean open(String url)
    {
        boolean open = true;
        Runtime rt = Runtime.getRuntime();
        
        try {
            //tenta executar o browser padrão por meio da integração
            //com a shellapi do ms-windows
            rt.exec("rundll32 SHELL32.DLL,ShellExec_RunDLL "+url);
        } catch (IOException e) {
            open = false;
        }
        
        return open;
    }

}
