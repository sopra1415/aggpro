/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author Heiko Geppert
 */
public class MainClass {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {        
        
        //Start Mainframe, just for testing
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                View.Login.LoginFrame.getLoginFrame().setVisible(true);
                
            }
        });
        
    }
}
