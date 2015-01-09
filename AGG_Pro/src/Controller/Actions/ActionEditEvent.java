/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Actions;

import Data.LiveClasses.Event;
import View.InputPanes.ManipulateEvent;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;

/**
 *
 * @author Heiko Geppert
 */
public class ActionEditEvent extends AbstractAction {
    private final ManipulateEvent me;


    @Override
    public void actionPerformed(ActionEvent ae) {
        Event currEvent = me.getMainFrame().getActualEvent();
        try {
            currEvent.setName(me.getTfEventName().getText());
            currEvent.setStartDate(new GregorianCalendar(Integer.parseInt(me.gettFStartDateYear().getText()), Integer.parseInt(me.gettFStartDateMonth().getText()), Integer.parseInt(me.gettFStartDateDay().getText()), Integer.parseInt(me.getTfStartDateHour().getText()), Integer.parseInt(me.getTfStartDateMinute().getText())));
            currEvent.setEndDate(new GregorianCalendar(Integer.parseInt(me.getTfEndDateYear().getText()), Integer.parseInt(me.gettFEndDateMonth().getText()), Integer.parseInt(me.gettFEndDateDay().getText()), Integer.parseInt(me.getTfEndDateHour().getText()), Integer.parseInt(me.getTfEndDateMinute().getText())));
        } catch (SQLException ex) {
            Logger.getLogger(ActionEditEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        //closes the inputpane
        me.close();
        
        // initiate the modification of the actual Event
        
    }
    
    public ActionEditEvent(ManipulateEvent me) {
        this.me = me;
    }
}
