#!/bin/sh

export VERSAO=1.2
export PACKAGE_NAME=keyleds.$VERSAO.bin.zip

export RAIZ=/home/desenvolvimento/dukitan/tags/keyleds
export CURRENT=$RAIZ/current/
export DISTRO=$RAIZ/distro/

echo "Criando: KeyLEDs $VERSAO"
cd $CURRENT;
echo "    Criando pacote... (Zip - All)"
zip -rq4 $DISTRO/$PACKAGE_NAME * -x *SVN* *svnignore* *.svn*
echo "    Pacote Finalizado"

