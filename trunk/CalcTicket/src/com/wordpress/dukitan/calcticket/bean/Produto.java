/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.dukitan.calcticket.bean;


/**
 *
 * @author david
 */
public class Produto
{
    private String descricao;
    private int quantidade;
    private float valor;
    private final String SEPARADOR = "\n\r    ";

    public Produto(String descricao, int quantidade, float valor)
    {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor = valor;
    }  
    
    public Produto()
    {
        
    }

    
    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public int getQuantidade()
    {
        return quantidade;
    }

    public void setQuantidade(String quantidade)
    {
        if (quantidade.equals("")){
            this.quantidade = 0;
        } else {
            this.quantidade = Integer.parseInt(quantidade);
        }
    }    

    public float getValor()
    {
        return valor;
    }

    public void setValor(String valor)
    {
        if (valor.equals("")){
            this.valor = 0;
        } else {        
            this.valor = Float.parseFloat(valor);
        }
    }
    
    public float calcular()
    {
        return quantidade * valor;
    }
    
    public String toString()
    {
        return "- "+descricao+SEPARADOR+quantidade+"x"+valor;
    }
    
    public void makeProduto(String info)
    {
        int posicao = info.indexOf(SEPARADOR);
        
        setDescricao(info.substring(2, posicao));
    }

    
    public boolean equals(Object obj)
    {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (this.descricao != other.descricao && (this.descricao == null || !this.descricao.equals(other.descricao))) {
            return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = 5;
        hash = 29 * hash + (this.descricao != null ? this.descricao.hashCode() : 0);
        return hash;
    }
    
    
}
