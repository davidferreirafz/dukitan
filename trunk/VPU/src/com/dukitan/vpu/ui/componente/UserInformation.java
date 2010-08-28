package com.dukitan.vpu.ui.componente;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserInformation extends JPanel
{
	private static final long serialVersionUID = 6769632013409189992L;

	//dados
	protected JLabel donoQuantidade      = null;	
	protected JLabel usuarioQuantidade   = null;
	protected JLabel diferencaQuantidade = null;	

	protected JLabel labelUsuario   = null;
	protected JLabel labelDono      = null;
	protected JLabel labelDiferenca = null;	

	
	public UserInformation(String titulo)
	{
		super();
		
		labelDono = new JLabel();
		labelDono.setText("Dono do Esquema:");
		labelDono.setBounds(new java.awt.Rectangle(10,10,136,61));
		labelUsuario = new JLabel();
		labelUsuario.setText("Usu�rio do Esquema:");
		labelUsuario.setBounds(new java.awt.Rectangle(10,40,136,61));
		labelDiferenca = new JLabel();
		labelDiferenca.setText("Faltando:");
		labelDiferenca.setBounds(new java.awt.Rectangle(10,70,136,61));
		
		donoQuantidade = new JLabel();
		donoQuantidade.setText("00");
		donoQuantidade.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
		donoQuantidade.setBounds(new java.awt.Rectangle(141,10,82,61));		
		usuarioQuantidade = new JLabel();
		usuarioQuantidade.setText("00");
		usuarioQuantidade.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
		usuarioQuantidade.setBounds(new java.awt.Rectangle(141,40,82,61));
		diferencaQuantidade = new JLabel();
		diferencaQuantidade.setText("00");
		diferencaQuantidade.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
		diferencaQuantidade.setBounds(new java.awt.Rectangle(141,70,82,61));
		
		setLayout(null);
		setBorder(javax.swing.BorderFactory.createTitledBorder(null, titulo, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
		add(labelDono, null);
		add(donoQuantidade, null);
		add(labelUsuario, null);
		add(usuarioQuantidade, null);
		add(labelDiferenca,null);
		add(diferencaQuantidade, null);		
	}


	public String getDonoQuantidade()
	{
		return donoQuantidade.getText();
	}

	public void setDonoQuantidade(String quantidade)
	{
		donoQuantidade.setText(quantidade);
	}

	public String getUsuarioQuantidade()
	{
		return usuarioQuantidade.getText();
	}

	public void setUsuarioQuantidade(String quantidade)
	{
		usuarioQuantidade.setText(quantidade);
	}

	public String getDiferencaQuantidade() {
		return diferencaQuantidade.getText();
	}
	
	public void setDiferencaQuantidade(String quantidade)
	{
		diferencaQuantidade.setText(quantidade);
	}
	
	public JPanel getPanel()
	{
		return this;
	}
}
