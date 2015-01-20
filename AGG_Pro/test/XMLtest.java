
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

import org.xml.sax.SAXException;
import Controller.Exchange.XML;

public class XMLtest {

    XML xml;
    DatabaseConnector dc;
    String eventName = "Test";

    @Before
    public void before() throws ClassNotFoundException, SQLException {
        dc = new DatabaseConnector(eventName);
        xml = new XML(dc);
        dc.clearDatabase();
        dc.createAllTables();

    }

    @Test
    public void database2xml() throws Exception {
        dc.insert("insert into Tournament(Id,NaMe) values(1,'satz1')");
        dc.insert("insert into Tournament(Id,NaMe) values(2,'satz2');");
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><AGGPRO><ENCOUNTER/><EVENTPROPERTIES/><KOSYSTEM/><MODUL/><MODULLIST/><PARTICIPANT/><PARTICIPANTLIST/><POINTS/><ROUND/><SWISSSYSTEM/><TOURNAMENT><RECORD><ID>1</ID><NAME>satz1</NAME><MODULID/></RECORD><RECORD><ID>2</ID><NAME>satz2</NAME><MODULID/></RECORD></TOURNAMENT></AGGPRO>", xml.database2xml());
    }

    @Test
    public void testImport() throws Exception {

        String xmlstr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><AGGPRO><ENCOUNTER/><EVENTPROPERTIES/><KOSYSTEM/><MODUL/><PARTICIPANT/><PARTICIPANTENCOUNTER/><PARTICIPANTLIST/><SWISSSYSTEM/><TOURNAMENT><RECORD><ID>1</ID><NAME>satz1</NAME></RECORD><RECORD><ID>2</ID><NAME>satz2</NAME></RECORD></TOURNAMENT><TOURNAMENTSYSTEM/></AGGPRO>";
        xml.xml2table("TOURNAMENT", xmlstr,dc);
        assertEquals("1,satz1,null,\n2,satz2,null,\n", dc.test_selecttostr("SELECT * FROM Tournament ORDER BY Id"));
    }

    @Test
    public void less() throws Exception {
	dc.insert("insert into Tournament(Id,NaMe) values(1,'sa<<tz>>>1')");
        dc.insert("insert into Tournament(Id,NaMe) values(2,'satz2');");
        dc.insert("insert into Tournament(Id,NaMe) values(3,'satz2');");
        String original = dc.test_selecttostr("SELECT * FROM Tournament ORDER BY Id");
        String xmlstr = xml.database2xml();
        dc.clearDatabase();
        dc.createAllTables();
        xml.xml2table("TOURNAMENT", xmlstr,dc);
        assertEquals(original, dc.test_selecttostr("SELECT * FROM Tournament ORDER BY Id"));

    }

    @Test
    public void apostroph() throws Exception {
        dc.insert("insert into Tournament(Id,NaMe) values(1,'s\"atz1')");
        dc.insert("insert into Tournament(Id,NaMe) values(2,'s''''atz2');");
        String original = dc.test_selecttostr("SELECT * FROM Tournament ORDER BY Id");
        String xmlstr = xml.database2xml();
        dc.clearDatabase();
        dc.createAllTables();
        xml.xml2table("TOURNAMENT", xmlstr,dc);
        assertEquals(original, dc.test_selecttostr("SELECT * FROM Tournament ORDER BY Id"));

    }

    @Test
    public void im_ex_portTournaments() throws ParserConfigurationException, SQLException, TransformerException, IOException, ClassNotFoundException, SAXException, ParseException, Exception {
        //create new event and new turnament
        Event event = new Event("Test", new GregorianCalendar(1, 2, 3, 4, 5), new GregorianCalendar(1, 2, 3, 4, 5));

        Participant p1 = new Participant(dc, "A1", "anton", "anhalt", "anan", "an@an", true, true, "nix", false, true);
        Participant p2 = new Participant(dc, "B1", "berta", "brecht", "bebr", "be@br", true, false, "nix", false, false);
        Participant p3 = new Participant(dc, "A2", "carl", "clein", "cacl", "ca@cl", false, true, "nix", false, false);
        Participant p4 = new Participant(dc, "A3", "danton", "dach", "dada", "da@da", true, true, "nix", true, false);

        Modul m1 = new Modul(dc, "m1", 1, 2, 3, null);
        Modul m2 = new Modul(dc, "m2", 4, 5, 6, null);
        Tournament t1 = new Tournament(dc, "t1", m1);
        t1.addParticipant(p1);
        t1.addParticipant(p2);
        t1.addParticipant(p3);
        event.addTournament(t1);
        Tournament t2 = new Tournament(dc, "t2", m1);
        t2.addParticipantInit(p1);
        t2.addParticipant(p3);
        event.addTournament(t2);
        Tournament t3 = new Tournament(dc, "t3", m2);
        t3.addParticipant(p4);
        event.addTournament(t3);

        XML xmlExport = new XML(dc);
        String xmlFile = "/tmp/aggpro/TournamentExport";//TODO path relative
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(t1.getId());
        ids.add(t2.getId());
        ids.add(t3.getId());
        xmlExport.tournaments2xmlFile(ids, "Export zum testen", xmlFile);
        DatabaseConnector dcNew = new DatabaseConnector("Test2");
        dcNew.clearDatabase();
        dcNew.createAllTables();
        XML xmlImport = new XML(dcNew);
        xmlImport.xmlTournaments2db(xmlFile);

        XML xmlExport2 = new XML(dcNew);
        xmlExport2.tournaments2xmlFile(ids, "zweiter export zum testen", xmlFile + "2");

        //restore event from xml file
        Event eventNew = new Event(dcNew);
        assertEquals(event.getName(), eventNew.getName());
        assertEquals(event.getTournament("t1").getName(), eventNew.getTournament("t1").getName());
        assertEquals(p1.getName(), eventNew.getParticipant(p1.getId()).getName());
    }

