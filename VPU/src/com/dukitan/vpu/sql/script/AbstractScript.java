package com.dukitan.vpu.sql.script;

abstract public class AbstractScript 
{
	static private String esquema = "";
	
	
	protected String getEsquema() 
	{
		return esquema;
	}

	static public void setEsquema(String esquema) {
		AbstractScript.esquema = esquema;
	}


}
