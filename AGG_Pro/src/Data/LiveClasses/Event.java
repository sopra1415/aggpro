package Data.LiveClasses;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Data.Database.DatabaseConnector;

public class Event {
	private String name;
	private Date startDate;
	private Date endDate;
	private ArrayList<Tournament> tournaments = new ArrayList<Tournament>();
	private ArrayList<Participant> participants = new ArrayList<Participant>();
        private DatabaseConnector dc;
	
	public Event(String name, Date startDate, Date endDate) throws ClassNotFoundException, SQLException {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
                DatabaseConnector dc = new DatabaseConnector(name);
                dc.insert("INSERT INTO EventProperties(Key, Value ) VALUES('name','"+name+"')"); 
                dc.insert("INSERT INTO EventProperties(Key, Value ) VALUES('startDate','"+startDate+"')"); 
                dc.insert("INSERT INTO EventProperties(Key, Value ) VALUES('endDate','"+endDate+"')"); 


	}
	public Event(){ }
	
	public Event(DatabaseConnector dc) throws SQLException{
		//TODO rs anzahl elemente testen
		ResultSet rs = dc.select("SELECT Value FROM EventProperties WHERE Key = 'name'");
		rs.next();
		this.name = rs.getString(1);
		rs = dc.select("SELECT Value FROM EventProperties WHERE Key = 'startDate'");
		rs.next();
		String startDateStr =  rs.getString(1);
		this.startDate= new Date(Long.parseLong(startDateStr));//TODO
		rs = dc.select("SELECT Value FROM EventProperties WHERE Key = 'endDate'");
		rs.next();
		String endDateStr =  rs.getString(1);
		this.endDate = new Date(Long.parseLong(endDateStr));
		ArrayList<Integer> tournamentIds = new ArrayList<>();
		rs = dc.select("SELECT Id FROM Tournament'");
		while(rs.next()){
			Integer id = rs.getInt(1);
			if(id != null){
				tournamentIds.add(id);
			}
		}

		ArrayList<Integer> participantIds = new ArrayList<>();
		rs = dc.select("SELECT Id FROM Participant'");
		while(rs.next()){
			Integer id = rs.getInt(1);
			if(id != null){
				participantIds.add(id);
			}
		}
		for (Integer id : tournamentIds) {
		this.tournaments.add(new Tournament(dc, id));	
		}
		for (Integer id : participantIds) {
			this.participants.add(new Participant(dc,id,tournaments));//TODO verknüpft auch turnier und teilnehmer
		}
	}
	// getters and setters

	public String getName() {
		return name;
	}

	public void setName(String name) throws SQLException {
		this.name = name;
                dc.update("UPDATE EventProperties SET name ='"+name+"'");
                
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) throws SQLException {
		this.startDate = startDate;
                dc.update("UPDATE EventProperties SET name ='"+startDate+"'");

	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) throws SQLException {
		this.endDate = endDate;
                dc.update("UPDATE EventProperties SET name ='"+endDate+"'");

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
	
	public void deleteParticipant(Participant participant) throws SQLException{
		participants.remove(participant);
                dc.delete("Participant", participant.getId());
	}
	
	public Tournament getTournament(int tournamentId) throws Exception{
		for(Tournament Tournament: tournaments){
			if(Tournament.getId() == tournamentId){
				return Tournament;
			}
		}
		return null;
	}
	
	public void addTournament(Tournament tournament){
		tournaments.add(tournament);
	}
	
	public void deleteTournament(Tournament tournament) throws SQLException{
		tournaments.remove(tournament);
                dc.delete("Tournament", tournament.getId());

	}
	
	

}
