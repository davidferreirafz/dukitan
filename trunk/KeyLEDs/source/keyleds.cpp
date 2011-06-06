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
 * F2IBuilder é um software livre; você pode redistribui-lo e/ou             *
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
#include <QProcess>
#include <QVBoxLayout>
#include <QCheckBox>
#include <KConfigDialog>
#include <QGroupBox>
#include <QRadioButton>
#include <QSpinBox>
#include <QDialogButtonBox>


#include <plasma/svg.h>
#include <plasma/theme.h>


QString KeyLEDs::DISPLAY_CAPS_LOCK="CAPS\n\rLOCK";
QString KeyLEDs::DISPLAY_NUM_LOCK="NUM\n\rLOCK";


KeyLEDs::KeyLEDs ( QObject *parent, const QVariantList &args )
        : Plasma::Applet ( parent, args ) {
    // this will get us the standard applet background, for free!
    setBackgroundHints ( DefaultBackground );
    setHasConfigurationInterface ( true );
    resize ( 100, 100 );

    setWindowTitle ( "LED Status" );
    ledCapsLock = false;
    ledNumLock  = false;

    fontSize = 8;
    alignVertical = true;

    updateLEDStatus();
}


KeyLEDs::~KeyLEDs() {
    if ( hasFailedToLaunch() ) {
        // Do some cleanup here
    } else {
        // Save settings
    }
}

void KeyLEDs::init() {
    // kick off a refresh timer
    QTimer* m_timer = new QTimer ( this );
    connect ( m_timer, SIGNAL ( timeout() ), this, SLOT ( updateLEDStatus() ) );
    m_timer->start ( 1000 );
}


void KeyLEDs::createConfigurationInterface(KConfigDialog* parent)
{/*
    QWidget *widget = new QWidget();
    QVBoxLayout *layout = new QVBoxLayout(widget);
    QCheckBox *cb = new QCheckBox(widget);
    cb->setText("This is just for show");
    layout->addWidget(cb);

    parent->addPage(widget, "General", icon());

    widget = new QWidget();
    layout = new QVBoxLayout(widget);
    cb = new QCheckBox(widget);
    cb->setText("David Ferreira");
    layout->addWidget(cb);

    parent->addPage(widget, "About", icon());   */
////
    QWidget *widget = new QWidget();
    widget->setMinimumSize(200,200);    
    QVBoxLayout *layout = new QVBoxLayout(widget);
    QCheckBox *cb = new QCheckBox(widget);
    cb->setText("David de Almeida Ferreira");
    layout->addWidget(cb);
    parent->addPage(widget, "About", icon());    
    
//Config
    widget = new QWidget();
    widget->setMinimumSize(200,200);
    QGroupBox * groupBox = new QGroupBox(widget);
    groupBox->setTitle("Leds Align");
    groupBox->setGeometry(QRect(20, 10, 251, 91));
    rbAlignVertical = new QRadioButton(groupBox);
    rbAlignVertical->setText("Vertical");
    rbAlignVertical->setGeometry(QRect(20, 20, 101, 23));
    QRadioButton * rbAlignHorizontal = new QRadioButton(groupBox);
    rbAlignHorizontal->setText("Horizontal");
    rbAlignHorizontal->setGeometry(QRect(20, 50, 101, 23));

    QGroupBox * groupBox_2 = new QGroupBox(widget);
    groupBox_2->setTitle("Font Size");
    groupBox_2->setGeometry(QRect(20, 110, 251, 80));
    spinBox = new QSpinBox(groupBox_2);
    spinBox->setGeometry(QRect(30, 30, 55, 27));
    spinBox->setMinimum(4);
    spinBox->setMaximum(40);
    spinBox->setValue(8);

    QObject::connect(spinBox, SIGNAL(valueChanged(int)), this, SLOT(getFontSize()));
    QObject::connect(rbAlignVertical, SIGNAL(clicked(bool)), this, SLOT(setAlign()));
    QObject::connect(rbAlignHorizontal, SIGNAL(clicked(bool)), this, SLOT(setAlign()));

    parent->addPage(widget, "Config", icon());
}



void KeyLEDs::paintInterface ( QPainter *p,
                               const QStyleOptionGraphicsItem *option, const QRect &contentsRect ) {
    p->setRenderHint ( QPainter::SmoothPixmapTransform );
    p->setRenderHint ( QPainter::Antialiasing );

    QFont f ( p->font() );
    f.setPixelSize ( fontSize );
    p->setFont ( f );

    QRect * rect1 = NULL;
    QRect * rect2 = NULL;

    int flags1;
    int flags2;

    if (alignVertical) {
        rect1 = new QRect(contentsRect.x(),contentsRect.y(),contentsRect.width(),contentsRect.height());
        rect2 = new QRect(contentsRect.x(),contentsRect.y(),contentsRect.width(),contentsRect.height());

        flags1 = Qt::AlignTop | Qt::AlignHCenter | Qt::AlignJustify;
        flags2 = Qt::AlignBottom | Qt::AlignHCenter | Qt::AlignJustify;

    } else {
        int tmp = contentsRect.width()/2;
        rect1 = new QRect(contentsRect.x(),contentsRect.y(),tmp,contentsRect.height());
        rect2 = new QRect(tmp,contentsRect.y(),tmp,contentsRect.height());

        flags1 = Qt::AlignCenter | Qt::AlignHCenter | Qt::AlignJustify;
        flags2 = flags1;
    }

    p->setPen ( ledCapsLock==true ? Qt::green : Qt::gray );
    p->drawText ( *rect1,
                  flags1,
                  DISPLAY_CAPS_LOCK );

    p->setPen ( ledNumLock==true ? Qt::green : Qt::gray );
    p->drawText ( *rect2,
                  flags2,
                  DISPLAY_NUM_LOCK );

    p->restore();
}

void KeyLEDs::setLedCapsLockState ( QString buffer ) {
    textCapsLock = getLedState ( "Caps Lock:",buffer );

    if ( textCapsLock.compare ( "on" ) ==0 ) {
        ledCapsLock=true;
    } else {
        ledCapsLock=false;
    }
}

void KeyLEDs::setLedNumLockState ( QString buffer ) {
    textNumLock = getLedState ( "Num Lock:",buffer );

    if ( textNumLock.compare ( "on" ) ==0 ) {
        ledNumLock=true;
    } else {
        ledNumLock=false;
    }
}

QString KeyLEDs::getLedState ( QString ledName,QString buffer ) {
    int initialPosition = buffer.indexOf ( ledName ) +ledName.size();

    QString text = buffer.mid ( initialPosition );
    text = text.simplified();

    int finalPosition = text.indexOf ( ":" )-2;

    text = text.mid ( 0,finalPosition );
    text = text.simplified();

    return text;
}

void KeyLEDs::updateLEDStatus() {
    QProcess cmd ( this );

    cmd.start ( "xset -q | grep Caps" );
    cmd.waitForFinished();

    QTextStream in ( cmd.readAllStandardOutput() );
    QString line = in.readAll();

    // remove any extraneous characters
    line = line.simplified();

    setLedCapsLockState ( line );
    setLedNumLockState ( line );

    update();
}

void KeyLEDs::getFontSize()
{
    fontSize = spinBox->value();
    update();
}

void KeyLEDs::setAlign() {
    if (rbAlignVertical->isChecked()) {
        alignVertical=true;
    } else {
        alignVertical=false;
    }
    update();
}

#include "keyleds.moc"
