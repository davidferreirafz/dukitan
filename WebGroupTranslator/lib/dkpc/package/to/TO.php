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

?>
