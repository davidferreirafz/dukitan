<?php

$PATH_APP = (defined('PATH_APP')) ? PATH_APP : './../../';
include_once($PATH_APP.'/lib/set_path.php');

include_once(PATH_DKPC.'/package/dao/DAO.php');
include_once($PATH_APP.'/package/to/IdiomaTO.php');



class IdiomaDAO extends DAO {

    function inserir(TO $idioma){
        //Não implementado, funcionalidade não disponivel.  
    }

    function atualizar(TO $idioma){
        //Não implementado, funcionalidade não disponivel.  
    }

    function excluir(TO $idioma){
        //Não implementado, funcionalidade não disponivel.  
    }

    function consultar(TO $idioma)
    {
        $parametro = array ($idioma->id_idioma);
    
        $resultado = $this->db->GetRow('SELECT * FROM idioma WHERE id_idioma=?',$parametro);
        
        $to = new IdiomaTO();
        
        if (sizeof($resultado)){
            $to->id_idioma  = $resultado['id_idioma'];    
            $to->sigla      = $resultado['sigla'];
            $to->descricao  = $resultado['descricao'];
        }
        
        return $to;
    }

    public function listar(TO $idioma)
    {
	    return $resultado = $this->db->GetAll('SELECT * FROM idioma i ORDER BY i.descricao');	
    }
}
?>
