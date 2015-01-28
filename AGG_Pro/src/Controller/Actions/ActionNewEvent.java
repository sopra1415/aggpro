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
public class ActionNewEvent extends AbstractAction {

    private final ManipulateEvent me;
    private String name;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!me.checkInputs()) {
            JOptionPane.showMessageDialog(new JFrame(), "Bitte überprüfen Sie Ihre Eingaben auf Sonderzeichen und Vollständigkeit. "
                    + "\n Außerdem muss das Startdatum vor dem Enddatum liegen.", "Dialog",JOptionPane.ERROR_MESSAGE);
        } else {
            try {

                name = me.getEventName();

                int startYear = me.getStartDateYear();
                int startMonth = me.getStartDateMonth()-1;
                int startDay = me.getStartDateDay();
                int endYear = me.getEndDateYear();
                int endMonth = me.getEndDateMonth()-1;
                int endDay = me.getEndDateDay();
                int startHour;
                int startMinute;
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

                // Event wird erzeugt     
                startDate = new GregorianCalendar(startYear, startMonth, startDay, startHour, startMinute);
                endDate = new GregorianCalendar(endYear, endMonth, endDay, endHour, endMinute);

                Event event = new Event(name, startDate, endDate);
                MainFrame.getMainFrame().setActualEvent(event);

                // mit dem Event was sinnvolles tun, zum aktuellen Event machen oder so
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ActionNewEvent.class.getName()).log(Level.SEVERE, null, ex);
            }
            //closes the inputpane

            MainFrame.getMainFrame().getAggToolBar().update();
            me.close();
        }

    }

    public ActionNewEvent(ManipulateEvent me) {
        this.me = me;
    }
}
