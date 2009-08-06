<?php
$PATH_APP = (defined('PATH_APP')) ? PATH_APP : './../../';
include_once($PATH_APP.'/lib/set_path.php');
include_once(PATH_DKPC.'/package/to/TO.php');


class IdiomaTO extends TO
{
    public $id_idioma;
    public $sigla;
    public $descricao;

    public function popular(array $vetor)
    {       
        $this->id_idioma = TO::setInt($vetor,'id_idioma');
        
        $this->sigla     = TO::setString($vetor,'sigla');
        $this->descricao = TO::setString($vetor,'descricao');
    }
}


?>
