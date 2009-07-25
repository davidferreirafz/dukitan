<?php

$PATH_APP = (defined('PATH_APP')) ? $PATH_APP : './../../';

	$id_software = $_POST['id_software'];
	$id_idioma   = $_POST['id_idioma'];
    $letra       = $_POST['letra'];
	
	
	if (($id_software>0)&&($id_idioma>0)){

	   	header("Location: ".$PATH_APP."/package/page/editar_texto_sem_traducao.php?id_software=".$id_software."&id_idioma=".$id_idioma."&letra=".$letra);

	} else {

        echo "ERRO tente novamente";	   

	}
?>
