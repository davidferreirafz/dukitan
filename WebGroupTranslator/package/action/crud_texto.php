<?php

$PATH_APP = (defined('PATH_APP')) ? $PATH_APP : './../../';

include_once($PATH_APP.'/package/dao/TextoDAO.php');
include_once($PATH_APP.'/package/dao/TextoPadraoDAO.php');


    $acao     = isset($_POST['acao'])?$_POST['acao']:"";

             
    switch (strtolower($acao)){
        
        case "salvar":

                $textoTO = new TextoTO();
                $textoTO->popular($_POST);
                
                $texto = new TextoDAO();
                
                if ($texto->atualizar($textoTO)==true){
                    echo "<center>Sucesso! Atualizado</center>";
                    echo "<script language=\"JavaScript\">window.close();</script>";
                } else {
                    header("Location: ".$PATH_APP."/package/page/crud_texto.php");
                }            
            break;

        default: 
                $textoTO = new TextoTO();
                $textoTO->popular($_GET);

                $texto = new TextoDAO();                
                $textoTO = $texto->consultar($textoTO);
                
                $textoPadrao = new TextoPadraoDAO();
                $textoPadraoTO = new TextoPadraoTO();
                $textoPadraoTO->chave=$textoTO->chave;
                $textoPadraoTO = $textoPadrao->consultar($textoPadraoTO);                
 
                session_start();
                $_SESSION['textoTO']=$textoTO;
                $_SESSION['textoPadraoTO']=$textoPadraoTO;
                session_commit();
                
               header("Location: ".$PATH_APP."/package/page/crud_texto.php");                
                
            break;
    }	
	
	
	
	/*if (($id_software>0)&&($id_idioma>0)){
	    
		header("Location: ".$PATH_APP."/page/editar_texto_traduzido.php?id_software=".$id_software."&id_idioma=".$id_idioma);
		
	} */
?>
