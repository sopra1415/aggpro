package Data.LiveClasses;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import Data.Database.DatabaseConnector;

public class TournamentTest {

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
		Tournament tournament = new Tournament(dc, "t1", m);
		
		
		//restore tournament from database
		tournament = new Tournament(dc, tournament.getId());
		assertEquals(tournament.getName(), "t1");
		assertEquals(tournament.getModul().getName(), m.getName());
	}

}
