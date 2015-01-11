

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import Data.Database.DatabaseConnector;
import Data.LiveClasses.*;


public class ModulTest {

	@Test
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

}
