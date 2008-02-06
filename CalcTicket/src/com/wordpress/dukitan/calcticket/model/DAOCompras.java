/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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

            try {               
                int tamanho = compras.size();
                
                for (int i=0; i<tamanho; i++){
                    baos = new ByteArrayOutputStream();
                    dos = new DataOutputStream(baos);
                    
                    Produto p = compras.getProduto(i);
                    
                    dos.writeUTF(p.getDescricao());
                    dos.writeInt(p.getQuantidade());
                    dos.writeFloat(p.getValor());

                    b = baos.toByteArray();
                    
                    dao.gravar(b);
                }
            } finally {
               dos.close();                
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
                }                
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
        
        return c;
    }
}
