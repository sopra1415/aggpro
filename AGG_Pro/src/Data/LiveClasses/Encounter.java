package Data.LiveClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Data.Database.DatabaseConnector;

public class Encounter {
	private Round round;
	private int id;
	private ArrayList<Participant> participants = new ArrayList<Participant>();
	private ArrayList<Integer> points = new ArrayList<>();
	
	public Encounter(String name, Round round) {
		super();
		this.round = round;
	}
	public Encounter(DatabaseConnector dc,int id,Round round,ArrayList<Participant> paticipants) throws SQLException{
		this.id=id;
		this.round=round;
		ResultSet rs = dc.select("SELECT ParticipantId,Points FROM Points WHERE EncounterId = "+id);
		while (rs.next()) {
			int participantId = rs.getInt(1);
			int points = rs.getInt(2);
			for (Participant participant : paticipants) {
				if(participant.getId() == participantId ){
					this.participants.add(participant);
					this.points.add(points);
				}
			}
			
		}
	}
	
	// getters and setters

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Round getRound() {
		return round;
	}
	public void setRound(Round round) {
		this.round = round;
	}
	
	public Participant getParticipant(int participantId) throws Exception{
		for(Participant participant: participants){
			if(participant.getId() == participantId){
				return participant;
			}
		}
		return null;
	}
	
	public void addParticpant(Participant participant){
		participants.add(participant);
	}
	
	public void deleteParticipant(Participant participant){
		participants.remove(participant);
	}
	

}
