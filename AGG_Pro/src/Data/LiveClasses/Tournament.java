package Data.LiveClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Data.Database.DatabaseConnector;

public class Tournament {
	private String name;
	private int id;
	private Modul modul;
	private ArrayList<Participant> participants = new ArrayList<Participant>();
	private ArrayList<Round> rounds = new ArrayList<Round>();
	
	
	public Tournament(String name, int id, Modul modul) {
		super();
		this.name = name;
		this.id = id;
		this.modul = modul;
	}
	public Tournament(DatabaseConnector dc,Integer id) throws SQLException{//von db erstellen
		this.id=id;
		ResultSet rs = dc.select("SELECT name,modulId FROM Tournament WHERE Id = " + id );
		rs.next();
		this.name=rs.getString(1);
		Integer modulId = rs.getInt(2);
		this.modul = new Modul(dc,modulId);
		//TODO rounds
		//participants werden in Participants gesetzt

	}
	// getters and setters

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Modul getModul() {
		return modul;
	}
	public void setModul(Modul modul) {
		this.modul = modul;
	}
	
	public Participant getParticipant(int participantId) throws Exception{
		for(Participant participant: participants){
			if(participant.getId() == participantId){
				return participant;
			}
		}
		return null;
	}
	
	public void addParticipant(Participant participant){
		participants.add(participant);
	}
	public void addParticipantInit(Participant participant){
		participants.add(participant);
	}
	
	public void deleteParticipant(Participant participant){
		participants.remove(participant);
	}
	
	public Round getRound(int roundId) throws Exception{
		for(Round round: rounds){
			if(round.getId() == roundId){
				return round;
			}
		}
		return null;
	}
	
	public void addRound(Round round){
		rounds.add(round);
	}
	
	public void deleteRound(Round round){
		rounds.remove(round);
	}

}
