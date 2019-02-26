/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.frames;

import br.com.cbi.listeners.Listener_Main;
import br.com.fs.api.dal.EntityManagerHelper;
import br.com.fs.api.interfaces.ManipulaFrames;
import br.com.fs.api.interfaces.entities.Frame_Sistema;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

/**
 *
 * @author tiago.teixeira
 */
public final class Form_Main extends ManipulaFrames {

    private static final long serialVersionUID = -419029088399611882L;
    private final Listener_Main listener;

    /**
     * Creates new form Main_Form
     */
    public Form_Main() {
        setImageIcon();
        initComponents();
        stateForm();
        startStatusBar();
        //cadastrarTelas();
        listener = new Listener_Main(this);
    }

    public JPopupMenu getPmAgenda() {
        return pmAgenda;
    }

    private void stateForm() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    @Override
    public JMenuItem getItemFechar() {
        return itemFechar;
    }

    private void startStatusBar() {
        statusBar.startStatusBar(this);
    }

    @Override
    public Optional<List<AbstractButton>> getListButtons() {
        return Optional.of(Arrays.asList(itemSelecionarBenef, itemAguardando, itemAtendido, itemConsultaStatus, itemDesmarcado, itemFaltou, itemMarcado, itemReagendou));
    }

    @Override
    public Optional<List<AbstractButton>> getListMenus() {
        List<AbstractButton> lista = new ArrayList();
        forMenu(menuBarCbiDefault.getComponents(), lista);
        return Optional.ofNullable(lista);
    }

    public List<AbstractButton> getMenuList() {
        List<AbstractButton> lista = new ArrayList();
        forMenu(menuBarCbiDefault.getComponents(), lista);
        return lista;
    }

    public boolean internalFrameIsOpen(JInternalFrame iframe) {
        return false;//listener.internalFrameIsOpen(iframe);
    }

    private void forMenu(Component[] components, List<AbstractButton> lista) {
        for (Component comp : components) {
            if (comp instanceof JMenu) {
                JMenu menu = (JMenu) comp;
                for (Component a : menu.getMenuComponents()) {
                    if (a instanceof JMenu) {
                        JMenu m = (JMenu) a;
                        forMenu(m.getMenuComponents(), lista);
                    } else if (a instanceof JMenuItem) {
                        JMenuItem i = (JMenuItem) a;
                        lista.add(i);
                    }
                }
            } else if (comp instanceof JMenuItem) {
                JMenuItem i = (JMenuItem) comp;
                lista.add(i);
            }
        }
    }

    public JMenuItem getItemGerenciadorRelatorios() {
        return itemGerenciadorRelatorios;
    }

    private void cadastrarTelas(EntityManagerHelper emh) {
        getListMenus().ifPresent(lista -> {
            lista.stream()
                    .filter(menu -> !menu.getText().equals("Logout") && !menu.getText().equals("Fechar"))
                    .forEach(menu -> emh.getOperation(EntityManagerHelper.ATUALIZAR, new Frame_Sistema(null, null, menu.getText()), EntityManagerHelper.DERBYDB_PU));
        });
    }

    public JComboBox<String> getCbLocaisAtendimento() {
        return cbLocaisAtendimento;
    }

    public JDesktopPane getDesktopPane() {
        return desktopPane;
    }

    public JInternalFrame getIf_exames_solicitados() {
        return if_exames_solicitados;
    }

    public JInternalFrame getIf_lista_espera() {
        return if_lista_espera;
    }

    public JInternalFrame getIf_atendimentos() {
        return if_atendimentos;
    }

    public JTable getTbExamesSolicitados() {
        return tbExamesSolicitados;
    }

    public JTable getTbListaEspera() {
        return tbListaEspera;
    }

    public JLabel getLbStatus() {
        return lbStatus;
    }

