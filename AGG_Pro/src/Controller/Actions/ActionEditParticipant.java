/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Actions;

import View.InputPanes.ManipulateParticipant;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Heiko Geppert
 */
public class ActionEditParticipant extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent ae) {

    }

    public ActionEditParticipant(ManipulateParticipant mp) {
        mp.close();
        
        //TODO do more
    }
    
    
    
}
