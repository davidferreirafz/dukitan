<?php

include_once(dirname(__FILE__).'/AbstractDAO.php');
include_once(dirname(__FILE__).'/../to/TO.php');


abstract class DAO extends AbstractDAO {


    abstract public function inserir(TO $to);

    abstract public function atualizar(TO $to);

    abstract public function excluir(TO $to);

    abstract public function consultar(TO $to);

    abstract public function listar(TO $to);
}
?>
