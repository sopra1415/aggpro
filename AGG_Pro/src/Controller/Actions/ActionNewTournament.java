/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Actions;

import Data.LiveClasses.Modul;
import Data.LiveClasses.Tournament;
import View.InputPanes.ManipulateTournament;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;

/**
 *
 * @author Heiko Geppert
 */
public class ActionNewTournament extends AbstractAction {

    private ManipulateTournament mt;
            
    public ActionNewTournament(ManipulateTournament mt) {
        this.mt = mt;
    }
            
    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            String name = mt.getTournamentName();
            int[] points = mt.getPoints();
            Modul modul = new Modul(mt.getMainFrame().getActualEvent().getDatabaseConnector(), name, points[0], points[1], points[2], null); 
            //TODO Turniersysteme in DB speichern
            Tournament tournament = new Tournament(mt.getMainFrame().getActualEvent().getDatabaseConnector() , name, modul);
        } catch (SQLException ex) {
            Logger.getLogger(ActionNewEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        //closes the inputpane
        mt.close(); 
    }
}
