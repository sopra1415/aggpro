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
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Heiko Geppert
 */
public class ActionEditParticipant extends AbstractAction {

    private Participant chosenParticipant;
    private ManipulateParticipant mp;

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!mp.checkInputs()) {
            JOptionPane.showMessageDialog(new JFrame(), "Bitte überprüfen Sie Ihre Eingaben auf Sonderzeichen und füllen Sie bitte alle Felder aus", "Dialog",
                    JOptionPane.ERROR_MESSAGE);
        } else {
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
                chosenParticipant.setTournaments(mp.getSelectedTournaments());
                MainFrame.getMainFrame().update();
            } catch (SQLException ex) {
                Logger.getLogger(ActionEditParticipant.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public ActionEditParticipant(ManipulateParticipant mp, Participant p) {
        this.mp = mp;
        this.chosenParticipant = p;
    }

}
