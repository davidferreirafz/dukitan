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

package com.wordpress.dukitan.calcticket.bean;

import java.util.Vector;

/**
 *
 * @author david
 */
public class Compras
{
    private Vector produtos;
    
    public Compras()
    {
        produtos = new Vector(10);
    }
    
    public void remover(Produto produto)
    {
        if (!produtos.isEmpty()){
            produtos.removeElement(produto);         
        }
    }
    
    public void remover(int indice)
    {
        if (!produtos.isEmpty()){
            produtos.removeElementAt(indice);
        }
    } 
 
    public void adicionar(Produto produto)
    {
        produtos.addElement(produto);
    }

    public void atualizar(Produto produto)
    {
        for (int i=0; i<produtos.size(); i++){
            if (getProduto(i).equals(produto)){
                produtos.setElementAt(produto, i);
                break;
            }
        }    
    }  
    
    public Produto getProduto(int indice)
    {
        return (Produto) produtos.elementAt(indice);
    }    
    
    public String[] getProdutos()
    {
        String lst[] = new String[produtos.size()];

        for (int i=0; i< lst.length; i++){
            lst[i]=produtos.elementAt(i).toString();
        }

        return lst;
    }
    
    public float getValor()
    {
        float totalCompra = 0;
        
        for (int i=0; i<produtos.size(); i++){
            Produto p = (Produto) produtos.elementAt(i);
            totalCompra+=p.calcular();
        }
        
        return totalCompra;
    }
    
    public int size()
    {
        return produtos.size();
    }
}
