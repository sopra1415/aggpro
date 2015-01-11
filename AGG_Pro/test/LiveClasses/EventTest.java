package LiveClasses;



import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import Data.Database.DatabaseConnector;
import Data.LiveClasses.*;
public class EventTest {
	
	DatabaseConnector dc;
	String eventName = "Test";
	@Before
	public void before() throws ClassNotFoundException, SQLException{
		dc = new DatabaseConnector(eventName);
		dc.clearDatabase();
		dc.createAllTables();
	}

	
	
	
	
	@Test
	public void test() throws Exception {
			GregorianCalendar start = new GregorianCalendar(2012,12,12,12,12);
			GregorianCalendar end = new GregorianCalendar(2013,11,11,11,11);
			Event event = new Event(eventName,start,end);
			assertEquals(event.getName(), eventName);
			
			Participant p = new Participant(dc, "A1", "vor", "nach", "nich", "mail", true, false, "other", true, false);
			Modul m = new Modul(dc, "m1", 1, 2, 3, null);
			Tournament t = new Tournament(dc,"t1",m);
			event.addParticpant(p);
			event.addTournament(t);


			//restore event from db
			System.out.println(dc.test_selecttostr("SELECT * FROM EventProperties"));
			event = new Event(dc);
		    assertEquals(start.getTimeInMillis(), event.getStartDate().getTimeInMillis());
		    assertEquals(end.getTimeInMillis(), event.getEndDate().getTimeInMillis());
		    Participant pNew = event.getParticipant(p.getId());
		    assertEquals(p.getName(),pNew.getName());
		    
		    Tournament tNew = new Tournament(dc, t.getId());
		    assertEquals(t.getName(), tNew.getName());
			
	}

}
