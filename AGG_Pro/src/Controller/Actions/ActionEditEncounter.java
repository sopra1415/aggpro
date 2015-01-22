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
    private final JComboBox combobox;

    public ActionEditEncounter(EncountersList encounters, Tournament actualTournament, JComboBox combobox) {
        this.encountersList = encounters;
        this.actualTournament = actualTournament;
        this.combobox = combobox;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        for (int i = 1; i <= encountersList.getTableEncountersModel().getRowCount(); i++) {
            int points0 = (Integer) encountersList.getTableEncountersModel().getValueAt(encountersList.getTableEncounters().getSelectedRow(), 4);
            int points1 = (Integer) encountersList.getTableEncountersModel().getValueAt(encountersList.getTableEncounters().getSelectedRow(), 10);
            String playerStart0 = (String) encountersList.getTableEncountersModel().getValueAt(encountersList.getTableEncounters().getSelectedRow(), 0);
            String playerStart1 = (String) encountersList.getTableEncountersModel().getValueAt(encountersList.getTableEncounters().getSelectedRow(), 6);

            ArrayList<Encounter> currentEncounters = actualTournament.getRounds().get(actualTournament.getRounds().size() - 1).getEncounters();
            ArrayList<Integer> points = new ArrayList<Integer>();
            points.add(points0);
            points.add(points1);

            for (Encounter currentEncounter : currentEncounters) {
                if (currentEncounter.getParticipants().get(0).getStartnumber().equals(playerStart0 + "") && currentEncounter.getParticipants().get(1).getStartnumber().equals("" + playerStart1)) {

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
