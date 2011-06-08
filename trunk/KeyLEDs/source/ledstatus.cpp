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



#include "ledstatus.h"

LedStatus::LedStatus() {
    capsLock = false;
    numLock  = false;
}

LedStatus::~LedStatus() {

}

void LedStatus::setLedCapsLockState ( QString buffer ) {
    textCapsLock = getLedState ( "Caps Lock:",buffer );

    if ( textCapsLock.compare ( "on" ) ==0 ) {
        capsLock=true;
    } else {
        capsLock=false;
    }
}

void LedStatus::setLedNumLockState ( QString buffer ) {
    textNumLock = getLedState ( "Num Lock:",buffer );

    if ( textNumLock.compare ( "on" ) ==0 ) {
        numLock=true;
    } else {
        numLock=false;
    }
}

QString LedStatus::getLedState ( QString ledName,QString buffer ) {
    int initialPosition = buffer.indexOf ( ledName ) +ledName.size();

    QString text = buffer.mid ( initialPosition );
    text = text.simplified();

    int finalPosition = text.indexOf ( ":" )-2;

    text = text.mid ( 0,finalPosition );
    text = text.simplified();

    return text;
}

void LedStatus::update ( QObject *instance ) {
    QProcess cmd ( instance );

    cmd.start ( "xset -q | grep Caps" );
    cmd.waitForFinished();

    QTextStream in ( cmd.readAllStandardOutput() );
    QString line = in.readAll();

    // remove any extraneous characters
    line = line.simplified();

    setLedCapsLockState ( line );
    setLedNumLockState ( line );
}

bool LedStatus::isCapsLock() {
    return capsLock;
}

bool LedStatus::isNumLock() {
    return numLock;
}

