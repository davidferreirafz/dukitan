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

/**
 *
 * @author david
 */
public class DAOCompras extends DAO
{
    private final String RECORDSTORE_NAME = "calcticket.compras";

    public void salvar(Compras compras)
    {
        byte[] b = null; 
        
        limpar();
        abrir(RECORDSTORE_NAME);
        
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

                    gravar(b);
                    dos.close();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            fechar();            
        }     
    }
    
    public Compras carregar()
    {
        Compras c = new Compras();
 
        abrir(RECORDSTORE_NAME);

        DataInputStream dis = null;
        ByteArrayInputStream bais;
        
        try {            
            int tamanho = size();
            
            if (tamanho>0){
                for (int i=0; i<tamanho; i++){

                    byte[] dados = ler(FIRST_ID+i);
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
            fechar();
        }
        
        return c;
    }
}
