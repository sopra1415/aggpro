package Data.LiveClasses;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.GregorianCalendar;

import org.junit.Test;

import Data.Database.DatabaseConnector;

public class EventTest {

	//TODO add participant & add tournament
	@Test
	public void test() throws ParseException {
		try {
			DatabaseConnector dc = new DatabaseConnector("event1tst");
			dc.clearDatabase();
			dc.createAllTables();
			GregorianCalendar start = new GregorianCalendar(2012,12,12,12,12);
			GregorianCalendar end = new GregorianCalendar(2013,11,11,11,11);
			Event event = new Event("event1tst",start,end);
			assertEquals(event.getName(), "event1tst");


			//restore event from db
			event = new Event(dc);
		    assertEquals(start.getTimeInMillis(), event.getStartDate().getTimeInMillis());
		    assertEquals(end.getTimeInMillis(), event.getEndDate().getTimeInMillis());
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
