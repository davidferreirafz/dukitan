<?php
$PATH_APP = (defined('PATH_APP')) ? PATH_APP : './../../';
include_once($PATH_APP.'/lib/set_path.php');
include_once(PATH_SAA.'/saa.php');
//Adicionando verificacao de Seguranca
SAA::check_page($_SERVER['PHP_SELF']);


	$id_software = $_POST['id_software'];
	$id_idioma   = $_POST['id_idioma'];
	$arquivo     = $_FILES['arquivo']['tmp_name'];
	
	$arquivo_enviado = is_uploaded_file($arquivo);
	
	if (($id_software<1)||($id_idioma<1)||($arquivo_enviado==false)){
		header("Location: ".$PATH_APP."/package/page/atualizar_texto_traduzido.php?id_software=".$id_software."&id_idioma=".$id_idioma);
		
	} else {
		include_once($PATH_APP.'/package/dao/ImportacaoDAO.php');
		include_once($PATH_APP.'/package/util/Arquivo.php');

		$constante = carregar($arquivo);
		
	    echo "<h2>Atualizando arquivo de tradu��o</h2>";
	
	    //Atualizar Texto Traduzido
	    $importacao = new ImportacaoDAO();
	    $retorno = $importacao->atualizarTexto($constante,$id_software,$id_idioma);
	    
	    //$retorno = inserirTexto($constante,$id_software,$id_idioma);
	    
		if (sizeof($retorno)>1){
			echo "Foram encontrados ".sizeof($retorno)." erros, ao inserir as chaves:<br>";
	
			for ($i=0; $i<sizeof($retorno);$i++){
				echo $retorno[$i]['chave']."<br>";
			}	
		} else {
			echo "Sucesso, dados importados";
		}
	}
?>
