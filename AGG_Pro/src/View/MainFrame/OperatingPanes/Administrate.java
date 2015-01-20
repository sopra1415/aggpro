/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.MainFrame.OperatingPanes;

import Data.LiveClasses.*;
import View.InputPanes.ManipulateParticipant;
import View.MainFrame.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Heiko Geppert
 */
public class Administrate extends javax.swing.JPanel {

    private MainFrame main;
    private Event actualEvent;
    private DefaultTableModel tableParticipantTableModel;

    /**
     * Creates new form Participant
     */
    public Administrate(MainFrame main) {
        this.main = main;
        this.actualEvent = main.getActualEvent();
        initComponents();
        initTable();
        initListeners();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTable = new javax.swing.JPanel();
        paneTableScrollPane = new javax.swing.JScrollPane();
        tableParticipant = new javax.swing.JTable();
        btnNewParticipant = new javax.swing.JButton();
        btnDeleteParticipant = new javax.swing.JButton();
        btnEditParticipant = new javax.swing.JButton();

        panelTable.setPreferredSize(new java.awt.Dimension(610, 408));

        tableParticipant.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Startnummer", "Name", "Vorname", "Nickname", "Bezahlt", "Anwesend", "Angemeldete Turniere"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableParticipantTableModel= (DefaultTableModel ) tableParticipant.getModel();
        tableParticipant.setFillsViewportHeight(true);
        paneTableScrollPane.setViewportView(tableParticipant);

        javax.swing.GroupLayout panelTableLayout = new javax.swing.GroupLayout(panelTable);
        panelTable.setLayout(panelTableLayout);
        panelTableLayout.setHorizontalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
        );
        panelTableLayout.setVerticalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTableLayout.createSequentialGroup()
                .addComponent(paneTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnNewParticipant.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNewParticipant.setText("neuer Teilnehmer");

        btnDeleteParticipant.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnDeleteParticipant.setText("Teilnehmer löschen");
        btnDeleteParticipant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteParticipantActionPerformed(evt);
            }
        });

        btnEditParticipant.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnEditParticipant.setText("Teilnehmer bearbeiten");
        btnEditParticipant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditParticipantActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNewParticipant, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDeleteParticipant, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEditParticipant, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelTable, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNewParticipant, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteParticipant, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditParticipant, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditParticipantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditParticipantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditParticipantActionPerformed

    private void btnDeleteParticipantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteParticipantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteParticipantActionPerformed

    private void initTable() {

        //load data
        ArrayList<Data.LiveClasses.Participant> allParticipants = this.actualEvent.getParticipants();
        for (Data.LiveClasses.Participant p : allParticipants) {
            Vector rowData = new Vector();
            rowData.add(p.getId());
            rowData.add(p.getName());
            rowData.add(p.getPrename());
            rowData.add(p.getNickname());
            rowData.add(p.isPaid());
            rowData.add(p.isPresent());
            rowData.add(p.getRegistratedTournaments());

            tableParticipantTableModel.addRow(rowData);
        }

    }

    private void initListeners() {

        btnNewParticipant.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                main.setEnabled(false);
                View.InputPanes.ManipulateParticipant f = new ManipulateParticipant(ManipulateParticipant.state.addParticipant);
                f.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        main.setEnabled(true);
                    }
                });
            }
        });

        btnEditParticipant.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    getSelectedParticipant();
                    main.setEnabled(false);
                    System.out.println("about to start the new frame");
                    View.InputPanes.ManipulateParticipant f = new ManipulateParticipant(ManipulateParticipant.state.modifyParticipant);
                    f.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            main.setEnabled(true);
                        }
                    });
                } catch (Exception e) {
                }
            }
        });

        btnDeleteParticipant.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    main.getActualEvent().deleteParticipant(getSelectedParticipant());
                    updateList();
                    return;
                } catch (SQLException ex) {
                    Logger.getLogger(Administrate.class.getName()).log(Level.SEVERE, null, ex);
                }

                JOptionPane.showMessageDialog(null, "Teilnehmer konnte nicht gefunden werden");
            }
        });
        tableParticipant.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2 && !me.isConsumed()) {
                    me.consume();
                    ManipulateParticipant mp = new ManipulateParticipant(ManipulateParticipant.state.modifyParticipant);

                }
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
    }

    public void updateList() {
        removeAllListEntrys();
        ArrayList<Data.LiveClasses.Participant> allParticipants = main.getActualEvent().getParticipants();
        for (Data.LiveClasses.Participant p : allParticipants) {
            tableParticipantTableModel.addRow(new Object[]{p.getStartnumber(), p.getName(), p.getPrename(),
                p.getNickname(), p.isPaid(), p.isPresent(), p.getRegistratedTournaments()});
        }
    }

    private void removeAllListEntrys() {
        for (int i = tableParticipantTableModel.getRowCount() - 1; i >= 0; i--) {
            tableParticipantTableModel.removeRow(i);
        }
    }

    public Data.LiveClasses.Participant getSelectedParticipant() {

        int selectedRow = tableParticipant.getSelectedRow();
        String selectedStartnumber = (String) tableParticipantTableModel.getValueAt(selectedRow, 0);
        for (Data.LiveClasses.Participant pa : main.getActualEvent().getParticipants()) {
            if (pa.getStartnumber().equals(selectedStartnumber)) {
                return pa;
            }
        }
        return null;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteParticipant;
    private javax.swing.JButton btnEditParticipant;
    private javax.swing.JButton btnNewParticipant;
    private javax.swing.JScrollPane paneTableScrollPane;
    private javax.swing.JPanel panelTable;
    private javax.swing.JTable tableParticipant;
    // End of variables declaration//GEN-END:variables
}