    public JTable getTbExames() {
        return tbExames;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        if_lista_espera = new javax.swing.JInternalFrame();
        painelControle = new javax.swing.JPanel();
        lbLocaisAtendimento = new javax.swing.JLabel();
        cbLocaisAtendimento = new javax.swing.JComboBox<>();
        painelMain_ListaEspera = new javax.swing.JPanel();
        scListaEspera = new javax.swing.JScrollPane();
        tbListaEspera = new javax.swing.JTable();
        if_exames_solicitados = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        scExamesSolicitados = new javax.swing.JScrollPane();
        tbExamesSolicitados = new javax.swing.JTable();
        scExames = new javax.swing.JScrollPane();
        tbExames = new javax.swing.JTable();
        pmAgenda = new javax.swing.JPopupMenu();
        itemSelecionarBenef = new javax.swing.JMenuItem();
        menuStatus = new javax.swing.JMenu();
        itemAguardando = new javax.swing.JMenuItem();
        itemAtendido = new javax.swing.JMenuItem();
        itemConsultaStatus = new javax.swing.JMenuItem();
        itemDesmarcado = new javax.swing.JMenuItem();
        itemMarcado = new javax.swing.JMenuItem();
        itemFaltou = new javax.swing.JMenuItem();
        itemReagendou = new javax.swing.JMenuItem();
        if_atendimentos = new javax.swing.JInternalFrame();
        panelMain = new javax.swing.JPanel();
        desktopPane = new javax.swing.JDesktopPane();
        statusBar = new br.com.fs.api.beans.JStatusBarCBI();
        menuBarCbiDefault = new javax.swing.JMenuBar();
        menuArquivo = new javax.swing.JMenu();
        menuCadastroGeral = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        separador1 = new javax.swing.JPopupMenu.Separator();
        itemLogout = new javax.swing.JMenuItem();
        itemFechar = new javax.swing.JMenuItem();
        menuRelatorios = new javax.swing.JMenu();
        itemGerenciadorRelatorios = new javax.swing.JMenuItem();

        if_lista_espera.setClosable(true);
        if_lista_espera.setIconifiable(true);
        if_lista_espera.setMaximizable(true);
        if_lista_espera.setResizable(true);
        if_lista_espera.setTitle("Lista de Espera");
        if_lista_espera.setToolTipText("");
        if_lista_espera.setNormalBounds(new java.awt.Rectangle(400, 400, 400, 400));
        if_lista_espera.setPreferredSize(new java.awt.Dimension(474, 401));
        try {
            if_lista_espera.setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        if_lista_espera.setVisible(true);

        lbLocaisAtendimento.setText("Local de Atendimento");

        javax.swing.GroupLayout painelControleLayout = new javax.swing.GroupLayout(painelControle);
        painelControle.setLayout(painelControleLayout);
        painelControleLayout.setHorizontalGroup(
            painelControleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelControleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbLocaisAtendimento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbLocaisAtendimento, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelControleLayout.setVerticalGroup(
            painelControleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelControleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelControleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbLocaisAtendimento)
                    .addComponent(cbLocaisAtendimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbListaEspera.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Status", "Matrícula", "Nome do Beneficiário", "Hora de Chegada", "Tempo de Espera"
            }
        ));
        scListaEspera.setViewportView(tbListaEspera);

        javax.swing.GroupLayout painelMain_ListaEsperaLayout = new javax.swing.GroupLayout(painelMain_ListaEspera);
        painelMain_ListaEspera.setLayout(painelMain_ListaEsperaLayout);
        painelMain_ListaEsperaLayout.setHorizontalGroup(
            painelMain_ListaEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scListaEspera, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        painelMain_ListaEsperaLayout.setVerticalGroup(
            painelMain_ListaEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scListaEspera, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout if_lista_esperaLayout = new javax.swing.GroupLayout(if_lista_espera.getContentPane());
        if_lista_espera.getContentPane().setLayout(if_lista_esperaLayout);
        if_lista_esperaLayout.setHorizontalGroup(
            if_lista_esperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelMain_ListaEspera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(painelControle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        if_lista_esperaLayout.setVerticalGroup(
            if_lista_esperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, if_lista_esperaLayout.createSequentialGroup()
                .addComponent(painelControle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelMain_ListaEspera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        if_exames_solicitados.setClosable(true);
        if_exames_solicitados.setIconifiable(true);
        if_exames_solicitados.setMaximizable(true);
        if_exames_solicitados.setResizable(true);
        if_exames_solicitados.setTitle("Exames Solicitados");
        if_exames_solicitados.setLayer(2);
        if_exames_solicitados.setName(""); // NOI18N
        if_exames_solicitados.setNormalBounds(new java.awt.Rectangle(100, 100, 100, 100));
        if_exames_solicitados.setVisible(true);

        jLabel1.setText("Status:");

        lbStatus.setBackground(new java.awt.Color(0, 0, 0));
        lbStatus.setForeground(new java.awt.Color(255, 0, 0));
        lbStatus.setText("Parado");

        tbExamesSolicitados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scExamesSolicitados.setViewportView(tbExamesSolicitados);

        tbExames.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scExames.setViewportView(tbExames);

        javax.swing.GroupLayout if_exames_solicitadosLayout = new javax.swing.GroupLayout(if_exames_solicitados.getContentPane());
        if_exames_solicitados.getContentPane().setLayout(if_exames_solicitadosLayout);
        if_exames_solicitadosLayout.setHorizontalGroup(
            if_exames_solicitadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(if_exames_solicitadosLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(if_exames_solicitadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(if_exames_solicitadosLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(scExames)
                    .addComponent(scExamesSolicitados)))
        );
        if_exames_solicitadosLayout.setVerticalGroup(
            if_exames_solicitadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(if_exames_solicitadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(if_exames_solicitadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scExamesSolicitados, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scExames, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
        );

        if_exames_solicitados.getAccessibleContext().setAccessibleDescription("");

        itemSelecionarBenef.setText("Selecionar Beneficiário");
        itemSelecionarBenef.setActionCommand("selecionarBeneficiario");
        pmAgenda.add(itemSelecionarBenef);

        menuStatus.setText("Mudar Status");

        itemAguardando.setText("Aguardando");
        itemAguardando.setActionCommand("status");
        menuStatus.add(itemAguardando);

        itemAtendido.setText("Atendido");
        itemAtendido.setActionCommand("status");
        menuStatus.add(itemAtendido);

        itemConsultaStatus.setText("Em consulta");
        itemConsultaStatus.setActionCommand("status");
        menuStatus.add(itemConsultaStatus);

        itemDesmarcado.setText("Desmarcado");
        itemDesmarcado.setActionCommand("status");
        menuStatus.add(itemDesmarcado);

        itemMarcado.setText("Marcado");
        itemMarcado.setActionCommand("status");
        menuStatus.add(itemMarcado);

        itemFaltou.setText("Não compareceu");
        itemFaltou.setActionCommand("status");
        menuStatus.add(itemFaltou);

        itemReagendou.setText("Reagendou");
        itemReagendou.setActionCommand("status");
        menuStatus.add(itemReagendou);

        pmAgenda.add(menuStatus);

        if_atendimentos.setClosable(true);
        if_atendimentos.setIconifiable(true);
        if_atendimentos.setMaximizable(true);
        if_atendimentos.setResizable(true);
        if_atendimentos.setTitle("Atendimentos Prévios/Anteriores");
        if_atendimentos.setToolTipText("");
        if_atendimentos.setNormalBounds(new java.awt.Rectangle(400, 400, 400, 400));
        if_atendimentos.setPreferredSize(new java.awt.Dimension(474, 401));
        try {
            if_atendimentos.setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        if_atendimentos.setVisible(true);

        javax.swing.GroupLayout if_atendimentosLayout = new javax.swing.GroupLayout(if_atendimentos.getContentPane());
        if_atendimentos.getContentPane().setLayout(if_atendimentosLayout);
        if_atendimentosLayout.setHorizontalGroup(
            if_atendimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        if_atendimentosLayout.setVerticalGroup(
            if_atendimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 364, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CBI - Cálculo de Bobinagem");
        setName("main_form"); // NOI18N

        desktopPane.setMinimumSize(new java.awt.Dimension(685, 505));
        desktopPane.setOpaque(false);

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        menuArquivo.setText("Arquivo");

        menuCadastroGeral.setText("Cadastros");

        jMenuItem1.setText("Marcas");
        jMenuItem1.setActionCommand("cad_marcas");
        menuCadastroGeral.add(jMenuItem1);

        jMenuItem2.setText("Categorias");
        jMenuItem2.setActionCommand("cad_categorias");
        menuCadastroGeral.add(jMenuItem2);

        menuArquivo.add(menuCadastroGeral);
        menuArquivo.add(separador1);

        itemLogout.setText("Logout");
        itemLogout.setActionCommand("logout");
        menuArquivo.add(itemLogout);

        itemFechar.setText("Fechar");
        itemFechar.setActionCommand("fechar");
        menuArquivo.add(itemFechar);

        menuBarCbiDefault.add(menuArquivo);

        menuRelatorios.setText("Relatórios");

        itemGerenciadorRelatorios.setText("Gerenciador de Relatórios");
        itemGerenciadorRelatorios.setActionCommand("report");
        menuRelatorios.add(itemGerenciadorRelatorios);

        menuBarCbiDefault.add(menuRelatorios);

        setJMenuBar(menuBarCbiDefault);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(statusBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 904, Short.MAX_VALUE)
                    .addComponent(panelMain, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbLocaisAtendimento;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JInternalFrame if_atendimentos;
    private javax.swing.JInternalFrame if_exames_solicitados;
    private javax.swing.JInternalFrame if_lista_espera;
    private javax.swing.JMenuItem itemAguardando;
    private javax.swing.JMenuItem itemAtendido;
    private javax.swing.JMenuItem itemConsultaStatus;
    private javax.swing.JMenuItem itemDesmarcado;
    private javax.swing.JMenuItem itemFaltou;
    private javax.swing.JMenuItem itemFechar;
    private javax.swing.JMenuItem itemGerenciadorRelatorios;
    private javax.swing.JMenuItem itemLogout;
    private javax.swing.JMenuItem itemMarcado;
    private javax.swing.JMenuItem itemReagendou;
    private javax.swing.JMenuItem itemSelecionarBenef;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JLabel lbLocaisAtendimento;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JMenu menuArquivo;
    private javax.swing.JMenuBar menuBarCbiDefault;
    private javax.swing.JMenu menuCadastroGeral;
    private javax.swing.JMenu menuRelatorios;
    private javax.swing.JMenu menuStatus;
    private javax.swing.JPanel painelControle;
    private javax.swing.JPanel painelMain_ListaEspera;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPopupMenu pmAgenda;
    private javax.swing.JScrollPane scExames;
    private javax.swing.JScrollPane scExamesSolicitados;
    private javax.swing.JScrollPane scListaEspera;
    private javax.swing.JPopupMenu.Separator separador1;
    private br.com.fs.api.beans.JStatusBarCBI statusBar;
    private javax.swing.JTable tbExames;
    private javax.swing.JTable tbExamesSolicitados;
    private javax.swing.JTable tbListaEspera;
    // End of variables declaration//GEN-END:variables

}
