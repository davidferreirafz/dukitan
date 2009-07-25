<?php

$PATH_APP = (defined('PATH_APP')) ? $PATH_APP : './../../';

	$id_software = $_POST['id_software'];
	$id_idioma   = $_POST['id_idioma'];
	$id_versao   = $_POST['id_versao'];
	
	
	if (($id_idioma<1)||($id_versao<1)){
	    
		header("Location: ".$PATH_APP."/package/page/exportar_texto_traduzido.php?id_software=".$id_software."&id_idioma=".$id_idioma."&id_versao=".$id_versao);
		
	} else {
	    
		include_once($PATH_APP.'/package/dao/ExportacaoDAO.php');
		include_once($PATH_APP.'/package/dao/IdiomaDAO.php');
        include_once($PATH_APP.'/package/dao/VersaoDAO.php');
        

        $idiomaTO = new IdiomaTO();
        $idiomaTO->popular($_POST);
        $idioma   = new IdiomaDAO();
        $idiomaTO = $idioma->consultar($idiomaTO);

        $versaoTO = new VersaoTO();
        $versaoTO->popular($_POST);
        $versao   = new VersaoDAO();
        $versaoTO = $versao->consultar($versaoTO);

        header("Content-Type:text/plain");
	    $exportacao = new ExportacaoDAO();
	    $retorno = $exportacao->recuperarTexto($id_idioma,$id_versao);
	    
        echo "###########################################################\n";
	    echo "##  WebGroupTranslator [WGT] - BETA  #       #     v0.3  ##\n";
        echo "##           FreeSoftware - GPLv3.0  #       #   Brazil  ##\n";
        echo "###########################################################\n";
        echo "## \n";
        echo "## Software: ".$versaoTO->nome_software."  version: ".$versaoTO->versao."\n";
        echo "## Translation file for ".$idiomaTO->descricao." (".$idiomaTO->sigla.")\n";
	    echo "## \n\n"; 
	    
		foreach ($retorno as $item){
			echo $item['chave']."=".$item['texto'];
	    	echo "\n";
	    }	    
	}
?>
