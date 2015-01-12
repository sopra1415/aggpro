package LiveClasses;



import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import Data.Database.DatabaseConnector;
import Data.LiveClasses.*;
import java.util.ArrayList;


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
        
        @Test
        public void generateNextRound() throws SQLException{
            
            ArrayList<TournamentSystem> tournamentSystems = new ArrayList<>();
            tournamentSystems.add(new SwissSystem("swiss System",4 , 3));
            tournamentSystems.add(new KoSystem("KO System",4, true));
            Modul m1 = new Modul(dc, "m1", 3,2,1,tournamentSystems);
            Tournament t1 = new Tournament(dc, "t1",m1);
            Participant p1 = new Participant(dc, "A1", "anton", "anhalt", "anan", "an@an", true, true, "nix", false, true);
            Participant p2 = new Participant(dc, "B1", "berta", "brecht", "bebr", "be@br", true, false, "nix", false, false);
            Participant p3 = new Participant(dc, "A2", "carl", "clein", "cacl", "ca@cl", false, true, "nix", false, false);
            Participant p4 = new Participant(dc, "A3", "danton", "dach", "dada", "da@da", true, true, "nix", true, false);
            t1.addParticipant(p1);
            t1.addParticipant(p2);
            t1.addParticipant(p3);
            t1.addParticipant(p4);
            t1.generateNextRound();
            t1.getRounds();
            for (Round round : t1.getRounds()) {
                for (Encounter encounter : round.getEncounters()) {
                System.out.println("encounter"+encounter.toString());
                    for (Participant participant : encounter.getParticipants()) {
                        System.out.println(participant.getName());
                    }
                 }
            }
          
        }

}
