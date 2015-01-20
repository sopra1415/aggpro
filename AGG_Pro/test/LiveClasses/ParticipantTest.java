package LiveClasses;



import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import Data.Database.DatabaseConnector;
import Data.LiveClasses.*;
import sun.security.krb5.internal.crypto.Des3;


public class ParticipantTest {

	@Test
	public void test() throws Exception {
		DatabaseConnector dc = new DatabaseConnector("participantTets");
		dc.clearDatabase();
		dc.createAllTables();
		Participant participant = new Participant(dc, "A1", "nach", "vor", "nick", "mail", true, false, "other", true, false);
		Modul m = new Modul(dc, "m1",1,2,3, null);
		Tournament t = new Tournament(dc,"t1",m);
		participant.addTournament(t);
		
		
		//restore Tournament from database 
		participant = new Participant(dc,1);
		assertEquals(participant.getStartnumber(), "A1");
		assertEquals(participant.getPrename(), "vor");
		assertEquals(participant.getName(), "nach");
		assertEquals(participant.getNickname(), "nick");
		assertEquals(participant.getEmail(), "mail");
		assertEquals(participant.isPaid(), true);
		assertEquals(participant.isPresent(), false);
		assertEquals(participant.getOther(), "other");
		assertEquals(participant.isFreepass(), true);
		assertEquals(participant.isSuperfreepass(), false);
		ArrayList<Participant> alp = new ArrayList<>();
		Tournament tNew = new Tournament(dc, t.getId(),alp);
		assertEquals(t.getName(),tNew.getName());
		participant.deleteTournament(t);
		assertEquals(participant.getTournament(t.getId()), null);
                
                
                
                participant.setName("sur");
participant.setPrename("pre");
participant.setNickname("nick");
participant.setPaid(false);
participant.setPresent(true);
participant.setOther("other2");
participant.setFreepass(false);
participant.setSuperfreepass(true);
participant.setEmail("email");
                
	}
        
        
        @Test
        public void test2() throws ClassNotFoundException, SQLException{
            DatabaseConnector dc = new DatabaseConnector("participantTets");
		dc.clearDatabase();
		dc.createAllTables();
		Participant participant = new Participant(dc, "A1", "nach", "vor", "nick", "mail", true, false, "other", true, false);
		Modul m = new Modul(dc, "m1",1,2,3, null);
		Tournament t = new Tournament(dc,"t1",m);
		participant.addTournament(t);
                assertEquals(1,t.getParticipants().size());
                
        }
        
        

}
