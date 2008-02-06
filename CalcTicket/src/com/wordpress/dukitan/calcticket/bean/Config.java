/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.dukitan.calcticket.bean;



/**
 *
 * @author david
 */
public class Config
{
    private float valorTicket;
    private float outroTicket;
            
    private float totalCompra;
    private int quantidadeTicket;
    
    private float valorDisponivel;
    private float valorComplemento;

    private final float VALOR_TICKET = 14.72f;
    private final float OUTRO_TICKET =  5.0f;    
    
    public int getQuantidadeTicket()
    {
        return quantidadeTicket;
    }

    public float getTotalCompra()
    {
        return totalCompra;
    }

    public float getValorComplemento()
    {
        return valorComplemento;
    }

    public float getValorDisponivel()
    {
        return valorDisponivel;
    }

    
    public Config()
    {
        valorTicket = VALOR_TICKET;
        outroTicket = OUTRO_TICKET;
    }

    public void setValor(String valor) 
    {
        if (valor.equals("")) {
            valorTicket = VALOR_TICKET;
        } else {
            float tmp =  Float.parseFloat(valor);
            
            if (tmp>0f){
                valorTicket = tmp;
            }
        }
    }  
    
    public String getValor()
    {
        return String.valueOf(valorTicket);
    }
    
    public void setOutroTicket(String valor)
    {
        if (valor.equals("")){
            outroTicket = OUTRO_TICKET;
        } else {
            float tmp =  Float.parseFloat(valor);
            
            if (tmp>0f){
                outroTicket = tmp;
            }            
        }
    }
    
    public String getOutroTicket()
    {
        return String.valueOf(outroTicket);
    }

    public void setTotalCompra(float totalCompra)
    {
        this.totalCompra=totalCompra;
    }
   
    
    public void calcular()
    {     
        quantidadeTicket = (int) (totalCompra/valorTicket);
                
        if (quantidadeTicket<=0){
            quantidadeTicket=1;
        }
        
        float credito = valorTicket * quantidadeTicket;
        float resto = totalCompra - credito;
        
        if (resto > outroTicket){
            quantidadeTicket++;
        }
        
        
        float saldo = quantidadeTicket * valorTicket;
        
        if (totalCompra<=saldo){
            valorDisponivel = saldo - totalCompra;
            valorComplemento = 0;
        } else {
            valorDisponivel = 0;

            valorComplemento = totalCompra - saldo;
        }
    }
    
}
