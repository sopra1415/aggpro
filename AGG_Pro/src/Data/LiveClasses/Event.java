package Data.LiveClasses;

import java.sql.Date;
import java.util.ArrayList;

public class Event {
	private String name;
	private Date startDate;
	private Date endDate;
	private ArrayList<Tournament> tournaments = new ArrayList<Tournament>();
	private ArrayList<Participant> participants = new ArrayList<Participant>();
	private String password;
	
	public Event(String name, Date startDate, Date endDate, String password) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.password = password;
	}
	// getters and setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	public Tournament getTournament(int tournamentId) throws Exception{
		for(Tournament Tournament: tournaments){
			if(Tournament.getId() == tournamentId){
				return Tournament;
			}
		}
		return null;
	}
	
	public void addTournament(Tournament Tournament){
		tournaments.add(Tournament);
	}
	
	public void deleteTournament(Tournament Tournament){
		tournaments.remove(Tournament);
	}
	
	public boolean checkPassword(String password){
		
		if(password.equals(this.password)){
			return true;
		}
		else{
			return false;
		}
	}
	

}
