package com.dukitan.vpu;



import gswminiport.base.config.GSWKernel;

import java.awt.Dimension;

import javax.swing.JOptionPane;

import com.dukitan.vpu.ui.janela.Principal;



public class VPUMain {

	protected Principal janela = null;

	public VPUMain()
	{	
		GSWKernel.setContextPath(".//");
	}
	public void criarJanela()
	{
		janela = new Principal();
		Dimension dim = janela.getToolkit().getScreenSize();      
		int x = (int) (dim.getWidth()  - janela.getSize().getWidth() )/2;
		int y = (int) (dim.getHeight() - janela.getSize().getHeight())/2;
		janela.setLocation(x,y);
		janela.setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		try {
			VPUMain executar = new VPUMain();
			executar.criarJanela();
		} catch (Exception e){
			 JOptionPane.showMessageDialog(null,"main: "+e.getMessage());
		}
	}

}
