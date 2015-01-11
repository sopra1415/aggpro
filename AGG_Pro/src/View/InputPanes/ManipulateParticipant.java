/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.InputPanes;

import Controller.Actions.ActionEditParticipant;
import Controller.Actions.ActionNewParticipant;
import Data.LiveClasses.Participant;
import Data.LiveClasses.Tournament;
import View.MainFrame.MainFrame;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Heiko Geppert
 */
public class ManipulateParticipant extends javax.swing.JFrame {

    public static enum state{ 
        addParticipant, modifyParticipant
    }
    private final MainFrame main;
    private DefaultTableModel tableTournamentsModel;
    private state state;
    private Participant actualPlayer;
    
    
    /**
     * Creates new form manipulateParticipant
     * @param s
     */
    public ManipulateParticipant(state s) {
        this.main = MainFrame.getMainFrame();
        this.state = s;
        initComponents();
        
        if (s==state.addParticipant){
            btnOK.setAction(new ActionNewParticipant(this));
        } else if (s==state.modifyParticipant){
            btnOK.setAction(new ActionEditParticipant(this, main.getAdministrate().getSelectedParticipant()));
            actualPlayer = main.getActualEvent().getParticipant(tfNumber.getText());
            preInitialize();
            
        }
        btnOK.setText("OK");
        
        
        lookAndFeel();
        updateTournamentList();
        this.setVisible(true);
    }
    
    private void preInitialize(){
        Participant p = main.getAdministrate().getSelectedParticipant();
        
        //preSetValues
        tfNumber.setText(p.getStartnumber());
        tfName.setText(p.getName());
        tfPreName.setText(p.getPrename());
        tfNickname.setText(p.getNickname());
        tfEmail.setText(p.getEmail());
        tfOther.setText(p.getOther());
        cbPaid.setSelected(p.isPaid());
        cbPresent.setSelected(p.isPresent());
        cbSuperfreepass.setSelected(p.isSuperfreepass());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbNumber = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        lbPreName = new javax.swing.JLabel();
        lbNickname = new javax.swing.JLabel();
        tfNumber = new javax.swing.JTextField();
        tfName = new javax.swing.JTextField();
        tfPreName = new javax.swing.JTextField();
        tfNickname = new javax.swing.JTextField();
        lbOther = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tfOther = new javax.swing.JTextArea();
        cbPaid = new javax.swing.JCheckBox();
        cbPresent = new javax.swing.JCheckBox();
        panelTable = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTournaments = new javax.swing.JTable();
        lbEmail = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        cbSuperfreepass = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbNumber.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbNumber.setText("Startnummer");

        lbName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbName.setText("Name");

        lbPreName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbPreName.setText("Vorname");

        lbNickname.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbNickname.setText("Nickname");

        tfNumber.setEditable(false);
        tfNumber.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tfNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNumberActionPerformed(evt);
            }
        });

        tfName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tfName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNameActionPerformed(evt);
            }
        });

        tfPreName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        tfNickname.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbOther.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbOther.setText("Sonstiges");

        tfOther.setColumns(20);
        tfOther.setRows(5);
        jScrollPane1.setViewportView(tfOther);

        cbPaid.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbPaid.setText("gezahlt");
        cbPaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPaidActionPerformed(evt);
            }
        });

        cbPresent.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbPresent.setText("anwesend");
        cbPresent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPresentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTableLayout = new javax.swing.GroupLayout(panelTable);
        panelTable.setLayout(panelTableLayout);
        panelTableLayout.setHorizontalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelTableLayout.setVerticalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        btnCancel.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnCancel.setText("Abbrechen");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnOK.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnOK.setText("OK");

        tableTournaments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Turnier", "Teilnahme"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableTournamentsModel = (DefaultTableModel)tableTournaments.getModel();
        tableTournaments.setFillsViewportHeight(true);
        jScrollPane2.setViewportView(tableTournaments);

        lbEmail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbEmail.setText("E-Mail");

        tfEmail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        cbSuperfreepass.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbSuperfreepass.setText("Superfreilos");
        cbSuperfreepass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSuperfreepassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tfNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbOther))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbPreName, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbNickname, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfPreName, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tfNickname, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(panelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancel))
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbPaid)
                                    .addComponent(cbSuperfreepass))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbPresent)
                                .addGap(9, 9, 9)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNumber)
                    .addComponent(tfNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbOther))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbName)
                            .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbPreName)
                            .addComponent(tfPreName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbNickname)
                            .addComponent(tfNickname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbEmail)
                            .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(panelTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbPaid)
                                .addComponent(cbPresent))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbSuperfreepass)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnCancel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnOK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbPaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPaidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPaidActionPerformed

    private void cbPresentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPresentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPresentActionPerformed

    private void tfNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNumberActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
       close();       
    }//GEN-LAST:event_btnCancelActionPerformed

    private void tfNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNameActionPerformed

    private void cbSuperfreepassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSuperfreepassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSuperfreepassActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private javax.swing.JCheckBox cbPaid;
    private javax.swing.JCheckBox cbPresent;
    private javax.swing.JCheckBox cbSuperfreepass;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbNickname;
    private javax.swing.JLabel lbNumber;
    private javax.swing.JLabel lbOther;
    private javax.swing.JLabel lbPreName;
    private javax.swing.JPanel panelTable;
    private javax.swing.JTable tableTournaments;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfNickname;
    private javax.swing.JTextField tfNumber;
    private javax.swing.JTextArea tfOther;
    private javax.swing.JTextField tfPreName;
    // End of variables declaration//GEN-END:variables

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
            java.util.logging.Logger.getLogger(ManipulateParticipant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
    }

    public boolean getParticipantPaid() {
        return cbPaid.isSelected();
    }

    public boolean getParticipantPresent() {
        return cbPresent.isSelected();
    }
    
    public void close(){
         main.setEnabled(true);
         this.dispose();
    }

    public String getParticipantName() {
        return tfName.getText();
    }

    public String getParticipantNickname() {
        return tfNickname.getText();
    }

    public String getParticipantNumber() {
        return tfNumber.getText();
    }

    public String getParticipantOther() {
        return tfOther.getText();
    }

    public String getParticipantPreName() {
        return tfPreName.getText();
    }

    public boolean getParticipantSuperfreepass() {
        return cbSuperfreepass.isSelected();
    }
    
    public String getParticipantEmail(){
        return tfEmail.getText();
    }
    
    private void updateTournamentList(){
        // clear Table
        for (int i = tableTournamentsModel.getRowCount()-1; i >= 0; i--) {
            tableTournamentsModel.removeRow(i);
        }
        for (Tournament t:main.getActualEvent().getTournaments()){
            if (state==state.addParticipant){
                tableTournamentsModel.addRow(new Object[]{t.getName(), false});    
            } else if (state == state.modifyParticipant){
                Object[] row = new Object[2];
                row[0] = t.getName();
                row[1] = actualPlayer.getTournaments().contains(t);
                tableTournamentsModel.addRow(row);
            }
            
        }
    }
    
    /**
     * returns all tournaments the choosen (or the new) participant is registrated for
     * @return 
     */
    public ArrayList<Tournament> getSelectedTournaments(){
        ArrayList<Tournament> selectedTournaments = new ArrayList<Tournament>();
        int counter = 0;
        for (Tournament t:main.getActualEvent().getTournaments()){
            if ((boolean)tableTournamentsModel.getValueAt(counter, 1)){
                selectedTournaments.add(t);
            }
            counter++;
        }
        return selectedTournaments;
    }
}
