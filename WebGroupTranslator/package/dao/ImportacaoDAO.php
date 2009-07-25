<?php

$PATH_APP = (defined('PATH_APP')) ? $PATH_APP : './../../';

include_once($PATH_APP.'/package/dao/AbstractDAO.php');

class ImportacaoDAO extends AbstractDAO
{
    public function inserirConstante($constante,$id_software){
    
    	$indiceErro = 0;
    	$constante_erro = array("indice");
    	
    	for ($i=0; $i<sizeof($constante); $i++){
    	
    		set_time_limit(2);  

		    $tmpChave = $constante[$i]['chave'];
		    $parametro = array ($tmpChave,$id_software);	
		
		    if ($this->db->Execute('INSERT INTO constante (chave,id_software) VALUES (?,?)',$parametro )==FALSE){	
			    $constante_erro[$indiceErro]=$constante[$i];		
    			$indiceErro++;
		    }
    	}
    	
    	return $constante_erro;
    }

    ///Associa Constantes a uma versao
    public function inserirVinculoConstanteVersao($constante,$id_software,$id_versao){
    
    	$indiceAviso = 0;
    	$indiceErro  = 0;	
    	$constante_aviso = array("indice");
    	$constante_erro = array("indice");	
    	
    	for ($i=0; $i<sizeof($constante); $i++){
    	
    		set_time_limit(2);
    
    		$tmpChave = $constante[$i]['chave'];
    		$parametro = array ($tmpChave,$id_software,$id_versao);
    		
    		if ($this->db->Execute('INSERT INTO constante_versao (chave,id_software,id_versao) VALUES (?,?,?)',$parametro)==FALSE){	
    			$constante_aviso[$indiceAviso]=$constante[$i];		
    			$indiceAviso++;
    		}
    	}
    	
    	if (sizeof($constante_aviso)>1){
    	
    		for ($i=0; $i<sizeof($constante_aviso);$i++){
    		
    			set_time_limit(2);
    			
    			$tmpChave = $constante_aviso[$i]['chave'];
    			$parametro = array ($id_versao,$tmpChave);		
    		
    			$resultado = $this->db->GetOne('SELECT chave FROM constante_versao WHERE id_versao=? AND chave=?',$parametro);	
    			
    			
    			if ($resultado==FALSE){
    				$constante_erro[$indiceErro]=$constante_aviso[$i];	
    				$indiceErro++;
    			}
    			
    		}
    
    		return $constante_erro;		
    	}
    }

    ///Grava o Texto padrão associado a constante
    public function inserirTextoPadrao($constante,$id_software,$id_idioma){
    	
    	$indiceErro = 0;
    	$constante_erro = array("indice");
    	
    	for ($i=0; $i<sizeof($constante); $i++){
    	
    		set_time_limit(2);
    	
    		$tmpChave = $constante[$i]['chave'];
    		$tmpTexto = $constante[$i]['texto'];
    		
    		$parametro = array($tmpChave,$id_software,$id_idioma,$tmpTexto);
    		
    		if ($this->db->Execute('INSERT INTO texto_padrao (chave,id_software,id_idioma,texto) VALUES (?,?,?,?)',$parametro)==FALSE){	
    			$constante_erro[$indiceErro]=$constante[$i];		
    			$indiceErro++;
    		}
    	
    	}
    	
    	return $constante_erro;
    }

    ///Grava o Texto de tradução
    public function inserirTexto($constante,$id_software,$id_idioma){
    	
    	$indiceErro = 0;
    	$constante_erro = array("indice");
    	
    	for ($i=0; $i<sizeof($constante); $i++){
    	
    		set_time_limit(2);
    	
    		$tmpChave = $constante[$i]['chave'];
    		$tmpTexto = $constante[$i]['texto'];
 		
    		$parametro = array($tmpChave,$id_software,$id_idioma,$tmpTexto);
    		
    		if ($this->db->Execute('INSERT INTO texto (chave,id_software,id_idioma,texto) VALUES (?,?,?,?)',$parametro)==FALSE){	
    			$constante_erro[$indiceErro]=$constante[$i];		
    			$indiceErro++;
    		}
    	}
    	
    	return $constante_erro;
    }    
    
    ///Atualiza o Texto de tradução
    public function atualizarTexto($constante,$id_software,$id_idioma){
    	   
    	$indiceErro = 0;
    	$constante_erro = array("indice");
    	
    	for ($i=0; $i<sizeof($constante); $i++){
    	
    		set_time_limit(2);
    	
    		$tmpChave = $constante[$i]['chave'];
    		$tmpTexto = $constante[$i]['texto'];
    		
    		if (strlen($tmpTexto)>1){       		
        		$parametro = array($tmpTexto,$tmpChave,$id_software,$id_idioma);
        		
        		if ($this->db->Execute('UPDATE texto t SET t.texto=? WHERE t.chave=? AND t.id_software=? AND t.id_idioma=?',$parametro)==FALSE){	
        			$constante_erro[$indiceErro]=$constante[$i];		
        			$indiceErro++;
        		}
    		}
    	
    	}
    	
    	return $constante_erro;
    }    
}
?>
