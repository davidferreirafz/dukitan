#!/bin/sh

export VERSAO=1.0
export PACKAGE_NAME=keyleds.$VERSAO.bin.zip

export RAIZ=/home/desenvolvimento/dukitan/
export PROJETO=$RAIZ/trunk/KeyLEDs/
export RELEASE=$PROJETO/bin/
export DISTRO=$RAIZ/tags/keyleds/

echo "Criando: KeyLEDs $VERSAO"
cd $RELEASE;
echo "    Criando pacote... (Zip - All)"
zip -rq4 $DISTRO/$PACKAGE_NAME * -x *SVN* *svnignore* *.svn*
echo "    Pacote Finalizado"

