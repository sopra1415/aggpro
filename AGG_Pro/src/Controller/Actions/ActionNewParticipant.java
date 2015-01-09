/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Actions;

import Data.Database.DatabaseConnector;
import Data.LiveClasses.Participant;
import View.InputPanes.ManipulateParticipant;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;

/**
 *
 * @author Heiko Geppert
 */
public class ActionNewParticipant extends AbstractAction {
    private final ManipulateParticipant mp;
    private DatabaseConnector dc;

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            Participant participant = new Participant(dc, mp.getTfName().getText(), mp.getTfPreName().getText(), mp.getTfNickname().getText(), mp.getCbPayed().isSelected(), mp.getCbPresent().isSelected(), mp.getTfOther().getText(), false, mp.getcBSuperfreepass().isSelected());
        } catch (SQLException ex) {
            Logger.getLogger(ActionNewParticipant.class.getName()).log(Level.SEVERE, null, ex);
        }
         mp.close();
         

        
    }

    public ActionNewParticipant(ManipulateParticipant mp) throws ClassNotFoundException, SQLException {
        this.mp = mp;
        this.dc = new DatabaseConnector(mp.getMainFrame().getActualEvent().getName());
        //TODO do more
    }
    
    
    
}
