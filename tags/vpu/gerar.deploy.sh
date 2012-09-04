#!/bin/sh

export RELEASE=0
export VERSAO=1.5
export VERSAO_BASE=1.x
export NAME=vpu
export PACKAGE_NAME=$NAME-$VERSAO-$RELEASE-bin.zip

export RAIZ=/home/desenvolvimento/dukitan/
export ORIGEM=$RAIZ/tags/vpu/
export TMP=/tmp/vpu
export COPYDIR=$ORIGEM/$VERSAO_BASE
cp -R $COPYDIR $TMP
rm -rf $TMP/$VERSAO_BASE
cp $ORIGEM"/"$NAME-$VERSAO-bin.jar  $TMP

echo "Criando: $NAME $VERSAO"
cd $TMP
echo "    Criando pacote... (Zip - All)"
zip -rq6 $ORIGEM/$PACKAGE_NAME * -x *SVN* *svnignore* *.svn*
echo "    Pacote Finalizado"

