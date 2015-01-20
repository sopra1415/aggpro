package LiveClasses;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Data.Database.DatabaseConnector;
import Data.LiveClasses.*;
import com.itextpdf.text.log.SysoCounter;
import java.text.ParseException;

public class RoundTest {

    DatabaseConnector dc;

    @Before
    public void before() throws ClassNotFoundException, SQLException {

        dc = new DatabaseConnector("Test");
        dc.clearDatabase();
        dc.createAllTables();
    }

      @Test
    public void test() throws SQLException {
        Modul m = new Modul(dc, "m1", 1, 2, 3, null);
        Tournament t = new Tournament(dc, "t1", m);
        Round round = new Round(dc, t, 1);

        //restore from database
        ArrayList<Participant> participants = null;
        round = new Round(dc, round.getId(), t, participants);
        assertEquals(round.getRound(), 1);
    }

    @Test
    public void testNewRound() throws SQLException, ParseException, Exception {

        Participant p1 = new Participant(dc, "A1", "anton", "anhalt", "anan", "an@an", true, true, "nix", false, true);
        Participant p2 = new Participant(dc, "B1", "berta", "brecht", "bebr", "be@br", true, false, "nix", false, false);
        Participant p3 = new Participant(dc, "A2", "carl", "clein", "cacl", "ca@cl", false, true, "nix", false, false);
        Participant p4 = new Participant(dc, "A3", "danton", "dach", "dada", "da@da", true, true, "nix", true, false);
        SwissSystem swiss = new SwissSystem("swiss2", 10, 10);
        ArrayList<TournamentSystem> ts = new ArrayList<>();
        ts.add(swiss);
        Modul m1 = new Modul(dc, "m1", 1, 2, 3, ts);

        Tournament t1 = new Tournament(dc, "t1", m1);
        t1.addParticipant(p1);
        t1.addParticipant(p2);
        t1.addParticipant(p3);
        t1.addParticipant(p4);

        assertEquals(0, t1.getRounds().size());
        //t1.generateNextRound();

        t1.addRound(new Round(dc,t1,1));
        Encounter enc1 = new Encounter(dc,t1.getRound(1));
        //Encounter enc2 = new Encounter(dc, t1.getRound(1));
        t1.getRound(1).addEncounter(enc1);
        //t1.getRound(1).addEncounter(enc2);
        t1.getRound(1).getEncounter(1).addParticpant(p1);
        t1.getRound(1).getEncounter(1).addParticpant(p2);
        
        ArrayList<Integer> al = new ArrayList<>();
        
        al.add(-1);
        al.add(-1);
//        t1.getRound(1).getEncounter(1).setPoints(p1, 1);
        //t1.getRound(1).getEncounter(1).setPoints(p2, 2);
        //t1.getRound(1).getEncounter(1).setPoints(al);
        
        //assertEquals(1, t1.getRounds().size());
        t1 = new Event(dc).getTournament("t1");   
        
        
        
        
        
        for (Round r : t1.getRounds()) {
            for (Encounter e : r.getEncounters()) {
                System.out.println("\tBegegnung : " + e.getId());
                assertEquals(e.getParticipant(1).getName(), "anton");
                assertEquals(e.getParticipant(2).getName(), "berta");
                for (Participant p : e.getParticipants()) {
                    //System.out.println("\t\tTeiln: " + p.getName());
                }
            }
        }


        Event e1 = new Event(dc);

    }
    
    @Test
    public void testGenerateNextRound() throws SQLException, ParseException{
        Participant p1 = new Participant(dc, "A1", "anton", "anhalt", "anan", "an@an", true, true, "nix", false, true);
        Participant p2 = new Participant(dc, "B1", "berta", "brecht", "bebr", "be@br", true, false, "nix", false, false);
        Participant p3 = new Participant(dc, "A2", "carl", "clein", "cacl", "ca@cl", false, true, "nix", false, false);
        Participant p4 = new Participant(dc, "A3", "danton", "dach", "dada", "da@da", true, true, "nix", true, false);
        SwissSystem swiss = new SwissSystem("swiss2", 10, 10);
        ArrayList<TournamentSystem> ts = new ArrayList<>();
        ts.add(swiss);
        Modul m1 = new Modul(dc, "m1", 1, 2, 3, ts);

        Tournament t1 = new Tournament(dc, "t1", m1);
        t1.addParticipant(p1);
        t1.addParticipant(p2);
        t1.addParticipant(p3);
        t1.addParticipant(p4);
        assertEquals(0,t1.getRounds().size());
        t1.generateNextRound();
        assertEquals(1,t1.getRounds().size());
        Event eventNew = new Event(dc);
        assertEquals(1,eventNew.getTournament("t1").getRounds().size());
        for (Round r : eventNew.getTournament("t1").getRounds()) {
            for (Encounter e: r.getEncounters()) {
                ArrayList<Integer> al = new ArrayList<>();
                al.add(1);
                al.add(2);
                 e.setPoints(al);
            }
        }
        
        //TODO 
    }


}
