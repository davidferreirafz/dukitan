/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
