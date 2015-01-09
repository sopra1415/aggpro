package Data.LiveClasses;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.GregorianCalendar;

import org.junit.Test;

import Data.Database.DatabaseConnector;

public class EventTest {

	@Test
	public void test() {
		try {
			DatabaseConnector dc = new DatabaseConnector("event1");
			dc.clearDatabase();
			dc.createAllTables();
			Event event = new Event("event1",new GregorianCalendar(2012,12,12,12,12),new GregorianCalendar(2012,12,12,12,12));
			assertEquals(event.getName(), "event1");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
