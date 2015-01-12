/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.MainFrame;

import Controller.Actions.ActionImportTournament;
import Controller.Actions.ActionListenerLoadEvent;
import Data.Database.EventLoader;
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

/**
 *
 * @author Heiko Geppert
 */
public class AggToolBar extends JToolBar{
    
    private  MainFrame main;
    
    private JPanel west;
    private JPanel center;
    private JPanel east;
    private JComboBox cbEvent;
    
    private JButton btnAddEvent;
    private JButton btnEditEvent;
    private JButton btnImport;
    private JButton btnExport;
    private JButton btnHelp;
    private JButton btnLock;
    
    public AggToolBar(MainFrame main){
        this.main = main;
        //Toolbar can't be moved
        this.setFloatable(false);     
        initJComponents();
        init();
        initActions();
        //später durch die Tatsächlich existierenen Events ersetzen
    }
    
    private void initJComponents(){
        this.btnLock = new JButton("Sperren");
        this.btnHelp = new JButton("Hilfe");
        this.btnExport = new JButton("Export");
        this.btnImport = new JButton("Import");
        this.btnEditEvent = new JButton("Event bearbeiten");
        this.btnAddEvent = new JButton("+");
        this.cbEvent = new JComboBox();
        this.east = new JPanel();
        this.center = new JPanel();
        this.west = new JPanel();
    }

    private void init() {     
        //this.setLayout(new BorderLayout());
        west.add(cbEvent);
        west.add(btnAddEvent);
        this.add(west, BorderLayout.WEST);
        this.addSeparator();
        
        center.add(btnEditEvent);
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
                main.setEnabled(false);
                View.InputPanes.ManipulateEvent f = new ManipulateEvent(main, ManipulateEvent.state.addEvent);
                f.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e){
                        main.setEnabled(true);
                    }
                });
            }
        });
        
        btnEditEvent.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                main.setEnabled(false);
                View.InputPanes.ManipulateEvent f = new ManipulateEvent(main, ManipulateEvent.state.modifyEvent);
                f.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e){
                        main.setEnabled(true);
                    }
                });
            }
        });
        
        btnExport.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                main.setEnabled(false);
                View.InputPanes.Export f = new Export();
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
        LoginFrame.getLoginFrame().setLoginVisible();
    }
    
    public String getSelectedEvent(){
        return cbEvent.getSelectedItem().toString();
    }
    
    public void update(){
        
        cbEvent.removeAllItems();
        EventLoader ev = new EventLoader();
        
        for (String s:ev.getEvents()){
            cbEvent.addItem(s);
            if (s.equals(main.getActualEvent().getName())){
                cbEvent.setSelectedItem(s);
            }
        }
    }
}
