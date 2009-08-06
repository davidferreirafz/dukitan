<?php

$PATH_APP = (defined('PATH_APP')) ? PATH_APP : './../../';
include_once($PATH_APP.'/lib/set_path.php');

include_once(PATH_DKPC.'/package/dao/DAO.php');
include_once($PATH_APP.'/package/to/TextoTO.php');


class TextoPadraoDAO extends DAO {

    function inserir(TO $textoPadrao){
	
    }

    function atualizar(TO $textoPadrao){
	
    }

    function excluir(TO $textoPadrao){
    }

    function consultar(TO $textoPadrao)
    {
        $parametro = array ($textoPadrao->chave);
	
        $resultado = $this->db->GetRow('SELECT * FROM texto_padrao tp WHERE tp.chave=?',$parametro);
        
        $textoPadraoTO = new TextoPadraoTO();
        
        if (sizeof($resultado)){
            $textoPadrao->id_software    = $resultado['id_software'];
            $textoPadrao->id_idioma      = $resultado['id_idioma'];
            $textoPadrao->chave          = $resultado['chave'];
            $textoPadrao->texto          = $resultado['texto'];        
        }
        
        return $textoPadrao;
    }
    
    public function listar(TO $textoPadrao)
    {
	      
    }

}
?>
