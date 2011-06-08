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

#include <kdialog.h>

#ifndef SINGLETONSTATE_H
#define SINGLETONSTATE_H

#include <iostream>

#include <KConfig>
#include <KConfigGroup>

class SingletonState {

    public:
        virtual ~SingletonState();
        static SingletonState * getInstance();

        int getFontSize();
        void setFontSize ( int value );
        bool getAlignVertical();
        void setAlignVertical ( bool value );


        void load();
        void save();

    protected:
        SingletonState();
        static SingletonState * instance;

    private:


        int fontSize;
        bool alignVertical;
};

#endif // SINGLETONSTATE_H
