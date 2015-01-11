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
public class ActionNewEvent extends AbstractAction{
    private final ManipulateEvent me;
    private String name;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            name = me.getEventName();
            System.out.println("Neues Event: "+name);
            int startYear = me.getStartDateYear();
            int startMonth = me.getStartDateMonth();
            int startDay = me.getStartDateDay();
            int endYear = me.getEndDateYear();
            int endMonth = me.getEndDateMonth();
            int endDay = me.getEndDateDay();
            int startHour = me.getStartDateHour();
            int startMinute = me.getStartDateMinute();
            int endHour = me.getEndDateHour();
            int endMinute = me.getEndDateMinute();
               // Event wird erzeugt     
            startDate = new GregorianCalendar(startYear, startMonth, startDay, startHour, startMinute);
            endDate = new GregorianCalendar(endYear, endMonth, endDay, endHour, endMinute);

            Event event = new Event(name, startDate, endDate);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ActionNewEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        //closes the inputpane
        me.close();        
    }

    public ActionNewEvent(ManipulateEvent me) {
        this.me = me;
    }    
}
