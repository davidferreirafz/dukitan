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


void KeyLEDs::createConfigurationInterface ( KConfigDialog* parent ) {
    QWidget *widget = new QWidget();
    widget->resize ( 477, 285 );
    widget->setMinimumSize ( QSize ( 477, 285 ) );

    QLabel * lbVersion = new QLabel ( widget );
    lbVersion->setText("Version");
    lbVersion->setGeometry ( QRect ( 20, 30, 78, 20 ) );

    QLabel * lbVersionNumber = new QLabel ( widget );
    lbVersionNumber->setText("1.2");
    lbVersionNumber->setGeometry ( QRect ( 90, 30, 41, 20 ) );

    QFont font;
    font.setBold ( true );
    font.setWeight ( 75 );
    lbVersionNumber->setFont ( font );


    QLabel * lbDescricao = new QLabel ( widget );
    lbDescricao->setText("KeyLEDs, is a mini application (plasmoid) for KDE 4.x, is a small application to install panels in the plasma to observing the state of CAPS LOCK and NUM LOCK.");
    lbDescricao->setGeometry ( QRect ( 20, 60, 441, 101 ) );
    lbDescricao->setTextFormat ( Qt::PlainText );
    lbDescricao->setAlignment ( Qt::AlignJustify|Qt::AlignTop );
    lbDescricao->setWordWrap ( true );

    QLabel * lbCopyright = new QLabel ( widget );
    lbCopyright->setText("Copyright (C) 2010-2011  David de Almeida Ferreira");
    lbCopyright->setGeometry ( QRect ( 20, 200, 451, 20 ) );

    QLabel * lbLicense = new QLabel ( widget );
    lbLicense->setText("KeyLEDs is free software; GPL v.3");
    lbLicense->setGeometry ( QRect ( 20, 170, 301, 20 ) );

    KUrlLabel * lburl = new KUrlLabel ( widget );
    lburl->setText ( "http://software.dukitan.com/en/keyleds/" );
    lburl->setUrl ( "http://software.dukitan.com/en/keyleds/" );
    lburl->setGeometry ( QRect ( 20, 230, 351, 20 ) );
    lburl->setOpenExternalLinks ( true );

    parent->addPage ( widget, "About" );    
    
//Config
    widget = new QWidget();
    widget->resize ( 477, 285 );
    widget->setMinimumSize ( QSize ( 477, 285 ) );
    
    QGroupBox * groupBox = new QGroupBox ( widget );
    groupBox->setMinimumSize(QSize(301, 101));    
    groupBox->setTitle ( "Leds Align" );
    groupBox->setGeometry(QRect(50, 20, 301, 121));    
    rbAlignVertical = new QRadioButton ( groupBox );
    rbAlignVertical->setText ( "Vertical" );
    rbAlignVertical->setGeometry ( QRect(20, 40, 130, 21) );
    rbAlignVertical->setChecked(true);    
    QRadioButton * rbAlignHorizontal = new QRadioButton ( groupBox );
    rbAlignHorizontal->setText ( "Horizontal" );
    rbAlignHorizontal->setGeometry ( QRect(20, 70, 130, 21) );
	
    QGroupBox * groupBox_2 = new QGroupBox ( widget );
    groupBox_2->setMinimumSize(QSize(301, 101));    
    groupBox_2->setTitle ( "Font Size" );
    groupBox_2->setGeometry ( QRect ( 50, 160, 301, 81 ) );
    spinBox = new QSpinBox ( groupBox_2 );
    spinBox->setGeometry ( QRect ( 30, 30, 55, 27 ) );
    spinBox->setMinimum ( 4 );
    spinBox->setMaximum ( 40 );
    spinBox->setValue ( 8 );

    QObject::connect ( spinBox, SIGNAL ( valueChanged ( int ) ), this, SLOT ( getFontSize() ) );
    QObject::connect ( rbAlignVertical, SIGNAL ( clicked ( bool ) ), this, SLOT ( setAlign() ) );
    QObject::connect ( rbAlignHorizontal, SIGNAL ( clicked ( bool ) ), this, SLOT ( setAlign() ) );

    parent->addPage ( widget, "Config", icon() );
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

    if ( alignVertical ) {
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

void KeyLEDs::getFontSize() {
    fontSize = spinBox->value();
    update();
}

void KeyLEDs::setAlign() {
    if ( rbAlignVertical->isChecked() ) {
        alignVertical=true;
    } else {
        alignVertical=false;
    }
    update();
}

#include "keyleds.moc"
