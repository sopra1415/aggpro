/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.InputPanes;

import Controller.Timer.AggTimer;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Heiko Geppert
 */
public class BeamerProjection extends JFrame {

    private JPanel panelTable = new JPanel();
    private JPanel panelTime = new JPanel();
    private JLabel lbTime;
    
    public BeamerProjection() {
        this.setTitle("AGG Pro");
        initComponents();
        // TODO Location sonnvoll mittig setzen
        this.setSize(200, 100);
        this.setLocation(400, 400);
        this.setVisible(true);
    }

    private void initComponents() {
        this.setLayout(new BorderLayout(3, 3));
        // TODO Tabelle adden
        this.add(panelTable, BorderLayout.CENTER);
        
        //TODO Zeit adden
        this.add(panelTime, BorderLayout.SOUTH);
        lbTime = new JLabel("00:00:00");
        lbTime.setFont(new java.awt.Font("Arial", 0, 32));
        panelTime.add(lbTime);
        
        AggTimer timer = AggTimer.getInstance();
        timer.addObservedComponent(lbTime);
    }
    
    
}
