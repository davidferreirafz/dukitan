package com.dukitan.vpu.xml;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.dukitan.vpu.xml.bean.Usuario;

@SuppressWarnings("rawtypes")
public class ArquivoXML 
{

	String filePath   = "";
	Usuario dono      = null;
	Usuario conexao   = null;
	Vector url        = null;
	Vector driver     = null;
	
	Element  elementoRaiz = null;
	File arquivo = null;
	
	public ArquivoXML(String filePath)
	{
		this.filePath=filePath;
		
		dono     = new Usuario();
		conexao  = new Usuario();
		url      = new Vector();
		driver   = new Vector();
	}
	
	public boolean carregar() throws JDOMException, IOException
	{
		boolean carregou = false;
		
		SAXBuilder sax = new SAXBuilder();	
		arquivo        = new File(filePath);
		Document documentoXml = sax.build(arquivo);
		
		elementoRaiz = documentoXml.getRootElement();			
		
		return carregou;
	}
	
	public boolean salvar() throws JDOMException, IOException
	{
		boolean salvou = false;
		
		return salvou;
	}
	
	protected void getUsuario(String tag, Usuario usuario)
	{
		List listaRaiz = elementoRaiz.getChildren(tag);
		Iterator itRaiz = listaRaiz.iterator();

		if(itRaiz.hasNext()){			
			Element  elemento = (Element)itRaiz.next();
			
			usuario.setNome(elemento.getChildText("usuario"));
			usuario.setSenha(elemento.getChildText("senha")); 
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void getLista(String tag, Vector vetor)
	{		
		List listaRaiz   = elementoRaiz.getChildren(tag);
		Iterator itRaiz  = listaRaiz.iterator();
		
		while(itRaiz.hasNext()){			
			Element  elementoNo = (Element)itRaiz.next();
			List     listaNo    = elementoNo.getChildren();	
			Iterator itNo       = listaNo.iterator();
		
			while(itNo.hasNext()){
				Element elemento = (Element)itNo.next();
				vetor.addElement(elemento.getChildText("valor"));
			}
		}	

	}

	public Usuario getConexao() {
		getUsuario("USUARIO",conexao);
		return conexao;
	}

	public Usuario getDono() {
		getUsuario("DONO",dono);		
		return dono;
	}

	public Vector getURL() {
		getLista("URL",url);
		return url;
	}

	public Vector getDriver() {
		getLista("DRIVER",driver);		
		return driver;
	}
	
}
