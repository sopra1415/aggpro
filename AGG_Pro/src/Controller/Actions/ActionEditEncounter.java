/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Actions;

import Data.LiveClasses.Encounter;
import Data.LiveClasses.Tournament;
import View.MainFrame.OperatingPanes.EncountersList;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JComboBox;

/**
 *
 * @author tobias
 */
public class ActionEditEncounter extends AbstractAction {

    private final EncountersList encountersList;
    private final Tournament actualTournament;


    public ActionEditEncounter(EncountersList encounters, Tournament actualTournament) {
        this.encountersList = encounters;
        this.actualTournament = actualTournament;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("Action");

        
        
        for (int i = 0; i < encountersList.getTableEncountersModel().getRowCount(); i++) {
            System.out.println("i: "+i);
            int points0 = (Integer) encountersList.getTableEncountersModel().getValueAt(i, 4);
            int points1 = (Integer) encountersList.getTableEncountersModel().getValueAt(i, 10);
            String playerStart0 = (String) encountersList.getTableEncountersModel().getValueAt(i, 0);
            String playerStart1 = (String) encountersList.getTableEncountersModel().getValueAt(i, 6);

            ArrayList<Encounter> currentEncounters = actualTournament.getRounds().get(actualTournament.getRounds().size() - 1).getEncounters();
            ArrayList<Integer> points = new ArrayList<Integer>();
            points.add(points0);
            points.add(points1);

            for (Encounter currentEncounter : currentEncounters) {
                if (currentEncounter.getParticipants().get(0).getStartnumber().equals(playerStart0) && currentEncounter.getParticipants().get(1).getStartnumber().equals(playerStart1)) {

                    try {
                        currentEncounter.setPoints(points);
                    } catch (SQLException ex) {
                        Logger.getLogger(ActionEditEncounter.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }
        }
    }

}
