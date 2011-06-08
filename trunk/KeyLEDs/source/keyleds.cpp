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
#include "keyleds.h"
#include <QPainter>
#include <QFontMetrics>
#include <QSizeF>
#include <QFile>
#include <KLocale>
#include <QTimer>

#include <QVBoxLayout>
#include <QCheckBox>
#include <KConfigDialog>
#include <QGroupBox>
#include <QRadioButton>
#include <QSpinBox>
#include <QDialogButtonBox>
#include <QLabel>
#include <KUrlLabel>

#include <plasma/svg.h>
#include <plasma/theme.h>


QString KeyLEDs::DISPLAY_CAPS_LOCK="CAPS\n\rLOCK";
QString KeyLEDs::DISPLAY_NUM_LOCK="NUM\n\rLOCK";


KeyLEDs::KeyLEDs ( QObject *parent, const QVariantList &args )
        : Plasma::Applet ( parent, args ) {

    setBackgroundHints ( DefaultBackground );
    setHasConfigurationInterface ( true );

    state = SingletonState::getInstance();

    setWindowTitle ( "LED Status" );

    resize ( 100, 100 );

    updateLEDStatus();
}


KeyLEDs::~KeyLEDs() {
    if ( hasFailedToLaunch() ) {
        // Do some cleanup here
    } else {

    }
}

void KeyLEDs::init() {
    // kick off a refresh timer
    QTimer* m_timer = new QTimer ( this );
    connect ( m_timer, SIGNAL ( timeout() ), this, SLOT ( updateLEDStatus() ) );
    m_timer->start ( 1000 );
}


void KeyLEDs::createConfigurationInterface ( KConfigDialog* parent ) {

    QWidget *about = new QWidget();
    dialogAbout = new DialogAbout ( about );
    parent->addPage ( about,"About" );

    QWidget *config = new QWidget();
    dialogConfig = new DialogConfig ( config );
    parent->addPage ( config,"Settings", icon() );


    connect ( dialogConfig->getSpinBox(),
              SIGNAL ( valueChanged ( int ) ),
              this,
              SLOT ( setFontSize() ) );

    connect ( dialogConfig->getRadioButtonVertical(),
              SIGNAL ( clicked ( bool ) ),
              this,
              SLOT ( setAlign() ) );

    connect ( dialogConfig->getRadioButtonHorizontal(),
              SIGNAL ( clicked ( bool ) ),
              this,
              SLOT ( setAlign() ) );
}



void KeyLEDs::paintInterface ( QPainter *p,
                               const QStyleOptionGraphicsItem *option, const QRect &contentsRect ) {

    p->beginNativePainting();

    p->setRenderHint ( QPainter::SmoothPixmapTransform );
    p->setRenderHint ( QPainter::Antialiasing );

    QFont f ( p->font() );
    f.setPixelSize ( state->getFontSize() );
    p->setFont ( f );

    QRect * rect1 = NULL;
    QRect * rect2 = NULL;

    int flags1;
    int flags2;

    if ( state->getAlignVertical() ) {
        rect1 = new QRect ( contentsRect.x(),contentsRect.y(),contentsRect.width(),contentsRect.height() );
        rect2 = new QRect ( contentsRect.x(),contentsRect.y(),contentsRect.width(),contentsRect.height() );

        flags1 = Qt::AlignTop | Qt::AlignHCenter | Qt::AlignJustify;
        flags2 = Qt::AlignBottom | Qt::AlignHCenter | Qt::AlignJustify;

    } else {
        int tmp = contentsRect.width() /2;
        rect1 = new QRect ( contentsRect.x(),contentsRect.y(),tmp,contentsRect.height() );
        rect2 = new QRect ( tmp,contentsRect.y(),tmp,contentsRect.height() );

        flags1 = Qt::AlignCenter | Qt::AlignHCenter | Qt::AlignJustify;
        flags2 = flags1;
    }

    p->setPen ( ledStatus.isCapsLock() ==true ? Qt::green : Qt::gray );
    p->drawText ( *rect1,
                  flags1,
                  DISPLAY_CAPS_LOCK );

    p->setPen ( ledStatus.isNumLock() ==true ? Qt::green : Qt::gray );
    p->drawText ( *rect2,
                  flags2,
                  DISPLAY_NUM_LOCK );

    p->endNativePainting();
}


void KeyLEDs::updateLEDStatus() {
    ledStatus.update ( this );
    update();
}

void KeyLEDs::setFontSize() {

    state->setFontSize ( dialogConfig->getSpinBox()->value() );

    state->save();

    update();
}

void KeyLEDs::setAlign() {

    state->setAlignVertical ( dialogConfig->getRadioButtonVertical()->isChecked() );

    state->save();

    update();
}


#include "keyleds.moc"
