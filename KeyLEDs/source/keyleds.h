/*****************************************************************************
* KeyLEDs         -  Plasmoid KeyLEDs                                       *
* E-Mail          -  davidferreira.fz@gmail.com                             *
* Site            -  http://software.dukitan.com/en/keyleds/                *
* vCard Author    -  http://david.dukitan.com/                              *
*                                                                           *
* Copyright (C) 2010  David de Almeida Ferreira                             *
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
// Here we avoid loading the header multiple times
#ifndef KEYLEDS_HEADER
#define KEYLEDS_HEADER
// We need the Plasma Applet headers
#include <KIcon>

#include <Plasma/Applet>
#include <Plasma/Svg>

class QSizeF;

// Define our plasma Applet
class KeyLEDs : public Plasma::Applet
{
    Q_OBJECT
public:
    // Basic Create/Destroy
    KeyLEDs(QObject *parent, const QVariantList &args);
    ~KeyLEDs();

    // The paintInterface procedure paints the applet to screen
    void paintInterface(QPainter *painter,
                        const QStyleOptionGraphicsItem *option,
                        const QRect& contentsRect);
    void init();

public slots:
    void updateLEDStatus();


private:
    Plasma::Svg m_svg;
    KIcon m_icon;
    QString textCapsLock;
    QString textNumLock;
    bool ledCapsLock;
    bool ledNumLock;
    QString getLedState(QString ledName,QString buffer);

    void setLedCapsLockState(QString buffer);
    void setLedNumLockState(QString buffer);

};

// This is the command that links your applet to the .desktop file
K_EXPORT_PLASMA_APPLET(keyleds, KeyLEDs)
#endif
