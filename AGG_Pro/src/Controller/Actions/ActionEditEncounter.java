/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Actions;

import Data.LiveClasses.Encounter;
import Data.LiveClasses.Tournament;
import View.MainFrame.OperatingPanes.EncountersList;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
        int points0 = (int) encountersList.getPointsChooser0().getSelectedItem();
        int points1 = (int) encountersList.getPointsChooser1().getSelectedItem();
        JComboBox pointsChooser0 = (JComboBox) encountersList.getTableEncountersModel().getValueAt(encountersList.getTableEncounters().getSelectedRow(), 4);
        JComboBox pointsChooser1 = (JComboBox) encountersList.getTableEncountersModel().getValueAt(encountersList.getTableEncounters().getSelectedRow(), 10);
        Integer player0 = (Integer) encountersList.getTableEncountersModel().getValueAt(encountersList.getTableEncounters().getSelectedRow(), 0);
        Integer player1 = (Integer) encountersList.getTableEncountersModel().getValueAt(encountersList.getTableEncounters().getSelectedRow(), 6);
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
