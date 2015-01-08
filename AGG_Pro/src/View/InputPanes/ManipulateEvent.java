/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.InputPanes;

import Controller.Actions.ActionListenerManipulateEvent;
import View.MainFrame.MainFrame;
import java.awt.BorderLayout;
import java.text.ParseException;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import org.jdatepicker.DateModel;
import org.jdatepicker.JDateComponent;
import org.jdatepicker.JDatePanel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author Heiko Geppert
 */
public class ManipulateEvent extends javax.swing.JFrame {

    
    public static enum state{ 
        addEvent, modifyEvent
    }
    private final MainFrame main;
    private state state;
    
    /**
     * Creates new form manipulateEvent
     * @param main the parent JFrame, used to have a modal Frame here
     * @param s specifys which sort of manipualtion is about to be done
     *  eighter creating a new Event, or editing the existing
     */
    public ManipulateEvent(MainFrame main, state s){
        super();
        this.main = main;
        this.state = s;
        //panelStartDate.add(startDatePicker);
        //panelEndDate.add(endDatePicker);
        initComponents();
        initOwnComponents();
        
        if (s==state.addEvent){
            this.setTitle("Neues Event");
        }else if (s==state.modifyEvent){
            this.setTitle("Event bearbeiten");
            lbEventName.setText("neuer Eventname");
            //TODO Daten des bisherigen Events holen und in die Textfelder schreiben
        }
        
        //init Listener ffor the ok button
        btnOK.addActionListener(new ActionListenerManipulateEvent(this));
        
        lookAndFeel();
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editort.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDatePickerUtil1 = new org.jdatepicker.util.JDatePickerUtil();
        jDatePickerUtil2 = new org.jdatepicker.util.JDatePickerUtil();
        jDatePickerUtil3 = new org.jdatepicker.util.JDatePickerUtil();
        jDatePickerUtil4 = new org.jdatepicker.util.JDatePickerUtil();
        jDatePickerUtil5 = new org.jdatepicker.util.JDatePickerUtil();
        jDatePickerUtil6 = new org.jdatepicker.util.JDatePickerUtil();
        jDatePickerUtil7 = new org.jdatepicker.util.JDatePickerUtil();
        jDatePickerUtil8 = new org.jdatepicker.util.JDatePickerUtil();
        lbEventName = new javax.swing.JLabel();
        lbTimeStart = new javax.swing.JLabel();
        lbTimeStart1 = new javax.swing.JLabel();
        tfEventName = new javax.swing.JTextField();
        panelStartDate = new javax.swing.JPanel();
        panelEndDate = new javax.swing.JPanel();
        panelTimeOfDay = new javax.swing.JPanel();
        cbTimeOfDay = new javax.swing.JCheckBox();
        lbStart = new javax.swing.JLabel();
        lbEnd = new javax.swing.JLabel();
        tfStart = new javax.swing.JFormattedTextField();
        tfEnd = new javax.swing.JFormattedTextField();
        btnOK = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbEventName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbEventName.setText("Event Name:");

        lbTimeStart.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbTimeStart.setText("Start Datum");

        lbTimeStart1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbTimeStart1.setText("End Datum");

        panelStartDate.setMinimumSize(new java.awt.Dimension(178, 127));
        panelStartDate.setPreferredSize(new java.awt.Dimension(178, 127));

        javax.swing.GroupLayout panelStartDateLayout = new javax.swing.GroupLayout(panelStartDate);
        panelStartDate.setLayout(panelStartDateLayout);
        panelStartDateLayout.setHorizontalGroup(
            panelStartDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelStartDateLayout.setVerticalGroup(
            panelStartDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 127, Short.MAX_VALUE)
        );

        panelEndDate.setMinimumSize(new java.awt.Dimension(180, 125));
        panelEndDate.setPreferredSize(new java.awt.Dimension(180, 125));

        javax.swing.GroupLayout panelEndDateLayout = new javax.swing.GroupLayout(panelEndDate);
        panelEndDate.setLayout(panelEndDateLayout);
        panelEndDateLayout.setHorizontalGroup(
            panelEndDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );
        panelEndDateLayout.setVerticalGroup(
            panelEndDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 125, Short.MAX_VALUE)
        );

