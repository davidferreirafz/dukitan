<?php

abstract class TO {
  
    protected function setString($vetor,$indice)
    {
        if (isset($vetor[$indice])){
            return $vetor[$indice];
        } else {
            return "";
        }
    }
    
    protected function setInt($vetor,$indice)
    {
        if (isset($vetor[$indice])){
            return $vetor[$indice];
        } else {
            return 0;
        }
    }
}

class Meta
{
    //Utilizado para aplicar filtro nas consultas;
    public $filter;

    //Utilizado para decisoes genericas;
    public $type;
}

?>
