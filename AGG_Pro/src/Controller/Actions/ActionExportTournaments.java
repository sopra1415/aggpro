/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Actions;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import Controller.Exchange.XML;
import Data.Database.DatabaseConnector;
import View.InputPanes.Export;
import View.MainFrame.MainFrame;
import java.awt.FileDialog;

/**
 *
 * @author Heiko Geppert
 */
public class ActionExportTournaments extends  AbstractAction {
	
	private Export export;

	public ActionExportTournaments(Export export) {
		this.export=export;
		
	}
	
	
    @Override
    public void actionPerformed(ActionEvent ae) {
    	
        DefaultTableModel dtm = export.getTableTournamentsModel();
        DatabaseConnector dc = MainFrame.getMainFrame().getActualEvent().getDatabaseConnector();
	XML xml = new XML(dc);
        String comment= export.getOther();
	ArrayList<Integer> tournamentIds = export.getSelectedTournamentIDs();
        
        FileDialog fd = new FileDialog(export, "Exportieren", FileDialog.SAVE);
        fd.setDirectory(System.getProperty("user.home"));
        fd.setFile("*.xml");
        
	fd.setVisible(true);
	String file= fd.getDirectory()+fd.getFile();
        
        if (fd.getFile()!=null){
            try {
                xml.tournaments2xmlFile(tournamentIds,comment,file);
                export.close();
            } catch (ParserConfigurationException | SQLException | TransformerException | IOException e) {
                e.printStackTrace();
            } 
        }
        
	
    } 
}
