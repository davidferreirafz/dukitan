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


#include "singletonstate.h"

SingletonState * SingletonState::instance;

SingletonState::SingletonState()
{
    fontSize = 8;
    alignVertical = true;

    load();
}

SingletonState::~SingletonState()
{
}

SingletonState * SingletonState::getInstance()
{
    if (instance==NULL) {
        instance = new SingletonState();
    }

    return instance;
}



void SingletonState::save()
{
    KConfig config( "KeyLEDs", KConfig::SimpleConfig );
    KConfigGroup configGroup( &config, "Config" );

    KConfigGroup alingGroup( &configGroup, "Alignment" );
    alingGroup.writeEntry("Vertical",int(alignVertical));
    alingGroup.config()->sync();

    KConfigGroup fontGroup( &configGroup, "Font" );
    fontGroup.writeEntry("Size",fontSize);
    fontGroup.config()->sync();
}

void SingletonState::load()
{
    KConfig config( "KeyLEDs", KConfig::SimpleConfig );
    KConfigGroup configGroup( &config, "Config" );

    KConfigGroup alingGroup( &configGroup, "Alignment" );
    QString vertical = alingGroup.readEntry("Vertical");

    KConfigGroup fontGroup( &configGroup, "Font" );
    QString size = fontGroup.readEntry("Size");

    fontSize=size.toInt();
    alignVertical=vertical.toInt();
}

void SingletonState::setAlignVertical(bool value)
{
    alignVertical=value;
}

void SingletonState::setFontSize(int value)
{
    fontSize=value;
}


bool SingletonState::getAlignVertical()
{
    return alignVertical;
}

int  SingletonState::getFontSize()
{
    return fontSize;
}
