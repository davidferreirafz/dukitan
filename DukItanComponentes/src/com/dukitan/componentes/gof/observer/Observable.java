/*****************************************************************************/
/* DukItan         -  DukItan Software Components                            */
/* E-Mail          -  davidferreira.fz@gmail.com                             */
/* Site            -  http://davidferreirafz.wordpress.com                   */ 
/* ICQ: 21877381      MSN: davidaf@uol.com.br                                */
/* G.talk: davidferreira.fz@gmail.com                                        */
/* Copyright (C) 2008  David de Almeida Ferreira                             */
/*****************************************************************************/
/*                                                                           */
/* Este arquivo é parte do projeto DSC - DukItan Software Components         */
/*                                                                           */
/* DSC é um software livre; você pode redistribui-lo e/ou                    */
/* modifica-lo dentro dos termos da Licença Pública Geral (GPL) GNU          */
/* como publicada pela Fundação do Software Livre (FSF); na versão 2 da      */
/* Licença                                                                   */
/*                                                                           */
/*****************************************************************************/
/*                                                                           */
/* This file is part of project DSC - DukItan Software Components            */
/*                                                                           */
/* DSC is free software; you can redistribute it and/or modify               */
/* it under the terms of the GNU  Lesser General Public License (LGPL) as    */
/* published by the Free Software Foundation; either version 2 of the        */
/* License.                                                                  */
/*                                                                           */
/*****************************************************************************/
package com.dukitan.componentes.gof.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


/**
 * Implementação de IObservable, está classe notificará as 
 * classes Observer sobre alterações.
 *  
 * Design Pattern: GoF - Observer
 * 
 * @author David Ferreira - davidferreira.fz@gmail.com
 */
public class Observable implements IObservable
{
    private List<Observer> listObserver;
    private boolean autoCommit = true;
    
    public Observable()
    {
        listObserver = new ArrayList<Observer>();
    }
    
    /**
     * {@inheritDoc}
     */  
    public void register(Observer observer)
    {
        listObserver.add(observer);
    }
    
    /**
     * {@inheritDoc}
     */  
    public void desregister(Observer observer)
    {
        listObserver.remove(observer);
    }
    
    /**
     * Notifica os observadores sobre as alterações de seu estado.
     * Este método deve ser chamado pela classes que implementamente
     * Observable nos métodos de alteração de seus valores ou que forem
     * importantes.
     */
    protected void updateObserver()
    {
        //Se a auto notificação estiver ativa ela informa sua lista de observers
        if (autoCommit){
            if ((listObserver!=null)&&(!listObserver.isEmpty())){
                ListIterator<Observer> i= listObserver.listIterator(0);
            
                while (i.hasNext()){
                    i.next().update();
                }
            }
        }
    }

    /**
     * Configura o Observable para não notificar as alterações.
     * É aplicado um esquema transacional para evitar que a notificação
     * sejam aplicada durante uma sequencia de modificações.
     */
    public void beginModify()
    {
        autoCommit = false;
    }
    
    /**
     * Configura o Observable para notificar qualquer modificação
     * aos Observers.
     * Executa imediatamente a notificação aos Observers 
     */
    public void endModify()
    {
        autoCommit = true;
        updateObserver();
    }    
}
