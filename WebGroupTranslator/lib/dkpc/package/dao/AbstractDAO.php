<?php

include_once(PATH_LIB.'/config.php');
include_once(PATH_ADO.'/adodb.inc.php');

abstract class AbstractDAO {

    protected $db; 


    function __construct()
    {
        global $DB_DRIVER,$DB_HOST, $DB_USER, $DB_PASS, $DB_BASE;
        
        $this->db = ADONewConnection($DB_DRIVER);

        //coloca o debug como ativo
        $this->db->debug = false;

        // conectando no banco de dados
        $this->db->PConnect($DB_HOST, $DB_USER, $DB_PASS, $DB_BASE);        
    }
}
?>
