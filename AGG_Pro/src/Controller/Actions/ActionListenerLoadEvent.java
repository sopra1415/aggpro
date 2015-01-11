/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Actions;

import Data.Database.DatabaseConnector;
import Data.LiveClasses.Event;
import View.Login.LoginFrame;
import View.MainFrame.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Heiko Geppert
 */
public class ActionListenerLoadEvent implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent ae) {
        //initiate the load of an new Event from the database...
        try {
            String futureEvent;
            if (!main.getInit()){
                futureEvent = main.getAggToolBar().getSelectedEvent();
            } else {
                futureEvent = LoginFrame.getLoginFrame().getSelectedEvent();
            }
            
            // loads the choosen Event
            main.setActualEvent(new Event(new DatabaseConnector(futureEvent)));
            
            
        } catch (NullPointerException ne){
        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            Logger.getLogger(ActionListenerLoadEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    private MainFrame  main;
    public ActionListenerLoadEvent(MainFrame main){
        this.main = main;
    }
}
