/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.InputPanes;

import Data.LiveClasses.KoSystem;
import Data.LiveClasses.SwissSystem;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.*;

/**
 *
 * @author Heiko Geppert
 */
public class AddTournamentSystem extends javax.swing.JFrame {

    private ManipulateTournament mt;
    
    /**
     * Creates new form addTournamentSystem
     */
    public AddTournamentSystem(ManipulateTournament mt) {     
        this.mt = mt;
        
        initComponents();
        lookAndFeel();
        
        this.setLayout(new BorderLayout());
        cards.add(panelChoose, "Systemauswahl");
        cards.add(panelKO, "KO System");
        cards.add(panelSwiss, "Schweizer System");
        this.add(cards, BorderLayout.CENTER);
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelChoose = new javax.swing.JPanel();
        btnSwiss = new javax.swing.JButton();
        btnKO = new javax.swing.JButton();
        panelKO = new javax.swing.JPanel();
        btnOK = new javax.swing.JButton();
        lbPlayerNumber = new javax.swing.JLabel();
        tfPlayerNumber = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        cbTwoGames = new javax.swing.JCheckBox();
        panelSwiss = new javax.swing.JPanel();
        btnOK1 = new javax.swing.JButton();
        lbRounds1 = new javax.swing.JLabel();
        tfRounds1 = new javax.swing.JTextField();
        lbCut1 = new javax.swing.JLabel();
        tfCut1 = new javax.swing.JTextField();
        btnCancel1 = new javax.swing.JButton();

        panelChoose.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        panelChoose.setMinimumSize(new java.awt.Dimension(250, 200));
        panelChoose.setPreferredSize(new java.awt.Dimension(250, 200));

        btnSwiss.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnSwiss.setText("Schweizer System");
        btnSwiss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSwissActionPerformed(evt);
            }
        });

        btnKO.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnKO.setText("KO System");
        btnKO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelChooseLayout = new javax.swing.GroupLayout(panelChoose);
        panelChoose.setLayout(panelChooseLayout);
        panelChooseLayout.setHorizontalGroup(
            panelChooseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelChooseLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(panelChooseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnKO, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSwiss, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        panelChooseLayout.setVerticalGroup(
            panelChooseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChooseLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(btnSwiss, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnKO, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        panelKO.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        panelKO.setMinimumSize(new java.awt.Dimension(250, 200));

        btnOK.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        lbPlayerNumber.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbPlayerNumber.setText("Spielerzahl");

        tfPlayerNumber.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btnCancel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCancel.setText("zurück");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        cbTwoGames.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cbTwoGames.setText("Doppel-KO-System");
        cbTwoGames.setEnabled(false);

        javax.swing.GroupLayout panelKOLayout = new javax.swing.GroupLayout(panelKO);
        panelKO.setLayout(panelKOLayout);
        panelKOLayout.setHorizontalGroup(
            panelKOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKOLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
            .addGroup(panelKOLayout.createSequentialGroup()
                .addGroup(panelKOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelKOLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbPlayerNumber)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfPlayerNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelKOLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(cbTwoGames, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelKOLayout.setVerticalGroup(
            panelKOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKOLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(panelKOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPlayerNumber)
                    .addComponent(tfPlayerNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(cbTwoGames)
                .addGap(29, 29, 29)
                .addGroup(panelKOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        panelSwiss.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        panelSwiss.setMinimumSize(new java.awt.Dimension(250, 200));

        btnOK1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnOK1.setText("OK");
        btnOK1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOK1ActionPerformed(evt);
            }
        });

        lbRounds1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbRounds1.setText("Runden");

        tfRounds1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbCut1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbCut1.setText("Schnitt");

        tfCut1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btnCancel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCancel1.setText("zurück");
        btnCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSwissLayout = new javax.swing.GroupLayout(panelSwiss);
        panelSwiss.setLayout(panelSwissLayout);
        panelSwissLayout.setHorizontalGroup(
            panelSwissLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSwissLayout.createSequentialGroup()
                .addGroup(panelSwissLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSwissLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelSwissLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRounds1)
                            .addComponent(lbCut1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelSwissLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfCut1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfRounds1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelSwissLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(btnOK1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        panelSwissLayout.setVerticalGroup(
            panelSwissLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSwissLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(panelSwissLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRounds1)
                    .addComponent(tfRounds1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelSwissLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCut1)
                    .addComponent(tfCut1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(panelSwissLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOK1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSwissActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSwissActionPerformed
        CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards, "Schweizer System");
    }//GEN-LAST:event_btnSwissActionPerformed

    private void btnKOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKOActionPerformed
        CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards, "KO System");
    }//GEN-LAST:event_btnKOActionPerformed

    // cancel Button in SwissSystem
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards, "Systemauswahl");
    }//GEN-LAST:event_btnCancelActionPerformed
    
    // cancel Button in KO System
    private void btnCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel1ActionPerformed
        CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards, "Systemauswahl");
    }//GEN-LAST:event_btnCancel1ActionPerformed

    private void btnOK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOK1ActionPerformed
        mt.addTournamentSystem(new SwissSystem("schweizer System", Integer.parseInt(tfCut1.getText()), Integer.parseInt(tfRounds1.getText())));
        close();
    }//GEN-LAST:event_btnOK1ActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        boolean temp  = false;
        if (!cbTwoGames.isSelected()){
            temp = true;
        }
        mt.addTournamentSystem(new KoSystem("KO System", Integer.parseInt(tfPlayerNumber.getText()), temp));
          //new TournamentSystemHolder(("KO System"), Integer.parseInt(tfPlayerNumber.getText()), temp));
        close();
    }//GEN-LAST:event_btnOKActionPerformed

    private void close(){
        mt.closeDialog();
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCancel1;
    private javax.swing.JButton btnKO;
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btnOK1;
    private javax.swing.JButton btnSwiss;
    private javax.swing.JCheckBox cbTwoGames;
    private javax.swing.JLabel lbCut1;
    private javax.swing.JLabel lbPlayerNumber;
    private javax.swing.JLabel lbRounds1;
    private javax.swing.JPanel panelChoose;
    private javax.swing.JPanel panelKO;
    private javax.swing.JPanel panelSwiss;
    private javax.swing.JTextField tfCut1;
    private javax.swing.JTextField tfPlayerNumber;
    private javax.swing.JTextField tfRounds1;
    // End of variables declaration//GEN-END:variables
    private JPanel cards = new JPanel(new CardLayout());
    
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
            java.util.logging.Logger.getLogger(AddTournamentSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
    }
}
