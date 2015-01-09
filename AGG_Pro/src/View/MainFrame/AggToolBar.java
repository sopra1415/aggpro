/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.MainFrame;

import Controller.Actions.ActionImportTournament;
import Controller.Actions.ActionListenerLoadEvent;
import View.InputPanes.Export;
import View.InputPanes.ManipulateEvent;
import View.Login.LoginFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import View.MainFrame.Help.HelpFrame;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Heiko Geppert
 */
public class AggToolBar extends JToolBar{
    
    private MainFrame main;
    
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
    
    public AggToolBar(MainFrame main){
        
        this.main = main;
        //this.setLayout(new BorderLayout());
        //TODO Layout so verwenden, dass Buttons links/rechtsbündig sind
        //Toolbar can't be moved
        this.setFloatable(false);        
        init();
        initActions();
        //später durch die Tatsächlich existierenen Events ersetzen
        cbEvent.addItem("Event Name");
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
                try {
                    main.setEnabled(false);
                    View.InputPanes.ManipulateEvent f = new ManipulateEvent(main, ManipulateEvent.state.addEvent);
                    f.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e){
                            main.setEnabled(true);
                        }
                    });
                } catch (ParseException ex) {
                    Logger.getLogger(AggToolBar.class.getName()).log(Level.SEVERE, null, ex);
                    main.setEnabled(true);
                }
            }
        });
        
        btnUser.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    main.setEnabled(false);
                    View.InputPanes.ManipulateEvent f = new ManipulateEvent(main, ManipulateEvent.state.modifyEvent);
                    f.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e){
                            main.setEnabled(true);
                        }
                    });
                } catch (ParseException ex) {
                    Logger.getLogger(AggToolBar.class.getName()).log(Level.SEVERE, null, ex);
                    main.setEnabled(true);
                }
            }
        });
        
        btnExport.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                main.setEnabled(false);
                View.InputPanes.Export f = new Export(main);
                f.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e){
                        main.setEnabled(true);
                    }
                });
            }
        });
        
        btnHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                main.setEnabled(false);
                View.MainFrame.Help.HelpFrame f = new HelpFrame(main);
                f.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e){
                        main.setEnabled(true);
                    }
                });
            }
        });
        
        btnLock.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                lock();
            }
        });
        
        btnImport.setAction(new ActionImportTournament());
        btnImport.setText("Import");
        
        cbEvent.addActionListener(new ActionListenerLoadEvent(main));
    }
    
    /**
     * locks the mainframe
     */
    protected void lock(){
        main.setVisible(false);
        View.Login.LoginFrame f = new LoginFrame(main);
        f.setVisible(true);
    }
    
    public String getSelectedEvent(){
        return cbEvent.getSelectedItem().toString();
    }
}
