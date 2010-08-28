package com.dukitan.vpu.sql.script;

public class DataScript extends AbstractScript
{
	public String getSQLTable()
	{
		//return "SELECT OWNER,TABLE_NAME FROM all_olap_tables WHERE table_type='TABLE' ORDER BY owner,table_name";
		return "SELECT owner,table_name FROM all_tables WHERE owner not in ('SYS','MDSYS','WKSYS','OLAPSYS','XDB','PUBLIC','SYSTEM','CTXSYS','WMSYS') ORDER BY owner,table_name";
	}

	public String getSQLView()
	{
		//return "SELECT OWNER,TABLE_NAME FROM all_olap_tables WHERE table_type='VIEW' ORDER BY owner,table_name";
		return "SELECT owner,view_name FROM all_views WHERE owner not in ('SYS','MDSYS','WKSYS','OLAPSYS','XDB','PUBLIC','SYSTEM','CTXSYS','WMSYS') ORDER BY owner,view_name";		
	}

	public String getSQLSequence()
	{
		//return "select OWNER,OBJECT_NAME from ALL_OBJECTS where owner = '"+getEsquema()+"' and OBJECT_TYPE = 'SEQUENCE'";
		return "SELECT sequence_owner,sequence_name FROM all_sequences WHERE sequence_owner not in ('SYS','MDSYS','WKSYS','OLAPSYS','XDB','PUBLIC','SYSTEM','CTXSYS','WMSYS') ORDER BY sequence_owner,sequence_name";		
	}	
	

}
