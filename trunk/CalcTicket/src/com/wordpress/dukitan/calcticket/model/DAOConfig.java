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

import com.wordpress.dukitan.calcticket.bean.Config;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

/**
 *
 * @author david
 */
public class DAOConfig
{
    private RecordStore rs;
    static private final int ID = 1;
    private final String RECORDSTORE_NAME = "calcticket.config";
    
    private void abrir()
    {
        try {
            rs = RecordStore.openRecordStore(RECORDSTORE_NAME, true);
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
    }
    
    private void fechar()
    {
        try {
            if (rs != null){
                rs.closeRecordStore();
            }
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
    }
    
    private boolean isDados()
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
    
    private void gravar(int id, byte[] dados)
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
    
    
    private byte[] ler(int id)
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
    
/////////////
    public static void salvar(Config config)
    {
        byte[] b = null;        
        DAOConfig dao = new DAOConfig();        
        dao.abrir();        
        
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);

            try {
                dos.writeUTF(config.getValor());
                dos.writeUTF(config.getOutroTicket());

                b = baos.toByteArray();

                dao.gravar(ID, b);
            } finally {
                dos.close();                
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            dao.fechar();            
        }      
    }
    
    public static Config carregar()
    {        
        DAOConfig dao = new DAOConfig();
        Config config = new Config();
        dao.abrir();

        byte[] dados = dao.ler(ID);
        DataInputStream dis = null;
        ByteArrayInputStream bais;

        try {            
            if (dados!=null){
                bais = new ByteArrayInputStream(dados);               
                dis = new DataInputStream(bais);

                config.setValor(dis.readUTF());
                config.setOutroTicket(dis.readUTF());
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (dis!=null){
                    dis.close();
                }
            } catch (IOException ex) { ex.printStackTrace(); }
            dao.fechar();
        }

        return config;            
    }
}
