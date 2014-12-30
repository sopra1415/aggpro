/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.MainFrame;

import java.awt.BorderLayout;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Heiko Geppert
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        lookAndFeel();
        initComponents();
        initOwnComponents();

        this.tbMainFrame.lock();
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
        
        // initialize components
        tbMainFrame = new AggToolBar(this);
        panelToolBar = new JPanel();
        panelFrame = new JPanel();
        panelFrame.setLayout(new BorderLayout());
        panelTournamentList = new JScrollPane();
        
        //TODO inhaltlich ordentlich umsetzen
        Vector<String> header = new Vector<String>();
        header.add("Turnier");
        Vector<Vector<String>> rowData = new Vector<Vector<String>>();
        Vector<String> temp = new Vector<String>();
        temp.add("Allgemeine Einstellungen");
        rowData.add(temp);
        
        tournamentList = new JTable(rowData, header);
        tournamentList.setModel(new DefaultTableModel(rowData, header));
        // table will be stretched to fill the whole "viewport"
        tournamentList.setFillsViewportHeight(true);
        
        
        
        panelTabPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        panelTabPane.add(new View.MainFrame.OperatingPanes.MainMenu());
        // customize components
        panelMainFrame = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false, panelTournamentList, panelTabPane);
        // calculate the Position of the divider
        double dDivLoc = this.getSize().width *0.25d;
        panelMainFrame.setDividerLocation(((int)dDivLoc));        
        panelMainFrame.setDividerSize(3);          
        panelMainFrame.setEnabled(false);
        
        //build the frame, with the components
        panelToolBar.add(tbMainFrame);
        panelTournamentList.add(tournamentList);
        this.add(panelToolBar, BorderLayout.NORTH);
        this.add(panelFrame, BorderLayout.CENTER);
        this.panelFrame.add(panelMainFrame, BorderLayout.CENTER);
          
    }
    
    private void lookAndFeel(){
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
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    // own Variables declaration - do modify ;)
    private JPanel panelToolBar;
    private JPanel panelFrame;
    private AggToolBar tbMainFrame;
    private JSplitPane panelMainFrame;
    private JScrollPane panelTournamentList;
    private JTabbedPane panelTabPane;
    
    private JTable tournamentList;

}
