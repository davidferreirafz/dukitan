<?php

$PATH_APP = (defined('PATH_APP')) ? $PATH_APP : './../../';

include_once($PATH_APP.'/package/to/TO.php');


class VersaoTO extends TO 
{
    public $id_versao;
    public $id_software;
    public $versao;
    public $nome_software;

    public function popular(array $vetor)
    {
        $this->id_versao   = TO::setInt($vetor,'id_versao');
        $this->id_software = TO::setInt($vetor,'id_software');

        $this->versao        = TO::setString($vetor,'versao');
        $this->nome_software = TO::setString($vetor,'nome_software');
    }
}


?>
