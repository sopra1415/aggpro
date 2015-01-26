/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.MainFrame.OperatingPanes;

import Controller.Actions.ActionEditEncounter;
import Data.LiveClasses.*;
import Data.LiveClasses.Tournament;
import View.MainFrame.MainFrame;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Heiko Geppert
 */
public class EncountersList extends javax.swing.JPanel {

    private Tournament actualTournament;

    public JTable getTableEncounters() {
        return tableEncounters;
    }

    public DefaultTableModel getTableEncountersModel() {
        return tableEncountersModel;
    }

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
        if (state != state.ACTUAL_ENCOUNTERS) {
            btnSave.setVisible(false);
        }
        btnSave.setAction(new ActionEditEncounter(this, actualTournament));
        btnSave.setText("Speichern");
        update();
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
        btnSave = new javax.swing.JButton();

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
                false, false, false, false, true, false, false, false, false, false, true
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
        tableEncounters.setRowHeight(30);
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

        btnSave.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnSave.setText("speichern");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbProgress)
                    .addComponent(pbProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave)
                .addGap(18, 18, 18)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(panelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelTable, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private javax.swing.JButton btnSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbProgress;
    private javax.swing.JPanel panelTable;
    private javax.swing.JProgressBar pbProgress;
    private javax.swing.JTable tableEncounters;
    // End of variables declaration//GEN-END:variables
    private DefaultTableModel tableEncountersModel;

    private void initTable(state s) {
        tableEncounters.getTableHeader().setReorderingAllowed(false);
        
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
        Vector rowData = null;
        for (Encounter e : matches) {

            rowData = e.getParticipants().get(0).getData();

            if (s != state.PAST_ENCOUNTERS) {
                rowData.add(e.getPoints().get(0));
            } else {
                rowData.add(e.getPoints().get(0));
            }

            rowData.add("VS.");

            for (Object o : e.getParticipants().get(1).getData()) {
                rowData.add(o);
            }

            if (s != state.PAST_ENCOUNTERS) {
                rowData.add(e.getPoints().get(1));
            } else {
                rowData.add(e.getPoints().get(1));
            }
            if (s == state.ACTUAL_ENCOUNTERS) {
                TableColumn column1 = tableEncounters.getColumnModel().getColumn(4);
                //column1.setCellRenderer(new ComboBoxCellRenderer(this));
                column1.setCellEditor(new ComboBoxCellEditor(this));

                TableColumn column2 = tableEncounters.getColumnModel().getColumn(10);
                //column2.setCellRenderer(new ComboBoxCellRenderer(this));
                column2.setCellEditor(new ComboBoxCellEditor(this));
            }

            tableEncountersModel.addRow(rowData);

        }
        if(s == state.ACTUAL_ENCOUNTERS){
            tableEncounters.getColumnModel().getColumn(4).setCellRenderer(new StatusColumnCellRenderer());
            tableEncounters.getColumnModel().getColumn(10).setCellRenderer(new StatusColumnCellRenderer());
        }
        

       

    }

    /**
     * updates the tournamentprogress
     */
    private void update() {
        
        this.pbProgress.setValue(actualTournament.getProgressOfTournament());
    }

    class ComboBoxPanel extends JPanel {

        private Integer[] m = new Integer[]{actualTournament.getModul().getPointsDraw(), actualTournament.getModul().getPointsWin(), actualTournament.getModul().getPointsLoose()};
        protected final JComboBox<Integer> comboBox = new JComboBox<Integer>(m) {
            @Override
            public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                return new Dimension(40, d.height);
            }
        };

        public ComboBoxPanel(EncountersList eL) {
            super();
            final EncountersList eList = eL;
            setOpaque(true);
            comboBox.setEditable(true);
            add(comboBox);

        }
    }

    class ComboBoxCellRenderer extends ComboBoxPanel
            implements TableCellRenderer {

        public ComboBoxCellRenderer(EncountersList eL) {
            super(eL);
            setName("Table.cellRenderer");
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            setBackground(isSelected ? table.getSelectionBackground()
                    : table.getBackground());
            if (value != null) {
                comboBox.setSelectedItem(value);
            }
            return this;
        }
    }

    class ComboBoxCellEditor extends ComboBoxPanel
            implements TableCellEditor {

        final EncountersList el;

        public ComboBoxCellEditor(EncountersList eL) {
            super(eL);
            this.el = eL;
        }

        @Override
        public Component getTableCellEditorComponent(
                JTable table, Object value, boolean isSelected, int row, int column) {
            setForeground(table.getForeground());
            this.setBackground(table.getSelectionBackground());
            comboBox.setSelectedItem(value);
            return this;
        }

        //Copid from DefaultCellEditor.EditorDelegate
        @Override
        public Object getCellEditorValue() {
            return comboBox.getSelectedItem();
        }

        @Override
        public boolean shouldSelectCell(EventObject anEvent) {
            if (anEvent instanceof MouseEvent) {
                MouseEvent e = (MouseEvent) anEvent;
                return e.getID() != MouseEvent.MOUSE_DRAGGED;
            }
            return true;
        }

        @Override
        public boolean stopCellEditing() {
            if (comboBox.isEditable()) {
                comboBox.actionPerformed(new ActionEvent(this, 0, ""));
            }
            fireEditingStopped();
            return true;
        }

        //Copid from AbstractCellEditor
        //protected EventListenerList listenerList = new EventListenerList();
        transient protected ChangeEvent changeEvent = null;

        @Override
        public boolean isCellEditable(EventObject e) {
            return true;
        }

        @Override
        public void cancelCellEditing() {
            fireEditingCanceled();
        }

        @Override
        public void addCellEditorListener(CellEditorListener l) {
            listenerList.add(CellEditorListener.class, l);
        }

        @Override
        public void removeCellEditorListener(CellEditorListener l) {
            listenerList.remove(CellEditorListener.class, l);
        }

        public CellEditorListener[] getCellEditorListeners() {
            return listenerList.getListeners(CellEditorListener.class);
        }

        protected void fireEditingStopped() {
            // Guaranteed to return a non-null array
            Object[] listeners = listenerList.getListenerList();
            // Process the listeners last to first, notifying
            // those that are interested in this event
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == CellEditorListener.class) {
                    // Lazily create the event:
                    if (changeEvent == null) {
                        changeEvent = new ChangeEvent(this);
                    }
                    ((CellEditorListener) listeners[i + 1]).editingStopped(changeEvent);
                }
            }

        }

        protected void fireEditingCanceled() {
            // Guaranteed to return a non-null array
            Object[] listeners = listenerList.getListenerList();
            // Process the listeners last to first, notifyingmouseListener
            // those that are interested in this event
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == CellEditorListener.class) {
                    // Lazily create the event:
                    if (changeEvent == null) {
                        changeEvent = new ChangeEvent(this);
                    }
                    ((CellEditorListener) listeners[i + 1]).editingCanceled(changeEvent);
                }
            }
        }
    }

    public void deselectTable() {
        try {
            tableEncounters.setColumnSelectionInterval(0, 0);
            tableEncounters.setRowSelectionInterval(0, 0);
        } catch (NullPointerException e) {

        }

    }

    public class StatusColumnCellRenderer extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

            //Cells are by default rendered as a JLabel.
            JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            //Get the status for the current row.
            if ((Integer)table.getValueAt(row, col) == -1) {
                // red
                l.setBackground(new Color(209,117,117));
            } else {
                // green
                l.setBackground(new Color(0,204,0));   //143,255,143));
            }

            //Return the JLabel which renders the cell.
            return l;

        }
    }
}