        cbTimeOfDay.setSelected(true);
        cbTimeOfDay.setText("Uhrzeit manuell angeben");
        cbTimeOfDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTimeOfDayActionPerformed(evt);
            }
        });

        lbStart.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbStart.setText("Beginn:");

        lbEnd.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbEnd.setText("Ende:");

        tfStart.setText("ss:mm");

        tfEnd.setText("ss:mm");

        javax.swing.GroupLayout panelTimeOfDayLayout = new javax.swing.GroupLayout(panelTimeOfDay);
        panelTimeOfDay.setLayout(panelTimeOfDayLayout);
        panelTimeOfDayLayout.setHorizontalGroup(
            panelTimeOfDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimeOfDayLayout.createSequentialGroup()
                .addGroup(panelTimeOfDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelTimeOfDayLayout.createSequentialGroup()
                        .addComponent(lbEnd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTimeOfDayLayout.createSequentialGroup()
                        .addComponent(lbStart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfStart, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbTimeOfDay, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(0, 7, Short.MAX_VALUE))
        );
        panelTimeOfDayLayout.setVerticalGroup(
            panelTimeOfDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimeOfDayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbTimeOfDay)
                .addGap(18, 18, 18)
                .addGroup(panelTimeOfDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbStart)
                    .addComponent(tfStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTimeOfDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEnd)
                    .addComponent(tfEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbEventName)
                        .addGap(101, 101, 101)
                        .addComponent(tfEventName))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(panelTimeOfDay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTimeStart, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelStartDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbTimeStart1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(btnOK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(panelEndDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEventName)
                    .addComponent(tfEventName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(95, 95, 95)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTimeStart)
                    .addComponent(lbTimeStart1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelEndDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelStartDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelTimeOfDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void initOwnComponents() {
        // Definition der Datepickers
      org.jdatepicker.impl.UtilDateModel model = new UtilDateModel();

        java.util.Properties prop = new Properties();
        
        //StartDatePicker
        startDatePanel = new JDatePanelImpl(model, prop);
        startDatePicker = new JDatePickerImpl(startDatePanel, new JFormattedTextField.AbstractFormatter() {

            @Override
            public Object stringToValue(String string) throws ParseException {
                return null;
            }

            @Override
            public String valueToString(Object o) throws ParseException {
                return null;
            }
        });
        // EndDate Picker
        endDatePanel = new JDatePanelImpl(model,prop);
        endDatePicker = new JDatePickerImpl(endDatePanel, new JFormattedTextField.AbstractFormatter() {

            @Override
            public Object stringToValue(String string) throws ParseException {
                return null;
            }

            @Override
            public String valueToString(Object o) throws ParseException {
                return null;
            }
        });
    
        
        startDatePanel.add(startDatePicker);
        endDatePanel.add(endDatePicker);
        
        System.out.println("Test1");
      
      
        startDatePanel.setLayout(new BorderLayout());
        startDatePanel.add(startDatePicker, BorderLayout.CENTER);
        endDatePanel.add(endDatePicker);        
    }
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.close();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void cbTimeOfDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTimeOfDayActionPerformed
       if (!cbTimeOfDay.isSelected()){
           tfStart.setEnabled(false);
           tfEnd.setEnabled(false);
       } else {
           tfStart.setEnabled(true);
           tfEnd.setEnabled(true);
       }
    }//GEN-LAST:event_cbTimeOfDayActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private javax.swing.JCheckBox cbTimeOfDay;
    private org.jdatepicker.util.JDatePickerUtil jDatePickerUtil1;
    private org.jdatepicker.util.JDatePickerUtil jDatePickerUtil2;
    private org.jdatepicker.util.JDatePickerUtil jDatePickerUtil3;
    private org.jdatepicker.util.JDatePickerUtil jDatePickerUtil4;
    private org.jdatepicker.util.JDatePickerUtil jDatePickerUtil5;
    private org.jdatepicker.util.JDatePickerUtil jDatePickerUtil6;
    private org.jdatepicker.util.JDatePickerUtil jDatePickerUtil7;
    private org.jdatepicker.util.JDatePickerUtil jDatePickerUtil8;
    private javax.swing.JLabel lbEnd;
    private javax.swing.JLabel lbEventName;
    private javax.swing.JLabel lbStart;
    private javax.swing.JLabel lbTimeStart;
    private javax.swing.JLabel lbTimeStart1;
    private javax.swing.JPanel panelEndDate;
    private javax.swing.JPanel panelStartDate;
    private javax.swing.JPanel panelTimeOfDay;
    private javax.swing.JFormattedTextField tfEnd;
    private javax.swing.JTextField tfEventName;
    private javax.swing.JFormattedTextField tfStart;
    // End of variables declaration//GEN-END:variables
    
    org.jdatepicker.impl.JDatePanelImpl startDatePanel;
    org.jdatepicker.impl.JDatePickerImpl startDatePicker;
    org.jdatepicker.impl.JDatePanelImpl endDatePanel;
    org.jdatepicker.impl.JDatePickerImpl endDatePicker;

    
    public boolean checkInputs() {
        if (cbTimeOfDay.isSelected()){
            if (tfStart.getText().equals("")) return false;
            if (tfEnd.getText().equals("")) return  false;
        }
        if (tfEventName.getText().equals("")) return false;
        return true;
    }
    
    public void close(){
        main.setEnabled(true);
        dispose();
    }
    
    public state getPurpose(){
        return this.state;
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
            java.util.logging.Logger.getLogger(ManipulateEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }
}
