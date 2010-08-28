package com.dukitan.vpu.ui.componente;

import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class ComboBox extends JComboBox 
{
	private static final long serialVersionUID = -7872186258314692520L;
	private String msgAdicionarTitulo     = "";
	private String msgAdicionarInformacao = "";
	private String respostaModelo         = "";
	
	public void setMsgAdicionarInformacao(String msgAdicionarInformacao) {
		this.msgAdicionarInformacao = msgAdicionarInformacao;
	}

	public void setMsgAdicionarTitulo(String msgAdicionarTitulo) {
		this.msgAdicionarTitulo = msgAdicionarTitulo;
	}

	public void setRespostaModelo(String respostaModelo) {
		this.respostaModelo = respostaModelo;
	}
	
	public boolean existeItem(String novoItem)
	{
		boolean achou = false;
		String item = null;
		int total = getItemCount();

		for (int i=0; i<total; i++){
			item = getItemAt(i).toString();
			if (item.equalsIgnoreCase(novoItem)){
				achou = true;
			}
		}
		
		return achou;
	}
	
    public void addItem(Object item) 
    {
    	if ((item!=null)&&(existeItem(item.toString())==false)){
        	super.addItem(item);
    	}
    }
    
    public void adicionar()
    {
		String novaURL =JOptionPane.showInputDialog(this,msgAdicionarInformacao+"\n\rExemplo:\n\r    "+respostaModelo+"    ",msgAdicionarTitulo,JOptionPane.INFORMATION_MESSAGE);
		if ((novaURL!= null)&&(novaURL.equalsIgnoreCase("")==false)){
			addItem(novaURL);
		}
    }

    public void setItens(List lista)
    {
		Iterator i =lista.iterator();
		while(i.hasNext()){
			addItem((String) i.next());
		}
    }
}
