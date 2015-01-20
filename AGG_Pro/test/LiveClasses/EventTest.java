package LiveClasses;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import Data.Database.DatabaseConnector;
import Data.LiveClasses.*;
import java.util.ArrayList;

public class EventTest {

    DatabaseConnector dc;
    String eventName = "Test";

    @Before
    public void before() throws ClassNotFoundException, SQLException {
        dc = new DatabaseConnector(eventName);
        dc.clearDatabase();
        dc.createAllTables();
    }

    @Test
    public void test() throws Exception {
        GregorianCalendar start = new GregorianCalendar(2012, 12, 12, 12, 12);
        GregorianCalendar end = new GregorianCalendar(2013, 11, 11, 11, 11);
        Event event = new Event(eventName, start, end);
        assertEquals(event.getName(), eventName);

        Participant p = new Participant(dc, "A1", "vor", "nach", "nich", "mail", true, false, "other", true, false);
        Modul m = new Modul(dc, "m1", 1, 2, 3, null);
        Tournament t = new Tournament(dc, "t1", m);
        event.addParticpant(p);
        event.addTournament(t);

        //restore event from db
        System.out.println(dc.test_selecttostr("SELECT * FROM EventProperties"));
        event = new Event(dc);
        assertEquals(start.getTimeInMillis(), event.getStartDate().getTimeInMillis());
        assertEquals(end.getTimeInMillis(), event.getEndDate().getTimeInMillis());
        Participant pNew = event.getParticipant(p.getId());
        assertEquals(p.getName(), pNew.getName());

        Tournament tNew = new Tournament(dc, t.getId(), null);
        assertEquals(t.getName(), tNew.getName());

    }

    @Test
    public void test2() throws ClassNotFoundException, SQLException, ParseException, Exception {
        GregorianCalendar start = new GregorianCalendar(2012, 12, 12, 12, 12);
        GregorianCalendar end = new GregorianCalendar(2013, 11, 11, 11, 11);
        Event event = new Event(eventName, start, end);
        Participant p1 = new Participant(dc, "A1", "anton", "anhalt", "anan", "an@an", true, true, "nix", false, true);
        Participant p2 = new Participant(dc, "B1", "berta", "brecht", "bebr", "be@br", true, false, "nix", false, false);
        Participant p3 = new Participant(dc, "A2", "carl", "clein", "cacl", "ca@cl", false, true, "nix", false, false);
        Participant p4 = new Participant(dc, "A3", "danton", "dach", "dada", "da@da", true, true, "nix", true, false);
        ArrayList<TournamentSystem> ts = new ArrayList<>();
        ts.add(new SwissSystem("swiss1", 5, 5));
        Modul m1 = new Modul(dc, "modulname", 1, 2, 3, ts);
        Tournament t1 = new Tournament(dc, "turnier1",m1);
        p1.addTournament(t1);
        p2.addTournament(t1);
        Event eNew = new Event(dc);
        assertEquals(p1.getName(),eNew.getTournament("turnier1").getParticipant(p1.getId()).getName());
        assertEquals(p2.getName(),eNew.getTournament("turnier1").getParticipant(p2.getId()).getName());
    }

}
