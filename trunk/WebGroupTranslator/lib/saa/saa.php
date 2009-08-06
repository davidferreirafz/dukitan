<?php

//include_once(dirname(__FILE__).'/../config.php');

include_once(dirname(__FILE__).'/package/to/UserTO.php');
include_once(dirname(__FILE__).'/package/dao/AccessDAO.php');

class SAA
{
    static protected function getUserTO()
    {
        session_start();        
        return $_SESSION['userTO'];
    }

    static public function check_page($resource)
    {
        $userTO = SAA::getUserTO();
        $accessDAO = new AccessDAO();

        if (($userTO==NULL)||($accessDAO->isAutorizado($userTO->id_user,$resource)==FALSE)){
            header("Location: ../../lib/saa/no_access.php");
            exit;
        }
    }
}


?>
