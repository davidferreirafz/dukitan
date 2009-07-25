<?php

$PATH_APP = (defined('PATH_APP')) ? $PATH_APP : './../../';

include_once($PATH_APP.'/package/dao/DAO.php');
include_once($PATH_APP.'/package/to/SoftwareTO.php');


class SoftwareDAO extends DAO {

    function inserir(TO $software)
    {
        //Não implementado, funcionalidade não disponivel.  
    }

    function atualizar(TO $software)
    {
        //Não implementado, funcionalidade não disponivel.  
    }

    function excluir(TO $software){
        //Não implementado, funcionalidade não disponivel.  
    }

    function consultar(TO $software)
    {
        //Não implementado, funcionalidade não disponivel.  
    }

    public function listar(TO $software)
    {
    	return $resultado = $this->db->GetAll('SELECT id_software,nome FROM software ORDER BY nome');	
    }
}
?>
