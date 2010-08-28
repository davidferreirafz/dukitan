package com.dukitan.vpu.sql.script;


public class GrantScript extends AbstractScript
{
	public String getGrantTable(String esquema,String tabela,String usuario)
	{
		return "GRANT SELECT, INSERT, UPDATE, DELETE ON "+esquema+"."+tabela+" TO "+usuario+";";		
		//return "GRANT SELECT, INSERT, UPDATE, DELETE ON "+tabela+" TO "+usuario+";";
	}
	
	public String getGrantTableSelect(String esquema,String tabela,String usuario)
	{
		return "GRANT SELECT ON "+esquema+"."+tabela+" TO "+usuario+";";		
	}
	
	public String getGrantView(String esquema,String view,String usuario)
	{
		return "GRANT SELECT ON "+esquema+"."+view+" TO "+usuario+";";		
	}
	
	public String getGrantSequence(String esquema,String sequence,String usuario)
	{
		return "GRANT SELECT ON "+getEsquema()+"."+sequence+" TO "+usuario+";";		
	}	
}
