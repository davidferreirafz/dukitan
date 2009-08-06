<?php

$PATH_APP = (defined('PATH_APP')) ? $PATH_APP : './../../';


function carregar($file){

    $novoIndice = 0;
    $constante = array("indice");


    $lines = file($file,FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);


    for ($i=0; $i <sizeof($lines); $i++)
    {

        $analise = $lines[$i];

        $posicao = stripos($analise, "=");

        if ($posicao > 0){

            $tmpChave = trim(substr($analise,0,$posicao));
            $tmpTexto = trim(substr($analise,$posicao+1));

            $constante[$novoIndice]= array("chave" => $tmpChave, "texto" => $tmpTexto);

            $novoIndice++;
        }

    }

    return $constante;
}


?>
