///***************************************************************************
// *   CalcTicket - Mobile                                                   *
// *   Copyright (C) 2008 by David Ferreira - FZ & DukItan Software          *
// *   davidferreira.fz@gmail.com - http://dukitan.wordpress.com             *
// ***************************************************************************
// *   Este programa é software livre; você pode redistribuí-lo e/ou         *
// *   modificá-lo sob os termos da Licença Pública Geral GNU, conforme      *
// *   publicada pela Free Software Foundation, tanto a versão 3 da          *
// *   Licença como (a seu critério) qualquer versão mais nova.              *
// ***************************************************************************
// *   This program is free software; you can redistribute it and/or modify  *
// *   it under the terms of the GNU General Public License as published by  *
// *   the Free Software Foundation; either version 3 of the License, or     *
// *   (at your option) any later version.                                   *
// *                                                                         *
// *   You should have received a copy of the GNU General Public License     *
// *   along with this program; if not, write to the                         *
// *   Free Software Foundation, Inc.,                                       *
// *   59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.             *
// ***************************************************************************/

package com.wordpress.dukitan.calcticket.model;

import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

/**
 *
 * @author david
 */
public class DAO
{
    static protected final int FIRST_ID = 1;
    protected RecordStore rs;
    private String RECORDSTORE_NAME;
    
    protected void abrir(String recordStoreName)
    {
        RECORDSTORE_NAME = recordStoreName;
        try {
            rs = RecordStore.openRecordStore(RECORDSTORE_NAME, true);
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
    }
    
    protected void fechar()
    {
        try {
            if (rs != null){
                rs.closeRecordStore();
            }
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
    }
    
    protected boolean isDados()
    {
        boolean retorno = false;
        try {
            if (rs.getNumRecords() >= 1) {
                retorno = true;
            }
        } catch (RecordStoreNotOpenException ex) {
            ex.printStackTrace();
        }
        
        return retorno;
    }
       
    protected void gravar(byte[] dados)
    {
        try {
            rs.addRecord(dados, 0, dados.length);
        } catch (RecordStoreNotOpenException ex) {
            ex.printStackTrace();
        } catch (InvalidRecordIDException ex) {
            ex.printStackTrace();
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
    }
    
    protected void gravar(int id, byte[] dados)
    {
        try {
           if (isDados()) {
                rs.setRecord(id, dados, 0, dados.length);
            } else {
                rs.addRecord(dados, 0, dados.length);
            }
        } catch (RecordStoreNotOpenException ex) {
            ex.printStackTrace();
        } catch (InvalidRecordIDException ex) {
            ex.printStackTrace();
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
    }    
    
    protected byte[] ler(int id)
    {
        byte[] dados = null;
        
        try {
           if (isDados()) {
                dados = rs.getRecord(id);
            }
        } catch (RecordStoreNotOpenException ex) {
            ex.printStackTrace();
        } catch (InvalidRecordIDException ex) {
            ex.printStackTrace();
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
        
        return dados;
    }
    
    protected int size()
    {
        try {
            return rs.getNumRecords();
        } catch (RecordStoreNotOpenException ex) {
            return 0;
        }
    }
    
    protected void limpar()
    {
        try {
            rs.deleteRecordStore(RECORDSTORE_NAME);
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
    }
    

}
