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
