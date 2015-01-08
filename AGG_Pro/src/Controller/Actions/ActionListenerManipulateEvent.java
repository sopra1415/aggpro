/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Actions;

import View.InputPanes.ManipulateEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author Heiko Geppert
 */
public class ActionListenerManipulateEvent implements  ActionListener {

    private ManipulateEvent me;
    
    public ActionListenerManipulateEvent(ManipulateEvent me){
        this.me = me;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        //chech the inputs 
        if (me.checkInputs()){
            //TODO save to objects
            if (me.getPurpose()==ManipulateEvent.state.addEvent){
                //create new Event
            } else {
                //modify event
            }
            //dispose
            me.close();
        }
    }
    
}
