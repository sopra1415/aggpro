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
        Integer points0 = (Integer) encountersList.getTableEncountersModel().getValueAt(encountersList.getTableEncounters().getSelectedRow(), 4);
        //System.out.println("Points0: " + points0);
        Integer points1 = (Integer) encountersList.getTableEncountersModel().getValueAt(encountersList.getTableEncounters().getSelectedRow(), 10);
        //System.out.println("Points1: " + points1);
        
        System.out.println("Row:"+encountersList.getTableEncounters().getSelectedRow()+"Column:"+encountersList.getTableEncounters().getSelectedColumn()+"Points"+points0+"/"+points1);
        
        String player0 = (String) encountersList.getTableEncountersModel().getValueAt(encountersList.getTableEncounters().getSelectedRow(), 0);
        String player1 = (String) encountersList.getTableEncountersModel().getValueAt(encountersList.getTableEncounters().getSelectedRow(), 6);
        ArrayList<Encounter> currentEncounters = actualTournament.getRounds().get(actualTournament.getRounds().size() - 1).getEncounters();
        ArrayList<Integer> points = new ArrayList<>();
        points.add(points0);
        points.add(points1);
        for (Encounter currentEncounter : currentEncounters) {
            if (currentEncounter.getParticipants().get(0).getStartnumber().equals(player0 + "") && currentEncounter.getParticipants().get(1).getStartnumber().equals("" + player1)) {

                try {
                    currentEncounter.setPoints(points);
                } catch (SQLException ex) {
                    Logger.getLogger(ActionEditEncounter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
