/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Actions;

import View.InputPanes.ManipulateEvent;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Heiko Geppert
 */
public class ActionNewEvent extends AbstractAction{
    private final ManipulateEvent me;

    @Override
    public void actionPerformed(ActionEvent ae) {
        //closes the inputpane
        me.close();
        
        // initiate the creation of an new Event
        
    }

    public ActionNewEvent(ManipulateEvent me) {
        this.me = me;
    }
    
    
}
