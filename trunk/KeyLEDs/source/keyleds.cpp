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
#include "keyleds.h"
#include <QPainter>
#include <QFontMetrics>
#include <QSizeF>
#include <QFile>
#include <KLocale>
#include <QTimer>
#include <QProcess>

#include <plasma/svg.h>
#include <plasma/theme.h>

KeyLEDs::KeyLEDs(QObject *parent, const QVariantList &args)
        : Plasma::Applet(parent, args),
        m_svg(this),
        m_icon("document")
{
    // this will get us the standard applet background, for free!
    setBackgroundHints(DefaultBackground);
    setHasConfigurationInterface(true);
    resize(100, 100);

    setWindowTitle("LED Status");
    ledCapsLock = false;
    ledNumLock  = false;

    updateLEDStatus();
}


KeyLEDs::~KeyLEDs()
{
    if (hasFailedToLaunch()) {
        // Do some cleanup here
    } else {
        // Save settings
    }
}

void KeyLEDs::init()
{
    // kick off a refresh timer
    QTimer* m_timer = new QTimer(this);
    connect(m_timer, SIGNAL(timeout()), this, SLOT(updateLEDStatus()));
    m_timer->start(1000);
}


void KeyLEDs::paintInterface(QPainter *p,
                             const QStyleOptionGraphicsItem *option, const QRect &contentsRect)
{
    p->setRenderHint(QPainter::SmoothPixmapTransform);
    p->setRenderHint(QPainter::Antialiasing);

    // Now we draw the applet, starting with our svg
    m_svg.resize((int)contentsRect.width(), (int)contentsRect.height());
    m_svg.paint(p, (int)contentsRect.left(), (int)contentsRect.top());


    QFont f(p->font());
    f.setPixelSize(8);
    p->setFont(f);

    p->setPen(ledCapsLock==true ? Qt::green : Qt::gray);
    p->drawText(contentsRect,
                Qt::AlignTop | Qt::AlignHCenter | Qt::AlignJustify,
                "CAPS\n\rLOCK");

    p->setPen(ledNumLock==true ? Qt::green : Qt::gray);
    p->drawText(contentsRect,
                Qt::AlignBottom | Qt::AlignHCenter | Qt::AlignJustify,
                "NUM\n\rLOCK");
    p->restore();
}

void KeyLEDs::setLedCapsLockState(QString buffer)
{
    textCapsLock = getLedState("Caps Lock:",buffer);

    if (textCapsLock.compare("on")==0) {
        ledCapsLock=true;
    } else {
        ledCapsLock=false;
    }
}

void KeyLEDs::setLedNumLockState(QString buffer)
{
    textNumLock = getLedState("Num Lock:",buffer);

    if (textNumLock.compare("on")==0) {
        ledNumLock=true;
    } else {
        ledNumLock=false;
    }
}

QString KeyLEDs::getLedState(QString ledName,QString buffer)
{
    int initialPosition = buffer.indexOf(ledName)+ledName.size();

    QString text = buffer.mid(initialPosition);
    text = text.simplified();

    int finalPosition = text.indexOf(":")-2;

    text = text.mid(0,finalPosition);
    text = text.simplified();

    return text;
}

void KeyLEDs::updateLEDStatus()
{
    QProcess cmd(this);

    cmd.start("xset -q | grep Caps");
    cmd.waitForFinished();

    QTextStream in(cmd.readAllStandardOutput());
    QString line = in.readAll();

    // remove any extraneous characters
    line = line.simplified();

    setLedCapsLockState(line);
    setLedNumLockState(line);

    update();
}


#include "keyleds.moc"
