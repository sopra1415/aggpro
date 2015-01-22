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
        int points = (Integer) combobox.getSelectedItem();
        String currentplayer = (String) encountersList.getTableEncountersModel().getValueAt(encountersList.getTableEncounters().getSelectedRow(), encountersList.getTableEncounters().getSelectedColumn() - 4);
        String playerStartNumber0 = (String) encountersList.getTableEncountersModel().getValueAt(encountersList.getTableEncounters().getSelectedRow(), 0);
        String playerStartNumber1 = (String) encountersList.getTableEncountersModel().getValueAt(encountersList.getTableEncounters().getSelectedRow(), 6);
        System.out.println("Points: "+ points);
        System.out.println("CurrentPlayer: "+ currentplayer);

        ArrayList<Encounter> currentEncounters = actualTournament.getRounds().get(actualTournament.getRounds().size() - 1).getEncounters();

        for (Encounter currentEncounter : currentEncounters) {
            if (currentEncounter.getParticipants().get(0).getStartnumber().equals(playerStartNumber0 + "") && currentEncounter.getParticipants().get(1).getStartnumber().equals("" + playerStartNumber1)) {

                try {
                    if (currentplayer.equals(playerStartNumber0)) {
                        currentEncounter.setPoints(points, 0);

                    } else {
                        currentEncounter.setPoints(points, 1);

                    }

                } catch (SQLException ex) {
                    Logger.getLogger(ActionEditEncounter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
