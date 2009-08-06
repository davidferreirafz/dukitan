<?php 

$PATH_APP = (defined('PATH_APP')) ? PATH_APP : './../../';
include_once($PATH_APP.'/package/util/Componente.php');

$id_software = isset($_GET['id_software']) ? $_GET['id_software'] : "";
$id_idioma   = isset($_GET['id_idioma'])   ? $_GET['id_idioma']   : "";

?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="pt-br">
<head>

  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
  <title>Importa&ccedil;&atilde;o dos Textos Traduzidos</title>


<script language="JavaScript">

function validarSelecao(){

	document.form.arquivo.disabled=false;
}

function validarSubmit(){

	document.form.botao.disabled=true;
	document.form.submit();

}

</script>

</head>


<body style="direction: ltr;">


<form enctype="multipart/form-data" method="post" action="<?php echo $PATH_APP ?>package/action/importar_texto_traduzido.php" name="form">
  <table style="width: 381px; text-align: left; margin-left: auto; margin-right: auto;" border="1" cellpadding="2" cellspacing="2">

    <caption>Importar Texto Traduzido</caption><tbody>

      <tr>

        <td style="width: 100px; text-align: left;">Software:</td>

        <td style="width: 263px;">
        
        <?php 
			echo comboboxSoftware('id_software',$id_software); 
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

      <tr>

        <td style="width: 100px;">Arquivo:</td>

		<td style="width: 263px;"><input disabled="disabled" name="arquivo" type="file""></td>
      </tr>

    </tbody>
  </table>

  <br>

  <div style="text-align: center;"><input value="Importar" name="botao" type="button" onclick="javascript:validarSubmit();"><br>

  </div>

  <br>

</form>

</body>
</html>
