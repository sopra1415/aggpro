/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Actions;

import Data.Database.DatabaseConnector;
import Data.LiveClasses.Participant;
import Data.LiveClasses.Tournament;
import View.InputPanes.ManipulateParticipant;
import View.MainFrame.MainFrame;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
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
            String startnumber = generateStartNumber();
            
            String name = mp.getParticipantName();
            String nickname = mp.getParticipantNickname();
            String other = mp.getParticipantOther();
            boolean paid = mp.getParticipantPaid();
            String prename = mp.getParticipantPreName();
            boolean present = mp.getParticipantPresent();
            boolean superfreepass = mp.getParticipantSuperfreepass();
            String email = mp.getParticipantEmail();
            
            Participant participant = new Participant(dc, startnumber, name, prename, nickname, email, paid, present, other, false, superfreepass);
            
            participant.setTournaments(mp.getSelectedTournaments());
            
            MainFrame.getMainFrame().getActualEvent().addParticpant(participant);
        } catch (SQLException ex) {
            Logger.getLogger(ActionNewParticipant.class.getName()).log(Level.SEVERE, null, ex);
        }
        mp.close();
        MainFrame.getMainFrame().update();
    }

    public ActionNewParticipant(ManipulateParticipant mp) {
        this.mp = mp;
        this.dc = MainFrame.getMainFrame().getActualEvent().getDatabaseConnector();
    }
    
    private String generateStartNumber(){
        int i = 1;
        String generatedNumber;
        ArrayList<Participant> allParticipants = MainFrame.getMainFrame().getActualEvent().getParticipants();
        while(true){
            generatedNumber = "a"+intTo3LetterString(i);
            boolean used = false;
            for (Participant p:allParticipants){
                if (p.getStartnumber().equals(generatedNumber)){
                    used = true;
                    break;
                }
            }
            if (!used){
                return generatedNumber;
            }            
            i++;
        }
    }
    
    private String intTo3LetterString(int input){
        String output ="";
        int temp = input;
        for (int i=0; i<3; i++){
            output += temp%10;
            temp/=10;
        }
        return new StringBuffer(output).reverse().toString();
        
    }
    
    
    
}
