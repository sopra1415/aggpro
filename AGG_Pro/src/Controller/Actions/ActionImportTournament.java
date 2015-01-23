/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Actions;

import Controller.Exchange.XML;
import View.MainFrame.MainFrame;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Heiko Geppert
 */
public class ActionImportTournament extends AbstractAction {
    FileDialog fDialog = null;
    XML xml;
    public void actionPerformed(ActionEvent ae) {
        fDialog = new FileDialog(new JFrame(), "Import", FileDialog.LOAD);
        fDialog.setFile("*.xml");
        fDialog.setVisible(true);
        File[] files = fDialog.getFiles();
        String filename = files[0].getAbsolutePath();
        System.out.println(filename);
        if (filename == null) {
            System.out.println("You cancelled the choice");
        } else {
            try {
                xml = new XML(MainFrame.getMainFrame().getActualEvent().getDatabaseConnector()
);
                xml.xmlTournaments2db(filename);
                MainFrame.getMainFrame().update();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ActionImportTournament.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ActionImportTournament.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ActionImportTournament.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(ActionImportTournament.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(ActionImportTournament.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
  
    
}
