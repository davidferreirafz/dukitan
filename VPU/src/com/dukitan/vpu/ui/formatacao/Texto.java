package com.dukitan.vpu.ui.formatacao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Texto 
{
	public static String getData()
	{
		SimpleDateFormat mask = new SimpleDateFormat("dd/MM/yyyy");
		Date data = new Date();
		return mask.format(data);
	}

	public static String cabecalhoArquivo()
	{
		StringBuffer txt = new StringBuffer();
		txt.append("\n\r-- Projeto: FAnAD \n\r-- SubProjeto: VPU - Verifica��o de Permiss�es entre Usu�rios");
		txt.append("\n\r-- Script gerado autom�ticamente pela ferramenta VPU em ");
		txt.append(getData());
		txt.append("\n\r--\n\r-----------------------------------------\n\r");		
		return txt.toString();		
	}
	public static String cabecalhoAgrupamento(String titulo, String esquema)
	{
		StringBuffer txt = new StringBuffer();
		txt.append("\n\r\n\r-- \n\r-- ");
		txt.append(titulo);
		txt.append(" do Esquema: ");
		txt.append(esquema);
		txt.append("\n\r--------\n\r");				
		return txt.toString();
	}
	

}
