package com.dukitan.vpu.bean;

public class ConexaoInfo
{
	private String bancoURL       = "";
	private String bancoDriver    = "";
	private String donoUsuario    = "";
	private String donoSenha      = "";
	private String conexaoUsuario = "";
	private String conexaoSenha   = "";
	
	public String getBancoDriver() {
		return bancoDriver;
	}
	public void setBancoDriver(String bancoDriver) {
		this.bancoDriver = bancoDriver;
	}
	public String getBancoURL() {
		return bancoURL;
	}
	public void setBancoURL(String bancoURL) {
		this.bancoURL = bancoURL;
	}
	public String getConexaoSenha() {
		return conexaoSenha;
	}
	public void setConexaoSenha(String conexaoSenha) {
		this.conexaoSenha = conexaoSenha;
	}
	public String getConexaoUsuario() {
		return conexaoUsuario;
	}
	public void setConexaoUsuario(String conexaoUsuario) {
		this.conexaoUsuario = conexaoUsuario;
	}
	public String getDonoSenha() {
		return donoSenha;
	}
	public void setDonoSenha(String donoSenha) {
		this.donoSenha = donoSenha;
	}
	public String getDonoUsuario() {
		return donoUsuario;
	}
	public void setDonoUsuario(String donoUsuario) {
		this.donoUsuario = donoUsuario;
	}

}
