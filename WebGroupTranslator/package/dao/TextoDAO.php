<?php

$PATH_APP = (defined('PATH_APP')) ? $PATH_APP : './../../';

include_once($PATH_APP.'/package/dao/DAO.php');
include_once($PATH_APP.'/package/to/TextoTO.php');


class TextoDAO extends DAO {

    public function __construct()
    {
        parent::__construct();
        $meta = new Meta();
    }


    function inserir(TO $texto){
        //Não implementado, funcionalidade não disponivel.	
    }

    function atualizar(TO $texto)
    {
	    $parametro = array ($texto->texto,$texto->id_texto);	

	    if ($this->db->Execute('UPDATE texto t SET t.texto=? WHERE t.id_texto=?;',$parametro)==FALSE){	
            return false;
	    } else {
	        return true;
	    }
    }

    function excluir(TO $texto){
        //Não implementado, funcionalidade não disponivel.        
    }

    function consultar(TO $texto)
    {
        $parametro = array ($texto->id_texto);
	
        $resultado = $this->db->GetRow('SELECT * FROM texto t WHERE t.id_texto=?',$parametro);
        
        $textoTO = new TextoTO();
        
        if (sizeof($resultado)){
            $textoTO->id_texto       = $resultado['id_texto'];
            $textoTO->id_software    = $resultado['id_software'];
            $textoTO->id_idioma      = $resultado['id_idioma'];
            $textoTO->chave          = $resultado['chave'];
            $textoTO->texto          = $resultado['texto'];        
        }
        
        return $textoTO;
    }
    
    public function listar(TO $texto)
    {

        $parametro = array($texto->id_software,$texto->id_idioma,$this->meta->filter.'%');

	    $sql = 'SELECT t.id_texto, t.chave, tp.texto as original, t.texto ';
	    $sql.= 'FROM texto t, texto_padrao tp WHERE t.chave=tp.chave '; 

        if ($this->meta->type=='FAULT_TRANSLATION'){
            $sql.= 'AND t.texto=tp.texto ';
        }

        $sql.= 'AND t.id_software=? AND t.id_idioma=? AND t.chave like(?) ORDER BY t.chave';
	    
    	return $resultado = $this->db->GetAll($sql,$parametro);        
    }
}
?>
