package com.dukitan.vpu.acao.evento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import com.dukitan.vpu.bean.ConexaoInfo;
import com.dukitan.vpu.bean.MetaObjetoBanco;
import com.dukitan.vpu.bean.ObjetoBanco;
import com.dukitan.vpu.excecao.LoginException;
import com.dukitan.vpu.sql.Consulta;
import com.dukitan.vpu.sql.RegistraConexao;
import com.dukitan.vpu.sql.script.GrantScript;
import com.dukitan.vpu.ui.componente.UserInformation;
import com.dukitan.vpu.ui.formatacao.Texto;


public class OuvinteConsulta implements ActionListener
{
	private ConexaoInfo conexaoInfo     = null;
	private JTextArea componenteScript  = null;
	private GrantScript grantScript     = null;
	private UserInformation uiTable     = null;
	private UserInformation uiView      = null;
	private UserInformation uiSequence  = null;
	private JTabbedPane panelTab        = null;
	
	public void setUiTable(UserInformation uiTable)
	{
		this.uiTable = uiTable;
	}
	public void setUiView(UserInformation uiView)
	{
		this.uiView = uiView;
	}
	public void setUiSequence(UserInformation uiSequence)
	{
		this.uiSequence = uiSequence;
	}
	public OuvinteConsulta(ConexaoInfo info,JTextArea componente,JTabbedPane tab) 
	{
		super();
		conexaoInfo      = info;
		componenteScript = componente;
		grantScript      = new GrantScript();
		panelTab		 = tab;
	}
	public void actionPerformed(ActionEvent e)
	{
		try {
			RegistraConexao registra = RegistraConexao.getInstance();
			registra.conectar(conexaoInfo);		
			executar();
			panelTab.setSelectedIndex(1);			
		} catch (Exception e1){
			 JOptionPane.showMessageDialog(null,e1.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void executar()
	{	
		StringBuffer script = new StringBuffer();
		
		try {
			script.append(Texto.cabecalhoArquivo());
			script.append(verificarTable());
			script.append(verificarView());
			script.append(verificarSequence());			
		} catch (LoginException e) {
			 JOptionPane.showMessageDialog(null,e.getMessage(),"Aviso",JOptionPane.INFORMATION_MESSAGE);
		}

		
		componenteScript.setText(script.toString());
	}
	protected String verificarTable() throws LoginException
	{
		StringBuffer sql = new StringBuffer();
		ObjetoBanco ob   = null;
		try {
			Consulta consulta = new Consulta();
			consulta.checarTable();				
			MetaObjetoBanco info = consulta.getInfoTable();
			
			int total = info.totalDiferenca();
			if (total>0){
				String esquemaAnterior = "";
				for (int i=0; i<total;i++){
					ob=info.getDiferenca(i);
					
					if (esquemaAnterior.equals(ob.getEsquema())==false){
						esquemaAnterior=ob.getEsquema();
						sql.append(Texto.cabecalhoAgrupamento("Tabelas",ob.getEsquema()));
					}
					
					if (ob.getEsquema().equalsIgnoreCase(conexaoInfo.getDonoUsuario())){
						sql.append(grantScript.getGrantTable(ob.getEsquema(),ob.getObjeto(),conexaoInfo.getConexaoUsuario()));						
					} else {
						sql.append(grantScript.getGrantTableSelect(ob.getEsquema(),ob.getObjeto(),conexaoInfo.getConexaoUsuario()));						
					}

					sql.append("\n\r");				
				}
			}
			
			if (uiTable!=null){
				uiTable.setDonoQuantidade(String.valueOf(info.totalUsuarioDono()));
				uiTable.setUsuarioQuantidade(String.valueOf(info.totalUsuarioConexao()));				
				uiTable.setDiferencaQuantidade(String.valueOf(info.totalDiferenca()));				
			}
		} catch (LoginException e){
			throw e;
		} catch (Exception e) {
			 JOptionPane.showMessageDialog(null,"Aviso"+e.getMessage());
		}
		return sql.toString();
	}
	protected String verificarView() throws LoginException
	{
		StringBuffer sql = new StringBuffer();
		ObjetoBanco ob   = null;		
		try {
			Consulta consulta = new Consulta();
			consulta.checarView();	
			MetaObjetoBanco info = consulta.getInfoView();	

			int total = info.totalDiferenca();
			if (total>0){
				String esquemaAnterior = "";
				for (int i=0; i<total;i++){
					ob=info.getDiferenca(i);
					
					if (esquemaAnterior.equals(ob.getEsquema())==false){				
						esquemaAnterior=ob.getEsquema();
						sql.append(Texto.cabecalhoAgrupamento("Views",ob.getEsquema()));						
					}
					
					sql.append(grantScript.getGrantView(ob.getEsquema(),ob.getObjeto(),conexaoInfo.getConexaoUsuario()));
					sql.append("\n\r");				
				}				
			}

			if (uiView!=null){
				uiView.setDonoQuantidade(String.valueOf(info.totalUsuarioDono()));
				uiView.setUsuarioQuantidade(String.valueOf(info.totalUsuarioConexao()));
				uiView.setDiferencaQuantidade(String.valueOf(info.totalDiferenca()));				
			}
		} catch (LoginException e){
			throw e;
		} catch (Exception e) {
			 JOptionPane.showMessageDialog(null,"Aviso "+e.getMessage());
		}
		return sql.toString();
	}	
	
	protected String verificarSequence() throws LoginException
	{
		StringBuffer sql = new StringBuffer();
		ObjetoBanco ob   = null;		
		try {
			Consulta consulta = new Consulta();
			consulta.checarSequence();
			MetaObjetoBanco info = consulta.getInfoSequence();	
			int total = info.totalDiferenca();
			if (total>0){
				String esquemaAnterior = "";
				for (int i=0; i<total;i++){
					ob=info.getDiferenca(i);
					
					if (esquemaAnterior.equals(ob.getEsquema())==false){					
						esquemaAnterior=ob.getEsquema();
						sql.append(Texto.cabecalhoAgrupamento("Sequences",ob.getEsquema()));							
					}				
					sql.append(grantScript.getGrantSequence(ob.getEsquema(),ob.getObjeto(),conexaoInfo.getConexaoUsuario()));
					sql.append("\n\r");				
				}
			}

			if (uiSequence!=null){
				uiSequence.setDonoQuantidade(String.valueOf(info.totalUsuarioDono()));
				uiSequence.setUsuarioQuantidade(String.valueOf(info.totalUsuarioConexao()));
				uiSequence.setDiferencaQuantidade(String.valueOf(info.totalDiferenca()));				
			}
		} catch (LoginException e){
			throw e;
		} catch (Exception e) {
			 JOptionPane.showMessageDialog(null,"Aviso"+e.getMessage());
		}
		return sql.toString();
	}	
}
