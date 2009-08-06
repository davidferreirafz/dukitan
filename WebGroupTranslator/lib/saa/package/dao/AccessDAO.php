<?php

include_once(PATH_DKPC.'/package/dao/AbstractDAO.php');

class AccessDAO extends AbstractDAO
{
    public function isAutorizado($id_user,$resource)
    {
	    $parametro = array ($id_user,$resource);
		
	    $sql = 'SELECT count(*) as resultado ';
	    $sql.= 'FROM saa_access a, saa_group g, saa_autorization au, saa_resource r ';
		$sql.= 'WHERE a.id_user=? ';
        $sql.= 'AND a.id_group=g.id_group ';
        $sql.= 'AND g.id_group=au.id_group ';
        $sql.= 'AND au.id_resource=r.id_resource ';
        $sql.= 'AND r.value=? ';
	    
	    $resultado = $this->db->GetCol($sql,$parametro);
	    
	    if ($resultado[0]['resultado'] == 1){
	        return true;
	    } else {
	        return false;
	    }
    }
    
}
?>
