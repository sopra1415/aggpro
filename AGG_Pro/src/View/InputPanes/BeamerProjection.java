/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.InputPanes;

import Controller.Timer.AggTimer;
import Data.LiveClasses.Encounter;
import Data.LiveClasses.Round;
import Data.LiveClasses.Tournament;
import View.MainFrame.MainFrame;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Heiko Geppert
 */
public class BeamerProjection extends JFrame {

    private MainFrame main;
    private Tournament actualTournament;
    private JScrollPane panelTable;
    private JPanel panelTime = new JPanel();
    private JLabel lbTime;
    private JTable tableEncounters;
    private DefaultTableModel tableEncountersModel;
    
    public BeamerProjection(MainFrame main, Tournament tournament) {
        this.main = main;
        this.actualTournament = tournament;
        this.setTitle("AGG Pro");
        this.setSize(800, 480);
        initComponents();
        initTableContent();
        this.setLocation(300, 200);
        this.setVisible(true);
    }

    private void initComponents() {
        this.setLayout(new BorderLayout(3, 3));
        
        tableEncountersModel = new DefaultTableModel(new Object[0][0],new String[]{"StartNummer", "Name", "Vorname", "Nickname", "Tisch","StartNummer", "Name", "Vorname", "Nickname"});
        tableEncounters = new JTable(tableEncountersModel);
        tableEncounters.setFillsViewportHeight(true);
        tableEncounters.setRowHeight(40);
        tableEncounters.setFont(new java.awt.Font("Arial", 0, 18));
        
        panelTable = new JScrollPane(tableEncounters);
        this.add(panelTable, BorderLayout.CENTER);
        
        this.add(panelTime, BorderLayout.SOUTH);
        lbTime = new JLabel("00:00:00");
        lbTime.setFont(new java.awt.Font("Arial", 0, 32));
        panelTime.add(lbTime);
        
        AggTimer timer = AggTimer.getInstance();
        timer.addObservedComponent(lbTime);
    }
    
    private void initTableContent(){
        tableEncounters.removeAll();
        try {
            int roundNumber = actualTournament.getRounds().size();
            ArrayList<Round> allRounds = actualTournament.getRounds();
            
            if (!allRounds.isEmpty()){
                Round actualRound = allRounds.get(roundNumber -1);
                Vector rowData = new Vector();
                int tableCounter = 0;
                for (Encounter e:actualRound.getEncounters()){

                    tableCounter++;
                    rowData = e.getParticipants().get(0).getData();            
                    rowData.add(tableCounter);     
                    for (Object o:e.getParticipants().get(1).getData()){
                        rowData.add(o);
                    }
                    tableEncountersModel.addRow(rowData);
                }                
            }  
        } catch (NullPointerException e){}
    }    
}
