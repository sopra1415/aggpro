/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Actions;

import Data.LiveClasses.Participant;
import View.InputPanes.ManipulateParticipant;
import View.MainFrame.MainFrame;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;

/**
 *
 * @author Heiko Geppert
 */
public class ActionEditParticipant extends AbstractAction {

    private Participant chosenParticipant;
    private ManipulateParticipant mp;
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        mp.close();
        try {
            chosenParticipant.setName(mp.getParticipantName());
            chosenParticipant.setNickname(mp.getParticipantNickname());
            chosenParticipant.setOther(mp.getParticipantOther());
            chosenParticipant.setPaid(mp.getParticipantPaid());
            chosenParticipant.setPrename(mp.getParticipantPreName());
            chosenParticipant.setPresent(mp.getParticipantPresent());
            chosenParticipant.setSuperfreepass(mp.getParticipantSuperfreepass());
            chosenParticipant.setEmail(mp.getParticipantEmail());
            MainFrame.getMainFrame().update();
        } catch (SQLException ex) {
            Logger.getLogger(ActionEditParticipant.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ActionEditParticipant(ManipulateParticipant mp, Participant p) {
        this.mp = mp;
        this.chosenParticipant = p;
    }
    
    
    
}
