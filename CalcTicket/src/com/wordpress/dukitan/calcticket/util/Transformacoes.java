/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.dukitan.calcticket.util;

/**
 *
 * @author david
 */
public class Transformacoes {
    
    public static String float2String(float numero)
    {
        String tmp = String.valueOf(numero);
        
        if (tmp.length()>4){
            return tmp.substring(0,tmp.indexOf(".")+3);            
        } else {
            return tmp;
        }
    }   
}
