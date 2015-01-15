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

    //@Test
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
    public void testNextRound() throws SQLException, ParseException {

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
        
        
        printRounds(t1.getRounds());
        
        System.out.println(dc.test_selecttostr("SELECT * from Round"));
        t1.generateNextRound();
        System.out.println(dc.test_selecttostr("SELECT * from Round"));
        printRounds(t1.getRounds());
        Event e1 = new Event(dc);
        printRounds(e1.getTournament("t1").getRounds());
        
    }
    public void printRounds(ArrayList<Round> r){
        System.out.println("Runden");
        for (Round r1 : r) {
            System.out.println(r.toString());
            for (Encounter e :  r1.getEncounters()) {
                System.out.println("    "+e.toString());
            }
        }
        System.out.println("/Runden");
        
    }

}
