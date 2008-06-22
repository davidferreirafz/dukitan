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

/**
 *
 * @author david
 */
public class DAOFactory
{

    public static DAOConfig getDAOConfig()
    {
        return new DAOConfig();
    }
    
    public static DAOCompras getDAOCompras()
    {
        return new DAOCompras();
    }
    
}
