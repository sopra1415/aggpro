/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.MainFrame.OperatingPanes;

import Data.LiveClasses.*;
import Data.LiveClasses.Tournament;
import View.MainFrame.MainFrame;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Heiko Geppert
 */
public class EncountersList extends javax.swing.JPanel {

    private Tournament actualTournament;
    private JComboBox pointsChooser0;
    private JComboBox pointsChooser1;

    public static enum state {

        PAST_ENCOUNTERS, ACTUAL_ENCOUNTERS, FUTURE_ENCOUNTERS
    }

    /**
     * Creates new form Participant
     *
     * @param state the List which should be shown
     */
    public EncountersList(state state, Tournament tournament) {
        this.actualTournament = tournament;
        initComponents();
        initTable(state);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pbProgress = new javax.swing.JProgressBar();
        lbProgress = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        panelTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEncounters = new javax.swing.JTable();

        lbProgress.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbProgress.setText("Turnierfortschritt");

        btnBack.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnBack.setText("zurück");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        panelTable.setPreferredSize(new java.awt.Dimension(610, 408));

        tableEncounters.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "StartNummer", "Name", "Vorname", "Nickname", "Points", "versus", "StartNummer", "Name", "Vorname", "Nickname", "Points"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableEncountersModel = (DefaultTableModel) tableEncounters.getModel();
        tableEncounters.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(tableEncounters);

        javax.swing.GroupLayout panelTableLayout = new javax.swing.GroupLayout(panelTable);
        panelTable.setLayout(panelTableLayout);
        panelTableLayout.setHorizontalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
        );
        panelTableLayout.setVerticalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbProgress)
                    .addComponent(pbProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(panelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelTable, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbProgress)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pbProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        MainFrame mf = MainFrame.getMainFrame();
        JPanel mmenu = new MainMenu(mf, actualTournament);
        mf.changeTab(mmenu);
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbProgress;
    private javax.swing.JPanel panelTable;
    private javax.swing.JProgressBar pbProgress;
    private javax.swing.JTable tableEncounters;
    // End of variables declaration//GEN-END:variables
    private DefaultTableModel tableEncountersModel;

    private void initTable(state s) {
        ArrayList<Encounter> matches = new ArrayList<Encounter>();
        Integer[] points = new Integer[3];
        points[0] = actualTournament.getModul().getPointsDraw();
        points[1] = actualTournament.getModul().getPointsLoose();
        points[2] = actualTournament.getModul().getPointsWin();


        //get the wanted encounters
        ArrayList<Round> allRounds = actualTournament.getRounds();
        if (s == state.ACTUAL_ENCOUNTERS) {
            // saves the Encounters of the latest  Round into matches
            if (!allRounds.isEmpty()) {
                matches.addAll(allRounds.get(allRounds.size() - 1).getEncounters());

            }
        } else if (s == state.PAST_ENCOUNTERS) {

            for (int i = 0; i < allRounds.size() - 1; i++) {
                matches.addAll(allRounds.get(i).getEncounters());
            }
        }

        //show the encounters
        tableEncounters.removeAll();
        Vector rowData;
        for (Encounter e : matches) {

            rowData = e.getParticipants().get(0).getData();
//            if (s != state.PAST_ENCOUNTERS) {
//            DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
//            JComboBox combo = new JComboBox();
//            TableColumn col = tableEncounters.getColumnModel().getColumn(4);
//            col.setCellEditor( new DefaultCellEditor( combo ) );            
//            } else{
//                e.getPoints().get(0);
//            }
            rowData.add("VS.");
            for (Object o : e.getParticipants().get(1).getData()) {
                rowData.add(o);
            }
            
            System.out.println(s != state.PAST_ENCOUNTERS);
            
            
//            if (s != state.PAST_ENCOUNTERS) {
//                DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
//                JComboBox combo = new JComboBox();
//                TableColumn col = tableEncounters.getColumnModel().getColumn(10);
//                col.setCellEditor( new DefaultCellEditor( combo ) );       
//            } else{
//                rowData.add(e.getPoints().get(1));
//            }
            System.out.println("");
            tableEncountersModel.addRow(rowData);
        }
    }

    public DefaultTableModel getTableEncountersModel() {
        return tableEncountersModel;
    }

    public JTable getTableEncounters() {
        return tableEncounters;
    }

    public JComboBox getPointsChooser0() {
        return pointsChooser0;
    }

    public JComboBox getPointsChooser1() {
        return pointsChooser1;
    }

}
