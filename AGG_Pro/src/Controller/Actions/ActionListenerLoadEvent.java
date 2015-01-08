/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Actions;

import View.MainFrame.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Heiko Geppert
 */
public class ActionListenerLoadEvent implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent ae) {
        //initiate the load of an new Event from the database...
    }    
    
    private MainFrame  main;
    public ActionListenerLoadEvent(MainFrame main){
        this.main = main;
    }
}
