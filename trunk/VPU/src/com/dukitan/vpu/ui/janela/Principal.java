package com.dukitan.vpu.ui.janela;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

import org.jdom.JDOMException;

import com.dukitan.vpu.acao.evento.OuvinteConsulta;
import com.dukitan.vpu.bean.ConexaoInfo;
import com.dukitan.vpu.ui.componente.ComboBox;
import com.dukitan.vpu.ui.componente.ImagePanel;
import com.dukitan.vpu.ui.componente.UserInformation;
import com.dukitan.vpu.xml.ArquivoXML;
import com.dukitan.vpu.xml.bean.Usuario;


public class Principal extends JFrame {

	private static final long serialVersionUID = -2624245426643411739L;
	
	private JPanel jPanel = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JPanel jPanel3 = null;
	private JPanel jPanel4 = null;
	private JPanel jPanel5 = null;
	private JPanel jPanel6 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JTextField donoUsuario = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JPasswordField donoSenha = null;
	private JLabel jLabel2 = null;
	private JTextField conexaoUsuario = null;
	private JLabel jLabel3 = null;
	private JPasswordField conexaoSenha = null;
	private JPanel jPanel7 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JPanel jPanel8 = null;
	private JPanel uiTable = null;
	private JPanel uiSequence = null;
	private JPanel uiView = null;
	private JPanel jPanel12 = null;
	private ConexaoInfo conexaoInfo = null;
	private JPanel jPanel13 = null;
	private JScrollPane jScrollPane = null;
	private JTextArea ScriptTextArea = null;

	private JPanel jPanel9 = null;

	private JTextPane jTextPane = null;

	private JPanel jPanel10 = null;

	private JLabel jLabel12 = null;

	private JLabel jLabel13 = null;	

	private ComboBox comboDriver = null;

	private ComboBox comboURL = null;

	private JButton jButton2 = null;

	private ImagePanel jImagePanel = null;

