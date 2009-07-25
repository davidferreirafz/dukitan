<?php 

$PATH_APP = (defined('PATH_APP')) ? $PATH_APP : './../../';
include_once($PATH_APP.'/package/util/componente.php');
include_once($PATH_APP.'/package/to/TextoTO.php');


    session_start();
    $textoTO = $_SESSION['textoTO'];
    $textoPadraoTO = $_SESSION['textoPadraoTO'];
    
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="pt-br">
<head>

  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
  <title>CRUD Texto</title>


  <meta content="David de Almeida Ferreira [F139297]" name="author">


<script language="JavaScript">


function validarSubmit(){

	document.form.botao.disabled=true;
	document.form.acao.value="SALVAR";
	document.form.submit();

}

</script>

</head>


<body style="direction: ltr;">

<form  method="post" action="<?php echo $PATH_APP ?>package/action/crud_texto.php" name="form">

        <?php 
			echo hidden('id_texto',$textoTO->id_texto);
            echo hidden('acao',""); 
        ?>
        
  <table style="width: 381px; text-align: left; margin-left: auto; margin-right: auto;" border="1" cellpadding="2" cellspacing="2">

    <caption>CRUD Texto</caption><tbody>

      <tr>

        <td style="width: 100px; text-align: left;">Chave</td>

        <td style="width: 263px;">
        
        <?php 
			echo $textoTO->chave; 
        ?>

        </td>

      </tr>
      
      <tr>

        <td style="width: 100px; text-align: left;">Texto Original</td>

        <td style="width: 263px;">
        
        <?php 
			echo $textoPadraoTO->texto; 
        ?>

        </td>

      </tr>
      <tr>

        <td style="width: 100px; text-align: left;">Texto:</td>

        <td style="width: 263px;">
        
        <?php 
			echo textArea('texto',$textoTO->texto); 
        ?>

        </td>

      </tr>

    </tbody>
  </table>

  <br>

  <div style="text-align: center;"><input value="Salvar" name="botao" type="button" onclick="javascript:validarSubmit();">
<input value="Fechar" name="botao1" type="button" onclick="javascript:window.close();">
<br>

  </div>

  <br>

</form>

</body>
</html>
