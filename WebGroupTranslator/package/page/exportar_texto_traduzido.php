<?php 

$PATH_APP = (defined('PATH_APP')) ? $PATH_APP : './../../';
include_once($PATH_APP.'/package/util/componente.php');

$id_software = isset($_GET['id_software']) ? $_GET['id_software'] : "";
$id_versao   = isset($_GET['id_versao'])   ? $_GET['id_versao']   : "";
$id_idioma   = isset($_GET['id_idioma'])   ? $_GET['id_idioma']   : "";

?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="pt-br">
<head>

  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
  <title>Exportar Texto Traduzido</title>


  <meta content="David de Almeida Ferreira [F139297]" name="author">


<script language="JavaScript">

function validarSoftware(){
	document.form.submit();
}

function validarVersao(){

}

function validarSubmit(){

	document.form.botao.disabled=true;
	document.form.submit();

}

</script>

</head>


<body style="direction: ltr;">

<form enctype="multipart/form-data" method="post" action="<?php echo $PATH_APP ?>package/action/exportar_texto_traduzido.php" name="form">
  <table style="width: 381px; text-align: left; margin-left: auto; margin-right: auto;" border="1" cellpadding="2" cellspacing="2">

    <caption><h2>Exportar Texto Traduzido</h2></caption><tbody>

      <tr>

        <td style="width: 100px; text-align: left;">Software:</td>

        <td style="width: 263px;">
        
        <?php 
			echo comboboxSoftware('id_software',$id_software,'javascript:validarSoftware();'); 
        ?>

        </td>

      </tr>

      <tr>

        <td style="width: 100px;">Vers&atilde;o:</td>

        <td style="width: 263px;">
        
        <?php 
			echo comboboxVersao('id_versao',$id_versao,$id_software,'javascript:validarVersao();'); 
        ?>
        
        </td>

      </tr>

      <tr>

        <td style="width: 100px;">Idioma:</td>

        <td style="width: 263px;">
        
        <?php 
			echo comboboxIdioma('id_idioma',$id_idioma,'javascript:validarSelecao();'); 
        ?>
        
        </td>

      </tr>

    </tbody>
  </table>

  <br>

  <div style="text-align: center;"><input value="Exportar" name="botao" type="button" onclick="javascript:validarSubmit();"><br>

  </div>

  <br>

</form>

</body>
</html>
