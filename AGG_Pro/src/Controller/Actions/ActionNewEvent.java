package Controller.Actions;

import Data.LiveClasses.Event;
import View.InputPanes.ManipulateEvent;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.ParseException;
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
            name = me.getTfEventName().getText();
            System.out.println(name);
               // Event wird erzeugt
            System.out.println("StartDateYear: " +Integer.parseInt(me.gettFStartDateYear().getText()));        
            startDate = new GregorianCalendar(Integer.parseInt(me.gettFStartDateYear().getText()), Integer.parseInt(me.gettFStartDateMonth().getText()), Integer.parseInt(me.gettFStartDateDay().getText()), Integer.parseInt(me.getTfStartDateHour().getText()), Integer.parseInt(me.getTfStartDateMinute().getText()));
            endDate = new GregorianCalendar(Integer.parseInt(me.getTfEndDateYear().getText()), Integer.parseInt(me.gettFEndDateMonth().getText()), Integer.parseInt(me.gettFEndDateDay().getText()), Integer.parseInt(me.getTfEndDateHour().getText()), Integer.parseInt(me.getTfEndDateMinute().getText()));

            Event event = new Event(name, startDate, endDate);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ActionNewEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        //closes the inputpane
        me.close();        
    }

    public ActionNewEvent(ManipulateEvent me) throws ParseException {
        this.me = me;
    }
        
        
    
    
    
}
