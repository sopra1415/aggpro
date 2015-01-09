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
            Participant participant = new Participant(dc, startnumber, mp.getTfName().getText(), mp.getTfPreName().getText(), mp.getTfNickname().getText(), mp.getTfEmail().getText(), mp.getCbPayed().isSelected(), mp.getCbPresent().isSelected(), mp.getTfOther().getText(), false, mp.getcBSuperfreepass().isSelected());
        } catch (SQLException ex) {
            Logger.getLogger(ActionNewParticipant.class.getName()).log(Level.SEVERE, null, ex);
        }
        mp.close();
    }

    public ActionNewParticipant(ManipulateParticipant mp) throws ClassNotFoundException, SQLException {
        this.mp = mp;
        this.dc = mp.getMainFrame().getActualEvent().getDatabaseConnector();
    }
    
    private String generateStartNumber(){
        int i = 1;
        String generatedNumber;
        ArrayList<Participant> allParticipants = mp.getMainFrame().getActualEvent().getParticipants();
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
