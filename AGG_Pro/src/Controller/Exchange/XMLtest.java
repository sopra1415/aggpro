package Controller.Exchange;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import Data.Database.DatabaseConnector;

import org.w3c.dom.Document;
/**
 * 
 * @author Jonathan Göggel
 *
 */

public class XMLtest {

	XML xml ;
	DatabaseConnector dc ;

	@Before
	public void before() throws ClassNotFoundException, SQLException{
		dc = new DatabaseConnector("test1");
		xml = new XML(dc);
		dc.clearDatabase();
		dc.createAllTables(); 

	}

	@Test
	public void test() throws Exception {
			dc.insert("insert into Tournament(Id,NaMe) values(1,'satz1')");
			dc.insert("insert into Tournament(Id,NaMe) values(2,'satz2');");
			//assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><AGGPRO><ENCOUNTER/><EVENTPROPERTIES/><KOSYSTEM/><MODUL/><PARTICIPANT/><PARTICIPANTENCOUNTER/><PARTICIPANTLIST/><SWISSSYSTEM/><TOURNAMENT><RECORD><ID>1</ID><NAME>satz1</NAME></RECORD><RECORD><ID>2</ID><NAME>satz2</NAME></RECORD></TOURNAMENT><TOURNAMENTSYSTEM/></AGGPRO>", xml.database2xml());
			//xml.database2xml();
			Document doc =xml.xmlWriteStrStart();
			xml.xmlWriteStrEnd(doc);
			doc =xml.xmlWriteStrStart();
			xml.appendTable2xml(doc, "Tournament");
			xml.xmlWriteStrEnd(doc);
	}
	@Test
	public void testImport() throws Exception{

		String xmlstr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><AGGPRO><ENCOUNTER/><EVENTPROPERTIES/><KOSYSTEM/><MODUL/><PARTICIPANT/><PARTICIPANTENCOUNTER/><PARTICIPANTLIST/><SWISSSYSTEM/><TOURNAMENT><RECORD><ID>1</ID><NAME>satz1</NAME></RECORD><RECORD><ID>2</ID><NAME>satz2</NAME></RECORD></TOURNAMENT><TOURNAMENTSYSTEM/></AGGPRO>";
		xml.xml2table("TOURNAMENT", xmlstr);
		assertEquals("1,satz1,\n2,satz2,\n",dc.test_selecttostr(2,"SELECT * FROM Tournament ORDER BY Id"));
	}
	
	//@Test
	public void less() throws Exception{
			dc.insert("insert into Tournament(Id,NaMe) values(1,'sa<<tz>>>1')");
			dc.insert("insert into Tournament(Id,NaMe) values(2,'satz2');");
			String original = dc.test_selecttostr(2,"SELECT * FROM Tournament ORDER BY Id");
			String xmlstr = xml.database2xml();
			dc.clearDatabase();
			dc.createAllTables();
			xml.xml2table("TOURNAMENT", xmlstr);
			assertEquals(original,dc.test_selecttostr(2,"SELECT * FROM Tournament ORDER BY Id"));
		
	}
	
	//@Test
	public void apostroph() throws Exception{
			dc.insert("insert into Tournament(Id,NaMe) values(1,'s\"atz1')");
			dc.insert("insert into Tournament(Id,NaMe) values(2,'s''''atz2');");
			String original = dc.test_selecttostr(2,"SELECT * FROM Tournament ORDER BY Id");
			String xmlstr = xml.database2xml();
			dc.clearDatabase();
			dc.createAllTables();
			xml.xml2table("TOURNAMENT", xmlstr);
			assertEquals(original,dc.test_selecttostr(2,"SELECT * FROM Tournament ORDER BY Id"));
		
	}

}
