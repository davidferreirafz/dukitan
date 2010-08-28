package com.dukitan.vpu.sql;

import gswminiport.base.banco.GSWPoolManager;
import gswminiport.base.banco.pool.GSWConnection;
import gswminiport.base.banco.pool.GSWPool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.dukitan.vpu.bean.MetaObjetoBanco;
import com.dukitan.vpu.bean.ObjetoBanco;
import com.dukitan.vpu.sql.script.DataScript;



public class Consulta
{

	private MetaObjetoBanco table    = null;
	private MetaObjetoBanco view     = null;
	private MetaObjetoBanco sequence = null;		
	private DataScript      script   = null;
	
	public Consulta()
	{
		table    = new MetaObjetoBanco();
		view     = new MetaObjetoBanco();
		sequence = new MetaObjetoBanco();
		
		script = new DataScript();		
	}
	
	public MetaObjetoBanco getInfoTable()
	{
		return table;
	}

	public MetaObjetoBanco getInfoView()
	{
		return view;
	}
	
	public MetaObjetoBanco getInfoSequence()
	{
		return sequence;
	}
	
	private void checarObjetoUsuarioDono(String sql,MetaObjetoBanco objeto) throws Exception
	{
	    GSWConnection conexao = null;
	    Statement     stmt    = null;
	    GSWPool pool          = null;
		Vector lista          = new Vector();

	    try {
	    	pool = GSWPoolManager.getInstance().getGSWPool("dono");
            conexao = pool.getConnection();
            stmt    = conexao.getStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){           	
            	lista.add(new ObjetoBanco(rs.getString(1),rs.getString(2)));
            }
	    } finally {
	    	objeto.setDadosDono(lista);	    	
			if (stmt != null){
				try {
					stmt.close();
				} catch (SQLException e1) {
					System.out.println(e1.getMessage());                    
				}
			}
			if (conexao != null){
				pool.releaseConnection(conexao);
			}
	    }	    
	}
	
	private void checarObjetoUsuarioConexao(String sql,MetaObjetoBanco objeto) throws Exception
	{
	    GSWConnection conexao = null;
	    Statement     stmt    = null;
	    GSWPool pool          = null;
		Vector lista          = new Vector();

	    ResultSet rs = null;		

    	try {            
	    	pool = GSWPoolManager.getInstance().getGSWPool("conexao");
            
	    	conexao = pool.getConnection();
            stmt    = conexao.getStatement();
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
            	lista.add(new ObjetoBanco(rs.getString(1),rs.getString(2)));
            }
	    } finally {
	    	objeto.setDadosConexao(lista);
	        if (stmt != null){
	            try {
	                stmt.close();
	            } catch (SQLException e1) {
	            	 System.out.println(e1.getMessage());                    
	            }
	        }
	        if (conexao != null){
	             pool.releaseConnection(conexao);
	        }     
	    }
	}
	
	public void checarTable() throws Exception
	{
		checarObjetoUsuarioDono(script.getSQLTable(),table);
		checarObjetoUsuarioConexao(script.getSQLTable(),table);
		table.checar();
	}	
	
	public void checarView() throws Exception
	{
		checarObjetoUsuarioDono(script.getSQLView(),view);
		checarObjetoUsuarioConexao(script.getSQLView(),view);
		view.checar();
	}
	
	public void checarSequence() throws Exception
	{
		checarObjetoUsuarioDono(script.getSQLSequence(),sequence);
		checarObjetoUsuarioConexao(script.getSQLSequence(),sequence);
		sequence.checar();
	}		
}
