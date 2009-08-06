<?php

include_once(PATH_DKPC.'/package/to/TO.php');


class UserTO extends TO 
{
    public $id_user;
    public $email;
    public $pass;
    public $full_name;

    public function popular(array $vetor)
    {
        $this->$id_user  = TO::setInt($vetor,'id_versao');
        $this->email     = TO::setString($vetor,'email');
        $this->pass      = TO::setString($vetor,'pass');
        $this->full_name = TO::setString($vetor,'full_name');        
    }
}


?>
