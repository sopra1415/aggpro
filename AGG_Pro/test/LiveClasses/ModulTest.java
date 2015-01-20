package LiveClasses;



import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import Data.Database.DatabaseConnector;
import Data.LiveClasses.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;


public class ModulTest {

	//@Test
	public void test() throws ClassNotFoundException, SQLException {
		DatabaseConnector dc = new DatabaseConnector("modultest");
		dc.clearDatabase();
		dc.createAllTables();
		Modul modul = new Modul(dc, "m1", 1, 2, 3, null);
		
		//restore from database
		modul = new Modul(dc, modul.getId());
		assertEquals(modul.getName(), "m1");
		assertEquals(modul.getPointsWin(), 1);
		assertEquals(modul.getPointsLoose(), 2);
		assertEquals(modul.getPointsDraw(), 3);
	}
        
        
        
        //@Test
        public void test2() throws ClassNotFoundException, SQLException, ParseException{
            DatabaseConnector dc = new DatabaseConnector("Test");
            GregorianCalendar ngc = new GregorianCalendar();
            ngc.setTimeInMillis(0);
            Event e = new Event("Test",ngc,ngc);
            ArrayList<TournamentSystem> alts = new ArrayList<>();
            alts.add(new SwissSystem("swiss123", 1, 2));
            Modul m = new Modul(dc, "name1", 1, 2, 3, alts);
            
        Event eNeu = new Event(dc);
         }
        
        @Test
        public void test3() throws ClassNotFoundException, SQLException, ParseException{
            DatabaseConnector dc = new DatabaseConnector("neues Event");
            System.out.println(dc.test_databaseToStr());
            Event e =new Event(dc);
        }

}
