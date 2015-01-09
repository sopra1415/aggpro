/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import View.InputPanes.Export;
import View.MainFrame.MainFrame;

/**
 *
 * @author Heiko Geppert
 */
public class ActionExportTournaments extends  AbstractAction {
	
	private Export me;

	public ActionExportTournaments(Export me) {
		this.me=me;
		MainFrame mainframe = me.getMainFrame();
		mainframe.getActualEvent();
	}
	
	
    @Override
    public void actionPerformed(ActionEvent ae) {
    	
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
