/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Actions;

import java.awt.FileDialog;
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
import Data.LiveClasses.Tournament;
import View.InputPanes.Export;
import View.MainFrame.MainFrame;

/**
 *
 * @author Heiko Geppert
 */
public class ActionExportTournaments extends  AbstractAction {
	
	private Export export;

	public ActionExportTournaments(Export export) {
		this.export=export;
		MainFrame mainframe = export.getMainFrame();
		DefaultTableModel dtm = export.getTableTournamentsModel();
		DatabaseConnector dc = mainframe.getActualEvent().getDatabaseConnector();
		XML xml = new XML(dc);
		ArrayList<Integer> tournamentIds=new ArrayList<>();
		//TODO Heiko get tournamentIds
		String comment= export.getOther();
		String file= "";//new FileDialog(); //TODO Heiko 
		try {
			xml.tournaments2xmlFile(tournamentIds,comment,file);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
    @Override
    public void actionPerformed(ActionEvent ae) {
    	
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
