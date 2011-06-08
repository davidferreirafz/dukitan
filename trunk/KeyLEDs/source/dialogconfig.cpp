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


#include "dialogconfig.h"

DialogConfig::DialogConfig ( QWidget *parent ) {
    state=SingletonState::getInstance();

    dialog.setupUi ( parent );

    load();
}

DialogConfig::~DialogConfig() {
}

void DialogConfig::load() {
    dialog.spinBox->setValue ( state->getFontSize() );

    dialog.rbVertical->setChecked ( state->getAlignVertical() );

    dialog.rbHorizontal->setChecked ( !state->getAlignVertical() );
}

QSpinBox *DialogConfig::getSpinBox() {
    return dialog.spinBox;
}

QRadioButton * DialogConfig::getRadioButtonVertical() {
    return dialog.rbVertical;
}

QRadioButton * DialogConfig::getRadioButtonHorizontal() {
    return dialog.rbHorizontal;
}

