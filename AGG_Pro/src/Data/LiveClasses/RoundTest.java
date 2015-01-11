package Data.LiveClasses;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Data.Database.DatabaseConnector;

public class RoundTest {

	DatabaseConnector dc;
	@Before
	public void before() throws ClassNotFoundException, SQLException{
	dc = new DatabaseConnector("Test");
	dc.clearDatabase();
	dc.createAllTables();
	}
	@Test
	public void test() throws SQLException {
		Modul m = new Modul(dc,"m1",1,2,3, null);
		Tournament t = new Tournament(dc, "t1", m);
		Round round = new Round(dc,t,1);
		
		//restore from database
		ArrayList<Participant> participants = null;
		round = new Round(dc, round.getId(), t, participants);
		assertEquals(round.getRound(), 1);
	}

}
