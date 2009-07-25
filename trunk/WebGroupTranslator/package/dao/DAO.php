<?php

$PATH_APP = (defined('PATH_APP')) ? $PATH_APP : './../../';

include_once($PATH_APP.'/package/dao/AbstractDAO.php');
include_once($PATH_APP.'/package/to/TO.php');


abstract class DAO extends AbstractDAO {

    public $meta;

    abstract public function inserir(TO $to);

    abstract public function atualizar(TO $to);

    abstract public function excluir(TO $to);

    abstract public function consultar(TO $to);

    abstract public function listar(TO $to);
}
?>
