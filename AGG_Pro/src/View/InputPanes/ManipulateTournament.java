/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.InputPanes;

import Controller.Actions.ActionNewTournament;
import Data.LiveClasses.TournamentSystem;
import View.MainFrame.MainFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Heiko Geppert
 */
public class ManipulateTournament extends javax.swing.JFrame {

    private MainFrame main;
    private DefaultTableModel tableTournamentSystemsModel;
    private ArrayList<TournamentSystem> choosenSystems;
    private boolean dialogOpen;
    private AddTournamentSystem addSystemFrame;
    
    public enum state{
        addTournament, modifyTournament
    }
    
    /**
     * Creates new form manipulateTournament
     */
    public ManipulateTournament(state state) {
        this.main = MainFrame.getMainFrame();
        this.choosenSystems = new ArrayList<TournamentSystem>();
        this.dialogOpen = false;
        
        initComponents();
        
        if (state==state.addTournament){
            this.setTitle("Neues Turnier  erstellen");
            btnOK.setAction(new ActionNewTournament(this));
            
        } else if (state == state.modifyTournament){
            this.setTitle("Turnier bearbeiten");
            //TODO Action für bearbeitung von Turnieren bieten
            //TODO bisherige Daten laden
        }
        btnOK.setText("OK");
        lookAndFeel();
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbName = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        lbGame = new javax.swing.JLabel();
        cbGame = new javax.swing.JComboBox();
        lbScore = new javax.swing.JLabel();
        lbScoreVictory = new javax.swing.JLabel();
        lbScoreDraw = new javax.swing.JLabel();
        lbDefeat = new javax.swing.JLabel();
        tfVictory = new javax.swing.JTextField();
        tfDraw = new javax.swing.JTextField();
        tfDefeat = new javax.swing.JTextField();
        lbTournamentSystem = new javax.swing.JLabel();
        panelTournamentSystems = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTournamentSystems = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbName.setText("Turniername");

        tfName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tfName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNameActionPerformed(evt);
            }
        });

        lbGame.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbGame.setText("Spiel");

        cbGame.setEditable(true);
        cbGame.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Spiel 1", "Item 2", "Item 3", "Item 4" }));
        cbGame.setEnabled(false);

        lbScore.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbScore.setText("Punkte bei ...");

        lbScoreVictory.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbScoreVictory.setText("Sieg");

        lbScoreDraw.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbScoreDraw.setText("Unentschieden");

        lbDefeat.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbDefeat.setText("Niederlage");

        tfVictory.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        tfDraw.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        tfDefeat.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbTournamentSystem.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbTournamentSystem.setText("Turniersysteme");

        tableTournamentSystems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TurnierSystem"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableTournamentSystemsModel = (DefaultTableModel) tableTournamentSystems.getModel();
        tableTournamentSystems.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(tableTournamentSystems);

        btnAdd.setText("+");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setText("-");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTournamentSystemsLayout = new javax.swing.GroupLayout(panelTournamentSystems);
        panelTournamentSystems.setLayout(panelTournamentSystemsLayout);
        panelTournamentSystemsLayout.setHorizontalGroup(
            panelTournamentSystemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(panelTournamentSystemsLayout.createSequentialGroup()
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDelete))
        );
        panelTournamentSystemsLayout.setVerticalGroup(
            panelTournamentSystemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTournamentSystemsLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTournamentSystemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnDelete)))
        );

        btnOK.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnOK.setText("OK");

        btnCancel.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnCancel.setText("abbrechen");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbScore)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbScoreDraw)
                        .addGap(18, 18, 18)
                        .addComponent(tfDraw, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbScoreVictory)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfVictory, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbName)
                    .addComponent(tfName)
                    .addComponent(lbGame)
                    .addComponent(cbGame, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbDefeat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfDefeat, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(panelTournamentSystems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lbTournamentSystem)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addComponent(btnOK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbName)
                    .addComponent(lbTournamentSystem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbGame)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbScore))
                    .addComponent(panelTournamentSystems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbScoreVictory)
                            .addComponent(tfVictory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbScoreDraw)
                            .addComponent(tfDraw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfDefeat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDefeat)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tfNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNameActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.close();
        
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        dialogOpen=true;
        btnOK.setEnabled(false);
        addSystemFrame = new AddTournamentSystem(this);
        addSystemFrame.setVisible(true);
        addSystemFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                btnOK.setEnabled(true);
            }
        });
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        tableTournamentSystemsModel.removeRow(tableTournamentSystems.getSelectedColumn());
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnOK;
    private javax.swing.JComboBox cbGame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDefeat;
    private javax.swing.JLabel lbGame;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbScore;
    private javax.swing.JLabel lbScoreDraw;
    private javax.swing.JLabel lbScoreVictory;
    private javax.swing.JLabel lbTournamentSystem;
    private javax.swing.JPanel panelTournamentSystems;
    private javax.swing.JTable tableTournamentSystems;
    private javax.swing.JTextField tfDefeat;
    private javax.swing.JTextField tfDraw;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfVictory;
    // End of variables declaration//GEN-END:variables
    
    public void close() {
        if (dialogOpen){
            addSystemFrame.dispose();
        }
        main.setEnabled(true);
        this.dispose();
        main.updateTournamentList();
    }
    
    
    public String getTournamentName(){
        return tfName.getText();
    }
    
    public int[] getPoints(){
        int[] points = {
            Integer.parseInt(tfVictory.getText()), 
            Integer.parseInt(tfDraw.getText()), 
            Integer.parseInt(tfDefeat.getText())
        };
        return points;        
    }
    
    public ArrayList<TournamentSystem> getTournamentSystems(){
        
        /*ArrayList<TournamentSystem> systems = new ArrayList<TournamentSystem>();
        
        for (TournamentSystemHolder tsh:choosenSystems){

            if (tsh.getName().equals("schweizer System")){
                systems.add(tsh.getSwissSystem());
            } else if (tsh.getName().equals("KO System")){
                systems.add(tsh.getKoSystem());
            } else {
                System.err.println("Fehler in Erstellung der Turniersysteme");
            }
        }*/
        return choosenSystems;
    }
    
    private void lookAndFeel() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
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
            java.util.logging.Logger.getLogger(ManipulateTournament.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }
    
    public void addTournamentSystem(TournamentSystem t){
        choosenSystems.add(t);
        tableTournamentSystemsModel.addRow(new Object[]{t.getName()});
    }
    
    public void closeDialog(){
        this.dialogOpen=false;
        this.btnOK.setEnabled(true);
    }
}
