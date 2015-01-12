package Data.LiveClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Data.Database.DatabaseConnector;
/**
 * Live Class for the Round
 */

public class Round {
	private int id;
	private int round;
	private Tournament tournament;
	private ArrayList<Encounter> encounters = new ArrayList<Encounter>();
	private DatabaseConnector dc;
	
        /**
         * Creates a new Round and Insert it into the database
         * @param dc
         * @param tournament
         * @param round
         * @throws SQLException 
         */
	public Round(DatabaseConnector dc, Tournament tournament,int round) throws SQLException {
		this.tournament = tournament;
		this.dc = dc;
		this.round = round;
		this.id = dc.insert("INSERT INTO Round(TournamentId,Round) VALUES ('"+tournament.getId()+"', '"+round+"')");
	}
        /**
         * Load Round from Database
         * @param dc
         * @param id
         * @param tournament
         * @param participants
         * @throws SQLException 
         */
	public Round(DatabaseConnector dc,int id,Tournament tournament,ArrayList<Participant> participants) throws SQLException{
		this.id=id;
                this.dc=dc;
		this.tournament=tournament;
		ResultSet rs = dc.select("SELECT Round FROM Round WHERE Id = " + id );
		rs.next();
		this.round = rs.getInt(1);
                
		//get Encounters of the Round
		rs = dc.select("SELECT id FROM Encounter WHERE TournamentId = "+tournament.getId() +" AND RoundId = "+id);
		while (rs.next()) {
			int encounterId=rs.getInt(1);
			encounters.add(new Encounter(dc,encounterId,this,participants));
		}
	}
	
	
	// getters and setters
	public int getId() {
		return id;
	}


	public Tournament getTournament() {
		return tournament;
	}
        public Encounter getEncounter(int encounterId) throws Exception{
		for(Encounter encounter: encounters){
			if(encounter.getId() == encounterId){
				return encounter;
			}
		}
		return null;
	}
        public int getRound(){
		return round;
	}
        
        public ArrayList<Encounter> getEncounters(){
            return encounters;
        }
/**
 * 
 * @param tournament
 * @throws SQLException 
 */
	public void setTournament(Tournament tournament) throws SQLException {
		this.tournament = tournament;
		dc.update("Round", "TournamentId", tournament.getId(), id);
	}

	
	/**
         * 
         * @param encounter
         * @throws SQLException 
         */
	public void addEncounter(Encounter encounter) throws SQLException{
		encounters.add(encounter);
		dc.update(String.format("INSERT INTO Encounter (TournamentId,RoundId  ) VALUES (%d,%d)",tournament.getId(),id));
	}
	/**
         * 
         * @param encounter
         * @throws SQLException 
         */
	public void deleteEncounter(Encounter encounter) throws SQLException{
		encounters.remove(encounter);
		dc.delete(String.format("DELETE FROM Encounter WHERE tournament = %d AND RoundId = %d",tournament.getId(),id));
	}
	

}
