/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Actions;

import Data.LiveClasses.Modul;
import Data.LiveClasses.Tournament;
import View.InputPanes.ManipulateTournament;
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
            Modul modul = new Modul(MainFrame.getMainFrame().getActualEvent().getDatabaseConnector(), name, points[0], points[1], points[2], null);
            modul.setTournamentSystems(mt.getTournamentSystems());
            //TODO Turniersysteme in DB speichern, irgendiwe mit ArrayLists @Tobi fragen
            Tournament tournament = new Tournament(MainFrame.getMainFrame().getActualEvent().getDatabaseConnector() , name, modul);
            MainFrame.getMainFrame().getActualEvent().addTournament(tournament);
        } catch (SQLException ex) {
            Logger.getLogger(ActionNewEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        mt.close(); 
    }
}
