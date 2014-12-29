/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.MainFrame;

import View.InputPanes.ManipulateEvent;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Heiko Geppert
 */
public class AggToolBar extends JToolBar{
    
    private JPanel west = new JPanel();
    private JPanel center = new JPanel();
    private JPanel east= new JPanel();
    private JComboBox cbEvent = new JComboBox();
    
    private JButton btnAddEvent = new JButton("+");
    private JButton btnUser = new JButton("Benutzer");
    private JButton btnImport = new JButton("Import");
    private JButton btnExport = new JButton("Export");
    private JButton btnHelp = new JButton("Hilfe");
    private JButton btnLock = new JButton("Sperren");
    
    public AggToolBar(){
        super();
        //this.setLayout(new BorderLayout());
        //TODO Layout so verwenden, dass Buttons links/rechtsbündig sind
        //Toolbar can't be moved
        this.setFloatable(false);        
        init();
        initActions();
    }

    private void init() {     
        //this.setLayout(new BorderLayout());
        west.add(cbEvent);
        west.add(btnAddEvent);
        this.add(west, BorderLayout.WEST);
        this.addSeparator();
        
        center.add(btnUser);
        center.add(btnImport);
        center.add(btnExport);
        this.add(center, BorderLayout.CENTER);
        this.addSeparator();
        
        east.add(btnHelp);
        east.add(btnLock);
        this.add(east, BorderLayout.EAST);
    }

    /**
     * adds the Actions to the Buttons
     */
    private void initActions() {
        btnAddEvent.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("new Event Frame");
                View.InputPanes.ManipulateEvent f = new ManipulateEvent(ManipulateEvent.state.addEvent);
            }
        });
    }
}
