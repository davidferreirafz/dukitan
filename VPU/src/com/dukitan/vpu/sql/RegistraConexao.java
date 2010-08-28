package com.dukitan.vpu.sql;

import com.dukitan.vpu.bean.ConexaoInfo;
import com.dukitan.vpu.excecao.LoginException;
import com.dukitan.vpu.excecao.LoginInformationException;
import com.dukitan.vpu.sql.script.AbstractScript;

import gswminiport.base.banco.GSWPoolManager;
import gswminiport.base.exception.GSWConnectionException;

public class RegistraConexao 
{
	static private RegistraConexao instance = null;   
	
	private RegistraConexao()
	{
	}
	
	static public RegistraConexao getInstance()
	{
		if (instance==null){
			instance = new RegistraConexao();
		}
		return instance;
	}
	
	public void conectar(ConexaoInfo info) throws LoginException,Exception
	{
		AbstractScript.setEsquema(info.getDonoUsuario().toUpperCase());
		
		if ((info.getBancoDriver().equals("")==false)&&(info.getBancoURL().equals("")==false)){
			try {
				GSWPoolManager.getInstance().criarGSWPool("dono",info.getDonoUsuario(),info.getDonoSenha(),info.getBancoDriver(),info.getBancoURL(),2);
			} catch (GSWConnectionException e){
				throw new Exception("Problemas na Conex�o\n\r  *Servidor Off-Line\n\r  *URI JDBC errada");
			} catch (Exception e) {
				throw new Exception("[Usu�rio do Esquema]: Verifique o usu�rio e senha\n\r "+e.getMessage());				
			}
			try {			
				GSWPoolManager.getInstance().criarGSWPool("conexao",info.getConexaoUsuario(),info.getConexaoSenha(),info.getBancoDriver(),info.getBancoURL(),2);
			} catch (GSWConnectionException e){
				throw new Exception("Problemas na Conex�o\n\r  *Servidor Off-Line\n\r  *URI JDBC errada");
			} catch (Exception e) {
				throw new Exception("[Usu�rio do Esquema]: Verifique o usu�rio e senha\n\r "+e.getMessage());				
			}	
		} else {
			throw new LoginInformationException("Sem informacoes de Driver ou URL");
		}
	}
}
