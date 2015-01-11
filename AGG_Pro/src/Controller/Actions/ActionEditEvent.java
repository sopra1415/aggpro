/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Actions;

import Data.LiveClasses.Event;
import View.InputPanes.ManipulateEvent;
import View.MainFrame.MainFrame;
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
        Event currEvent = MainFrame.getMainFrame().getActualEvent();
        try {
            currEvent.setName(me.getEventName());
            
            int startYear = me.getStartDateYear();
            int startMonth = me.getStartDateMonth();
            int startDay = me.getStartDateDay();
            int startHour = me.getStartDateHour();
            int startMinute = me.getStartDateMinute();
            int endYear = me.getEndDateYear();
            int endMonth = me.getEndDateMonth();
            int endDay = me.getEndDateDay();
            int endHour = me.getEndDateHour();
            int endMinute = me.getEndDateMinute(); 
            
            GregorianCalendar startDate = new GregorianCalendar(startYear, startMonth, startDay, startHour, startMinute);
            GregorianCalendar endDate = new GregorianCalendar(endYear, endMonth, endDay, endHour, endMinute);

            
            currEvent.setStartDate(startDate);
            currEvent.setEndDate(endDate);
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
