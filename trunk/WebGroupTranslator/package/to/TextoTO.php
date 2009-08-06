<?php
$PATH_APP = (defined('PATH_APP')) ? PATH_APP : './../../';
include_once($PATH_APP.'/lib/set_path.php');
include_once(PATH_DKPC.'/package/to/TO.php');


class TextoPadraoTO extends TO
{
    public $chave;
    public $id_idioma;
    public $id_software;    
    public $texto;
}


class TextoTO extends TO {

    public $id_texto;  
    public $id_idioma;
    public $id_software;
    public $chave;
    public $texto;  
    
    
    public function popular(array $vetor)
    {       
        $this->id_texto    = TO::setInt($vetor,'id_texto');       
        $this->id_idioma   = TO::setInt($vetor,'id_idioma');
        $this->id_software = TO::setInt($vetor,'id_software');
        
        $this->chave = TO::setString($vetor,'chave');
        $this->texto = TO::setString($vetor,'texto');
    }
}


?>
