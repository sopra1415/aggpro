package Controller.Exchange;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.Before;
import org.junit.Test;

import Data.Database.DatabaseConnector;
import Data.LiveClasses.Event;
import Data.LiveClasses.Modul;
import Data.LiveClasses.Participant;
import Data.LiveClasses.Tournament;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import sun.org.mozilla.javascript.ast.NewExpression;
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
		dc = new DatabaseConnector("Test");
		xml = new XML(dc);
		dc.clearDatabase();
		dc.createAllTables(); 

	}

	//@Test
	public void test() throws Exception {
			dc.insert("insert into Tournament(Id,NaMe) values(1,'satz1')");
			dc.insert("insert into Tournament(Id,NaMe) values(2,'satz2');");
			assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><AGGPRO><ENCOUNTER/><EVENTPROPERTIES/><KOSYSTEM/><MODUL/><MODULLIST/><PARTICIPANT/><PARTICIPANTLIST/><POINTS/><ROUND/><SWISSSYSTEM/><TOURNAMENT><RECORD><ID>1</ID><NAME>satz1</NAME><MODULID/></RECORD><RECORD><ID>2</ID><NAME>satz2</NAME><MODULID/></RECORD></TOURNAMENT></AGGPRO>",xml.database2xml());
	}
	//@Test
	public void testImport() throws Exception{

		String xmlstr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><AGGPRO><ENCOUNTER/><EVENTPROPERTIES/><KOSYSTEM/><MODUL/><PARTICIPANT/><PARTICIPANTENCOUNTER/><PARTICIPANTLIST/><SWISSSYSTEM/><TOURNAMENT><RECORD><ID>1</ID><NAME>satz1</NAME></RECORD><RECORD><ID>2</ID><NAME>satz2</NAME></RECORD></TOURNAMENT><TOURNAMENTSYSTEM/></AGGPRO>";
		xml.xml2table("TOURNAMENT", xmlstr);
		assertEquals("1,satz1,\n2,satz2,\n",dc.test_selecttostr("SELECT * FROM Tournament ORDER BY Id"));
	}
	
	//@Test
	public void less() throws Exception{
			//dc.insert("insert into Tournament(Id,NaMe) values(1,'sa<<tz>>>1')");
			//dc.insert("insert into Tournament(Id,NaMe) values(2,'satz2');");
			dc.insert("insert into Tournament(Id,NaMe) values(2,'satz2');");
			String original = dc.test_selecttostr("SELECT * FROM Tournament ORDER BY Id");
			String xmlstr = xml.database2xml();
			dc.clearDatabase();
			dc.createAllTables();
			xml.xml2table("TOURNAMENT", xmlstr);
			assertEquals(original,dc.test_selecttostr("SELECT * FROM Tournament ORDER BY Id"));
		
	}
	
	//@Test
	public void apostroph() throws Exception{
			dc.insert("insert into Tournament(Id,NaMe) values(1,'s\"atz1')");
			dc.insert("insert into Tournament(Id,NaMe) values(2,'s''''atz2');");
			String original = dc.test_selecttostr("SELECT * FROM Tournament ORDER BY Id");
		String xmlstr = xml.database2xml();
			dc.clearDatabase();
			dc.createAllTables();
			xml.xml2table("TOURNAMENT", xmlstr);
			assertEquals(original,dc.test_selecttostr("SELECT * FROM Tournament ORDER BY Id"));
		
	}
	
	@Test
	public void im_ex_portTournaments() throws ParserConfigurationException, SQLException, TransformerException, IOException, ClassNotFoundException, SAXException, ParseException{
		//create new event and new turnament
		Event event = new Event("event1", new GregorianCalendar(1,2,3,4,5), new GregorianCalendar(1,2,3,4,5));
		
		Participant p1 = new Participant(dc, "A1", "anton", "anhalt", "anan", "an@an", true, true, "nix", false, true);
		Participant p2 = new Participant(dc, "B1", "berta", "brecht", "bebr", "be@br", true, false, "nix", false, false);
		Participant p3 = new Participant(dc, "A2", "carl", "clein", "cacl","ca@cl", false, true, "nix", false, false);
		Participant p4 = new Participant(dc, "A3", "danton", "dach", "dada", "da@da", true, true, "nix", true, false);
		
		
		Modul m1 = new Modul(dc,"m1",1,2,3);
		Modul m2 = new Modul(dc,"m2",4,5,6);
		Tournament t1 = new  Tournament(dc,"t1",m1);
		t1.addParticipant(p1);
		t1.addParticipant(p2);
		t1.addParticipant(p3);
		event.addTournament(t1);
		Tournament t2 = new  Tournament(dc,"t2",m1);
		t2.addParticipantInit(p1);
		t2.addParticipant(p3);
		event.addTournament(t2);
		Tournament t3 = new  Tournament(dc,"t3",m2);
		t3.addParticipant(p4);
		event.addTournament(t3);
		
		
		
	XML xmlExport = new XML(dc);	
	String xmlFile = "/tmp/aggpro/TournamentExport";
	ArrayList<Integer> ids = new ArrayList<>();
	ids.add(t1.getId());
	//ids.add(t2.getId());
	//ids.add(t3.getId());
	xmlExport.tournaments2xmlFile(ids, "Export zum testen", xmlFile);
	DatabaseConnector dcNew = new DatabaseConnector("test2");
	dcNew.clearDatabase();
	dcNew.createAllTables();
	XML xmlImport = new XML(dcNew);
	xmlImport.xmlTournaments2db(xmlFile);
	
	XML xmlExport2 = new XML(dcNew);
	xmlExport2.tournaments2xmlFile(ids, "zweiter export zum testen", xmlFile+"2");
	
	
	//restore event from xml file
	for (String table : dc.getAllTables()) {
		System.out.println(dc.test_selecttostr("SELECT * FROM" + table));	
	}
	//Event eventNew = new Event(dcNew);
	//assertEquals(event.getName(), eventNew.getName());
	//assertEquals(event.getTournament("t1").getName(),eventNew.getTournament("t1").getName());
	//assertEquals();
	
	
	}

}
