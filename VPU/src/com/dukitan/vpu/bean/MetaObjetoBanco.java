package com.dukitan.vpu.bean;

import java.util.Vector;

public class MetaObjetoBanco 
{
	protected Vector usuarioDono    = null;
	protected Vector usuarioConexao = null;
	protected Vector diferenca      = null;

	public MetaObjetoBanco()
	{
		diferenca = new Vector();
	}
	
	public void setDadosDono(Vector objetoDono)
	{
		usuarioDono = objetoDono;	
	}
	
	public void setDadosConexao(Vector objetoConexao)
	{
		usuarioConexao = objetoConexao;
	}
		
	public ObjetoBanco getDiferenca(int i)
	{
		//String retorno = "";
		ObjetoBanco ob = null;
		
		if ((i>=0)&&(i<diferenca.size())){
			ob = (ObjetoBanco)diferenca.get(i);
		}
		
		//return retorno;
		return ob;
	}
	
	public void checar()
	{
		boolean achou  = false;
		ObjetoBanco adm     = null;
		ObjetoBanco conexao = null;

		diferenca.clear();

		for (int d=0; d<usuarioDono.size(); d++){
			achou = false;
			adm = (ObjetoBanco) usuarioDono.get(d);
			for (int c=0; c<usuarioConexao.size(); c++){
				conexao = (ObjetoBanco) usuarioConexao.get(c);				
				if (adm.getObjeto().equals(conexao.getObjeto())){
					achou = true;
					break;
				}
			}
			if (!achou){
				diferenca.add(adm);
			}
		}
	}
	
	public int totalUsuarioDono()
	{
		return usuarioDono.size();
	}
	
	public int totalUsuarioConexao()
	{
		return usuarioConexao.size();		
	}
	
	public int totalDiferenca()
	{
		return diferenca.size();
	}
}
