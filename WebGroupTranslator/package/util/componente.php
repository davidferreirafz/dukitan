<?php

$PATH_APP = (defined('PATH_APP')) ? $PATH_APP : './../../';


include_once($PATH_APP.'/package/dao/IdiomaDAO.php');
include_once($PATH_APP.'/package/dao/SoftwareDAO.php');
include_once($PATH_APP.'/package/dao/VersaoDAO.php');
include_once($PATH_APP.'/package/dao/TextoDAO.php');


function hidden($nome,$valor="")
{   
    $html = '<INPUT TYPE="hidden" NAME="'.$nome.'" VALUE="'.$valor.'" >';
    
    return $html;
}


function textEdit($nome,$valor,$evento="",$javascript="")
{
    $script = $evento.'='.$javascript;
    
    $html = '<INPUT TYPE="TEXT" NAME="'.$nome.'" VALUE="'.$valor.'" '.$script.'>';
    
    return $html;
}

function textArea($nome,$valor,$cols=50,$rows=4,$evento="",$javascript="")
{
    $script = $evento.'='.$javascript;
    
    $html = '<TEXTAREA NAME="'.$nome.'" COLS="'.$cols.'" ROWS="'.$rows.'" '.$script.' >';
    $html.= $valor;
    $html.= '</TEXTAREA>';
    
    return $html;
}

function comboboxSoftware($nome,$valor,$javascript="")
{
	$html = '<select size="1" name="'.$nome.'" onchange="'.$javascript.'">';
	
	$software = new SoftwareDAO();
	$lista = $software->listar(new SoftwareTO());
		
	$achou = false;
	$valores = '';
		
	for ($i=0; $i<sizeof($lista);$i++){
			
			if ($lista[$i]['id_software']==$valor){
				$selecionado = 'selected="selected"';
				$achou = true;
			} else {
				$selecionado = "";
			}
	
			$valores.='<option value="'.$lista[$i]['id_software'].'" '.$selecionado.' >'.$lista[$i]['nome'].'</option>';
		}


	if ($achou==false){
		$selecionado = 'selected="selected"';
	} else {
		$selecionado = "";
	}

	$html.= '<option '.$selecionado.' value="0">Selecione...</option>';	
	$html.= $valores;
	$html.= '</select>';
	
	return $html;
}


function comboboxVersao($nome,$valor,$id_software,$javascript="")
{
	$html = '<select size="1" name="'.$nome.'" onchange="'.$javascript.'">';
	

	$versao   = new VersaoDAO();
	$versaoTO = new VersaoTO();
    $versaoTO->id_software=$id_software;
	
	$lista = $versao->listar($versaoTO);	
		
	$achou = false;
	$valores = '';
	
	for ($i=0; $i<sizeof($lista);$i++){
			
			if ($lista[$i]['id_versao']==$valor){
				$selecionado = 'selected="selected"';
				$achou = true;
			} else {
				$selecionado = "";
			}
	
			$valores.='<option value="'.$lista[$i]['id_versao'].'" '.$selecionado.' >'.$lista[$i]['versao'].'</option>';
		}


	if ($achou==false){
		$selecionado = 'selected="selected"';
	} else {
		$selecionado = "";
	}

	$html.= '<option '.$selecionado.' value="0">Selecione...</option>';	
	$html.= $valores;	
	$html.= '</select>';
	
	return $html;
}


function comboboxIdioma($nome,$valor,$javascript="")
{
	$html = '<select size="1" name="'.$nome.'" onchange="'.$javascript.'">';
	
	$idioma = new IdiomaDAO();
	
	$lista = $idioma->listar(new IdiomaTO());
		
	$achou = false;
	$valores = '';
		
	for ($i=0; $i<sizeof($lista);$i++){
			
			if ($lista[$i]['id_idioma']==$valor){
				$selecionado = 'selected="selected"';
				$achou = true;
			} else {
				$selecionado = "";
			}
	
			$valores.='<option value="'.$lista[$i]['id_idioma'].'" '.$selecionado.' >'.$lista[$i]['descricao'].'</option>';
		}


	if ($achou==false){
		$selecionado = 'selected="selected"';
	} else {
		$selecionado = "";
	}

	$html.= '<option '.$selecionado.' value="0">Selecione...</option>';	
	$html.= $valores;
	$html.= '</select>';
	
	return $html;
}


function comboboxAlfabeto($nome,$valor,$javascript="")
{
    $html = '<select size="1" name="'.$nome.'" onchange="'.$javascript.'">';
    
    $alfa = array();
    
    for ($i=0;$i<26;$i++){
        $alfa[($i)]=array('letra'=>chr(65+$i),'descricao'=>chr(65+$i));
    }

    for ($i=0;$i<10;$i++){
        $alfa[($i+26)]=array('letra'=>chr(48+$i),'descricao'=>chr(48+$i));
    }

    $lista = $alfa;
        
    $achou = false;
    $valores = '';
        
    for ($i=0; $i<sizeof($lista);$i++){
            
            if ($lista[$i]['letra']==$valor){
                $selecionado = 'selected="selected"';
                $achou = true;
            } else {
                $selecionado = "";
            }
    
            $valores.='<option value="'.$lista[$i]['letra'].'" '.$selecionado.' >'.$lista[$i]['descricao'].'</option>';
        }

    $html.= '<option '.$selecionado.' value="">Selecione...</option>'; 
    $html.= $valores;
    $html.= '</select>';
    
    return $html;
}


function gridIdioma($link)
{
	$lista = recuperarIdioma();
	
	$html='<TABLE BORDER="1"><TR><TD>Sigla</TD><TD>Descrição</TD></TR>';
	
	for ($i=0; $i<sizeof($lista);$i++){
	
		$html.='<TR>';
		$html.='<TD><a href="'.$link.'?id_idioma='.$lista[$i]['id_idioma'].'">'.$lista[$i]['sigla'].'</TD><TD>'.$lista[$i]['descricao'].'</A></TD>';
		$html.='</TR>';	
	}
	
	$html.='</TABLE>';
	
	return $html;
}




function gridTexto($link,$id_software,$id_idioma,$letra="",$tipo="")
{
    if (($id_software<1)&&($id_idioma<1)){
        return;
    }
    
    $texto = new TextoDAO();
    
    $textoTO = new TextoTO();
    $textoTO->id_software=$id_software;
    $textoTO->id_idioma=$id_idioma;

    $texto->meta->filter=$letra;
    $texto->meta->type=$tipo;
    
    $lista = $texto->listar($textoTO);
    
    $total = sizeof($lista);

    if ($total<=0){
        $html.='Pesquisa sem resultado...';
    } else {

        $html='<DIV ALIGN="CENTER"><sub>Encontrado(s) '.$total.' registro(s).</sub><TABLE BORDER="1"><TR><TH> Chave</TH><TH>Original</TH><TH>Tradução</TH></TR>';

        for ($i=0; $i<$total;$i++){
        
            $html.='<TR>';
            $html.='<TD><a href="javascript:abrirJanela(\''.$link.'?id_texto='.$lista[$i]['id_texto'].'\');">'.$lista[$i]['chave'].'</TD><TD>'.$lista[$i]['original'].'</A></TD><TD>'.$lista[$i]['texto'].'</A></TD>';
            $html.='</TR>'; 
        }

        $html.='</TABLE></DIV>';
    }
    
    return $html;
}


?>
