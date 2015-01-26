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
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
            int startYear = me.getStartDateYear();
            int startMonth = me.getStartDateMonth()-1;
            int startDay = me.getStartDateDay();
            int startHour;
            int startMinute;
            int endYear = me.getEndDateYear();
            int endMonth = me.getEndDateMonth()-1;
            int endDay = me.getEndDateDay();
            int endHour;
            int endMinute;

            if (me.isTimeOfDaySelected()) {
                startHour = me.getStartDateHour();
                startMinute = me.getStartDateMinute();
                endHour = me.getEndDateHour();
                endMinute = me.getEndDateMinute();
            } else {
                startHour = startMinute = endHour = endMinute = 0;
            }

            if (!me.checkInputs()) {
                JOptionPane.showMessageDialog(new JFrame(), "Bitte überprüfen Sie Ihre Eingaben auf Sonderzeichen bzw. Das Startdatum muss vor dem Enddatum liegen", "Dialog",
                        JOptionPane.ERROR_MESSAGE);

            } else {
                currEvent.setName(me.getEventName());

                GregorianCalendar startDate = new GregorianCalendar(startYear, startMonth, startDay, startHour, startMinute);
                GregorianCalendar endDate = new GregorianCalendar(endYear, endMonth, endDay, endHour, endMinute);

                currEvent.setStartDate(startDate);
                currEvent.setEndDate(endDate);
                MainFrame.getMainFrame().setActualEvent(currEvent);
                
                me.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActionEditEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ActionEditEvent(ManipulateEvent me) {
        this.me = me;
    }
}
