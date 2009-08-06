<?php

include_once('../set_path.php');

include_once('./package/to/UserTO.php');


$userTO = new UserTO();
$userTO->email='davidferreira.fz@gmail.com';
$userTO->full_name='David Ferreira[F139297]';
$userTO->id_user=1;


session_start();
$_SESSION['userTO']=$userTO;
session_commit();

echo "Path:".dirname(__FILE__).'<BR>';
echo "File:".__FILE__.'<BR>';
echo "Script:".$_SERVER['SCRIPT_NAME'].'<BR>';

?>
