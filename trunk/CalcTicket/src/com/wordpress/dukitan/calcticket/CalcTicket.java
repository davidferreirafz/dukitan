/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.dukitan.calcticket;

import com.wordpress.dukitan.calcticket.bean.Config;
import com.wordpress.dukitan.calcticket.bean.Produto;
import com.wordpress.dukitan.calcticket.controle.Fachada;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import org.netbeans.microedition.lcdui.SplashScreen;

/**
 * @author david
 */
public class CalcTicket extends MIDlet implements CommandListener {

    private boolean midletPaused = false;

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private java.util.Hashtable __previousDisplayables = new java.util.Hashtable();
    private Form formPrincipal;
    private StringItem fpValorCompra;
    private StringItem fpValorDisponivel;
    private StringItem fpQuantidadeTickets;
    private StringItem fpValorComplemento;
    private Form formConfig;
    private TextField fcValorTicket;
    private TextField fcOutroTicket;
    private Form formProduto;
    private TextField produtoQuantidade;
    private TextField produtoValor;
    private TextField produtoDescricao;
    private Form formAlterar;
    private TextField faQuantidade;
    private StringItem faDescricao;
    private TextField faValor;
    private List formList;
    private Form formSobre;
    private StringItem stringItem2;
    private StringItem stringItem3;
    private StringItem stringItem;
    private StringItem stringItem1;
    private StringItem stringItem4;
    private SplashScreen splashScreen;
    private Command adicionarCommand;
    private Command listarCommand;
    private Command sairCommand;
    private Command voltarCommand;
    private Command salvarCommand;
    private Command removerCommand;
    private Command alterarCommand;
    private Command configurarCommand;
    private Command sobreCommand;
    private Command statusCommand;
    private Command adicionar2Command;
    private Font fonteValor;
    private Image dukitan;
    //</editor-fold>//GEN-END:|fields|0|

