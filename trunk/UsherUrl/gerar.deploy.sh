#!/bin/sh

export VERSAO=1.6
export PACKAGE_NAME="DukItanUsherUrl".$VERSAO."xpi"

export RAIZ=/home/desenvolvimento/dukitan
export RELEASE=$RAIZ/tags/usherurl/
export CURRENT=$RAIZ/trunk/UsherUrl/source


echo "Criando extensao/Firefox: DUU: Usher Url"
cd $CURRENT;
echo "    Criando pacote... (Zip - All)"
zip -rq4 $RELEASE/$PACKAGE_NAME * -x *SVN* *svnignore* *.svn* *.js~* *dtd~* *ties~* *rdf~ *~ 
echo "    Pacote Finalizado"

