<?php

$PATH_APP = (defined('PATH_APP')) ? $PATH_APP : './../../';

include_once($PATH_APP.'/package/to/TO.php');


class SoftwareTO extends TO
{
    public $id_software;
    public $nome;
    public $site;
}


?>
