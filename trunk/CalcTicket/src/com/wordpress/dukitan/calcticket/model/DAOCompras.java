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

import com.wordpress.dukitan.calcticket.bean.Compras;
import com.wordpress.dukitan.calcticket.bean.Produto;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
public class DAOCompras
{
    static private final int ID = 1;
    private RecordStore rs;
    private final String RECORDSTORE_NAME = "calcticket.compras";
    
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
    
    private void gravar(byte[] dados)
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
    
    private int size()
    {
        try {
            return rs.getNumRecords();
        } catch (RecordStoreNotOpenException ex) {
            return 0;
        }
    }
    
    private void limpar()
    {
        try {
            rs.deleteRecordStore(RECORDSTORE_NAME);
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
    }
    
////////////////////////////  

    public static void salvar(Compras compras)
    {
        byte[] b = null;        
        DAOCompras dao = new DAOCompras();  
        dao.limpar();
        dao.abrir();
        
        try {
            ByteArrayOutputStream baos = null;
            DataOutputStream dos = null;

            int tamanho = compras.size();
                
            if (tamanho>0){
                for (int i=0; i<tamanho; i++){
                    baos = new ByteArrayOutputStream();
                    dos = new DataOutputStream(baos);

                    Produto p = compras.getProduto(i);

                    dos.writeUTF(p.getDescricao());
                    dos.writeInt(p.getQuantidade());
                    dos.writeFloat(p.getValor());

                    b = baos.toByteArray();

                    dao.gravar(b);
                    dos.close();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            dao.fechar();            
        }     
    }
    
    public static Compras carregar()
    {
        Compras c = new Compras();
        DAOCompras dao = new DAOCompras();

        dao.abrir();

        DataInputStream dis = null;
        ByteArrayInputStream bais;
        
        try {            
            int tamanho = dao.size();
            
            if (tamanho>0){
                for (int i=0; i<tamanho; i++){

                    byte[] dados = dao.ler(ID+i);
                    if (dados!=null){
                        bais = new ByteArrayInputStream(dados);               
                        dis = new DataInputStream(bais);

                        Produto p = new Produto();

                        p.setDescricao(dis.readUTF());
                        p.setQuantidade(String.valueOf(dis.readInt()));
                        p.setValor(String.valueOf(dis.readFloat()));

                        c.adicionar(p);

                        dis.close();
                    }                
                }           
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            dao.fechar();
        }
        
        return c;
    }
}
