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
	private DatabaseConnector dc;
	
	public Encounter(DatabaseConnector dc,Round round) throws SQLException {
		super();//TODO ?????
		this.round = round;
		this.dc = dc;
		this.id = dc.insert(String.format("INSERT INTO Encounter (RoundId) VALUES (%d)",round.getRound()));
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
	public Round getRound() {
		return round;
	}
	public void setRound(Round round) throws SQLException {
		this.round = round;
		dc.update("Encounter","RoundId",round.getId(),id);
	}
	
	public Participant getParticipant(int participantId) throws Exception{
		for(Participant participant: participants){
			if(participant.getId() == participantId){
				return participant;
			}
		}
		return null;
	}
	
	public void addParticpant(Participant participant) throws SQLException{
		participants.add(participant);
		dc.insert(String.format("INSERT INTO Points (ParticipantId,EncounterId,Points) VALUES (%d%d%d)",participant.getId(),id,0));
	}
	
	public void deleteParticipant(Participant participant) throws SQLException{
		participants.remove(participant);
		dc.delete(String.format("DELETE FROM Points WHERE ParticipantId = %d AND EncounterId = %d",participant.getId(),id));
	}
	

}
