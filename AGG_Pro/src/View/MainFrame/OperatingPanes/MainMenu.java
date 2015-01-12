/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.MainFrame.OperatingPanes;

import Controller.Timer.AggTimer;
import Data.LiveClasses.Tournament;
import View.InputPanes.BeamerProjection;
import View.InputPanes.ManipulateTime;
import View.MainFrame.MainFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;

/**
 *
 * @author Heiko Geppert
 */
public class MainMenu extends javax.swing.JPanel {

    private MainFrame main;
    private Tournament actualTournament;
    
    /**
     * Creates new form MainMenu
     * @param main the mainFrame of the program
     */
    public MainMenu(MainFrame main, Tournament tournament) {
        this.main = main;
        this.actualTournament = tournament;
        initComponents();
        addListeners();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPreviousEncounters = new javax.swing.JButton();
        btnActualEncounters = new javax.swing.JButton();
        btnFutureEncounters = new javax.swing.JButton();
        btnParticipants = new javax.swing.JButton();
        pbProgress = new javax.swing.JProgressBar();
        lbProgress = new javax.swing.JLabel();
        lbRemainingTime = new javax.swing.JLabel();
        tfRemainingTime = new javax.swing.JTextField();
        panelPicture = new javax.swing.JPanel();
        btnBeamer = new javax.swing.JButton();
        btnNextRound = new javax.swing.JButton();

        btnPreviousEncounters.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnPreviousEncounters.setText("<html>\nBisherige \nBegegnungen\n</html>");
        btnPreviousEncounters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousEncountersActionPerformed(evt);
            }
        });

        btnActualEncounters.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnActualEncounters.setText("<html>\nAktuelle \nBegegnungen\n</html>");
        btnActualEncounters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualEncountersActionPerformed(evt);
            }
        });

        btnFutureEncounters.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnFutureEncounters.setText("<html>\nZukünftige \nBegegnungen\n</html>");

        btnParticipants.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnParticipants.setText("Teilnehmer");
        btnParticipants.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParticipantsActionPerformed(evt);
            }
        });

        lbProgress.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbProgress.setText("Turnierfortschritt");

        lbRemainingTime.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbRemainingTime.setText("<html>\nVerbleibende \nRundenzeit:\n</html>");

        tfRemainingTime.setEditable(false);
        tfRemainingTime.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tfRemainingTime.setText("00:00:00");

        javax.swing.GroupLayout panelPictureLayout = new javax.swing.GroupLayout(panelPicture);
        panelPicture.setLayout(panelPictureLayout);
        panelPictureLayout.setHorizontalGroup(
            panelPictureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelPictureLayout.setVerticalGroup(
            panelPictureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btnBeamer.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnBeamer.setText("<html> Beamer- projektion </html>");
        btnBeamer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBeamerActionPerformed(evt);
            }
        });

        btnNextRound.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnNextRound.setText("<html>\nNächste \nRunde\n</html>");
        btnNextRound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextRoundActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pbProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbProgress))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addComponent(lbRemainingTime, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tfRemainingTime, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnFutureEncounters, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnActualEncounters, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnParticipants, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(panelPicture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPreviousEncounters, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNextRound, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBeamer, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPreviousEncounters, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBeamer, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNextRound, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnActualEncounters, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFutureEncounters, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnParticipants, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panelPicture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tfRemainingTime, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRemainingTime, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbProgress)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pbProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBeamerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBeamerActionPerformed
        BeamerProjection f = new BeamerProjection(main, actualTournament);
    }//GEN-LAST:event_btnBeamerActionPerformed

    private void btnParticipantsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParticipantsActionPerformed
        main.changeTab(new TournamentParticipants(main, actualTournament));
    }//GEN-LAST:event_btnParticipantsActionPerformed

    private void btnPreviousEncountersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousEncountersActionPerformed
        EncountersList encounters = new EncountersList(EncountersList.state.PAST_ENCOUNTERS, actualTournament);
        main.changeTab(encounters);
    }//GEN-LAST:event_btnPreviousEncountersActionPerformed

    private void btnActualEncountersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualEncountersActionPerformed
        EncountersList encounters = new EncountersList(EncountersList.state.ACTUAL_ENCOUNTERS, actualTournament);
        main.changeTab(encounters);
    }//GEN-LAST:event_btnActualEncountersActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualEncounters;
    private javax.swing.JButton btnBeamer;
    private javax.swing.JButton btnFutureEncounters;
    private javax.swing.JButton btnNextRound;
    private javax.swing.JButton btnParticipants;
    private javax.swing.JButton btnPreviousEncounters;
    private javax.swing.JLabel lbProgress;
    private javax.swing.JLabel lbRemainingTime;
    private javax.swing.JPanel panelPicture;
    private javax.swing.JProgressBar pbProgress;
    private javax.swing.JTextField tfRemainingTime;
    // End of variables declaration//GEN-END:variables

    private void addListeners() {
        //add mouseListener to the timeTextField
        tfRemainingTime.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                main.setEnabled(false);
                View.InputPanes.ManipulateTime f = new ManipulateTime(main);
                f.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e){
                        main.setEnabled(true);
                    }
                });
            }

            @Override
            public void mousePressed(MouseEvent me) {
               
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                
            }

            @Override
            public void mouseExited(MouseEvent me) {
                
            }
        });  
        
        //add the timeTextField to the observed items
        AggTimer timer = AggTimer.getInstance();
        timer.addObservedComponent(tfRemainingTime);
    }
}
