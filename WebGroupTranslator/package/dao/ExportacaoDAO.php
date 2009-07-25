<?php

$PATH_APP = (defined('PATH_APP')) ? $PATH_APP : './../../';

include_once($PATH_APP.'/package/dao/AbstractDAO.php');

class ExportacaoDAO extends AbstractDAO
{
    public function recuperarTexto($id_idioma,$id_versao)
    {
	    $parametro = array ($id_idioma,$id_versao);
		
	    return $resultado = $this->db->GetAll('SELECT t.chave,t.texto FROM texto t, constante_versao cv WHERE t.chave=cv.chave AND t.id_idioma=? AND cv.id_versao=? ORDER BY t.chave',$parametro);
    }
    
}
?>
