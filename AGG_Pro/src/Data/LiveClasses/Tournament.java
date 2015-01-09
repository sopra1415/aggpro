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
        DatabaseConnector dc;
	
	
	public Tournament(DatabaseConnector dc, String name, Modul modul) throws SQLException {
		super();
                this.dc = dc;
		this.name = name;
		this.modul = modul;
                this.id = dc.insert("INSERT INTO Tournament(name, modul) VALUES ('"+name+"', '"+modul+"')");
	}
	public Tournament(DatabaseConnector dc,Integer id) throws SQLException{//von db erstellen
		this.id=id;
		ResultSet rs = dc.select("SELECT name,modulId FROM Tournament WHERE Id = " + id );
		rs.next();
		this.name=rs.getString(1);
		Integer modulId = rs.getInt(2);
		this.modul = new Modul(dc,modulId);
		
		rs=dc.select("id FROM Round WHERE TournamentId = " + id);
		while(rs.next()){
			int roundId = rs.getInt(1);
			rounds.add(new Round(dc, roundId,this,participants));
		}
		//TODO rounds
		//participants werden in Participants gesetzt

	}
	// getters and setters

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) throws SQLException {
		this.name = name;
                dc.update("Tournament", "Name", name, id);
                
        }
	public Modul getModul() {
		return modul;
	}
	public void setModul(Modul modul) throws SQLException {
		this.modul = modul;
                dc.update("Tournament", "Modul", modul.getId()+"", id);

	}
	
	public Participant getParticipant(int participantId) throws Exception{
		for(Participant participant: participants){
			if(participant.getId() == participantId){
				return participant;
			}
		}
		return null;
	}
	
	public void addParticipant(Participant participant) throws SQLException{
		participants.add(participant);
                dc.insert("INSERT INTO ParticipantList(ParticipantId, TournamentId) VALUES("+participant.getId()+", "+id+")");
	}
	public void addParticipantInit(Participant participant){
		participants.add(participant);
	}
	
	public void deleteParticipant(Participant participant) throws SQLException{
		participants.remove(participant);
                dc.delete(String.format("DELETE FROM ParticipantList WHERE ParticipantId = %d AND TournamentID = %d", participant.getId(), id));
	}
	
	public Round getRound(int roundId) throws Exception{
		for(Round round: rounds){
			if(round.getId() == roundId){
				return round;
			}
		}
		return null;
	}
	
	public void addRound(Round round) throws SQLException{
		rounds.add(round);
		dc.insert(String.format("INSERT INTO Round (TournamentId,Round) VALUES (%d,%d) ",id,round));
	}
	
	public void deleteRound(Round round) throws SQLException{
		rounds.remove(round);
		dc.delete(String.format("DELETE FROM Round WHERE TournamentId = %d AND Round = %d",id,round));
	}

}
