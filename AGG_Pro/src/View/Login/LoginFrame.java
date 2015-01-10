/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Login;

import Data.Database.DatabaseConnector;
import Data.Database.EventLoader;
import Data.LiveClasses.Event;
import View.MainFrame.AggToolBar;
import View.MainFrame.MainFrame;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Heiko Geppert
 */
public class LoginFrame extends javax.swing.JFrame {
    
    private static LoginFrame loginFrame;
    
    public static LoginFrame getLoginFrame(){
        if (loginFrame == null){
            loginFrame = new LoginFrame();
        }
        return loginFrame;
    }
    
    /**
     * Creates new form LoginFrame
     */
    private LoginFrame() {
        initComponents();
        Icon aggLogo;
        //load the AGG Logo to the Frame
        try {
            aggLogo = new ImageIcon(getClass().getResource("/res/AGG_logo.png"));
            panelPicture.setLayout(new BorderLayout());
            JLabel lbPicture = new JLabel(aggLogo);
            panelPicture.add(lbPicture, BorderLayout.CENTER);
            
        } catch (Exception ex) {
            System.err.println("Error while loading the AGG_logo");
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);            
        }
        

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        // Frame appears in the middle of the screen
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth()) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight()) / 2);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPicture = new javax.swing.JPanel();
        panelUI = new javax.swing.JPanel();
        tfUser = new javax.swing.JTextField();
        lbUser = new javax.swing.JLabel();
        lbPassword = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        tfPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AGG Pro - Login");
        setMinimumSize(new java.awt.Dimension(350, 200));
        setResizable(false);

        panelPicture.setMinimumSize(new java.awt.Dimension(350, 200));
        panelPicture.setPreferredSize(new java.awt.Dimension(350, 200));

        javax.swing.GroupLayout panelPictureLayout = new javax.swing.GroupLayout(panelPicture);
        panelPicture.setLayout(panelPictureLayout);
        panelPictureLayout.setHorizontalGroup(
            panelPictureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        panelPictureLayout.setVerticalGroup(
            panelPictureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        tfUser.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tfUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfUserActionPerformed(evt);
            }
        });

        lbUser.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbUser.setText("Benutzer:");

        lbPassword.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbPassword.setText("Passwort:");

        btnLogin.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnLogin.setText("Anmelden");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        tfPassword.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        javax.swing.GroupLayout panelUILayout = new javax.swing.GroupLayout(panelUI);
        panelUI.setLayout(panelUILayout);
        panelUILayout.setHorizontalGroup(
            panelUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUILayout.createSequentialGroup()
                .addGroup(panelUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelUILayout.createSequentialGroup()
                            .addComponent(lbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tfUser, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelUILayout.createSequentialGroup()
                            .addComponent(lbPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tfPassword))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelUILayout.setVerticalGroup(
            panelUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUILayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbUser)
                    .addComponent(tfUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPassword)
                    .addComponent(tfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogin)
                .addContainerGap())
        );

        tfUser.getAccessibleContext().setAccessibleName("tbUser");
        lbUser.getAccessibleContext().setAccessibleName("lbUser");
        lbPassword.getAccessibleContext().setAccessibleName("lbPassword");
        btnLogin.getAccessibleContext().setAccessibleName("btnLogin");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(237, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelPicture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelUI, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(213, 213, 213))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(panelPicture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(125, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        
        if (checkLogin()){
            this.setVisible(false);
            initDbConnection(tfUser.getText());
            MainFrame.getMainFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Kombination von Benutzer (Event) und Passwort falsch");
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void tfUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfUserActionPerformed

    private boolean checkLogin(){        
        if (tfPassword.getPassword().equals("qwertzui")){
            return true;
        }
        //TODO set false and change passwort
        return true;
    }
    
    private void initDbConnection(String preferedDatabase){
        EventLoader ev = new EventLoader();
        // try to load selected DB (by User Field)
        if(ev.getEvents().contains(preferedDatabase)) {
            try {                
                MainFrame.getMainFrame().setActualEvent(new Event(new DatabaseConnector(preferedDatabase)));
            } catch (ClassNotFoundException | SQLException | ParseException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else{
            // create new Event, if there are no Events yet
            if (ev.getEvents().isEmpty()){
                try {
                    MainFrame.getMainFrame().setActualEvent(new Event("neues Event", new GregorianCalendar(2015, 1,1), new GregorianCalendar(2015,1,1)));
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(AggToolBar.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // load any DB
                try {
                    MainFrame.getMainFrame().setActualEvent(new Event(new DatabaseConnector(ev.getEvents().get(0))));
                } catch (ClassNotFoundException | SQLException | ParseException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JLabel lbUser;
    private javax.swing.JPanel panelPicture;
    private javax.swing.JPanel panelUI;
    private javax.swing.JPasswordField tfPassword;
    private javax.swing.JTextField tfUser;
    // End of variables declaration//GEN-END:variables
}