    /**
     * The CalcTicket constructor.
     */
    public CalcTicket() {
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    /**
     * Switches a display to previous displayable of the current displayable.
     * The <code>display</code> instance is obtain from the <code>getDisplay</code> method.
     */
    private void switchToPreviousDisplayable() {
        Displayable __currentDisplayable = getDisplay().getCurrent();
        if (__currentDisplayable != null) {
            Displayable __nextDisplayable = (Displayable) __previousDisplayables.get(__currentDisplayable);
            if (__nextDisplayable != null) {
                switchDisplayable(null, __nextDisplayable);
            }
        }
    }
    //</editor-fold>//GEN-END:|methods|0|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
        formPrincipal = new Form("Calc Ticket - Status", new Item[] { getFpValorCompra(), getFpValorDisponivel(), getFpQuantidadeTickets(), getFpValorComplemento() });//GEN-BEGIN:|0-initialize|1|0-postInitialize
        formPrincipal.addCommand(getSobreCommand());
        formPrincipal.addCommand(getSairCommand());
        formPrincipal.addCommand(getListarCommand());
        formPrincipal.addCommand(getAdicionarCommand());
        formPrincipal.addCommand(getConfigurarCommand());
        formPrincipal.setCommandListener(this);
        formConfig = new Form("Configura\u00E7\u00F5es", new Item[] { getFcValorTicket(), getFcOutroTicket() });
        formConfig.addCommand(getVoltarCommand());
        formConfig.addCommand(getSalvarCommand());
        formConfig.setCommandListener(this);
        formProduto = new Form("Adicionar Produto", new Item[] { getProdutoDescricao(), getProdutoValor(), getProdutoQuantidade() });
        formProduto.addCommand(getVoltarCommand());
        formProduto.addCommand(getSalvarCommand());
        formProduto.setCommandListener(this);
        formAlterar = new Form("Alterar Produto", new Item[] { getFaDescricao(), getFaValor(), getFaQuantidade() });
        formAlterar.addCommand(getVoltarCommand());
        formAlterar.addCommand(getSalvarCommand());
        formAlterar.setCommandListener(this);
        formList = new List("Lista de Compras", Choice.IMPLICIT);
        formList.addCommand(getStatusCommand());
        formList.addCommand(getAlterarCommand());
        formList.addCommand(getRemoverCommand());
        formList.addCommand(getAdicionar2Command());
        formList.setCommandListener(this);
        formList.setFitPolicy(Choice.TEXT_WRAP_ON);
        formList.setSelectCommand(getAlterarCommand());//GEN-END:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, getSplashScreen());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
        exibirStatus();
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        Displayable __currentDisplayable = display.getCurrent();
        if (__currentDisplayable != null  &&  nextDisplayable != null) {
            __previousDisplayables.put(nextDisplayable, __currentDisplayable);
        }
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|









    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == formAlterar) {//GEN-BEGIN:|7-commandAction|1|83-preAction
            if (command == salvarCommand) {//GEN-END:|7-commandAction|1|83-preAction
                // write pre-action user code here
                switchDisplayable(null, formList);//GEN-LINE:|7-commandAction|2|83-postAction
                // write post-action user code here
                alterarProduto();
            } else if (command == voltarCommand) {//GEN-LINE:|7-commandAction|3|81-preAction
                // write pre-action user code here
                switchToPreviousDisplayable();//GEN-LINE:|7-commandAction|4|81-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|5|41-preAction
        } else if (displayable == formConfig) {
            if (command == salvarCommand) {//GEN-END:|7-commandAction|5|41-preAction
                salvarConfig();
                // write pre-action user code here
                switchDisplayable(null, formPrincipal);//GEN-LINE:|7-commandAction|6|41-postAction
                // write post-action user code here
                exibirStatus();
            } else if (command == voltarCommand) {//GEN-LINE:|7-commandAction|7|39-preAction
                // write pre-action user code here
                switchDisplayable(null, formPrincipal);//GEN-LINE:|7-commandAction|8|39-postAction
                // write post-action user code here
                exibirStatus();
            }//GEN-BEGIN:|7-commandAction|9|132-preAction
        } else if (displayable == formList) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|9|132-preAction
                // write pre-action user code here
                formListAction();//GEN-LINE:|7-commandAction|10|132-postAction
                // write post-action user code here
            } else if (command == adicionar2Command) {//GEN-LINE:|7-commandAction|11|218-preAction
                // write pre-action user code here
                switchDisplayable(null, formProduto);//GEN-LINE:|7-commandAction|12|218-postAction
                // write post-action user code here
            } else if (command == alterarCommand) {//GEN-LINE:|7-commandAction|13|136-preAction
                // write pre-action user code here
                validacaoSelecao();//GEN-LINE:|7-commandAction|14|136-postAction
                // write post-action user code here
            } else if (command == removerCommand) {//GEN-LINE:|7-commandAction|15|137-preAction
                // write pre-action user code here
                switchDisplayable(null, formList);//GEN-LINE:|7-commandAction|16|137-postAction
                // write post-action user code here
                removerProduto();
            } else if (command == statusCommand) {//GEN-LINE:|7-commandAction|17|205-preAction
                // write pre-action user code here
                switchDisplayable(null, formPrincipal);//GEN-LINE:|7-commandAction|18|205-postAction
                // write post-action user code here
                exibirStatus();                
            }//GEN-BEGIN:|7-commandAction|19|28-preAction
        } else if (displayable == formPrincipal) {
            if (command == adicionarCommand) {//GEN-END:|7-commandAction|19|28-preAction
                // write pre-action user code here
                switchDisplayable(null, formProduto);//GEN-LINE:|7-commandAction|20|28-postAction
                // write post-action user code here
                inicializarProduto();
            } else if (command == configurarCommand) {//GEN-LINE:|7-commandAction|21|76-preAction
                // write pre-action user code here
                switchDisplayable(null, formConfig);//GEN-LINE:|7-commandAction|22|76-postAction
                // write post-action user code here
                carregarConfig();
            } else if (command == listarCommand) {//GEN-LINE:|7-commandAction|23|35-preAction
                // write pre-action user code here
                //tableItem.setModel(Fachada.getTable());
                switchDisplayable(null, formList);//GEN-LINE:|7-commandAction|24|35-postAction
                // write post-action user code here            
                carregarLista();
            } else if (command == sairCommand) {//GEN-LINE:|7-commandAction|25|33-preAction
                Fachada.salvar();
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|26|33-postAction
                // write post-action user code here
            } else if (command == sobreCommand) {//GEN-LINE:|7-commandAction|27|183-preAction
                // write pre-action user code here
                switchDisplayable(null, getFormSobre());//GEN-LINE:|7-commandAction|28|183-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|29|45-preAction
        } else if (displayable == formProduto) {
            if (command == salvarCommand) {//GEN-END:|7-commandAction|29|45-preAction
                // write pre-action user code here
                validacaoProduto();//GEN-LINE:|7-commandAction|30|45-postAction
                // write post-action user code here
            } else if (command == voltarCommand) {//GEN-LINE:|7-commandAction|31|44-preAction
                // write pre-action user code here
                switchToPreviousDisplayable();//GEN-LINE:|7-commandAction|32|44-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|33|185-preAction
        } else if (displayable == formSobre) {
            if (command == voltarCommand) {//GEN-END:|7-commandAction|33|185-preAction
                // write pre-action user code here
                switchToPreviousDisplayable();//GEN-LINE:|7-commandAction|34|185-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|35|210-preAction
        } else if (displayable == splashScreen) {
            if (command == SplashScreen.DISMISS_COMMAND) {//GEN-END:|7-commandAction|35|210-preAction
                // write pre-action user code here
                switchDisplayable(null, formPrincipal);//GEN-LINE:|7-commandAction|36|210-postAction
                // write post-action user code here
                exibirStatus();
            }//GEN-BEGIN:|7-commandAction|37|7-postCommandAction
        }//GEN-END:|7-commandAction|37|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|38|
    //</editor-fold>//GEN-END:|7-commandAction|38|





    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: adicionarCommand ">//GEN-BEGIN:|27-getter|0|27-preInit
    /**
     * Returns an initiliazed instance of adicionarCommand component.
     * @return the initialized component instance
     */
    public Command getAdicionarCommand() {
        if (adicionarCommand == null) {//GEN-END:|27-getter|0|27-preInit
            // write pre-init user code here
            adicionarCommand = new Command("Adicionar", Command.OK, 0);//GEN-LINE:|27-getter|1|27-postInit
            // write post-init user code here
        }//GEN-BEGIN:|27-getter|2|
        return adicionarCommand;
    }
    //</editor-fold>//GEN-END:|27-getter|2|



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: sairCommand ">//GEN-BEGIN:|32-getter|0|32-preInit
    /**
     * Returns an initiliazed instance of sairCommand component.
     * @return the initialized component instance
     */
    public Command getSairCommand() {
        if (sairCommand == null) {//GEN-END:|32-getter|0|32-preInit
            // write pre-init user code here
            sairCommand = new Command("Sair", Command.OK, 4);//GEN-LINE:|32-getter|1|32-postInit
            // write post-init user code here
        }//GEN-BEGIN:|32-getter|2|
        return sairCommand;
    }
    //</editor-fold>//GEN-END:|32-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: listarCommand ">//GEN-BEGIN:|34-getter|0|34-preInit
    /**
     * Returns an initiliazed instance of listarCommand component.
     * @return the initialized component instance
     */
    public Command getListarCommand() {
        if (listarCommand == null) {//GEN-END:|34-getter|0|34-preInit
            // write pre-init user code here
            listarCommand = new Command("Lista", Command.BACK, 0);//GEN-LINE:|34-getter|1|34-postInit
            // write post-init user code here
        }//GEN-BEGIN:|34-getter|2|
        return listarCommand;
    }
    //</editor-fold>//GEN-END:|34-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: voltarCommand ">//GEN-BEGIN:|38-getter|0|38-preInit
    /**
     * Returns an initiliazed instance of voltarCommand component.
     * @return the initialized component instance
     */
    public Command getVoltarCommand() {
        if (voltarCommand == null) {//GEN-END:|38-getter|0|38-preInit
            // write pre-init user code here
            voltarCommand = new Command("Voltar", Command.BACK, 0);//GEN-LINE:|38-getter|1|38-postInit
            // write post-init user code here
        }//GEN-BEGIN:|38-getter|2|
        return voltarCommand;
    }
    //</editor-fold>//GEN-END:|38-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: salvarCommand ">//GEN-BEGIN:|40-getter|0|40-preInit
    /**
     * Returns an initiliazed instance of salvarCommand component.
     * @return the initialized component instance
     */
    public Command getSalvarCommand() {
        if (salvarCommand == null) {//GEN-END:|40-getter|0|40-preInit
            // write pre-init user code here
            salvarCommand = new Command("Salvar", Command.OK, 0);//GEN-LINE:|40-getter|1|40-postInit
            // write post-init user code here
        }//GEN-BEGIN:|40-getter|2|
        return salvarCommand;
    }
    //</editor-fold>//GEN-END:|40-getter|2|







    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: removerCommand ">//GEN-BEGIN:|70-getter|0|70-preInit
    /**
     * Returns an initiliazed instance of removerCommand component.
     * @return the initialized component instance
     */
    public Command getRemoverCommand() {
        if (removerCommand == null) {//GEN-END:|70-getter|0|70-preInit
            // write pre-init user code here
            removerCommand = new Command("Remover", Command.OK, 1);//GEN-LINE:|70-getter|1|70-postInit
            // write post-init user code here
        }//GEN-BEGIN:|70-getter|2|
        return removerCommand;
    }
    //</editor-fold>//GEN-END:|70-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: configurarCommand ">//GEN-BEGIN:|75-getter|0|75-preInit
    /**
     * Returns an initiliazed instance of configurarCommand component.
     * @return the initialized component instance
     */
    public Command getConfigurarCommand() {
        if (configurarCommand == null) {//GEN-END:|75-getter|0|75-preInit
            // write pre-init user code here
            configurarCommand = new Command("Configurar", Command.OK, 0);//GEN-LINE:|75-getter|1|75-postInit
            // write post-init user code here
        }//GEN-BEGIN:|75-getter|2|
        return configurarCommand;
    }
    //</editor-fold>//GEN-END:|75-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: alterarCommand ">//GEN-BEGIN:|78-getter|0|78-preInit
    /**
     * Returns an initiliazed instance of alterarCommand component.
     * @return the initialized component instance
     */
    public Command getAlterarCommand() {
        if (alterarCommand == null) {//GEN-END:|78-getter|0|78-preInit
            // write pre-init user code here
            alterarCommand = new Command("Alterar", Command.OK, 0);//GEN-LINE:|78-getter|1|78-postInit
            // write post-init user code here
        }//GEN-BEGIN:|78-getter|2|
        return alterarCommand;
    }
    //</editor-fold>//GEN-END:|78-getter|2|







    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: fcValorTicket ">//GEN-BEGIN:|97-getter|0|97-preInit
    /**
     * Returns an initiliazed instance of fcValorTicket component.
     * @return the initialized component instance
     */
    public TextField getFcValorTicket() {
        if (fcValorTicket == null) {//GEN-END:|97-getter|0|97-preInit
            // write pre-init user code here
            fcValorTicket = new TextField("Valor do Ticket:", "0", 5, TextField.DECIMAL);//GEN-BEGIN:|97-getter|1|97-postInit
            fcValorTicket.setLayout(ImageItem.LAYOUT_DEFAULT | ImageItem.LAYOUT_NEWLINE_BEFORE | ImageItem.LAYOUT_NEWLINE_AFTER | Item.LAYOUT_EXPAND | Item.LAYOUT_2);//GEN-END:|97-getter|1|97-postInit
            // write post-init user code here
        }//GEN-BEGIN:|97-getter|2|
        return fcValorTicket;
    }
    //</editor-fold>//GEN-END:|97-getter|2|



    //<editor-fold defaultstate="collapsed" desc=" Generated Method: carregarConfig ">//GEN-BEGIN:|100-entry|0|101-preAction
    /**
     * Performs an action assigned to the carregarConfig entry-point.
     */
    public void carregarConfig() {//GEN-END:|100-entry|0|101-preAction
        // write pre-action user code here
        Config config = Fachada.getConfig();//GEN-BEGIN:|100-entry|1|101-postAction
        
        fcValorTicket.setString(config.getValor());
        fcOutroTicket.setString(config.getOutroTicket());//GEN-END:|100-entry|1|101-postAction
        // write post-action user code here
    }//GEN-BEGIN:|100-entry|2|
    //</editor-fold>//GEN-END:|100-entry|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: salvarConfig ">//GEN-BEGIN:|103-entry|0|104-preAction
    /**
     * Performs an action assigned to the salvarConfig entry-point.
     */
    public void salvarConfig() {//GEN-END:|103-entry|0|104-preAction
        // write pre-action user code here
        Config config = new Config();//GEN-BEGIN:|103-entry|1|104-postAction
        
        config.setValor(fcValorTicket.getString());
        config.setOutroTicket(fcOutroTicket.getString());
        
        Fachada.setConfig(config);//GEN-END:|103-entry|1|104-postAction
        // write post-action user code here
    }//GEN-BEGIN:|103-entry|2|
    //</editor-fold>//GEN-END:|103-entry|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: salvarProduto ">//GEN-BEGIN:|109-entry|0|110-preAction
    /**
     * Performs an action assigned to the salvarProduto entry-point.
     */
    public void salvarProduto() {//GEN-END:|109-entry|0|110-preAction
        // write pre-action user code here
        Produto produto = new Produto();//GEN-BEGIN:|109-entry|1|110-postAction
        
        produto.setDescricao(produtoDescricao.getString());
        produto.setQuantidade(produtoQuantidade.getString());
        produto.setValor(produtoValor.getString());
        
        Fachada.adicionar(produto);//GEN-END:|109-entry|1|110-postAction
        // write post-action user code here
    }//GEN-BEGIN:|109-entry|2|
    //</editor-fold>//GEN-END:|109-entry|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: produtoDescricao ">//GEN-BEGIN:|113-getter|0|113-preInit
    /**
     * Returns an initiliazed instance of produtoDescricao component.
     * @return the initialized component instance
     */
    public TextField getProdutoDescricao() {
        if (produtoDescricao == null) {//GEN-END:|113-getter|0|113-preInit
            // write pre-init user code here
            produtoDescricao = new TextField("Descri\u00E7\u00E3o:", "null", 20, TextField.ANY | TextField.INITIAL_CAPS_WORD | TextField.INITIAL_CAPS_SENTENCE);//GEN-BEGIN:|113-getter|1|113-postInit
            produtoDescricao.setLayout(ImageItem.LAYOUT_DEFAULT | ImageItem.LAYOUT_NEWLINE_BEFORE | ImageItem.LAYOUT_NEWLINE_AFTER | Item.LAYOUT_EXPAND | Item.LAYOUT_2);//GEN-END:|113-getter|1|113-postInit
            // write post-init user code here
        }//GEN-BEGIN:|113-getter|2|
        return produtoDescricao;
    }
    //</editor-fold>//GEN-END:|113-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: produtoValor ">//GEN-BEGIN:|114-getter|0|114-preInit
    /**
     * Returns an initiliazed instance of produtoValor component.
     * @return the initialized component instance
     */
    public TextField getProdutoValor() {
        if (produtoValor == null) {//GEN-END:|114-getter|0|114-preInit
            // write pre-init user code here
            produtoValor = new TextField("Valor:", "0", 5, TextField.DECIMAL);//GEN-BEGIN:|114-getter|1|114-postInit
            produtoValor.setLayout(ImageItem.LAYOUT_LEFT | ImageItem.LAYOUT_NEWLINE_BEFORE | ImageItem.LAYOUT_NEWLINE_AFTER | Item.LAYOUT_EXPAND | Item.LAYOUT_2);//GEN-END:|114-getter|1|114-postInit
            // write post-init user code here
        }//GEN-BEGIN:|114-getter|2|
        return produtoValor;
    }
    //</editor-fold>//GEN-END:|114-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: produtoQuantidade ">//GEN-BEGIN:|115-getter|0|115-preInit
    /**
     * Returns an initiliazed instance of produtoQuantidade component.
     * @return the initialized component instance
     */
    public TextField getProdutoQuantidade() {
        if (produtoQuantidade == null) {//GEN-END:|115-getter|0|115-preInit
            // write pre-init user code here
            produtoQuantidade = new TextField("Quantidade:", "1", 2, TextField.NUMERIC);//GEN-BEGIN:|115-getter|1|115-postInit
            produtoQuantidade.setLayout(ImageItem.LAYOUT_LEFT | ImageItem.LAYOUT_NEWLINE_BEFORE | ImageItem.LAYOUT_NEWLINE_AFTER | Item.LAYOUT_EXPAND | Item.LAYOUT_2);//GEN-END:|115-getter|1|115-postInit
            // write post-init user code here
        }//GEN-BEGIN:|115-getter|2|
        return produtoQuantidade;
    }
    //</editor-fold>//GEN-END:|115-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: inicializarProduto ">//GEN-BEGIN:|116-entry|0|117-preAction
    /**
     * Performs an action assigned to the inicializarProduto entry-point.
     */
    public void inicializarProduto() {//GEN-END:|116-entry|0|117-preAction
        // write pre-action user code here
        produtoDescricao.setString("");//GEN-BEGIN:|116-entry|1|117-postAction
        produtoQuantidade.setString("1");
        produtoValor.setString("");
//GEN-END:|116-entry|1|117-postAction
        // write post-action user code here
    }//GEN-BEGIN:|116-entry|2|
    //</editor-fold>//GEN-END:|116-entry|2|



    //<editor-fold defaultstate="collapsed" desc=" Generated Method: formListAction ">//GEN-BEGIN:|131-action|0|131-preAction
    /**
     * Performs an action assigned to the selected list element in the formList component.
     */
    public void formListAction() {//GEN-END:|131-action|0|131-preAction
        // enter pre-action user code here
        String __selectedString = formList.getString(formList.getSelectedIndex());//GEN-LINE:|131-action|1|131-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|131-action|2|
    //</editor-fold>//GEN-END:|131-action|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: carregarLista ">//GEN-BEGIN:|141-entry|0|142-preAction
    /**
     * Performs an action assigned to the carregarLista entry-point.
     */
    public void carregarLista() {//GEN-END:|141-entry|0|142-preAction
        // write pre-action user code here
        formList.deleteAll();//GEN-BEGIN:|141-entry|1|142-postAction
        
        String lst[] = Fachada.getProdutos();
        
        if (lst!=null){
            for (int i=0; i< lst.length; i++){
                formList.append(lst[i], null);
            }
        }
//GEN-END:|141-entry|1|142-postAction
        // write post-action user code here
    }//GEN-BEGIN:|141-entry|2|
    //</editor-fold>//GEN-END:|141-entry|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: removerProduto ">//GEN-BEGIN:|145-entry|0|146-preAction
    /**
     * Performs an action assigned to the removerProduto entry-point.
     */
    public void removerProduto() {//GEN-END:|145-entry|0|146-preAction
        // write pre-action user code here
        int selecao = formList.getSelectedIndex();//GEN-BEGIN:|145-entry|1|146-postAction
        
        if (selecao>=0){
/*
    String item = formList.getString(selecao);
    Produto p = new Produto();
    p.makeProduto(item);
 */
            Fachada.remover(selecao);
            
            carregarLista();
        }//GEN-END:|145-entry|1|146-postAction
        // write post-action user code here
    }//GEN-BEGIN:|145-entry|2|
    //</editor-fold>//GEN-END:|145-entry|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: carregarProduto ">//GEN-BEGIN:|153-entry|0|154-preAction
    /**
     * Performs an action assigned to the carregarProduto entry-point.
     */
    public void carregarProduto() {//GEN-END:|153-entry|0|154-preAction
        // write pre-action user code here
        int selecao = formList.getSelectedIndex();//GEN-BEGIN:|153-entry|1|154-postAction
        
        if (selecao>=0){
            Produto p = Fachada.getProduto(selecao);
            
            faDescricao.setText(p.getDescricao());
            faQuantidade.setString(String.valueOf(p.getQuantidade()));
            faValor.setString(String.valueOf(p.getValor()));
        }//GEN-END:|153-entry|1|154-postAction
        // write post-action user code here
    }//GEN-BEGIN:|153-entry|2|
    //</editor-fold>//GEN-END:|153-entry|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: faDescricao ">//GEN-BEGIN:|150-getter|0|150-preInit
    /**
     * Returns an initiliazed instance of faDescricao component.
     * @return the initialized component instance
     */
    public StringItem getFaDescricao() {
        if (faDescricao == null) {//GEN-END:|150-getter|0|150-preInit
            // write pre-init user code here
            faDescricao = new StringItem("Descri\u00E7\u00E3o:", null, Item.PLAIN);//GEN-LINE:|150-getter|1|150-postInit
            // write post-init user code here
        }//GEN-BEGIN:|150-getter|2|
        return faDescricao;
    }
    //</editor-fold>//GEN-END:|150-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: faValor ">//GEN-BEGIN:|151-getter|0|151-preInit
    /**
     * Returns an initiliazed instance of faValor component.
     * @return the initialized component instance
     */
    public TextField getFaValor() {
        if (faValor == null) {//GEN-END:|151-getter|0|151-preInit
            // write pre-init user code here
            faValor = new TextField("Valor:", "0", 5, TextField.DECIMAL);//GEN-BEGIN:|151-getter|1|151-postInit
            faValor.setLayout(ImageItem.LAYOUT_DEFAULT);//GEN-END:|151-getter|1|151-postInit
            // write post-init user code here
        }//GEN-BEGIN:|151-getter|2|
        return faValor;
    }
    //</editor-fold>//GEN-END:|151-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: faQuantidade ">//GEN-BEGIN:|152-getter|0|152-preInit
    /**
     * Returns an initiliazed instance of faQuantidade component.
     * @return the initialized component instance
     */
    public TextField getFaQuantidade() {
        if (faQuantidade == null) {//GEN-END:|152-getter|0|152-preInit
            // write pre-init user code here
            faQuantidade = new TextField("Quantidade:", "0", 2, TextField.NUMERIC);//GEN-BEGIN:|152-getter|1|152-postInit
            faQuantidade.setLayout(ImageItem.LAYOUT_DEFAULT);//GEN-END:|152-getter|1|152-postInit
            // write post-init user code here
        }//GEN-BEGIN:|152-getter|2|
        return faQuantidade;
    }
    //</editor-fold>//GEN-END:|152-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: validacaoSelecao ">//GEN-BEGIN:|159-if|0|159-preIf
    /**
     * Performs an action assigned to the validacaoSelecao if-point.
     */
    public void validacaoSelecao() {//GEN-END:|159-if|0|159-preIf
        // enter pre-if user code here
        if (formList.getSelectedIndex()>=0) {//GEN-LINE:|159-if|1|160-preAction
            // write pre-action user code here
            switchDisplayable(null, formAlterar);//GEN-LINE:|159-if|2|160-postAction
            // write post-action user code here
            carregarProduto();
        } else {//GEN-LINE:|159-if|3|161-preAction
            // write pre-action user code here
            switchDisplayable(null, formList);//GEN-LINE:|159-if|4|161-postAction
            // write post-action user code here
        }//GEN-LINE:|159-if|5|159-postIf
        // enter post-if user code here
    }//GEN-BEGIN:|159-if|6|
    //</editor-fold>//GEN-END:|159-if|6|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: alterarProduto ">//GEN-BEGIN:|167-entry|0|168-preAction
    /**
     * Performs an action assigned to the alterarProduto entry-point.
     */
    public void alterarProduto() {//GEN-END:|167-entry|0|168-preAction
        // write pre-action user code here
        Produto produto = new Produto();//GEN-BEGIN:|167-entry|1|168-postAction
        
        produto.setDescricao(faDescricao.getText());
        produto.setQuantidade(faQuantidade.getString());
        produto.setValor(faValor.getString());
        
        Fachada.alterar(produto);
        
        carregarLista();//GEN-END:|167-entry|1|168-postAction
        // write post-action user code here
    }//GEN-BEGIN:|167-entry|2|
    //</editor-fold>//GEN-END:|167-entry|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: exibirStatus ">//GEN-BEGIN:|175-entry|0|176-preAction
    /**
     * Performs an action assigned to the exibirStatus entry-point.
     */
    public void exibirStatus() {//GEN-END:|175-entry|0|176-preAction
        // write pre-action user code here
        fpValorCompra.setText("  "+Fachada.getTotalCompra());//GEN-BEGIN:|175-entry|1|176-postAction
        
        fpQuantidadeTickets.setText("  "+Fachada.getQuantidadeTickets());
        
        fpValorDisponivel.setText("  "+Fachada.getValorDisponivel());
        
        fpValorComplemento.setText("  "+Fachada.getValorComplemento());
        
//GEN-END:|175-entry|1|176-postAction
        // write post-action user code here
    }//GEN-BEGIN:|175-entry|2|
    //</editor-fold>//GEN-END:|175-entry|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: fpValorCompra ">//GEN-BEGIN:|171-getter|0|171-preInit
    /**
     * Returns an initiliazed instance of fpValorCompra component.
     * @return the initialized component instance
     */
    public StringItem getFpValorCompra() {
        if (fpValorCompra == null) {//GEN-END:|171-getter|0|171-preInit
            // write pre-init user code here
            fpValorCompra = new StringItem("Valor da Compra: ", null, Item.PLAIN);//GEN-BEGIN:|171-getter|1|171-postInit
            fpValorCompra.setLayout(ImageItem.LAYOUT_DEFAULT);
            fpValorCompra.setFont(getFonteValor());//GEN-END:|171-getter|1|171-postInit
            // write post-init user code here
        }//GEN-BEGIN:|171-getter|2|
        return fpValorCompra;
    }
    //</editor-fold>//GEN-END:|171-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: fpQuantidadeTickets ">//GEN-BEGIN:|172-getter|0|172-preInit
    /**
     * Returns an initiliazed instance of fpQuantidadeTickets component.
     * @return the initialized component instance
     */
    public StringItem getFpQuantidadeTickets() {
        if (fpQuantidadeTickets == null) {//GEN-END:|172-getter|0|172-preInit
            // write pre-init user code here
            fpQuantidadeTickets = new StringItem("Total de Tickets:", null);//GEN-BEGIN:|172-getter|1|172-postInit
            fpQuantidadeTickets.setFont(getFonteValor());//GEN-END:|172-getter|1|172-postInit
            // write post-init user code here
        }//GEN-BEGIN:|172-getter|2|
        return fpQuantidadeTickets;
    }
    //</editor-fold>//GEN-END:|172-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: fpValorDisponivel ">//GEN-BEGIN:|173-getter|0|173-preInit
    /**
     * Returns an initiliazed instance of fpValorDisponivel component.
     * @return the initialized component instance
     */
    public StringItem getFpValorDisponivel() {
        if (fpValorDisponivel == null) {//GEN-END:|173-getter|0|173-preInit
            // write pre-init user code here
            fpValorDisponivel = new StringItem("Valor Dispon\u00EDvel:", null);//GEN-BEGIN:|173-getter|1|173-postInit
            fpValorDisponivel.setFont(getFonteValor());//GEN-END:|173-getter|1|173-postInit
            // write post-init user code here
        }//GEN-BEGIN:|173-getter|2|
        return fpValorDisponivel;
    }
    //</editor-fold>//GEN-END:|173-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: fpValorComplemento ">//GEN-BEGIN:|178-getter|0|178-preInit
    /**
     * Returns an initiliazed instance of fpValorComplemento component.
     * @return the initialized component instance
     */
    public StringItem getFpValorComplemento() {
        if (fpValorComplemento == null) {//GEN-END:|178-getter|0|178-preInit
            // write pre-init user code here
            fpValorComplemento = new StringItem("Pagar em Dinheiro:", null);//GEN-BEGIN:|178-getter|1|178-postInit
            fpValorComplemento.setFont(getFonteValor());//GEN-END:|178-getter|1|178-postInit
            // write post-init user code here
        }//GEN-BEGIN:|178-getter|2|
        return fpValorComplemento;
    }
    //</editor-fold>//GEN-END:|178-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: fcOutroTicket ">//GEN-BEGIN:|179-getter|0|179-preInit
    /**
     * Returns an initiliazed instance of fcOutroTicket component.
     * @return the initialized component instance
     */
    public TextField getFcOutroTicket() {
        if (fcOutroTicket == null) {//GEN-END:|179-getter|0|179-preInit
            // write pre-init user code here
            fcOutroTicket = new TextField("Novo Ticket apartir:", "0", 3, TextField.DECIMAL);//GEN-LINE:|179-getter|1|179-postInit
            // write post-init user code here
        }//GEN-BEGIN:|179-getter|2|
        return fcOutroTicket;
    }
    //</editor-fold>//GEN-END:|179-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: formSobre ">//GEN-BEGIN:|181-getter|0|181-preInit
    /**
     * Returns an initiliazed instance of formSobre component.
     * @return the initialized component instance
     */
    public Form getFormSobre() {
        if (formSobre == null) {//GEN-END:|181-getter|0|181-preInit
            // write pre-init user code here
            formSobre = new Form("Sobre", new Item[] { getStringItem(), getStringItem2(), getStringItem1(), getStringItem3(), getStringItem4() });//GEN-BEGIN:|181-getter|1|181-postInit
            formSobre.addCommand(getVoltarCommand());
            formSobre.setCommandListener(this);//GEN-END:|181-getter|1|181-postInit
            // write post-init user code here
        }//GEN-BEGIN:|181-getter|2|
        return formSobre;
    }
    //</editor-fold>//GEN-END:|181-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem ">//GEN-BEGIN:|188-getter|0|188-preInit
    /**
     * Returns an initiliazed instance of stringItem component.
     * @return the initialized component instance
     */
    public StringItem getStringItem() {
        if (stringItem == null) {//GEN-END:|188-getter|0|188-preInit
            // write pre-init user code here
            stringItem = new StringItem("CalcTicket", "Calcule quantos tickets ser\u00E3o necess\u00E1rios para pagar suas compras.", Item.PLAIN);//GEN-BEGIN:|188-getter|1|188-postInit
            stringItem.setLayout(ImageItem.LAYOUT_DEFAULT);//GEN-END:|188-getter|1|188-postInit
            // write post-init user code here
        }//GEN-BEGIN:|188-getter|2|
        return stringItem;
    }
    //</editor-fold>//GEN-END:|188-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: sobreCommand ">//GEN-BEGIN:|182-getter|0|182-preInit
    /**
     * Returns an initiliazed instance of sobreCommand component.
     * @return the initialized component instance
     */
    public Command getSobreCommand() {
        if (sobreCommand == null) {//GEN-END:|182-getter|0|182-preInit
            // write pre-init user code here
            sobreCommand = new Command("Sobre", Command.HELP, 0);//GEN-LINE:|182-getter|1|182-postInit
            // write post-init user code here
        }//GEN-BEGIN:|182-getter|2|
        return sobreCommand;
    }
    //</editor-fold>//GEN-END:|182-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem1 ">//GEN-BEGIN:|189-getter|0|189-preInit
    /**
     * Returns an initiliazed instance of stringItem1 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem1() {
        if (stringItem1 == null) {//GEN-END:|189-getter|0|189-preInit
            // write pre-init user code here
            stringItem1 = new StringItem("Licen\u00E7a: GPL v3", "Software Livre - Free Software - de acordo com os termos da GPL v3.");//GEN-LINE:|189-getter|1|189-postInit
            // write post-init user code here
        }//GEN-BEGIN:|189-getter|2|
        return stringItem1;
    }
    //</editor-fold>//GEN-END:|189-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem2 ">//GEN-BEGIN:|190-getter|0|190-preInit
    /**
     * Returns an initiliazed instance of stringItem2 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem2() {
        if (stringItem2 == null) {//GEN-END:|190-getter|0|190-preInit
            // write pre-init user code here
            stringItem2 = new StringItem("Autor:", "David Ferreira \"FZ\" <davidferreira.fz@gmail.com>");//GEN-LINE:|190-getter|1|190-postInit
            // write post-init user code here
        }//GEN-BEGIN:|190-getter|2|
        return stringItem2;
    }
    //</editor-fold>//GEN-END:|190-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem3 ">//GEN-BEGIN:|191-getter|0|191-preInit
    /**
     * Returns an initiliazed instance of stringItem3 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem3() {
        if (stringItem3 == null) {//GEN-END:|191-getter|0|191-preInit
            // write pre-init user code here
            stringItem3 = new StringItem("Informa\u00E7\u00F5es:", "v1.0 : Fortaleza - Cear\u00E1 - Brasil");//GEN-LINE:|191-getter|1|191-postInit
            // write post-init user code here
        }//GEN-BEGIN:|191-getter|2|
        return stringItem3;
    }
    //</editor-fold>//GEN-END:|191-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem4 ">//GEN-BEGIN:|192-getter|0|192-preInit
    /**
     * Returns an initiliazed instance of stringItem4 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem4() {
        if (stringItem4 == null) {//GEN-END:|192-getter|0|192-preInit
            // write pre-init user code here
            stringItem4 = new StringItem("Site:", "http://dukitan.wordpress.com", Item.HYPERLINK);//GEN-LINE:|192-getter|1|192-postInit
            // write post-init user code here
        }//GEN-BEGIN:|192-getter|2|
        return stringItem4;
    }
    //</editor-fold>//GEN-END:|192-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: validacaoProduto ">//GEN-BEGIN:|199-if|0|199-preIf
    /**
     * Performs an action assigned to the validacaoProduto if-point.
     */
    public void validacaoProduto() {//GEN-END:|199-if|0|199-preIf
        // enter pre-if user code here
        if (!produtoDescricao.getString().equals("")//GEN-BEGIN:|199-if|1|200-preAction
                ) {//GEN-END:|199-if|1|200-preAction
            salvarProduto();
            // write pre-action user code here
            switchDisplayable(null, formPrincipal);//GEN-LINE:|199-if|2|200-postAction
            // write post-action user code here
            exibirStatus();
        } else {//GEN-LINE:|199-if|3|201-preAction
            // write pre-action user code here
//GEN-LINE:|199-if|4|201-postAction
            // write post-action user code here
        }//GEN-LINE:|199-if|5|199-postIf
        // enter post-if user code here
    }//GEN-BEGIN:|199-if|6|
    //</editor-fold>//GEN-END:|199-if|6|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: statusCommand ">//GEN-BEGIN:|204-getter|0|204-preInit
    /**
     * Returns an initiliazed instance of statusCommand component.
     * @return the initialized component instance
     */
    public Command getStatusCommand() {
        if (statusCommand == null) {//GEN-END:|204-getter|0|204-preInit
            // write pre-init user code here
            statusCommand = new Command("Status", Command.BACK, 0);//GEN-LINE:|204-getter|1|204-postInit
            // write post-init user code here
        }//GEN-BEGIN:|204-getter|2|
        return statusCommand;
    }
    //</editor-fold>//GEN-END:|204-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: fonteValor ">//GEN-BEGIN:|208-getter|0|208-preInit
    /**
     * Returns an initiliazed instance of fonteValor component.
     * @return the initialized component instance
     */
    public Font getFonteValor() {
        if (fonteValor == null) {//GEN-END:|208-getter|0|208-preInit
            // write pre-init user code here
            fonteValor = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM);//GEN-LINE:|208-getter|1|208-postInit
            // write post-init user code here
        }//GEN-BEGIN:|208-getter|2|
        return fonteValor;
    }
    //</editor-fold>//GEN-END:|208-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: splashScreen ">//GEN-BEGIN:|209-getter|0|209-preInit
    /**
     * Returns an initiliazed instance of splashScreen component.
     * @return the initialized component instance
     */
    public SplashScreen getSplashScreen() {
        if (splashScreen == null) {//GEN-END:|209-getter|0|209-preInit
            // write pre-init user code here
            splashScreen = new SplashScreen(getDisplay());//GEN-BEGIN:|209-getter|1|209-postInit
            splashScreen.setTitle("splashScreen");
            splashScreen.setCommandListener(this);
            splashScreen.setFullScreenMode(true);
            splashScreen.setImage(getDukitan());
            splashScreen.setText("");
            splashScreen.setTimeout(5000);
            splashScreen.setAllowTimeoutInterrupt(false);//GEN-END:|209-getter|1|209-postInit
            // write post-init user code here
        }//GEN-BEGIN:|209-getter|2|
        return splashScreen;
    }
    //</editor-fold>//GEN-END:|209-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: dukitan ">//GEN-BEGIN:|211-getter|0|211-preInit
    /**
     * Returns an initiliazed instance of dukitan component.
     * @return the initialized component instance
     */
    public Image getDukitan() {
        if (dukitan == null) {//GEN-END:|211-getter|0|211-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|211-getter|1|211-@java.io.IOException
                dukitan = Image.createImage("/dukitan_100x148.png");
            } catch (java.io.IOException e) {//GEN-END:|211-getter|1|211-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|211-getter|2|211-postInit
            // write post-init user code here
        }//GEN-BEGIN:|211-getter|3|
        return dukitan;
    }
    //</editor-fold>//GEN-END:|211-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: adicionar2Command ">//GEN-BEGIN:|217-getter|0|217-preInit
    /**
     * Returns an initiliazed instance of adicionar2Command component.
     * @return the initialized component instance
     */
    public Command getAdicionar2Command() {
        if (adicionar2Command == null) {//GEN-END:|217-getter|0|217-preInit
            // write pre-init user code here
            adicionar2Command = new Command("Adicionar", Command.OK, 2);//GEN-LINE:|217-getter|1|217-postInit
            // write post-init user code here
        }//GEN-BEGIN:|217-getter|2|
        return adicionar2Command;
    }
    //</editor-fold>//GEN-END:|217-getter|2|





















    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay () {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable (null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet ();
        } else {
            initialize ();
            startMIDlet ();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }

}
