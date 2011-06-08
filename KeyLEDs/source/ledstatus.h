/*****************************************************************************
* KeyLEDs         -  Plasmoid KeyLEDs                                       *
* E-Mail          -  davidferreira.fz@gmail.com                             *
* Site            -  http://software.dukitan.com/en/keyleds/                *
* vCard Author    -  http://david.dukitan.com/                              *
*                                                                           *
* Copyright (C) 2010-2011  David de Almeida Ferreira                        *
*****************************************************************************
*                                                                           *
* Este arquivo é parte do programa KeyLEDs.                                 *
*                                                                           *
* KeyLEDs é um software livre; você pode redistribui-lo e/ou                *
* modifica-lo dentro dos termos da Licença Pública Geral (GPL) GNU          *
* como publicada pela Fundação do Software Livre (FSF); na versão 2 da      *
* Licença                                                                   *
*                                                                           *
*****************************************************************************
*                                                                           *
* This file is part of KeyLEDs.                                             *
*                                                                           *
* KeyLEDs is free software; you can redistribute it and/or modify           *
* it under the terms of the GNU  Lesser General Public License (LGPL) as    *
* published by the Free Software Foundation; either version 2 of the        *
* License.                                                                  *
*                                                                           *
*****************************************************************************/

#include <QString>
#include <QTextStream>
#include <QProcess>
#include <QObject>

#ifndef LEDSTATUS_H
#define LEDSTATUS_H

class LedStatus {

    public:
        LedStatus();
        virtual ~LedStatus();
        void update ( QObject *instance );

        bool isCapsLock();
        bool isNumLock();

    private:
        QString textCapsLock;
        QString textNumLock;

        bool capsLock;
        bool numLock;

        void setLedCapsLockState ( QString buffer );
        void setLedNumLockState ( QString buffer );
        QString getLedState ( QString ledName,QString buffer );
};

#endif // LEDSTATUS_H
