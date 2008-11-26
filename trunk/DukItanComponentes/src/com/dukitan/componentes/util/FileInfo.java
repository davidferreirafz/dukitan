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
package com.dukitan.componentes.util;

import java.io.File;

/**
 * Classe com meta informações sobre um arquivo.
 * 
 * @author David Ferreira - davidferreira.fz@gmail.com
 */
public class FileInfo
{
    private File file;
    private String extension = "";
    
    /**
     * Construtor
     * @param file instância de File
     */
    public FileInfo(File file)
    {
        this.file = file;
        
        process();
    }
    
   
    /**
     * Processa as informações sobre o arquivo.
     */
    protected void process()
    {
        if (file!=null){
            String nome = file.getName();
            
            int i = nome.lastIndexOf('.');              
            
            if (i > 0 &&  i < nome.length() - 1) {
                extension = nome.substring(i+1).toLowerCase();
            }
        }
    }
    
    /**
     * Retorna a extensão do arquivo.
     * @return extensão do arquivo
     */
    public String getExtension()
    {
        return extension;
    }
    
    /**
     * Informa se o arquivo é uma imagem.
     * @return retorna true em caso positivo.
     */
    public boolean isImage()
    {
        if ((extension.equalsIgnoreCase("bmp"))||
            (extension.equalsIgnoreCase("png"))){
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Informa se o arquivo é um binário com métricas.
     * @return retorna true em caso positivo.
     */
    public boolean isMetrics()
    {
        if (extension.equalsIgnoreCase("dat")){
            return true;
        } else {
            return false;
        }
    }   
    
    /**
     * Informa se o arquivo é um arquivo de projeto.
     * @return retorna true em caso positivo.
     */
    public boolean isProject()
    {
        if (extension.equalsIgnoreCase("f2i")){
            return true;
        } else {
            return false;
        }
    }    
}
