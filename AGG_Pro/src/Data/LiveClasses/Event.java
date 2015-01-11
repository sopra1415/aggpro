package Data.LiveClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Data.Database.DatabaseConnector;
import java.text.ParseException;
import java.util.GregorianCalendar;

public class Event {
	private String name;
	private GregorianCalendar startDate = new GregorianCalendar();
	private GregorianCalendar endDate = new GregorianCalendar();
	private ArrayList<Tournament> tournaments = new ArrayList<Tournament>();
	private ArrayList<Participant> participants = new ArrayList<Participant>();
        private DatabaseConnector dc;
	
	public Event(String name, GregorianCalendar startDate, GregorianCalendar endDate) throws ClassNotFoundException, SQLException {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
        this.dc = dc;
                dc = new DatabaseConnector(name);
                dc.createAllTables();
                dc.insert("INSERT INTO EventProperties(Key, Value ) VALUES('name','"+name+"')"); 
                dc.insert("INSERT INTO EventProperties(Key, Value ) VALUES('startDate','"+startDate.getTimeInMillis()+"')"); 
                dc.insert("INSERT INTO EventProperties(Key, Value ) VALUES('endDate','"+endDate.getTimeInMillis()+"')"); 

                System.out.println(dc.test_selecttostr("SELECT * FROM EventProperties"));

	}
	
	public Event(DatabaseConnector dc) throws SQLException, ParseException{
		//TODO rs anzahl elemente testen
		ResultSet rs = dc.select("SELECT Value FROM EventProperties WHERE Key = 'name'");
		if( rs.next()){
			this.name = rs.getString(1);
		}
		rs = dc.select("SELECT Value FROM EventProperties WHERE Key = 'startDate'");
		if(rs.next()){
		String startDateStr =  rs.getString(1);
		// Kovertierung des Strings aus der DB in einen Gergorian Calendar
                startDate.setTimeInMillis(Long.parseLong(startDateStr));
		}
		rs = dc.select("SELECT Value FROM EventProperties WHERE Key = 'endDate'");
		if(rs.next()){
			
		String endDateStr =  rs.getString(1);
                endDate.setTimeInMillis(Long.parseLong(endDateStr));
		}
		ArrayList<Integer> tournamentIds = new ArrayList<>();
		rs = dc.select("SELECT Id FROM Tournament");
		while(rs.next()){
			Integer id = rs.getInt(1);
			if(id != null){
				tournamentIds.add(id);
			}
		}

		ArrayList<Integer> participantIds = new ArrayList<>();
		rs = dc.select("SELECT Id FROM Participant");
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

	public GregorianCalendar getStartDate() {
		return startDate;
	}

	public void setStartDate(GregorianCalendar startDate) throws SQLException {
		this.startDate = startDate;
                dc.update("UPDATE EventProperties SET name ='"+startDate+"'");

	}

	public GregorianCalendar getEndDate() {
		return endDate;
	}

	public void setEndDate(GregorianCalendar endDate) throws SQLException {
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
        public ArrayList<Participant> getParticipants(){
            return participants;
        }
        
        public ArrayList<Tournament> getTournaments(){
            return tournaments;
        }
        
        public Tournament getTournament(String tournamentName){
            for (Tournament t:tournaments){
                if (t.getName().equals(tournamentName)){
                    return t;
                }
            }
            return null;
        }
        
        public DatabaseConnector getDatabaseConnector(){
            return dc;
        }
	
	

}
