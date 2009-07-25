<?php

$PATH_APP = (defined('PATH_APP')) ? $PATH_APP : './../../';

include_once($PATH_APP.'/package/dao/DAO.php');
include_once($PATH_APP.'/package/to/VersaoTO.php');


class VersaoDAO extends DAO {

    function inserir(TO $versao){
        //Não implementado, funcionalidade não disponivel.  
    }

    function atualizar(TO $versao){
        //Não implementado, funcionalidade não disponivel.  
    }

    function excluir(TO $versao){
        //Não implementado, funcionalidade não disponivel.  
    }

    function consultar(TO $versao)
    {
        $parametro = array ($versao->id_versao);
    
        $sql ='SELECT v.id_versao,v.id_software,v.versao,s.nome as nome_software ';
        $sql.='FROM versao v, software s ';
        $sql.='WHERE v.id_software=s.id_software ';
        $sql.='AND v.id_versao=?';

        $resultado = $this->db->GetRow($sql,$parametro);
        
        $to = new VersaoTO();
        
        if (sizeof($resultado)){
            $to->id_versao     = $resultado['id_versao'];
            $to->id_software   = $resultado['id_software'];
            $to->versao        = $resultado['versao'];
            $to->nome_software = $resultado['nome_software'];
        }
        
        return $to; 
    }
    
    public function listar(TO $versao)
    {
	    $parametro = array($versao->id_software);	

    	return $resultado = $this->db->GetAll('SELECT id_versao, versao FROM versao WHERE id_software=? ORDER BY versao',$parametro);        
    }
}
?>