	/**
	 * This is the default constructor
	 */
	public Principal() {
		super();
		conexaoInfo = new ConexaoInfo();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(467, 401);
		this.setContentPane(getJPanel());
		this.setComponentOrientation(java.awt.ComponentOrientation.LEFT_TO_RIGHT);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("FAnAD :: VPU");
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowOpened(java.awt.event.WindowEvent e) {
				ArquivoXML configuracao = new ArquivoXML(".//config/vpu.xml");
				try {
					configuracao.carregar();
					Usuario user = configuracao.getDono();
					donoUsuario.setText(user.getNome());
					donoSenha.setText(user.getSenha());					
					user = configuracao.getConexao();
					conexaoUsuario.setText(user.getNome());
					conexaoSenha.setText(user.getSenha());
					
					comboDriver.setItens(configuracao.getDriver());
					comboURL.setItens(configuracao.getURL());
				} catch (JDOMException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(getJTabbedPane(), null);
			jPanel.add(getJPanel6(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setComponentOrientation(java.awt.ComponentOrientation.LEFT_TO_RIGHT);
			jTabbedPane.setName("tabContainer");
			jTabbedPane.setBounds(new java.awt.Rectangle(0,0,461,327));
			jTabbedPane.addTab("Informações", null, getJPanel1(), null);
			jTabbedPane.addTab("Resultado das Permissões", null, getJPanel2(), null);
			jTabbedPane.addTab("Script", null, getJPanel13(), null);
			jTabbedPane.addTab("Sobre", null, getJPanel3(), null);			
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.setRows(2);
			jPanel1 = new JPanel();
			jPanel1.setLayout(gridLayout1);
			jPanel1.add(getJPanel8(), null);
			jPanel1.add(getJPanel7(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			GridLayout gridLayout2 = new GridLayout();
			gridLayout2.setRows(2);
			jPanel2 = new JPanel();
			jPanel2.setLayout(gridLayout2);
			jPanel2.add(getUiTable(), null);
			jPanel2.add(getUiView(), null);
			jPanel2.add(getUiSequence(), null);
			jPanel2.add(getJPanel12(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setLayout(null);
			jPanel3.add(getJPanel9(), null);
			jPanel3.add(getJImagePanel(), null);
		}
		return jPanel3;
	}

	/**
	 * This method initializes jPanel4	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new java.awt.Rectangle(30,83,128,22));
			jLabel1.setText("Senha");
			jLabel = new JLabel();
			jLabel.setBounds(new java.awt.Rectangle(30,25,128,22));
			jLabel.setText("Usuário");
			jPanel4 = new JPanel();
			jPanel4.setLayout(null);
			jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dono do Esquema", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
			jPanel4.add(getDonoUsuario(), null);
			jPanel4.add(jLabel, null);
			jPanel4.add(jLabel1, null);
			jPanel4.add(getDonoSenha(), null);
		}
		return jPanel4;
	}

	/**
	 * This method initializes jPanel5	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jLabel3 = new JLabel();
			jLabel3.setBounds(new java.awt.Rectangle(30,83,128,22));
			jLabel3.setText("Senha");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new java.awt.Rectangle(30,25,128,22));
			jLabel2.setText("Usuário");
			TitledBorder titledBorder = javax.swing.BorderFactory.createTitledBorder(null, "Dono do Esquema", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null);
			titledBorder.setTitle("Usuário de Conexão");
			jPanel5 = new JPanel();
			jPanel5.setLayout(null);
			jPanel5.setBorder(titledBorder);
			jPanel5.add(jLabel2, null);
			jPanel5.add(getConexaoUsuario(), null);
			jPanel5.add(jLabel3, null);
			jPanel5.add(getConexaoSenha(), null);
		}
		return jPanel5;
	}

	/**
	 * This method initializes jPanel6	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel6() {
		if (jPanel6 == null) {
			jPanel6 = new JPanel();
			jPanel6.setBounds(new java.awt.Rectangle(0,328,461,46));
			jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.LOWERED));
			jPanel6.setLayout(null);
			jPanel6.add(getJButton(), null);
			jPanel6.add(getJButton1(), null);
		}
		return jPanel6;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton()
	{	
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new java.awt.Rectangle(28,11,101,25));
			jButton.setText("Executar");
			
			OuvinteConsulta ouvinte = new OuvinteConsulta(conexaoInfo,ScriptTextArea,jTabbedPane);
			
			ouvinte.setUiTable((UserInformation) getUiTable());
			ouvinte.setUiView((UserInformation) getUiView());
			ouvinte.setUiSequence((UserInformation) getUiSequence());

			jButton.addActionListener(ouvinte);				
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					configurar();
				}
			});	
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new java.awt.Rectangle(333,10,85,26));
			jButton1.setText("Fechar");

			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes DonoUsuario	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDonoUsuario() {
		if (donoUsuario == null) {
			donoUsuario = new JTextField();
			donoUsuario.setBounds(new java.awt.Rectangle(30,47,128,22));
			donoUsuario.setText("");
		}
		return donoUsuario;
	}

	/**
	 * This method initializes DonoSenha	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getDonoSenha() {
		if (donoSenha == null) {
			donoSenha = new JPasswordField();
			donoSenha.setBounds(new java.awt.Rectangle(30,105,128,22));
			donoSenha.setText("");
		}
		return donoSenha;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getConexaoUsuario() {
		if (conexaoUsuario == null) {
			conexaoUsuario = new JTextField();
			conexaoUsuario.setBounds(new java.awt.Rectangle(30,47,128,22));
			conexaoUsuario.setText("");
		}
		return conexaoUsuario;
	}

	/**
	 * This method initializes jPasswordField	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getConexaoSenha() {
		if (conexaoSenha == null) {
			conexaoSenha = new JPasswordField();
			conexaoSenha.setBounds(new java.awt.Rectangle(30,105,128,22));
			conexaoSenha.setText("");
		}
		return conexaoSenha;
	}

	/**
	 * This method initializes jPanel7	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel7() {
		if (jPanel7 == null) {
			jLabel5 = new JLabel();
			jLabel5.setBounds(new java.awt.Rectangle(20,70,120,16));
			jLabel5.setText("JDBC Connection");
			jLabel4 = new JLabel();
			jLabel4.setText("Driver");
			jLabel4.setBounds(new java.awt.Rectangle(20,20,120,16));
			jPanel7 = new JPanel();
			jPanel7.setLayout(null);
			jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Conexão", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
			jPanel7.add(jLabel4, null);
			jPanel7.add(jLabel5, null);
			jPanel7.add(getComboDriver(), null);
			jPanel7.add(getComboURL(), null);
			jPanel7.add(getJButton2(), null);
		}
		return jPanel7;
	}

	/**
	 * This method initializes jPanel8	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel8() {
		if (jPanel8 == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(1);
			jPanel8 = new JPanel();
			jPanel8.setLayout(gridLayout);
			jPanel8.add(getJPanel4(), null);
			jPanel8.add(getJPanel5(), null);
		}
		return jPanel8;
	}

	/**
	 * This method initializes jPanel9	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getUiTable() {
		if (uiTable == null) {
			uiTable = new UserInformation("Tabelas");
		}
		return uiTable;
	}

	/**
	 * This method initializes jPanel10	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getUiSequence() {
		if (uiSequence == null) {
			uiSequence = new UserInformation("Sequences");
		}
		return uiSequence;
	}

	/**
	 * This method initializes jPanel11	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getUiView() {
		if (uiView == null) {
			uiView = new UserInformation("Views");
		}
		return uiView;
	}

	/**
	 * This method initializes jPanel12	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel12() {
		if (jPanel12 == null) {
			TitledBorder titledBorder3 = javax.swing.BorderFactory.createTitledBorder(null, "Tabelas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null);
			titledBorder3.setTitle("[etc]");
			jPanel12 = new JPanel();
			jPanel12.setLayout(null);
			jPanel12.setBorder(titledBorder3);
		}
		return jPanel12;
	}

	/**
	 * This method initializes jPanel13	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel13() {
		if (jPanel13 == null) {
			GridLayout gridLayout3 = new GridLayout();
			gridLayout3.setRows(1);
			jPanel13 = new JPanel();
			jPanel13.setLayout(gridLayout3);
			jPanel13.add(getJScrollPane(), null);
		}
		return jPanel13;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getScriptTextArea());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getScriptTextArea() {
		if (ScriptTextArea == null) {
			ScriptTextArea = new JTextArea();
			ScriptTextArea.setTabSize(4);
			ScriptTextArea.setToolTipText("[Temporariamente] Para copiar use: CTRL+A (Selecionar tudo) e CTRL+C (Copiar)");
			ScriptTextArea.setWrapStyleWord(true);
		}
		return ScriptTextArea;
	}
	
	private void configurar()
	{
		conexaoInfo.setBancoDriver(comboDriver.getSelectedItem().toString());
		conexaoInfo.setBancoURL(comboURL.getSelectedItem().toString());		
		conexaoInfo.setDonoUsuario(donoUsuario.getText());
		conexaoInfo.setDonoSenha(new String(donoSenha.getPassword()));
		conexaoInfo.setConexaoUsuario(conexaoUsuario.getText());
		conexaoInfo.setConexaoSenha(new String(conexaoSenha.getPassword()));
	}

	/**
	 * This method initializes jPanel9	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel9() {
		if (jPanel9 == null) {
			jPanel9 = new JPanel();
			jPanel9.setLayout(null);
			jPanel9.setBounds(new java.awt.Rectangle(177,8,271,284));
			jPanel9.add(getJTextPane(), null);
			jPanel9.add(getJPanel10(), null);
		}
		return jPanel9;
	}

	/**
	 * This method initializes jTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getJTextPane() {
		if (jTextPane == null) {
			jTextPane = new JTextPane();
			jTextPane.setBounds(new java.awt.Rectangle(9,116,252,151));
			jTextPane.setText("Este software é livre para uso, estudo, distribuição e modificação desde que os termos da GPL sejam respeitados.");
			jTextPane.setDoubleBuffered(true);
			jTextPane.setEditable(false);
		}
		return jTextPane;
	}

	/**
	 * This method initializes jPanel10	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel10() {
		if (jPanel10 == null) {
			TitledBorder titledBorder1 = javax.swing.BorderFactory.createTitledBorder(null, "Desenvolvido por", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0,0,170));
			titledBorder1.setTitle("Desenvolvido em Parceria por");
			jLabel12 = new JLabel();
			jLabel12.setText("SEAD-NUADD - 2005/2006");
			jLabel13 = new JLabel();
			jLabel13.setText("DukItan Software - 2005/2010");			
			jPanel10 = new JPanel();
			jPanel10.setBounds(new java.awt.Rectangle(9,11,252,91));
			jPanel10.setBorder(titledBorder1);
			jPanel10.add(jLabel12, null);
			jPanel10.add(jLabel13, null);
		}
		return jPanel10;
	}

	/**
	 * This method initializes comboDriver	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getComboDriver() {
		if (comboDriver == null) {
			comboDriver = new ComboBox();
			comboDriver.setBounds(new java.awt.Rectangle(20,37,400,22));
			comboDriver.setMsgAdicionarTitulo("Adicionando Driver");
			comboDriver.setMsgAdicionarInformacao("Informe o novo Driver");
		}
		return comboDriver;
	}

	/**
	 * This method initializes comboURL	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getComboURL() {
		if (comboURL == null) {
			comboURL = new ComboBox();
			comboURL.setBounds(new java.awt.Rectangle(20,87,293,22));
			comboURL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
			comboURL.setEditable(false);
			comboURL.setComponentOrientation(java.awt.ComponentOrientation.LEFT_TO_RIGHT);
			comboURL.setMsgAdicionarTitulo("Adicionando URL");
			comboURL.setMsgAdicionarInformacao("Informe a nova URL de Conex�o");
			comboURL.setRespostaModelo("jdbc:oracle:thin:@host:1521:instancia");
		}
		return comboURL;
	}

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setBounds(new java.awt.Rectangle(319,87,100,22));
			jButton2.setEnabled(false);
			jButton2.setText("Adicionar");
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					comboURL.adicionar();
				}
			});
		}
		return jButton2;
	}

	/**
	 * This method initializes jImagePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJImagePanel() {
		if (jImagePanel == null) {
			jImagePanel = new ImagePanel();
			jImagePanel.setBounds(new java.awt.Rectangle(9,8,160,284));
			jImagePanel.loadImage(".//resource/sobre.png");
		}
		return jImagePanel;
	}
	
}  //  @jve:decl-index=0:visual-constraint="125,0"
