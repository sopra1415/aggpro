/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;

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
		mainframe.getActualEvent();
		export.getTableTournamentsModel();
		DatabaseConnector dc = null;
		XML xml = new XML(dc);
		ArrayList<Tournament> tournaments=new ArrayList<>();
		String comment="";
		String file="";
		xml.tournaments2xmlFile(tournaments,comment,file);
	}
	
	
    @Override
    public void actionPerformed(ActionEvent ae) {
    	
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
