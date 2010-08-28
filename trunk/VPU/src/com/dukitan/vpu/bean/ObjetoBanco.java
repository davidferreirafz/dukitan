package com.dukitan.vpu.bean;

public class ObjetoBanco
{
	private String esquema = "";
	private String objeto  = "";
	
	public ObjetoBanco(String esquema, String objeto)
	{
		this.esquema = esquema;
		this.objeto = objeto;		
	}
	
	public String getEsquema() {
		return esquema;
	}
	public void setEsquema(String esquema) {
		this.esquema = esquema;
	}
	public String getObjeto() {
		return objeto;
	}
	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}
	
}
