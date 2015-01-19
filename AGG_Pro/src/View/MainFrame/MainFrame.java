package View.MainFrame;

import Data.LiveClasses.*;
import View.InputPanes.ManipulateTournament;
import View.Login.LoginFrame;
import View.MainFrame.OperatingPanes.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Heiko Geppert
 */
public class MainFrame extends javax.swing.JFrame {

    private Event actualEvent;
    private Tournament selectedTournament;
    private Administrate administrate;

    private static MainFrame mainFrame;

    public static MainFrame getMainFrame() {
        if (mainFrame == null) {
            mainFrame = new MainFrame();
        }
        return mainFrame;
    }

    /**
     * Creates new form MainFrame
     */
    private MainFrame() {
        super("AGG Pro Tournament Manager");
        lookAndFeel();
        initComponents();
        initOwnComponents();
        initListener();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setName("AGG Pro Tournament Manager"); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * This method is called from within the constructor to initialize the form
     * with the manually added elements, such as layoutmanagers.
     */
    private void initOwnComponents() {
        this.setLayout(new BorderLayout());
        initTable();
        initJComponents();
        setComponents();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    // own Variables declaration - do modify ;)
    private JPanel panelToolBar;
    private JPanel panelFrame;
    private AggToolBar tbMainFrame;
    private JSplitPane panelMainFrame;
    private JPanel panelTournamentListWithButton;
    private JScrollPane panelTournamentList;
    private JTabbedPane panelTabPane;
    private JPanel panelTournamentButtons;
    private JButton btnNewTournament;
    private JButton btnDeleteTournament;

    private JTable tableTournamentList;
    private DefaultTableModel tableTournamentListModel;

    private void initTable() {
        tableTournamentList = new JTable();
        tableTournamentList.setModel(new DefaultTableModel(new Object[0][0], new String[]{"Turnier"}) {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        });
        tableTournamentListModel = ((DefaultTableModel) tableTournamentList.getModel());
        tableTournamentList.setFillsViewportHeight(true);
    }

    private void initJComponents() {
        panelToolBar = new JPanel();
        panelFrame = new JPanel();
        panelFrame.setLayout(new BorderLayout());
        panelTournamentListWithButton = new JPanel();
        panelTournamentList = new JScrollPane(tableTournamentList);
        panelTabPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        panelMainFrame = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false, panelTournamentListWithButton, panelTabPane);

        btnNewTournament = new JButton("+");
        btnDeleteTournament = new JButton("-");
        tbMainFrame = new AggToolBar(this);
    }

    private void setComponents() {
        panelTournamentButtons = new JPanel(new GridLayout(1, 2));
        panelTournamentButtons.add(btnNewTournament);
        panelTournamentButtons.add(btnDeleteTournament);
        panelTournamentListWithButton.setLayout(new BorderLayout());
        panelTournamentListWithButton.add(panelTournamentList, BorderLayout.CENTER);
        panelTournamentListWithButton.add(panelTournamentButtons, BorderLayout.SOUTH);
        panelTabPane.add(new MainMenu(this, null));

        // customize components        
        // calculate the Position of the divider
        double dDivLoc = this.getSize().width * 0.25d;
        panelMainFrame.setDividerLocation(((int) dDivLoc));
        panelMainFrame.setDividerSize(3);
        panelMainFrame.setEnabled(false);

        //build the frame, with the components
        panelToolBar.add(tbMainFrame);
        this.add(panelToolBar, BorderLayout.NORTH);
        this.add(panelFrame, BorderLayout.CENTER);
        this.panelFrame.add(panelMainFrame, BorderLayout.CENTER);
    }

    private void initListener() {
        tableTournamentList.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                setSelectedTournamentByClick();
                int row = tableTournamentList.getSelectedRow();
                if (row != 0) {
                    try {
                        String tournamentName = getSelectedTournament().getName();
                        for (int i = 0; i < panelTabPane.getTabCount(); i++) {
                            if (panelTabPane.getTitleAt(i).equals(tournamentName)) {
                                panelTabPane.setSelectedIndex(i);
                                // return, if the panel is already open
                                return;
                            }

                        }
                        // open the panel of the selected tournament
                        panelTabPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
                        panelTabPane.add(tournamentName, new MainMenu(mainFrame, getSelectedTournament()));
                        panelTabPane.setTabComponentAt(panelTabPane.getTabCount() - 1, new ButtonTabComponent(panelTabPane));
                        panelTabPane.setSelectedIndex(panelTabPane.getTabCount() - 1);
                    } catch (NullPointerException e) {
                    }
                } else if (row == 0) {
                    panelTabPane.setSelectedComponent(administrate);
                }
            }

        });

        final MainFrame main = this;
        btnNewTournament.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                main.setEnabled(false);
                ManipulateTournament f = new ManipulateTournament(ManipulateTournament.state.addTournament);
                f.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        main.setEnabled(true);
                    }
                });
            }
        });

        btnDeleteTournament.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    for (int i = 0; i < panelTabPane.getTabCount(); i++) {
                        if (panelTabPane.getTitleAt(i).equals(getSelectedTournament().getName())) {
                            panelTabPane.removeTabAt(i);

                        }
                    }
                    actualEvent.deleteTournament(getSelectedTournament());
                    update();

                    //updateList();
                    return;
                } catch (SQLException ex) {
                    Logger.getLogger(Administrate.class.getName()).log(Level.SEVERE, null, ex);
                }

                JOptionPane.showMessageDialog(null, "Teilnehmer konnte nicht gefunden werden");
            }
        });
    }

    private void setSelectedTournamentByClick() {
        int row = tableTournamentList.getSelectedRow();
        if (row != 0) {
            try {
                String selectedTournamentString = (String) tableTournamentListModel.getValueAt(row, 0);
                selectedTournament = getActualEvent().getTournament(selectedTournamentString);
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
    }

    public Tournament getSelectedTournament() {
        return selectedTournament;
    }

    private void lookAndFeel() {
        /* Set the Nimbus look and feel */
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public AggToolBar getAggToolBar() {
        return tbMainFrame;
    }

    public Event getActualEvent() {
        return actualEvent;
    }

    public void changeTab(JPanel jp) {
        String tournamentName = getSelectedTournament().getName();
        this.panelTabPane.remove(panelTabPane.getSelectedComponent());
        panelTabPane.add(tournamentName, jp);
        panelTabPane.setTabComponentAt(panelTabPane.getSelectedIndex() + 1, new ButtonTabComponent(panelTabPane));
        panelTabPane.setSelectedIndex(panelTabPane.getSelectedIndex() + 1);
    }

    public void setActualEvent(Event newActualEvent) {

        System.out.println("set Actual Event");
        this.panelTabPane.removeAll();
        this.actualEvent = newActualEvent;
        this.administrate = new Administrate(this);
        this.panelTabPane.add("Administration", administrate);

        if (LoginFrame.getLoginFrame().getSelectedEvent() != null || firstInit) {
            this.tbMainFrame.update();
        } else {
            firstInit = true;
        }
        update();
        //TODO alle Operating panes schließen/testen, ob das mit removeAll getan wird
    }

    public void update() {
        // update the tournament List
        updateTournamentList();

        // update the ParticipantList
        administrate.updateList();
    }

    public void updateTournamentList() {
        removeAllListEntrys();
        tableTournamentListModel.addRow(new Object[]{"Event-Teilnehmer"});
        ArrayList<Tournament> allTournaments = actualEvent.getTournaments();
        for (Tournament t : allTournaments) {
            tableTournamentListModel.addRow(new Object[]{t.getName()});
        }
    }

    private void removeAllListEntrys() {
        for (int i = tableTournamentListModel.getRowCount() - 1; i >= 0; i--) {
            tableTournamentListModel.removeRow(i);
        }
    }

    public Administrate getAdministrate() {
        return administrate;
    }

    private boolean init = false;
    private boolean firstInit = false;

    public void setFirstInit() {
        firstInit = true;
    }

    public void setInit(boolean b) {
        init = b;
    }

    public boolean getInit() {
        return init;
    }
}
