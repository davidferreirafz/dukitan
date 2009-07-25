<?php 

$PATH_APP = (defined('PATH_APP')) ? $PATH_APP : './../../';
include_once($PATH_APP.'/package/util/componente.php');

$id_software = isset($_GET['id_software']) ? $_GET['id_software'] : "";
$id_idioma   = isset($_GET['id_idioma'])   ? $_GET['id_idioma']   : "";
$letra       = isset($_GET['letra'])       ? $_GET['letra']       : "";


?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="pt-br">
<head>

  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
  <title>Editar Textos Traduzidos</title>


<script language="JavaScript">

function abrirJanela(link)
{
    h=280;
    w=480;

    wleft = (screen.width - w) / 2;
    wtop = (screen.height - h) / 2;

    var janela = window.open(link, 'EditWebGroupTranslator', 'status=yes,height='+h+',width='+w, false);
    janela.moveTo(wleft, wtop);
}

function validarSubmit()
{
	document.form.botao.disabled=true;
	document.form.submit();

}

</script>

</head>


<body style="direction: ltr;">


<form  method="post" action="<?php echo $PATH_APP ?>package/action/editar_texto_sem_traducao.php" name="form">
  <table style="width: 381px; text-align: left; margin-left: auto; margin-right: auto;" border="1" cellpadding="2" cellspacing="2">

    <caption><h2>Editar Texto sem Tradução</h2></caption><tbody>

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
			echo comboboxIdioma('id_idioma',$id_idioma); 
        ?>
        
        </td>

      </tr>

      <tr>

        <td style="width: 100px;">Letra:</td>

        <td style="width: 263px;">
        
        <?php 
            echo comboboxAlfabeto('letra',$letra); 
        ?>
        
        </td>

      </tr>

    </tbody>
  </table>

  <br>

  <div style="text-align: center;"><input value="Recarregar" name="botao" type="button" onclick="javascript:validarSubmit();"><br>

  </div>

  <br>

</form>
<div style="text-align: center;">
        <?php 
			echo gridTexto($PATH_APP.'/package/action/crud_texto.php',$id_software,$id_idioma,$letra,'FAULT_TRANSLATION'); 
        ?>
  </div>
</body>
</html>
