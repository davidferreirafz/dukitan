package com.dukitan.vpu.ui.componente;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


/**
 * Classe para exibi��o de uma imagem dentro de um JPanel (Swing)
 * Esta classe foi desenvolvida baseando na classe PicturePanel de
 * Daniel F. Martins <daniel_tritone@terra.com.br>
 * 
 * @author David de A. Ferreira <davidferreira@uol.com.br>
 * @version 1.0
 */
public class ImagePanel extends JPanel 
{
	private static final long serialVersionUID = -5855213845809007082L;
	private Image image;

	public void loadImage(String path)
	{
		this.image = new ImageIcon(path).getImage();

		/* Redimensiona a imagem */
		this.image = this.image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		if (this.image != null){
			g.drawImage(image, 0, 0, this);
		}
	}	
	
}
