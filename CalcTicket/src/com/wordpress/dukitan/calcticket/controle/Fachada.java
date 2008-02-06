/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.dukitan.calcticket.controle;

import com.wordpress.dukitan.calcticket.bean.Compras;
import com.wordpress.dukitan.calcticket.bean.Config;
import com.wordpress.dukitan.calcticket.bean.Produto;
import com.wordpress.dukitan.calcticket.model.DAOCompras;
import com.wordpress.dukitan.calcticket.model.DAOConfig;
import com.wordpress.dukitan.calcticket.util.Transformacoes;


/**
 *
 * @author david
 */
public class Fachada
{
    private static Config config;
    private static Compras compras;   
    
    
    static 
    {
        carregar();
    }
    
    
    public static void carregar()
    {       
        config  = DAOConfig.carregar();
        compras = DAOCompras.carregar();
        
        calcular();
    }

    public static void salvar()
    {
        DAOConfig.salvar(config);
        DAOCompras.salvar(compras);
    }
    
    public static Config getConfig()
    {
        return config;
    }
    
    public static void setConfig(Config config)
    {
        Fachada.config = config;
        calcular();
    }
    
    public static Produto getProduto(int indice)
    {
        return compras.getProduto(indice);
    }
    
    public static void adicionar(Produto produto)
    {
        compras.adicionar(produto);
        calcular();
    }

    public static void alterar(Produto produto)
    {
        compras.atualizar(produto);

        calcular();        
    }

    public static void remover(int indice)
    {
        compras.remover(indice);
        calcular();
    }    
    
    public static String[] getProdutos()
    {
        return compras.getProdutos();
    }
    
    
    protected static void calcular()
    {
        float totalCompra = compras.getValor();
 
        config.setTotalCompra(totalCompra);
        config.calcular();
    }
    
    public static String getTotalCompra()
    {
        return Transformacoes.float2String(config.getTotalCompra());
    }
    
    public static String getQuantidadeTickets()
    {
        return String.valueOf(config.getQuantidadeTicket());
    }
    
    public static String getValorDisponivel()
    {
        return Transformacoes.float2String(config.getValorDisponivel());
    }

    public static String getValorComplemento()
    {
        return Transformacoes.float2String(config.getValorComplemento());
    }    

}
