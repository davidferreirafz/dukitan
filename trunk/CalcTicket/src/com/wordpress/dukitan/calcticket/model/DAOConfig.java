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

/**
 *
 * @author david
 */
public class DAOConfig extends DAO
{
    private final String RECORDSTORE_NAME = "calcticket.config";
    
    public void salvar(Config config)
    {
        byte[] b = null;        
  
        abrir(RECORDSTORE_NAME);        
        
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);

            try {
                dos.writeUTF(config.getValor());
                dos.writeUTF(config.getOutroTicket());

                b = baos.toByteArray();

                gravar(FIRST_ID, b);
            } finally {
                dos.close();                
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            fechar();            
        }      
    }
    
    public Config carregar()
    {        
        Config config = new Config();
        abrir(RECORDSTORE_NAME);

        byte[] dados = ler(FIRST_ID);
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
            fechar();
        }

        return config;            
    }
}
