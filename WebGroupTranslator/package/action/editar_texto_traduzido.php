<?php
$PATH_APP = (defined('PATH_APP')) ? PATH_APP : './../../';
include_once($PATH_APP.'/lib/set_path.php');
include_once(PATH_SAA.'/saa.php');
//Adicionando verificacao de Seguranca
SAA::check_page($_SERVER['PHP_SELF']);


	$id_software = $_POST['id_software'];
	$id_idioma   = $_POST['id_idioma'];
    $letra       = $_POST['letra'];
	
	if (($id_software>0)&&($id_idioma>0)){
		header("Location: ".$PATH_APP."/package/page/editar_texto_traduzido.php?id_software=".$id_software."&id_idioma=".$id_idioma."&letra=".$letra);
		
	} else {
	    echo "ERRO, tente novamente";
	}
?>