    @Test
    public void im_ex_portTournamentsWithJoinDatabases() throws ParserConfigurationException, SQLException, TransformerException, IOException, ClassNotFoundException, SAXException, ParseException, Exception {
        //TODO event eigenschaften werden noch nicht übertragen


//create first new event and new turnament
        Event event1 = new Event("Test", new GregorianCalendar(1, 2, 3, 4, 5), new GregorianCalendar(1, 2, 3, 4, 5));

        Participant p1 = new Participant(dc, "A1", "anton", "anhalt", "anan", "an@an", true, true, "nix", false, true);
        Participant p2 = new Participant(dc, "B1", "berta", "brecht", "bebr", "be@br", true, false, "nix", false, false);
        Participant p3 = new Participant(dc, "A2", "carl", "clein", "cacl", "ca@cl", false, true, "nix", false, false);
        Participant p4 = new Participant(dc, "A3", "danton", "dach", "dada", "da@da", true, true, "nix", true, false);

        Modul m1 = new Modul(dc, "m1", 1, 2, 3, null);
        Modul m2 = new Modul(dc, "m2", 4, 5, 6, null);
        Tournament t1 = new Tournament(dc, "t1", m1);
        t1.addParticipant(p1);
        t1.addParticipant(p2);
        t1.addParticipant(p3);
        event1.addTournament(t1);
        Tournament t2 = new Tournament(dc, "t2", m1);
        t2.addParticipantInit(p1);
        t2.addParticipant(p3);
        event1.addTournament(t2);
        Tournament t3 = new Tournament(dc, "t3", m2);
        t3.addParticipant(p4);
        event1.addTournament(t3);

        XML xmlExport = new XML(dc);
        String xmlFile = "/tmp/aggpro/TournamentExportJoin";//TODO relative path
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(t1.getId());
        ids.add(t2.getId());
        ids.add(t3.getId());
        xmlExport.tournaments2xmlFile(ids, "Export zum Join testen", xmlFile);

        //new dc for the new event
        dc.clearDatabase();

        //create second new event and tournament
        Event event2 = new Event("Test", new GregorianCalendar(1, 2, 3, 4, 5), new GregorianCalendar(1, 2, 3, 4, 5));

        Participant p5 = new Participant(dc, "A1", "antooooon", "anhalt", "anan", "an@an", true, true, "nix", false, true);
        Participant p6 = new Participant(dc, "C1", "emil", "emmentaler", "emem", "em@em", true, false, "nix", false, false);
        Participant p7 = new Participant(dc, "C2", "fritz", "feuer", "frfe", "fr@fe", false, true, "nix", false, false);
        Participant p8 = new Participant(dc, "D3", "günter", "gauck", "güga", "gü@ga", true, true, "nix", true, false);

        Modul m3 = new Modul(dc, "m1", 1, 2, 3, null);
        Modul m4 = new Modul(dc, "m2", 4, 5, 6, null);
        Tournament t4 = new Tournament(dc, "t4", m3);
        t4.addParticipant(p5);
        t4.addParticipant(p6);
        t4.addParticipant(p7);
        event2.addTournament(t4);
        Tournament t5 = new Tournament(dc, "t5", m3);
        t5.addParticipantInit(p1);
        t5.addParticipant(p8);
        event2.addTournament(t5);
        Tournament t6 = new Tournament(dc, "t6", m4);
        t6.addParticipant(p5);
        event2.addTournament(t6);

        //join
        XML xmlImport = new XML(dc);
        xmlImport.xmlTournaments2db(xmlFile);
        
        
        XML xmlExport2 = new XML(dc);
        xmlExport2.tournaments2xmlFile(ids, "zweiter export mit beiden events zum testen", xmlFile + "2");

        //restore event from xml file	
        Event eventNew = new Event(dc);
        assertEquals(t1.getName(), eventNew.getTournament("t1").getName());
        assertEquals(t6.getName(),eventNew.getTournament("t6").getName());
        assertEquals(p1.getName(), eventNew.getParticipant(p1.getId()).getName());
        assertEquals(p1.getName(), eventNew.getParticipant(1).getName());
    }

}
