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

package com.wordpress.dukitan.calcticket.controle;

import com.wordpress.dukitan.calcticket.bean.Compras;
import com.wordpress.dukitan.calcticket.bean.Config;
import com.wordpress.dukitan.calcticket.bean.Produto;
import com.wordpress.dukitan.calcticket.model.DAOCompras;
import com.wordpress.dukitan.calcticket.model.DAOConfig;
import com.wordpress.dukitan.calcticket.model.DAOFactory;
import com.wordpress.dukitan.calcticket.util.Transformacoes;


/**
 *
 * @author david
 */
public class Fachada
{
    private Config config;
    private Compras compras;   
    private DAOCompras daoCompras;
    private DAOConfig daoConfig;    
    
    
    public Fachada() 
    {
        carregar();
    }
    
    
    public void carregar()
    {    
        daoCompras = DAOFactory.getDAOCompras();
        daoConfig = DAOFactory.getDAOConfig();        
        
        config  = daoConfig.carregar();
        compras = daoCompras.carregar();
        
        calcular();
    }

    public void salvar()
    {
        daoConfig.salvar(config);
        daoCompras.salvar(compras);
    }
    
    public  Config getConfig()
    {
        return config;
    }
    
    public void setConfig(Config config)
    {
        this.config = config;
        calcular();
    }
    
    public Produto getProduto(int indice)
    {
        return compras.getProduto(indice);
    }
    
    public void adicionar(Produto produto)
    {
        compras.adicionar(produto);
        calcular();
    }

    public void alterar(Produto produto)
    {
        compras.atualizar(produto);

        calcular();        
    }

    public void remover(int indice)
    {
        compras.remover(indice);
        calcular();
    }    
    
    public String[] getProdutos()
    {
        return compras.getProdutos();
    }
    
    
    protected void calcular()
    {
        float totalCompra = compras.getValor();
 
        config.setTotalCompra(totalCompra);
        config.calcular();
    }
    
    public String getTotalCompra()
    {
        return Transformacoes.float2String(config.getTotalCompra());
    }
    
    public String getQuantidadeTickets()
    {
        return String.valueOf(config.getQuantidadeTicket());
    }
    
    public String getValorDisponivel()
    {
        return Transformacoes.float2String(config.getValorDisponivel());
    }

    public String getValorComplemento()
    {
        return Transformacoes.float2String(config.getValorComplemento());
    }    

}
