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
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        System.out.println("start");
        
        /* Create and display the form *//* taken out, cuz not needed for testing atm
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new View.Login.LoginFrame().setVisible(true);
            }
        });
        */
        
        //Start Mainframe, just for testing
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View.MainFrame.MainFrame().setVisible(true);
            }
        });
        
        //Start some OptionPanes
        /* Create and display the form *//*
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View.InputPanes.manipulateParticipant().setVisible(true);
            }
        });*/
    }
}
